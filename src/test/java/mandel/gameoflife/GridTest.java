package mandel.gameoflife;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridTest
{
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
        grid.nextGen();

        //then
        assertEquals("0110\n1010\n0010\n0000\n", grid.toString());
    }
}