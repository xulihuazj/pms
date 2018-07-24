package com.xulihuazj.pms.utils.security;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.*;

/**
 * 加解密 相关方法帮助类
 */
public class SecurityHelper {

    private static Logger logger = LogManager.getLogger(SecurityHelper.class);

    /**
     * 编码方式
     */
    private static final String DEFAULT_ENCODING = "UTF-8";

    private static boolean initialized = false;

    enum DigestEnum {
        MD5,
        SHA1,
        SHA512,
    }

    /**
     * DES加密方式
     *
     * @param message 明文
     * @param key     加密密钥
     * @return
     */
    public static String desEncrypt(String message, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes(DEFAULT_ENCODING));
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
        IvParameterSpec iv = new IvParameterSpec(key.getBytes(DEFAULT_ENCODING));
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        return URLDecoder.decode(toHexString(cipher.doFinal(message.getBytes(DEFAULT_ENCODING))));
    }

    /**
     * DES 解密方法
     *
     * @param message 密文
     * @param key     密钥
     * @return
     */
    public static String descDecrypt(String message, String key) {
        try {
            message = URLEncoder.encode(message, DEFAULT_ENCODING);
            //转换十六进制 字符数组
            byte[] byteSrc = convertHexString(message);
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            DESKeySpec desKeySpec = new DESKeySpec(key.getBytes(DEFAULT_ENCODING));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
            IvParameterSpec iv = new IvParameterSpec(key.getBytes(DEFAULT_ENCODING));
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
            byte[] retByt = cipher.doFinal(byteSrc);
            return new String(retByt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * AES256 解密
     *
     * @param bytes
     * @param key
     * @return
     */
    public static String aes256Decode(byte[] bytes, byte[] key) {
        if (!initialized) {
            Security.addProvider(new BouncyCastleProvider());
            initialized = true;
        }
        byte[] result;
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        try {
            // 设置 解密模式为AES的EBC模式
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            result = cipher.doFinal(bytes);
            return new String(result, DEFAULT_ENCODING);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 字符串换十六进制数组
     */
    private static byte[] convertHexString(String str) {
        byte[] digests = new byte[str.length() / 2];
        for (int i = 0; i < digests.length; i++) {
            String byteString = str.substring(2 * i, 2 * i + 2);
            int byteValue = Integer.parseInt(byteString, 16);
            digests[i] = (byte) byteValue;
        }
        return digests;
    }

    /**
     * 数组转 十六进制 字符串
     */
    private static String toHexString(byte[] bytes) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            // 将一个byte和 0xFF进行了与运算
            String plainText = Integer.toHexString(0xff & bytes[i]);
            if (plainText.length() < 2) {
                plainText = "0" + plainText;
            }
            hexString.append(plainText);
        }
        return hexString.toString();
    }


    /*--------------------------------------------- MD5--------------------------------------------*/

    /**
     * MD5加密
     *
     * @param md5Str 要进行MD5加密的字符串
     * @return 加密后md5串
     */
    public static String MD5(String md5Str) {
        return generateDataDigest(md5Str, DigestEnum.MD5, null);
    }

//    public static String MD5Utf(String md5Str, String charSet) {
//        return generateDataDigest(md5Str,DigestEnum.MD5,charSet);
//    }

    /**
     * SHA1加密 默认全部转换成小写，如需大写请使用 重载传参数
     *
     * @param sha1Str
     * @return
     */
    public static String SHA1(String sha1Str) {
        String digestStr = generateDataDigest(sha1Str, DigestEnum.SHA1, null);
        if (StringUtils.isNotBlank(digestStr)) {
            digestStr = digestStr.toLowerCase();
        }
        return digestStr;
    }

    /**
     * 生成数据摘要
     *
     * @param digStr  待加密字符串
     * @param type    摘要类型
     * @param charset 字符集
     * @return
     */
    private static String generateDataDigest(String digStr, DigestEnum type, String charset) {
        final char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        byte[] btInput;
        try {
            if (StringUtils.isNotBlank(charset) && DEFAULT_ENCODING.equalsIgnoreCase(charset)) {
                btInput = digStr.getBytes();
            } else {
                btInput = digStr.getBytes(DEFAULT_ENCODING);
            }
            MessageDigest messageDigest = null;
            if (type.equals(DigestEnum.MD5)) {
                messageDigest = MessageDigest.getInstance("MD5");
            } else if (type.equals(DigestEnum.SHA1)) {
                messageDigest = MessageDigest.getInstance("SHA-1");
            } else if (type.equals(DigestEnum.SHA512)) {
                messageDigest = MessageDigest.getInstance("SHA-512");
            } else {
                return null;
            }
            byte[] messageDigests = messageDigest.digest(btInput);
            StringBuilder strBuilder = new StringBuilder();
            for (byte b : messageDigests) {
                char c = hexDigits[(b >>> 4) & 0xf];
                strBuilder.append(c);
                c = hexDigits[b & 0xf];
                strBuilder.append(c);
            }
            return strBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 利用Java 原生的摘要实现SHA256加密
     *
     * @param str 加密后的报文
     * @returns
     */
    public static String getSHA256StrJava(String str) {
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes(DEFAULT_ENCODING));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return encodeStr;
    }

    /**
     * 将byte数组 转换成 16进制字符串
     *
     * @param bytes byte数组
     * @return
     */
    private static String byte2Hex(byte[] bytes) {
        StringBuffer strBuffer = new StringBuffer();
        String temp;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                // 1 得到一位的进位 补0操作
                strBuffer.append("0");
            }
            strBuffer.append(temp);
        }
        return strBuffer.toString();
    }


}
