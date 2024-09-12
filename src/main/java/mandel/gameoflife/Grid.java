package mandel.gameoflife;

import java.util.Arrays;

public class Grid
{
    private int[][] grid;
    private int[][] initial;

    public Grid(int width, int height) {
        grid = new int[height][width];
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

    public int getWidth() {
        return grid[0].length;
    }

    public int getHeight() {
        return grid.length;
    }

    public void put(int x, int y)
    {
        grid[y][x] = 1;
    }

    public void remove(int x, int y)
    {
        grid[y][x] = 0;
    }

    public void clear()
    {
        for (int[] box : grid) {
            Arrays.fill(box, 0);
        }
    }

    public void setInitial()
    {
        initial = grid;
    }

    public void reset()
    {
        grid = initial;
    }

    public boolean isAlive(int x, int y) {
        return grid[y][x] == 1;
    }

    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                builder.append(grid[y][x]);
            }
            builder.append("\n");
        }

        return builder.toString();
    }
}
