package com.javademo.common.basic.dto.file;

import java.io.*;

/**
 *File类的构造方法+常用方法
 * @author shuyi
 * @date 2020/4/29
 */
public class FileDemo1 {
    public static void main(String[] args) throws IOException {
        //获取路径分隔符
//        String pathSeparator= File.pathSeparator;
//        System.out.println(pathSeparator);


        //获取文件名称分隔符  windows操作系统:反斜杠  linux操作系统：正斜杠
        //因为不同操作系统文件分隔符有区别，所以以后在写文件路径的时候，不能写死，可以用File.separator获取然后拼接
//        String separator=File.separator;
//        System.out.println(separator);

//        savePath();

//        savePath2("E:\\","interfaces.js");

//        savePath3();

//        showPath();

//        showType();

//        createFile();

//        createFileDelete();
        String file="上传的文件111";
        String path="E:\\application\\file.txt";
        addFile(file,path);
        String path2="E:\\application\\file2.txt";
        addFile2(file,path2);
    }

   //构造方法
    public static void savePath(){
        File f1=new File("E:\\application\\app-html\\operation_2期\\honeycomb_operation_system\\src\\api");
        System.out.println("文件夹路径{}f1:"+f1);
    }

    public static void savePath2(String parent,String child){
        File f2=new File(parent,child);
        System.out.println("父子文件组合路径{}f2:"+f2);
    }

    public static void savePath3(){
        File parent=new File("C:\\");
        File f3=new File(parent,"index.js");
        System.out.println("无参组合的文件路径{}f3:"+f3);

    }

    //常用方法
    public static  void showPath(){
        File file=new File("F:\\projectdemo\\src\\main\\java\\com\\javademo\\common\\basic\\dto\\file\\Demo1.java");
        File file1=new File("FileDemo1.java");
        //getAbsolutePath() 获取绝对路径  这里有个疑问？？？不存在的文件；路径也是可以获取？？？还是new File()的时候写的什么就获取什么
        System.out.println(file.getAbsolutePath());
        System.out.println(file1.getAbsolutePath());
        //getPath() 获取相对路径  --构建文件时的路径，相对根路径或者其他路径而展示的路径
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
        File file3=new File("F:\\projectdemo\\src\\main\\java\\com\\javademo\\common\\basic\\dto\\file");
        //exists()判断文件和文件夹是否在此目录下存在
        System.out.println(file3.exists());
        //isFile()判断是否为文件
        System.out.println(file3.isFile());
        //isDirectory()判断是否为文件夹
        System.out.println(file3.isDirectory());
    }


    public static void createFile() throws IOException{
        File file3=new File("F:\\projectdemo\\src\\main\\java\\com\\javademo\\common\\basic\\dto\\2.txt");
        //createNewFile(),在指定目录下创建文件，文件已经存在则创建不成功，创建的只能是文件，不能是文件夹
        Boolean result=file3.createNewFile();
        System.out.println("文件是否创建成功{}"+result);

        //如果我前面没有dto,用相对路径是可以创建成功的，但是我加上dto文件夹路径，就会抛出IO异常
//        File file4=new File("dto\\2.txt");
        File file4=new File("3.txt");
        Boolean result2=file4.createNewFile();
        System.out.println("文件是否创建成功{}"+result2);
    }

    public static void createFileDelete() throws IOException{
        File f6=new File("F:\\study\\file");
        


    }

    //、写入文件  覆盖
    public static boolean addFile(String string,String path) {
        PrintStream stream=null;
        try {
            stream=new PrintStream(path);//写入的文件path
            stream.print(string);//写入的字符串
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    //非覆盖的写入文件，在原有的文件内容后面继续写入
    public static boolean addFile2(String string,String path2) {
        //文件的续写
        FileWriter fw = null;
        try {
            fw = new FileWriter(path2,true);
            //写入换行
            fw.write("\r\n");//Windows平台下用\r\n，Linux/Unix平台下用\n
            //续写一个hello world!
            fw.write(string);
            fw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }





}
