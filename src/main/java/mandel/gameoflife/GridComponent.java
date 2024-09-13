package mandel.gameoflife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GridComponent extends JComponent {

    private final Grid grid;
    private final int cellSize = 20;

    public GridComponent(Grid grid) {
        this.grid = grid;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX() / cellSize;
                int y = e.getY() / cellSize;

                if (grid.isAlive(x, y)) {
                    grid.remove(x, y);
                } else {
                    grid.put(x, y);
                }
                grid.setInitial();
                repaint();
            }
        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int y = 0; y < grid.getHeight(); y++) {
            for (int x = 0; x < grid.getWidth(); x++) {
                if (grid.isAlive(x, y)) {
                    g.setColor(Color.BLACK);
                    g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
                } else {
                    g.setColor(Color.GRAY);
                    g.drawRect(x * cellSize, y * cellSize, cellSize, cellSize);
                }
            }
        }
    }
}

