package com.javademo.common.basic.dto.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 文件上传服务端：读取客户上传的文件，保存在服务器磁盘，给客户做出回应。
 * @author shuyi
 * @date 2020/5/11
 */
public class TCPServer {
    public static void main(String[] args) throws IOException {
        //创建一个服务器ServerSocket对象，和系统要指定的端口号
        ServerSocket server =new ServerSocket(8888);
        //用ServerSocket对象中的方法accept,获取客户端请求的Socket对象
        Socket socket=server.accept();
        //使用Socket对象中的方法getInputStrean，获取网络字节输入流InputStream对象
        InputStream is=socket.getInputStream();
        //判断E盘是否有这个文件夹，没有就创建一个这个文件夹
        File file=new File("E:\\img");
        if(!file.exists()){
            file.mkdirs();
        }
        //创建一个本地字节输出流FileOutputStream对象，构造方法中绑定要输出的目的的
        FileOutputStream fos=new FileOutputStream(file+"\\1.jpg");
        //使用网络字节输入流对象中的read方法，读取客户端上传的文件
        int len=0;
        byte[] bytes=new byte[1024];
        while ((len =is.read(bytes))!=-1){
            //使用本地字节输出流FileOutputStream对象中的write,把读取到的文件保存到服务器文件夹
            fos.write(bytes,0,len);
        }

    }
}
