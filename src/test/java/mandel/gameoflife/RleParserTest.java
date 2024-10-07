package mandel.gameoflife;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RleParserTest {

    @Test
    public void loadFromRle() {
        // given
        Grid grid = new Grid(10, 10);
        Clipboard mockClipboard = Mockito.mock(Clipboard.class);
        Transferable transferable = new StringSelection("x = 3, y = 3, rule = B3/S23\nbob$2bo$3o!");
        Mockito.when(mockClipboard.getContents(null)).thenReturn(transferable);
        Mockito.when(mockClipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)).thenReturn(true);
        RleParser rle = new RleParser(grid.getGrid(), mockClipboard);

        // when
        String defaultRle = "x = 3, y = 3, rule = B3/S23\nbob$2bo$3o!";
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
