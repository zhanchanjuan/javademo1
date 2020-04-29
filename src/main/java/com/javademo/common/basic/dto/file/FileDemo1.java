package com.javademo.common.basic.dto.file;

import java.io.File;
import java.io.IOException;

/**
 *File类的构造方法+常用方法
 * @author shuyi
 * @date 2020/4/29
 */
public class FileDemo1 {
    public static void main(String[] args) throws IOException {
        //获取路径分隔符
        String pathSeparator= File.pathSeparator;
        System.out.println(pathSeparator);


        //获取文件名称分隔符  windows操作系统:反斜杠  linux操作系统：正斜杠
        //因为不同操作系统文件分隔符有区别，所以以后在写文件路径的时候，不能写死，可以用File.separator获取然后拼接
        String separator=File.separator;
        System.out.println(separator);

//        savePath();

//        savePath2("E:\\","interfaces.js");

//        savePath3();

//        showPath();

//        showType();

        createFile();
    }

   //构造方法
    public static void savePath(){
        File f1=new File("E:\\application\\app-html\\operation_2期\\honeycomb_operation_system\\src\\api");
        System.out.println("文件夹路径{}"+f1);
    }

    public static void savePath2(String parent,String child){
        File f2=new File(parent,child);
        System.out.println("父子文件组合路径{}"+f2);
    }

    public static void savePath3(){
        File parent=new File("C:\\");
        File f3=new File(parent,"index.js");
        System.out.println("无参组合的文件路径{}"+f3);

    }

    //常用方法
    public static  void showPath(){
        File file=new File("F:\\projectdemo\\src\\main\\java\\com\\javademo\\common\\basic\\dto\\file\\FileDemo1.java");
        File file1=new File("FileDemo1.java");
        //getAbsolutePath() 获取绝对路径
        System.out.println(file.getAbsolutePath());
        System.out.println(file1.getAbsolutePath());
        System.out.println(file.getPath());
        System.out.println(file1.getPath());

        //getName()方法  获取文件名(路径最末位)
        File file3=new File("F:\\projectdemo\\src\\main\\java\\com\\javademo\\common\\basic\\dto");
        System.out.println(file.getName());
        System.out.println(file3.getName());

        //file4.length() 获取文件大小327 KB (335,794 字节)
        File file4=new File("E:\\application\\图片(1)\\图片\\关于\\about.jpg");
        System.out.println(file4.length());

    }

    public static void showType(){
        File file3=new File("F:\\projectdemo\\src\\main\\java\\com\\javademo\\common\\basic\\dto");
        //exists()判断文件和文件夹是否在此目录下存在
        System.out.println(file3.exists());
        //isFile()判断是否为文件
        System.out.println(file3.isFile());
        //isDirectory()判断是否为文件夹
        System.out.println(file3.isDirectory());
    }


    public static void createFile() throws IOException{
        File file3=new File("F:\\projectdemo\\src\\main\\java\\com\\javademo\\common\\basic\\dto\\1.txt");
        //createNewFile(),在指定目录下创建文件，文件已经存在则创建不成功，创建的只能是文件，不能是文件夹
        Boolean result=file3.createNewFile();
        System.out.println("文件是否创建成功{}"+result);

        //如果我前面没有dto,用相对路径是可以创建成功的，但是我加上dto文件夹路径，就会抛出IO异常
//        File file4=new File("dto\\2.txt");
        File file4=new File("2.txt");
        Boolean result2=file4.createNewFile();
        System.out.println("文件是否创建成功{}"+result2);
    }




}