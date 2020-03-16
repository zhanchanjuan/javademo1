package com.javademo.common.basic.dto;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.UUID;

/**
 * Created by cj on 2020/3/16.
 */
public class stringByteChange implements Serializable {

    private static final long serialVersionUID =31556889864403199L;


    public static String DES_KEY = "#3*T(+~q";


static class DesUtil{

        private static final Charset SECURITY_CHARSET = Charset.forName("utf-8");//设置编码格式为utf-8
        //进制转换和加密
        //二进制转16进制字符串
        public static String parseByte2HexStr(byte buf[]) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < buf.length; i++) {
                String hex = Integer.toHexString(buf[i] & 0xFF);  //buf[i] & 0xFF  需要&0xff手动将其余位清零
                if (hex.length() == 1) {
                    hex = '0' + hex;
                }
                sb.append(hex.toUpperCase());
            }
            return sb.toString();
        }

        //16进制转为二进制
        public static byte[] parseHexStr2Byte(String hexStr) {
            if (hexStr.length() < 1) {
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


        //进制转换以后的加密test-demo
        //加密 公共方法
        public static byte[] desEncrypt(byte[] data, String sKey) {
            try {
                byte[] key = sKey.getBytes();

                IvParameterSpec iv = new IvParameterSpec(key);
                DESKeySpec desKey = new DESKeySpec(key);

                SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
                SecretKey securekey = keyFactory.generateSecret(desKey);

                Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

                cipher.init(Cipher.ENCRYPT_MODE, securekey, iv);

                return cipher.doFinal(data);
            } catch (Throwable e) {
                e.printStackTrace();
            }
            return null;
        }

        //加密
        public static String desEncrypt(String srcStr) {
            byte[] src = srcStr.getBytes(SECURITY_CHARSET);
            byte[] buf = desEncrypt(src, DES_KEY);
            return parseByte2HexStr(buf);//密码加密后二进制转16进制输出
        }

        //解密 公共方法
        public static byte[] desDecrypt(byte[] src, String sKey) throws Exception {
            byte[] key = sKey.getBytes();
            // 初始化向量
            IvParameterSpec iv = new IvParameterSpec(key);
            // 创建一个DESKeySpec对象
            DESKeySpec desKey = new DESKeySpec(key);
            // 创建一个密匙工厂
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            // 将DESKeySpec对象转换成SecretKey对象
            SecretKey securekey = keyFactory.generateSecret(desKey);
            // Cipher对象实际完成解密操作
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, securekey, iv);
            // 真正开始解密操作
            return cipher.doFinal(src);
        }


        //解密
        public static String desDecrypt(String hexStr) throws Exception {
            byte[] src = parseHexStr2Byte(hexStr);
            byte[] buf = desDecrypt(src,DES_KEY);
            return new String(buf, SECURITY_CHARSET);
        }


}

//des加密解密测试
    public static void main(String[] args) throws Exception {
        String password =DesUtil.desEncrypt("z111111");
        System.out.println("密码加密以后是:"+password);
        try{
            String oldPwd=DesUtil.desDecrypt(password);
            System.out.println("解密后的密码："+oldPwd);
        }catch (Exception e){
            throw new Exception("解密失败");
        }
    }




}
