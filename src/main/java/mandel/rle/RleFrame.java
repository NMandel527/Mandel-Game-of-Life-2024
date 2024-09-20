package mandel.rle;

import javax.swing.*;

public class RleFrame extends JFrame {
    public RleFrame(Rle game) {
        setTitle("Game of Life");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new RleComponent(game));
    }
}
