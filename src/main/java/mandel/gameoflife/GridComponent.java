package mandel.gameoflife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GridComponent extends JComponent {

    private final Grid grid;
    private final int CELLSIZE = 20;

    public GridComponent(Grid grid) {
        this.grid = grid;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX() / CELLSIZE;
                int y = e.getY() / CELLSIZE;

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

        g.setColor(Color.WHITE);

        for (int y = 0; y < grid.getHeight(); y++) {
            for (int x = 0; x < grid.getWidth(); x++) {
                g.setColor(Color.GRAY);
                g.drawRect(x * CELLSIZE, y * CELLSIZE, CELLSIZE, CELLSIZE);
                if (grid.isAlive(x, y)) {
                    g.setColor(Color.BLACK);
                    g.fillRect(x * CELLSIZE, y * CELLSIZE, CELLSIZE, CELLSIZE);
                }
            }
        }
    }
}

