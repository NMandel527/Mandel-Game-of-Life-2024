package mandel.gameoflife;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        try {
            String filePath = "https://conwaylife.com/patterns/glider.rle";

            URL url = new URL(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();

            String rleContent = content.toString();
            System.out.println(rleContent);

            Grid grid = new Grid(100, 80);
            grid.loadFromRle(rleContent);

            new GridFrame(grid).setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


