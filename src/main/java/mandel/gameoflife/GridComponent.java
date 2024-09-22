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
        grid.setInitial();

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
                repaint();
            }
        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        super.paintComponent(g);

        g.setColor(Color.LIGHT_GRAY);

        int cellSize = 20;

        for (int i = 0; i <= getWidth() / cellSize; i++) {
            g.drawLine(i * cellSize, 0, i * cellSize, getHeight());
        }
        for (int i = 0; i <= getHeight() / cellSize; i++) {
            g.drawLine(0, i * cellSize, getWidth(), i * cellSize);
        }

        for (int y = 0; y < grid.getHeight(); y++) {
            for (int x = 0; x < grid.getWidth(); x++) {
                if (grid.isAlive(x, y)) {
                    g.setColor(Color.BLACK);
                    g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
                }
            }
        }
    }
}

