package franz.with.tut.util;

public class Vector2D {
    public int x;
    public int y;

    public Vector2D(int x, int y) {
        set(x, y);
    }

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }
}