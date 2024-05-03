package GAME24;

import java.awt.Graphics;

public class Rect {
    int x;
    int y;
    int w;
    int h;
    int old_x;
    int old_y;

    public Rect(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        old_x = x;
        old_y = y;
    }

    public void moveBy(int dx, int dy) {
        x += dx;
        y += dy;
        old_x = x;
        old_y = y;
    }

    public boolean overlaps(Rect r) {
        return (x + w >= r.x) && (y + h >= r.y) && (x <= r.x + r.w) && (y <= r.y + r.h);
    }

    public boolean cameFromLeftOf(Rect r) {
        return old_x + w <= r.x && x + w > r.x;
    }

    public boolean cameFromRightOf(Rect r) {
        return old_x >= r.x + r.w && x < r.x + r.w;
    }

    public boolean cameFromAbove(Rect r) {
        return old_y + h <= r.y && y + h > r.y;
    }

    public boolean cameFromBelow(Rect r) {
        return old_y >= r.y + r.h && y < r.y + r.h;
    }

    public void pushbackFrom(Rect rect1) {
        // Calculate the minimum translation vector (MTV) to push the rectangles apart
        double dx = 0, dy = 0;

        // Calculate the overlap on the x-axis
        if (rect1.x < x && x < rect1.x + rect1.w) {
            dx = (rect1.x + rect1.w) - x;
        } else if (x < rect1.x && rect1.x < x + w) {
            dx = rect1.x - (x + w);
        }

        // Calculate the overlap on the y-axis
        if (rect1.y < y && y < rect1.y + rect1.h) {
            dy = (rect1.y + rect1.h) - y;
        } else if (y < rect1.y && rect1.y < y + h) {
            dy = rect1.y - (y + h);
        }

        // Determine the axis with the smallest overlap
        if (Math.abs(dx) < Math.abs(dy)) {
            // Push back on the x-axis
            x += dx;
        } else {
            // Push back on the y-axis
            y += dy;
        }
    }


    public void draw(Graphics pen) {
        pen.drawRect(x, y, w, h);
    }

    public String toString() {
        return "new Rect(" + x + ", " + y + ", " + w + ", " + h + "),";
    }

    
}
