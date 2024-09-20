package mandel.rle;

import javax.swing.*;

public class RLEFrame extends JFrame {
    public RLEFrame(RLE game) {
        setTitle("Game of Life");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new RLEComponent(game));
    }
}
