package mandel.rle;

import javax.swing.*;
import java.awt.*;

public class RLEComponent extends JComponent{
    private final RLE game;

    public RLEComponent(RLE game) {
        this.game = game;
        Timer timer = new Timer(500, e -> {
            game.nextGen();
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int[][] grid = game.getGrid();
        int cellSize = 20;

        g.setColor(Color.LIGHT_GRAY);

        for (int i = 0; i <= getWidth() / cellSize; i++) {
            g.drawLine(i * cellSize, 0, i * cellSize, getHeight());
        }
        for (int i = 0; i <= getHeight() / cellSize; i++) {
            g.drawLine(0, i * cellSize, getWidth(), i * cellSize);
        }

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == 1) {
                    g.setColor(Color.BLACK);
                    g.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);
                }
            }
        }
    }
}
