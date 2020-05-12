package com.javademo.common.basic.dto.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 本地字节流读，网络字节流写
 * 文件上传案例的客户端：读取本地文件，上传到服务器，读取服务器回写的数据
 * @author shuyi
 * @date 2020/5/11
 */

public class TCPClient {
    public static void main(String[] args) throws IOException {
        //1、创建一个本地字节输入流FileInputStream对象，构造方法中绑定要读取的数据源    本地-->客户端服务--->获取
        FileInputStream fis=new FileInputStream("E:\\application\\图片(1)\\图片\\app logo\\product.jpg");
        //2、创建一个客户端socket对象，构造方法中绑定服务器的ip和端口号
        Socket socket=new Socket("127.0.0.1",55896);
        //3、使用socket中的getOutPutStream,获取网络字节输出流OutPutStream对象
        OutputStream os=socket.getOutputStream();
        //4、使用本地字节输入流FileInputStream对象中的方法read读取本地文件  ---》读取本地
        int len =0;
        byte[] bytes=new byte[1024];
        while((len =fis.read(bytes))!=-1){
            //5、使用网络字节输出流OutPutStream对象中的write()方法，把读取到的文件上传到服务  --》写到服务器
            os.write(bytes,0,len);
        }
        /**
         * 为了避免死循环，这里上传文件后，给服务器一个结束标记，禁止此套接字的输出流
         */
        socket.shutdownOutput();
        //6、使用socket中的getInputStream()方法,获取网络字节输出流中的InputStream对象
        InputStream is =socket.getInputStream();
        //7、使用网络字节输出流中的InputStream对象的read方法读取服务回写的数据---》回写服务器数据
        while ((len = is.read(bytes))!=-1){
            System.out.println(new String(bytes,0,len));
        }
        //8、释放资源
        fis.close();
        os.close();

    }

}
