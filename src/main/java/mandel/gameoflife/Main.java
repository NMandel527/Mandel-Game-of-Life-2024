package mandel.gameoflife;

public class Main {
    public static void main(String[] args) {
        String filePath = "https://conwaylife.com/patterns/glider.rle";

        Grid grid = new Grid(100, 80);
        grid.loadFromRle(filePath);

        new GridFrame(grid).setVisible(true);
    }
}


