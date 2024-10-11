package mandel.gameoflife;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class GridControllerTest {

    private static final String GLIDER_RLE = """
            #N Glider
            #O Richard K. Guy
            #C The smallest, most common, and first discovered spaceship. Diagonal, has period 4 and speed c/4.
            #C www.conwaylife.com/wiki/index.php?title=Glider
            x = 3, y = 3, rule = B3/S23
            bob$2bo$3o!
            """.trim();

    @Test
    void toggleCellOn() {
        // given
        Grid model = mock();
        GridComponent view = mock();
        RleParser parser = mock();
        GridController controller = new GridController(model, view, parser);

        doReturn(10).when(view).getCellSize();
        doReturn(100).when(model).getWidth();
        doReturn(100).when(model).getHeight();

        // when
        controller.toggleCell(50, 100);

        // then
        verify(model).put(5, 10);
        verify(view).repaint();
    }

    @Test
    void toggleCellOff() {
        // given
        Grid model = mock();
        GridComponent view = mock();
        RleParser parser = mock();
        GridController controller = new GridController(model, view, parser);

        doReturn(10).when(view).getCellSize();
        doReturn(100).when(model).getWidth();
        doReturn(100).when(model).getHeight();
        doReturn(true).when(model).isAlive(5, 10);

        // when
        controller.toggleCell(50, 100);

        // then
        verify(model).remove(5, 10);
        verify(view).repaint();
    }

    @Test
    public void pasteRle() {
        // given
        Grid model = mock();
        GridComponent view = mock();
        RleParser parser = mock();
        GridController controller = new GridController(model, view, parser);

        // when
        controller.paste(GLIDER_RLE);

        // then
        verify(parser).loadFromRle(GLIDER_RLE);
        verify(view).repaint();
    }

    @Test
    public void pasteUrl() {
        // given
        Grid model = mock();
        GridComponent view = mock();
        RleParser parser = mock();
        GridController controller = new GridController(model, view, parser);
        String url = "https://conwaylife.com/patterns/glider.rle";

        // when
        controller.paste(url);

        // then
        verify(parser).loadFromRle(url);
        verify(view).repaint();
    }
}