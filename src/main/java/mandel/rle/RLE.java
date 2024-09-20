package mandel.rle;

public class RLE {
    private int[][] grid;
    public RLE(int rows, int cols) {
        this.grid = new int[rows][cols];
    }

    public void loadFromRLE(String rleContent) {
        String[] lines = rleContent.split("\n");
        StringBuilder rleInfo = new StringBuilder();

        for (String line : lines) {
            if (!line.startsWith("#") && !line.startsWith("x")) {
                rleInfo.append(line);
            }
        }
        decodeRLE(rleInfo.toString());
    }

    public void decodeRLE(String rle) {
        int row = 0;
        int col = 0;
        int count = 0;

        for (int i = 0; i < rle.length(); i++) {
            char c = rle.charAt(i);
            if (Character.isDigit(c)) {
                count = Integer.parseInt(String.valueOf(c));;
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
                            grid[row][col] = 1;
                            col++;
                        }
                        break;
                    case '$':
                        row += count;
                        col = 0;
                        break;
                    case '!':
                        return;
                }
                count = 0;
            }
        }
    }

    public int[][] getGrid() {
        return grid;
    }

    public void nextGen()
    {
        int [][] tempGrid = new int[grid.length][grid[0].length];

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {

                int livingNeighbors = 0;

                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {

                        if (i == 0 && j == 0) {
                            continue;
                        }

                        int yi = y + i;
                        int xj = x + j;

                        if (yi >= 0 && yi < grid.length && xj >= 0 && xj < grid[y].length) {
                            if (grid[yi][xj] == 1) {
                                livingNeighbors++;
                            }
                        }
                    }
                }

                if ((grid[y][x] == 1 && !(livingNeighbors < 2 || livingNeighbors > 3)) || livingNeighbors == 3) {
                    tempGrid[y][x] = 1;
                }
            }
        }
        grid = tempGrid;
    }
}
