import javax.swing.*;
import java.awt.*;

public class ProduceRandom extends JFrame {
    private int[] random;
    public ProduceRandom(int random[]) {
        this.random = random;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 800);
        setResizable(true);
        setVisible(true);
    }

    public static void main(String[] args) {
        double[] seed = { 5, 7.5, 9.5, 10.5, 9.5, 10, 12, 14.5, 12.5, 12 };
        int[] eat = { 1, 0, 1, 1, 0, 1, 0, 0, 1, 1};
        double[] seedp = new double[100];
        for (int i = 0; i < 10; i++)
        {
            int seedNum = i;
            int index = i * 10;
            double seed1 = seed[seedNum];
            for (int j = 0; j < 10; j++)
            {
                seedp[index + j] = ((int)(seed1 * 101 + 81) )% 10;
                seed1 = seedp[index + j];
            }
        }
        int[] random = new int[1000];
        for(int i = 0; i < 100; i++)
        {
            int index = i * 10;
            double seed1 = seedp[i];
            for(int j = 0; j < 10; j++)
            {
                random[index + j] = ((int)(seed1 * 101 + 81)) % 100;
                seed1 = random[index + j];
            }
        }
        int[] a = new int[100];
        for (int i = 0; i < 1000; i++)
        {
            a[random[i]]++;
        }
        new ProduceRandom(random);
        for(int i = 0; i < 100; i++)
        {
            System.out.print(i + " : " + a[i] + " || ");
            if(i % 10 == 0)
                System.out.println();
        }
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.blue);
        for(int i = 1; i < 1000; i++)
        {
            g.drawOval(random[i - 1], random[i], 100, 100);
        }
    }
}
