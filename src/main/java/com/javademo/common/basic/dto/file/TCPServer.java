package com.javademo.common.basic.dto.file;

import com.javademo.common.exception.CommonException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * 网络字节流读，本地字节流写  类似于文件复制，将文件存储到输入流，再通过输出流写入到上传位置
 * 文件上传服务端：读取客户上传的文件，保存在服务器磁盘，给客户做出回应。
 * @author shuyi
 * @date 2020/5/11
 */
public class TCPServer {
    public static void main(String[] args) throws IOException {
        //1、创建一个服务器ServerSocket对象，和系统要指定的端口号
        ServerSocket server =new ServerSocket(55896);
        /*
        * 优化2 上传多个文件或者一直支持上传文件或者客户端上传一个文件，就保存一个文件
        *方法;让服务器一直处于监听状态(死循环的accept()方法)
        * */
        while(true){
//2、用ServerSocket对象中的方法accept,获取客户端请求的Socket对象
            Socket socket=server.accept();
            /**
             * 优化3
             * 多个客户端往同一个服务器上传文件，用多线程，一个客户端请求，新建一个线程去处理请求任务
             */
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        //3、使用Socket对象中的方法getInputStream，获取网络字节输入流InputStream对象
                        InputStream is=socket.getInputStream();
                        //4、判断E盘是否有这个文件夹，没有就创建一个这个文件夹
                        File file=new File("E:\\upload2");
                        if(!file.exists()){
                            file.mkdirs();
                        }
                        /**
                         * 优化1
                         *为了上传不同名称的文件，自定义一个文件的命名规则
                         * 域名+毫秒值+随机数
                         */
                        String fileName="honeycomb"+System.currentTimeMillis()+new Random().nextInt(99999)+".jpg";

                        //5、创建一个本地字节输出流FileOutputStream对象，构造方法中绑定要输出的目的的
                        FileOutputStream fos=new FileOutputStream(file+"\\"+fileName);
                        //6、使用网络字节输入流对象中的read方法，读取客户端上传的文件
                        int len=0;
                        byte[] bytes=new byte[1024];
                        while ((len =is.read(bytes))!=-1){
                            //7、使用本地字节输出流FileOutputStream对象中的write,把读取到的文件保存到服务器文件夹
                            fos.write(bytes,0,len);
                        }
                        //8、使用socket对象中的方法，getOutputStream,获取网络字节输出流OutputStream对象
                        //9、使用网络字节输出流OutputStream对象中的write，给客户端回写"上传成功"
                        OutputStream os=socket.getOutputStream();
                        os.write("上传成功！".getBytes());

                        //10、释放资源
                        fos.close();
                        socket.close();
                    }catch (Exception e){
                        throw new CommonException("文件上传异常"+e);
                    }
                }
            }).start();

        }
    }
}
