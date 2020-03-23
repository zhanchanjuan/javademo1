package com.javademo.common.model;

import com.javademo.common.exception.CommonException;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author shuyi
 * @date 2020/3/20
 * 应用启动加载文件
 */
@Component
public class AlipayProperties {


    public static final String APP_ID = "appId";
    public static final String PRIVARY_KEY = "privateKey";
    public static final String PUBLIC_KEY = "publicKey";
    /**
     * 服务器异步通知页面路径
     */
    public static final String NOTIFY_URL = "notifyUrl";
    /**
     * 页面跳转同步通知页面路径
     */
    public static final String RETURN_URL = "returnUrl";
    /**
     * 签名方式
     */
    public static final String SIGN_TYPE = "signType";
    /**
     * 字符编码
     */
    public static final String CHARSET = "charset";
    /**
     * 支付宝网关路径
     */
    public static final String GATEWAY_URL = "gatewayUrl";
    public static final String LOG_PATH = "logPath";

    /**
     * 保存加载配置参数
     */
    private static Map<String, String> propertiesMap = new HashMap<String, String>();


    /**
     * 加载配置文件属性
     */
    public static void loadProperties() {
        // 获得PathMatchingResourcePatternResolver对象--可以获取类路径和文件夹路径下的文件资源
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            // 加载resource文件，配置文件资源路径
            Resource resources = resolver.getResource("classpath:alipay.properties");
            //定义一个spring管理配置文件的工厂类
            PropertiesFactoryBean config = new PropertiesFactoryBean();
            //存入地址
            config.setLocation(resources);
            //bean的初始化  必须实现InitializingBean
            config.afterPropertiesSet();
            //获取properties配置文件对象
            Properties prop = config.getObject();
            //将配置文件里的配置数据--循环遍历所有得键值对并且存入集合
            for (String key : prop.stringPropertyNames()) {
                propertiesMap.put(key, (String) prop.get(key));
            }
            System.out.println("key值集合"+prop.stringPropertyNames());
            System.out.println("map的键值组合"+propertiesMap);
        } catch (Exception e) {
            throw new CommonException("配置文件加载失败");
        }
    }

    /**
     * 获取配置参数值
     * @param key
     * @return
     */
    public static String getKey(String key) {
        return propertiesMap.get(key);
    }

    public static String getAppId() {
        return propertiesMap.get(APP_ID);
    }

    public static String getPrivateKey() {
        return propertiesMap.get(PRIVARY_KEY);
    }

    public static String getPublicKey() {
        return propertiesMap.get(PUBLIC_KEY);
    }

    public static String getNotifyUrl() {
        return propertiesMap.get(NOTIFY_URL);
    }

    public static String getReturnUrl() {
        return propertiesMap.get(RETURN_URL);
    }

    public static String getSignType() {
        return propertiesMap.get(SIGN_TYPE);
    }

    public static String getCharset() {
        return propertiesMap.get(CHARSET);
    }

    public static String getGatewayUrl() {
        return propertiesMap.get(GATEWAY_URL);
    }

    public static String getLogPath() {
        return propertiesMap.get(LOG_PATH);
    }



    /**
     * PathMatchingResourcePatternResolver文件资源获取--实验测试
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        System.out.println("---------------测试一-----------------");
        //获取和读取本地文件
        Resource r = resolver.getResource("file:E:\\application\\honeycomb_app.sql");
        System.out.println((r.getFilename()));
        try {
            System.out.println(org.apache.commons.io.IOUtils.toString(r.getInputStream()));
        } catch (IOException e) {
           throw new CommonException("本地文件读取失败");
        }

        System.out.println("---------------测试二-----------------");
        //获取项目中的文件信息(名称+路径+内容等等) classpath*：表示加载类路径中所有匹配的资源  **表示上级目录下的所以文件
        Resource[] rs = resolver.getResources("classpath*:com/javademo/common/**/*.class");
        for(Resource resources : rs){
            //文件绝对路径
            System.out.println(resources.getURL().getPath());
        }

        System.out.println("---------------测试三-----------------");
        Resource[] rcs = resolver.getResources("classpath:com/javademo/common/exception/*.class");
        for(Resource resources : rcs){
            //文件绝对路径
            System.out.println(resources.getURL().getPath());
            System.out.println(resources.getFilename());
            //File对象
            System.out.println(resources.getFile());
            //InputStream对象  这个输出的文件内容有一个乱码问题需要解决---????
            System.out.println(org.apache.commons.io.IOUtils.toString(resources.getInputStream()));
        }

    }




}
