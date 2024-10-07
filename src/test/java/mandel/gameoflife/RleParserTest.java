package mandel.gameoflife;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RleParserTest
{
    @Test
    public void loadFromRle() {
        // given
        Grid grid = new Grid(5, 5);
        RleParser rle = new RleParser(grid.getGrid());

        // when
        String defaultRle = "x = 3, y = 3, rule = B3/S23\n bob$2bo$3o!";
        rle.loadFromRle(defaultRle);
        int[][] rleGrid = grid.getGrid();

        // then
        assertEquals(1, rleGrid[49][50]);
        assertEquals(1, rleGrid[50][51]);
        assertEquals(1, rleGrid[51][49]);
        assertEquals(1, rleGrid[51][50]);
        assertEquals(1, rleGrid[51][51]);
    }
}