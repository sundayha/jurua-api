package com.jurua.api.common.utils.cache.broadcast;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author 张博【zhangb@lianliantech.cn】
 *
 * RabbitMQ 广播实现
 */
@Component
public class RabbitMQBroadcast implements CacheMsgBroadcast, Consumer {

    private static final Logger log = LoggerFactory.getLogger(RabbitMQBroadcast.class);
    private static final String EXCHANGE_NAME = "JURUA_API_RMQ_FANOUT";
    private static final String EXCHANGE_TYPE = "fanout";
    private ConnectionFactory connectionFactory;
    private Connection producerConnection;
    private Channel producerChannel;
    private String consumerTag;

    RabbitMQBroadcast() {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connect();
    }

    @Override
    public void connect() {
        Connection consumerConnection;
        Channel consumerChannel;
        try {
            producerConnection = connectionFactory.newConnection();
            producerChannel = producerConnection.createChannel();
            producerChannel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);

            consumerConnection = connectionFactory.newConnection();
            consumerChannel = consumerConnection.createChannel();
            consumerChannel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
            String queueName = consumerChannel.queueDeclare().getQueue();
            consumerChannel.queueBind(queueName, EXCHANGE_NAME, "");
            consumerChannel.basicConsume(queueName, true, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void broadcast(MsgType msg) {
        // 链接失败时重试开启链接和通道
        if (!producerConnection.isOpen() || !producerChannel.isOpen()) {
            // 防止多线程同时进入开启链接和通道
            synchronized (RabbitMQBroadcast.class) {
                if (!producerConnection.isOpen() || !producerChannel.isOpen()) {
                    try {
                        producerConnection = connectionFactory.newConnection();
                        producerChannel = producerConnection.createChannel();
                    } catch (Exception e) {
                        log.error("RabbitMQ 重试开启 producerConnection producerChannel 异常", e);
                    }
                }
            }
        }
        try {
            producerChannel.basicPublish(EXCHANGE_NAME, "", null, msg.toBytes());
        } catch (Exception e) {
            log.error("RabbitMQ 发布消息异常", e);
        }
    }

    @Override
    public String getAddress() {
        return consumerTag;
    }

    @Override
    public void handleConsumeOk(String consumerTag) {
        this.consumerTag = consumerTag;
    }

    @Override
    public void handleCancelOk(String consumerTag) {
    }

    @Override
    public void handleCancel(String consumerTag) throws IOException {
    }

    @Override
    public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {
    }

    @Override
    public void handleRecoverOk(String consumerTag) {
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        MsgType msgType = MsgType.toObject(body);
        // 不接收自己发布的信息
        if (!msgType.address.equals(consumerTag)) {
            switchMsg(MsgType.toObject(body));
        }
    }
}
