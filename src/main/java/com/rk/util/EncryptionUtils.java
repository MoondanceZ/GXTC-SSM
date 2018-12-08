package com.rk.util;

import org.springframework.util.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.*;
import java.util.Base64;

/**
 * 加密工具类
 */
@SuppressWarnings("restriction")
public final class EncryptionUtils {

    public static final String AES_ENCRYPT_KEY = "MOONDANCEZ";

    /**
     * MD5加密
     *
     * @param source 要加密的字符串
     * @return 加密后的结果
     */
    public static String MD5(String source) {
        // 32位加密md
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(source.getBytes(Charset.forName("UTF-8"))); // 更新
        byte[] bt = md.digest(); // 摘要

        // 保留结果的字符串
        StringBuilder sb = new StringBuilder();
        int p = 0;
        for (int i = 0; i < bt.length; i++) {
            p = bt[i];
            if (p < 0)
                p += 256; // 负值时的处理
            if (p < 16)
                sb.append("0");
            sb.append(Integer.toHexString(p));// 转换成16进制
        }
        return sb.toString();
    }

    /**
     * AES加密
     *
     * @param source 需要加密的内容
     * @return 加密后的字符串
     */
    public static String encryptAES(String source) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }
        try {
            Cipher cipher = getCipher(Cipher.ENCRYPT_MODE);
            //8.获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
            byte[] byte_encode = source.getBytes("utf-8");
            //9.根据密码器的初始化方式--加密：将数据加密
            byte[] byte_AES = cipher.doFinal(byte_encode);
            //10.将加密后的数据转换为字符串
            //这里用Base64Encoder中会找不到包
            //解决办法：
            //在项目的Build path中先移除JRE System Library，再添加库JRE System Library，重新编译后就一切正常了。
            String AES_encode = new BASE64Encoder().encode(byte_AES);
            //11.将字符串返回
            return AES_encode;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //如果有错就返加null
        return null;
    }

    private static Cipher getCipher(int mode) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        //1.构造密钥生成器，指定为AES算法,不区分大小写
        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        //2.根据AES_ENCRYPT_KEY规则初始化密钥生成器
        //生成一个128位的随机源,根据传入的字节数组
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );
        secureRandom.setSeed(AES_ENCRYPT_KEY.getBytes());
        keygen.init(128, secureRandom);
        //3.产生原始对称密钥
        SecretKey original_key = keygen.generateKey();
        //4.获得原始对称密钥的字节数组
        byte[] raw = original_key.getEncoded();
        //5.根据字节数组生成AES密钥
        SecretKey key = new SecretKeySpec(raw, "AES");
        //6.根据指定算法AES自成密码器
        Cipher cipher = Cipher.getInstance("AES");
        //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
        cipher.init(mode, key);

        return cipher;
    }

    /**
     * AES解密
     *
     * @param encryptSource 密文
     * @return 加密后的字符串
     */
    public static String decryptAES(String encryptSource) {
        if (StringUtils.isEmpty(encryptSource)) {
            return null;
        }
        try {
            Cipher cipher = getCipher(Cipher.DECRYPT_MODE);
            //8.将加密并编码后的内容解码成字节数组
            byte[] byte_content = new BASE64Decoder().decodeBuffer(encryptSource);
            /*
             * 解密
             */
            byte[] byte_decode = cipher.doFinal(byte_content);
            String AES_decode = new String(byte_decode, "utf-8");
            return AES_decode;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        //如果有错就返加null
        return null;
    }

    /**
     * 将二进制字节数组转换成十六进制字符串
     *
     * @param byteArray 二进制字节数组
     * @return 十六进制字符串
     */
    public static String parseByteArray2HexStr(byte[] byteArray) {
        if (byteArray == null || byteArray.length == 0) {
            return null;
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            String hex = Integer.toHexString(byteArray[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }


    /**
     * 将十六进制字符串转换为二进制字节数组
     *
     * @param hexStr 十六进制字符串
     * @return 二进制字节数组
     */
    public static byte[] parseHexStr2ByteArray(String hexStr) {
        if (StringUtils.isEmpty(hexStr) || hexStr.length() < 1) {
            return null;
        }

        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**
     * SHA1加密
     *
     * @param source 要加密的字符串
     * @return 加密后的结果
     */
    public static String encryptSHA1(String source) {
        return encrypt(source, "SHA-1");
    }

    private static String encrypt(String strSrc, String encName) {
        MessageDigest md = null;
        String strDes = null;

        byte[] bt = strSrc.getBytes();
        try {
            md = MessageDigest.getInstance(encName);
            md.update(bt);
            strDes = parseByteArray2HexStr(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Invalid algorithm.");
            return null;
        }
        return strDes;
    }

    /**
     * BASE64加密
     *
     * @param source 要加密的字符串
     * @return 加密后的结果
     */
    public static String encryptBASE64(String source) {
        return new String(Base64.getDecoder().decode(source.getBytes()));
    }

    /**
     * BASE64解密
     *
     * @param source 要加密的字符串
     * @return 加密后的结果
     */
    public static String decryptBASE64(String source) {
        return new String(Base64.getDecoder().decode(source.getBytes()));
    }
}