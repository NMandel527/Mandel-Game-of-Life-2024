package mandel.gameoflife;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridTest
{
    @Test
    public void nextGen()
    {
        // given
        Grid grid = new Grid(4, 4);
        grid.put(0, 1);
        grid.put(1, 1);
        grid.put(2, 1);
        grid.put(2, 2);
        grid.put(1, 3);

        // when
        grid.nextGen();

        // then
        StringBuilder expected = new StringBuilder();
        for (int i = 0; i < grid.getWidth(); i++) {
            for (int j = 0; j < grid.getHeight(); j++) {
                if (grid.isAlive(j, i)) {
                    expected.append('1');
                } else {
                    expected.append('0');
                }
            }
            expected.append('\n');
        }
        assertEquals(expected.toString(), grid.toString());
    }
}