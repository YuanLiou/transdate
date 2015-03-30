import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

public class transdate {

    public static void main(String[] args) {
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
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
