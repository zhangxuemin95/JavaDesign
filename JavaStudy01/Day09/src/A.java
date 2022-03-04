import java.io.*;

public class A {
    public static void main(String[] args) throws IOException {
//        FileReader fr = new FileReader("D:\\工作文档\\practice.txt");
//        char[] buf = new char[20];
//        int count = fr.read(buf);
//        System.out.println(count);
//        //System.out.println(fr.read());
//        System.out.println(new String(buf).toString());


        FileWriter fw = new FileWriter("D:\\工作文档\\practice.txt");
        char[] buf = new char[20];
        fw.write("爱情不努力");
        fw.close();
    }
}
