package mandel.rle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RleTest {
    Rle rle = new Rle(10, 10);

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

    @Test
    public void nextGen() {
        // given
        String rleContent = "x = 3, y = 3\nbob$2bo$3o!";
        rle.loadFromRle(rleContent);

        // when
        rle.nextGen();
        int[][] grid = rle.getGrid();

        // then
        int[][] expectedGrid = new int[10][10];
        expectedGrid[1][0] = 1;
        expectedGrid[1][2] = 1;
        expectedGrid[2][1] = 1;
        expectedGrid[2][2] = 1;
        expectedGrid[3][1] = 1;

        for (int i = 0; i < expectedGrid.length; i++) {
            for (int j = 0; j < expectedGrid[i].length; j++) {
                assertEquals(expectedGrid[i][j], grid[i][j], "Mismatch at cell (" + i + ", " + j + ")");
            }
        }
    }
}