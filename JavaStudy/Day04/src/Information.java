import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Information {
    public static void main(String[] args) {
        File file = new File("D:\\工作文档\\信息安全\\密文.txt");
        Scanner in = null;
        try {
            in = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String light = in.nextLine();

        int[] letter = new int[26];
        char[] a = light.toCharArray();
        for(int i = 0; i < a.length; i++)
        {
            letter[a[i] - 65]++;
        }
        for(int i = 0; i < letter.length;i++)
        {
            System.out.println((char)(i + 65) + " : " + letter[i]);
        }
        light = light.replace('A', 't');
        light = light.replace('H', 'h');
        light = light.replace('C', 'e');
        light = light.replace('R', 'r');
        light = light.replace('E', 'c');
        light = light.replace('N', 'n');
        light = light.replace('O', 'i');
        light = light.replace('S', 's');
        light = light.replace('G', 'g');
        light = light.replace('I', 'o');
        light = light.replace('V', 'v');
        light = light.replace('F', 'm');
        light = light.replace('T', 'a');
        light = light.replace('D', 'd');
        light = light.replace('U', 'u');
        light = light.replace('L', 'l');
        light = light.replace('Y', 'y');
        light = light.replace('W', 'w');
        light = light.replace('Z', 'z');
        light = light.replace('P', 'p');
        light = light.replace('X', 'x');
        light = light.replace('M', 'f');
        light = light.replace('B', 'b');
        light = light.replace('J', 'j');
        System.out.println(light);
    }
}
