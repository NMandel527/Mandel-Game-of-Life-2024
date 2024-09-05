package mandel.gameoflife;

public class Grid
{
    private int[][] grid;

    public Grid(int width, int height)
    {
        grid = new int[height][width];
    }

    public void nextGen()
    {
        int [][] tempGrid = new int[grid.length][grid[0].length];

        for (int y = 0; y < grid.length; y++)
        {
            for (int x = 0; x < grid[y].length; x++)
            {

                int livingNeighbors = 0;

                for (int i = -1; i <= 1; i++)
                {
                    for (int j = -1; j <= 1; j++)
                    {

                        if (i == 0 && j == 0)
                        {
                            continue;
                        }

                        int yi = y + i;
                        int xj = x + j;

                        if (yi >= 0 && yi < grid.length && xj >= 0 && xj < grid[y].length) {
                            if (grid[yi][xj] == 1)
                            {
                                livingNeighbors += 1;
                            }
                        }
                    }
                }

                if (grid[y][x] == 1)
                {
                    if (livingNeighbors < 2 || livingNeighbors > 3)
                    {
                        tempGrid[y][x] = 0;
                    }
                    else
                    {
                        tempGrid[y][x] = 1;
                    }
                }
                else
                {
                    if (livingNeighbors == 3)
                    {
                        tempGrid[y][x] = 1;
                    }
                    else
                    {
                        tempGrid[y][x] = 0;
                    }
                }
            }
        }

        for (int y = 0; y < tempGrid.length; y++)
        {
            System.arraycopy(tempGrid[y], 0, grid[y], 0, tempGrid[y].length);
        }
    }

    public void put(int x, int y)
    {
        grid[y][x] = 1;
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
