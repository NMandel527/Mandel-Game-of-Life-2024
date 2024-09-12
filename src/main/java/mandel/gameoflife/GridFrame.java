package mandel.gameoflife;

import javax.swing.*;
import java.awt.*;

public class GridFrame extends JFrame {
    Grid grid = new Grid(80, 80);
    private final Timer timer;
    private final JButton playAndPause;
    private final JButton next;
    private final JButton clear;
    private final JButton reset;
    private boolean isPlaying;

    public GridFrame() {
        setSize(1000, 800);
        setTitle("Game of Life");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        GridComponent gridComponent = new GridComponent(grid);
        add(gridComponent, BorderLayout.CENTER);

        JPanel panel = new JPanel();

        playAndPause = new JButton("Play");
        next = new JButton("Next");
        clear = new JButton("Clear");
        reset = new JButton("Reset");

        setButtonColors(playAndPause);
        setButtonColors(next);
        setButtonColors(clear);
        setButtonColors(reset);

        isPlaying = false;

        timer = new Timer(1000, e -> {
            grid.nextGen();
            repaint();
        });

        playAndPause.addActionListener(e -> {
            if (isPlaying) {
                timer.stop();
                playAndPause.setText("Play");
                next.setEnabled(true);
                clear.setEnabled(true);
                reset.setEnabled(true);
            } else {
                timer.start();
                playAndPause.setText("Pause");
                next.setEnabled(false);
                clear.setEnabled(false);
                reset.setEnabled(false);
            }
            isPlaying = !isPlaying;
        });

        next.addActionListener(e -> {
            grid.nextGen();
            repaint();
        });

        clear.addActionListener(e -> {
            grid.clear();
            repaint();
        });

        reset.addActionListener(e -> {
            grid.reset();
            repaint();
        });

        panel.add(playAndPause);
        panel.add(next);
        panel.add(reset);
        panel.add(clear);

        add(panel, BorderLayout.SOUTH);
    }

    public void setButtonColors(JButton button)
    {
        button.setBackground(Color.BLUE);
        button.setForeground(Color.WHITE);
    }
}
