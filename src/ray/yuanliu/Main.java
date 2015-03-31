package ray.yuanliu;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) {
        boolean success;
        try {
            BufferedReader br = new BufferedReader(new FileReader("date.txt"));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();    //讀取一行
            while (line != null) {
                String[] tokens = line.split("\\.");
                int minkuo = Integer.parseInt(tokens[0]);
                int year = minkuo + 1911;
                String result = String.valueOf(year) +"-" +tokens[1] +"-" +tokens[2];
                //System.out.println(result);
                sb.append(result);
                sb.append(System.lineSeparator());    //換行
                line = br.readLine();    //讓他讀取下一行
            }
            String everything = sb.toString();
            //System.out.print(everything);
            System.out.println("done!");
            PrintWriter out = new PrintWriter("result.txt");
            out.print(everything);
            out.close();
            success = true;
        }
        catch (Exception e) {
            e.printStackTrace();
            success = false;
        }
        if (success) {
            final JFrame parent = new JFrame();
            JOptionPane.showMessageDialog(parent, "Done!");
            parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } else {
            final JFrame parent = new JFrame();
            JOptionPane.showMessageDialog(parent, "Failed, Try again.");
            parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        System.exit(0);
    }
}
