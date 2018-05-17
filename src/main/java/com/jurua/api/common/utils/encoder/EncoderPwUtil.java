package com.jurua.api.common.utils.encoder;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

/**
 * @author 张博【zhangb@lianliantech.cn】
 * 密码格式 BASE64(BASE64(salt)+$$$zb$bz$+BASE64(PBKDF2SHA256(pw+salt)))
 */
public class EncoderPwUtil {

    /**
     * 加密算法
     */
    private final static String ALGORITHM = "PBKDF2WithHmacSHA256";

    /**
     * 分隔符
     */
    private final static String SPLIT = Base64.getEncoder().encodeToString("$$$zb$bz$".getBytes());
    /**
     * 算法循环次数
     */
    private final static int ITERATIONS = 4089;

    /**
     * 密钥长度
     */
    private final static int KEY_SIZE = 256 * 4;

    /**
     * 随机盐大小
     */
    private final static int SALT_SIZE = 512;

    /**
     * 创建人：张博【zhangb@lianliantech.cn】
     * 时间：2017/12/12 下午10:42
     * @param password 明文密码
     * @param salt 随机盐
     * @apiNote 得到编码后的密码
     */
    private static String getEncoderPassword(String password, byte[] salt) {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_SIZE);
        SecretKeyFactory keyFactory;
        try {
            keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            try {
                byte[] hashBase64 = Base64.getEncoder().encode(keyFactory.generateSecret(spec).getEncoded());
                return new String(hashBase64);
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 创建人：张博【zhangb@lianliantech.cn】
     * 时间：2017/12/12 下午11:13
     * @apiNote 得到随机盐
     */
    private static byte[] getSalt() {
        byte[] bytes = new byte[SALT_SIZE];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(bytes);
        return bytes;
    }

    /**
     * 创建人：张博【zhangb@lianliantech.cn】
     * 时间：2017/12/12 下午11:14
     * @param password 明文密码
     * @apiNote format 并 encode 密码和盐
     */
    public static String getEncodePw(String password) {
        byte[] salt = getSalt();
        String hash = getEncoderPassword(password, salt);
        String saltStr = Base64.getEncoder().encodeToString(salt);
        return new String(Base64.getEncoder().encode(String.format("%s".concat(SPLIT).concat("%s"), saltStr, hash).getBytes()));
    }

    /**
     * 创建人：张博【zhangb@lianliantech.cn】
     * 时间：2017/12/12 下午11:22
     * @param pw 明文密码
     * @param hashPw 编码后的密码
     * @apiNote 明文密码与数据库中的密码做比较
     */
    public static Boolean pwMatch(String pw, String hashPw) {
        String[] hash = new String(Base64.getDecoder().decode(hashPw)).split(SPLIT);
        byte[] salt = Base64.getDecoder().decode(hash[0]);
        String newPw = getEncoderPassword(pw, salt);
        return newPw != null && newPw.equals(hash[1]);
    }

    public static void main(String[] args) {
        System.out.println(getEncodePw("zhangbo1990124"));
    }
}
