package mandel.rle;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        try {
            String filePath = "C:/Users/necha/Downloads/file.rle";
            String rleContent = new String(Files.readAllBytes(Paths.get(filePath)));
            RLE game = new RLE(100, 80);
            game.loadFromRLE(rleContent);

            RLEFrame frame = new RLEFrame(game);
            frame.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
