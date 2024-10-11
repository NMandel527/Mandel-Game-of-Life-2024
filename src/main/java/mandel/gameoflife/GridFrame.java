package mandel.gameoflife;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;

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
        RleParser parser = new RleParser(grid.getGrid());
        GridController controller = new GridController(grid, gridComponent, parser);
        gridComponent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.toggleCell(e.getX(), e.getY());
            }
        });

        gridComponent.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                controller.toggleCell(e.getX(), e.getY());
            }
        });

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

        paste.addActionListener(e -> {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            String data = null;
            if (clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)) {
                Transferable transferable = clipboard.getContents(null);
                if (transferable != null) {
                    try {
                        data = (String) transferable.getTransferData(DataFlavor.stringFlavor);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
            controller.paste(data);
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
