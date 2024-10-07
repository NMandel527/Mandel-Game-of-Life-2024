package mandel.gameoflife;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

public class RleParser {
    private int width;
    private int height;
    private final int[][] grid;

    public RleParser(int[][] grid) {
        this.grid = grid;
    }

    public void loadFromRle(String defaultRle) {
        StringBuilder rleInfo = new StringBuilder();

        try {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            String data = clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)
                    ? (String) clipboard.getData(DataFlavor.stringFlavor) : defaultRle;
            if (data.startsWith("http://") || data.startsWith("https://")) {
                try (InputStream inputStream = new URL(data).openStream()) {
                    rleInfo.append(IOUtils.toString(inputStream, StandardCharsets.UTF_8));
                } catch (IOException e) {
                    System.out.println("Error reading from the URL: " + e.getMessage());
                }
            } else {
                File file = new File(data);
                if (file.exists()) {
                    try {
                        rleInfo.append(IOUtils.toString(new FileInputStream(file),
                                StandardCharsets.UTF_8)).append("\n");
                    } catch (IOException e) {
                        System.out.println("Error reading from the file: " + e.getMessage());
                    }
                } else {
                    rleInfo.append(data).append("\n");
                }
            }
            boolean validRle = validate(rleInfo.toString());
            if (validRle) {
                processRle(rleInfo.toString());
            } else {
                processRle(defaultRle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean validate(String rle) {
        boolean isValid = true;

        if (rle.isEmpty()) {
            isValid = false;
        } else {
            int length = rle.length();
            for (int i = 0, startIndex = 0; i <= length; i++) {
                if (i == length || rle.charAt(i) == '\n') {
                    String rleLine = rle.substring(startIndex, i).trim();
                    if (!rleLine.isEmpty()
                            && !(rleLine.charAt(0) == '#'
                            || rleLine.charAt(0) == 'x'
                            || rleLine.charAt(0) == 'o'
                            || rleLine.charAt(0) == 'b'
                            || rleLine.charAt(0) == '$'
                            || rleLine.charAt(0) == '!'
                            || Character.isDigit(rleLine.charAt(0)))) {
                        isValid = false;
                        break;
                    }
                    startIndex = i + 1;
                }
            }
        }
        return isValid;
    }

    public void processRle(String rleData) {
        int startIndex = 0;
        for (int i = 0; i <= rleData.length(); i++) {
            if (i == rleData.length() || rleData.charAt(i) == '\n') {
                String rleLine = rleData.substring(startIndex, i).trim();
                if (!rleLine.isEmpty()) {
                    if (rleLine.charAt(0) == 'x') {
                        getDimensions(rleLine);
                    } else if (rleLine.charAt(0) != '#') {
                        decodeRle(rleLine);
                    }
                }
                startIndex = i + 1;
            }
        }
    }

    public void getDimensions(String dim) {
        StringBuilder xdim = new StringBuilder();
        StringBuilder ydim = new StringBuilder();

        int i = 0;

        while (i < dim.length() && dim.charAt(i) != 'y') {
            if (dim.charAt(i) >= '0' && dim.charAt(i) <= '9') {
                xdim.append(dim.charAt(i));
            }
            i++;
        }

        while (i < dim.length() && dim.charAt(i) != 'r') {
            if (dim.charAt(i) >= '0' && dim.charAt(i) <= '9') {
                ydim.append(dim.charAt(i));
            }
            i++;
        }

        if (!xdim.isEmpty()) {
            width = Integer.parseInt(xdim.toString());
        }
        if (!ydim.isEmpty()) {
            height = Integer.parseInt(ydim.toString());
        }
    }

    public void decodeRle(String rle) {
        int row = grid.length / 2 - height / 2;
        int col = grid[0].length / 2 - width / 2;
        int count = 0;

        for (int i = 0; i < rle.length(); i++) {
            char c = rle.charAt(i);
            if (Character.isDigit(c)) {
                count = Integer.parseInt(String.valueOf(c));
                ;
            } else {
                if (count == 0) {
                    count = 1;
                }
                switch (c) {
                    case 'b':
                        col += count;
                        break;
                    case 'o':
                        for (int j = 0; j < count; j++) {
                            if (row < grid.length && col < grid[0].length) {
                                grid[row][col++] = 1;
                            }
                        }
                        break;
                    case '$':
                        row += count;
                        col = grid[0].length / 2 - width / 2;
                        break;
                    case '!':
                        return;
                    default:
                        System.out.println("Invalid character: " + c);
                        break;
                }
                count = 0;
            }
        }
    }
}