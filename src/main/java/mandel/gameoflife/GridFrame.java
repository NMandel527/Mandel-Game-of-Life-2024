package mandel.gameoflife;

import javax.swing.*;
import java.awt.*;

public class GridFrame extends JFrame {
    private final Timer timer;
    private final JButton playAndPause;
    private final JButton next;
    private final JButton clear;
    private final JButton reset;
    private boolean isPlaying;

    public GridFrame(Grid grid) {
        setSize(800, 800);
        setTitle("Game of Life");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        int cellsize = Math.min(getHeight() / grid.getHeight(), getWidth() / grid.getWidth());

        GridComponent gridComponent = new GridComponent(grid, cellsize);
        add(gridComponent, BorderLayout.CENTER);

        playAndPause = new JButton("Play");
        next = new JButton("Next");
        clear = new JButton("Clear");
        reset = new JButton("Reset");

        setButtonColors(playAndPause);
        setButtonColors(next);
        setButtonColors(clear);
        setButtonColors(reset);

        JButton paste = new JButton("Paste");
        paste.setBackground(Color.BLACK);
        paste.setForeground(Color.WHITE);

        isPlaying = false;

        timer = new Timer(500, e -> {
            grid.nextGen();
            repaint();
        });

        String defaultRle = "x = 3, y = 3, rule = B3/S23\n bob$2bo$3o!";
        paste.addActionListener(e -> {
            RleParser rle = new RleParser(grid.getGrid());
            rle.loadFromRle(defaultRle);
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

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        buttonPanel.add(playAndPause);
        buttonPanel.add(next);
        buttonPanel.add(clear);
        buttonPanel.add(reset);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(paste, BorderLayout.WEST);

        add(panel, BorderLayout.SOUTH);
    }

    public void setButtonColors(JButton button) {
        button.setBackground(Color.BLUE);
        button.setForeground(Color.WHITE);
    }
}
