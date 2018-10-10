package com.jurua.api.common.utils.cache.broadcast;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;



/**
 * @author 张博【zhangb@lianliantech.cn】
 *
 * RabbitMQ 广播实现
 */
@Component
public class RabbitMQBroadcast implements CacheMsgBroadcast, Consumer {

    private static final Logger log = LoggerFactory.getLogger(RabbitMQBroadcast.class);
    @Value("${rabbitmq.exchangeName}")
    private String exchangeName;
    @Value("${rabbitmq.exchangeType}")
    private String exchangeType;
    @Value("${rabbitmq.host}")
    private String host;
    @Value("${rabbitmq.port}")
    private int port;
    @Value("${rabbitmq.userName}")
    private String userName;
    @Value("${rabbitmq.password}")
    private String password;
    private ConnectionFactory connectionFactory;
    private Connection producerConnection;
    private Channel producerChannel;
    private String consumerTag;

    @PostConstruct
    public void init() {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(userName);
        connectionFactory.setPassword(password);
        connect();
    }

    @Override
    public void connect() {
        Connection consumerConnection;
        Channel consumerChannel;
        try {
            producerConnection = connectionFactory.newConnection();
            producerChannel = producerConnection.createChannel();
            producerChannel.exchangeDeclare(exchangeName, exchangeType);

            consumerConnection = connectionFactory.newConnection();
            consumerChannel = consumerConnection.createChannel();
            consumerChannel.exchangeDeclare(exchangeName, exchangeType);
            String queueName = consumerChannel.queueDeclare().getQueue();
            consumerChannel.queueBind(queueName, exchangeName, "");
            consumerChannel.basicConsume(queueName, true, this);
        } catch (Exception e) {
            log.error("rabbitmq 链接异常", e);
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
            producerChannel.basicPublish(exchangeName, "", null, msg.toBytes());
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
