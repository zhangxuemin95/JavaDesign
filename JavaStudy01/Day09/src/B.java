import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;

public class B {

    public static void main(String[] args) throws Exception {
//        FileInputStream fis = new FileInputStream("D:\\工作文档\\practice.txt");
//        byte[] buf = new byte[40];
//        int count = fis.read(buf);
//        //System.out.println((char)fis.read());
//        System.out.println(count);
//        for(int i = 0; i < count; i++)
//            System.out.println(buf[i]);
//        System.out.println(new String(buf));
//        fis.close();


        FileOutputStream fos = new FileOutputStream("D:\\工作文档\\practice.txt");
        byte[] buf = new byte[20];
        buf[0] = -25;
        buf[1] = -120;
        buf[2] = -79;
        fos.write(buf, 0, 3);
        fos.close();

    }
}
