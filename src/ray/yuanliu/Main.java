package ray.yuanliu;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import java.io.File;

public class Main {

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        setUI();
        boolean success = false;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int myfiles = fileChooser.showOpenDialog(new JFrame());
        final JFrame parent = new JFrame();
        if (myfiles == JFileChooser.APPROVE_OPTION) {
            // User selects a file
            File selectedFile = fileChooser.getSelectedFile();
            // Check whether it is txt file
            if (!selectedFile.getName().endsWith(".txt")) {
                // 不是 txt 檔案
                JOptionPane.showMessageDialog(parent, "請選擇文字檔案");
                System.exit(0);
                return;
            }
            try {
                BufferedReader br = new BufferedReader(new FileReader(selectedFile));
                StringBuilder sb = new StringBuilder();
                //讀取一行
                String line = br.readLine();
                while (line != null) {
                    String[] tokens = line.split("\\.");
                    int minkuo = Integer.parseInt(tokens[0]);
                    int year = minkuo + 1911;
                    String result = String.valueOf(year) + "-" + tokens[1] + "-" + tokens[2];
                    sb.append(result);
                    //換行
                    sb.append(System.lineSeparator());
                    //讓他讀取下一行
                    line = br.readLine();
                }
                String everything = sb.toString();
                System.out.println("done!");
                PrintWriter out = new PrintWriter("result.txt");
                out.print(everything);
                out.close();
                success = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (success) {
            JOptionPane.showMessageDialog(parent, "Done!");
        } else {
            JOptionPane.showMessageDialog(parent, "Failed, Try again.");
        }

        parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.exit(0);
    }

    // Use Nimbus UI
    private static void setUI() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, fall back to cross-platform
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
