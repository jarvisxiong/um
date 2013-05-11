package com.intelitune.ccos.test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public  class Test2{

   //Java IO测试
   public static void main(String[] args) {
       try {
         //FileReader文件读入对象，读入"c:\\in.txt"文件，该文件必须存在
//         FileReader in = new FileReader("c:\\1.txt");
      	 Reader in=new StringReader("qqqqqqqqqqqq");
         //FileWriter文件输出对象，用来写入内容
         FileWriter out = new FileWriter("c:\\2.txt");

         int ch;

         //从in.txt中读入一个个字节
         while ((ch = in.read()) != -1) {  //到文件末尾时，会返回-1，所以这里根据ch是否为-1来结束输入输出过程
           //将读入的字节保存在out对象中
           out.write(ch);
         }

         in.close();  //关闭输入流
         out.close( );  //关闭输出流

         //至此，将在c:盘上创建一个名为out.txt的文件，该文件内容与in.txt完全一样。
       } catch(IOException e) {
       }
     }

}
