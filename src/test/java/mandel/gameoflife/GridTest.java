package mandel.gameoflife;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridTest
{
    Grid rle = new Grid(10, 10);

    @Test
    public void nextGen()
    {
        //given
        Grid grid = new Grid(4, 4);
        grid.put(0, 1);
        grid.put(1, 1);
        grid.put(2, 1);
        grid.put(2, 2);
        grid.put(1, 3);

        //when
        grid.nextGen();

        //then
        assertEquals("0100\n0110\n1010\n0000\n", grid.toString());
    }

    @Test
    public void loadFromRle() {
        // given
        String rleContent = "x = 3, y = 3\nbob$2bo$3o!";

        // when
        rle.loadFromRle(rleContent);
        int[][] grid = rle.getGrid();

        //then
        assertEquals(1, grid[0][1]);
        assertEquals(1, grid[1][2]);
        assertEquals(1, grid[2][0]);
        assertEquals(1, grid[2][1]);
        assertEquals(1, grid[2][2]);

        assertEquals(0, grid[0][0]);
        assertEquals(0, grid[0][2]);
        assertEquals(0, grid[1][1]);
        assertEquals(0, grid[3][3]);
    }

    @Test
    public void decodeRle() {
        // given
        String encoded = "bob$2bo$3o!";

        // when
        rle.decodeRle(encoded);
        int[][] grid = rle.getGrid();

        // then
        assertEquals(1, grid[0][1]);
        assertEquals(1, grid[1][2]);
        assertEquals(1, grid[2][0]);
        assertEquals(1, grid[2][1]);
        assertEquals(1, grid[2][2]);

        assertEquals(0, grid[0][0]);
        assertEquals(0, grid[0][2]);
        assertEquals(0, grid[1][1]);
        assertEquals(0, grid[3][3]);
    }
}