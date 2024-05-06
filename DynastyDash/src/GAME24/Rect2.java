package GAME24;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Rect2 extends Rect {

    Rect resizer;

    boolean resizing = false;
    boolean moving = false;
    int prevX, prevY;

    public Rect2(int x, int y, int w, int h) {
        super(x, y, w, h);

        // Create a resizer rectangle at the bottom-right corner of rect2
        resizer = new Rect(x + w - 10, y + h - 10, 10, 10);
    }

    // Method to handle mouse press event for resizing and moving
    public void onMousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        // Check if mouse press is within the resizer rectangle
        if (e.getButton() == MouseEvent.BUTTON1 && resizer.contains(mouseX, mouseY)) {
            // Start resizing
            resizing = true;
            resizer.x = mouseX;
            resizer.y = mouseY;
        } else if (contains(mouseX, mouseY)) {
            // Start moving
            moving = true;
            prevX = mouseX;
            prevY = mouseY;
        }
    }

    // Method to handle mouse drag event for resizing and moving
    public void onMouseDragged(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        if (resizing) {
            // Calculate the change in x and y coordinates
            int dx = mouseX - resizer.x;
            int dy = mouseY - resizer.y;

            // Resize rect2 by the change in coordinates
            resizeBy(dx, dy);

            // Update the position of the resizer rectangle
            resizer.moveBy(dx, dy);

            // Update the previous position
            resizer.x = mouseX;
            resizer.y = mouseY;
        } else if (moving) {
            // Calculate the change in x and y coordinates
            int dx = mouseX - prevX;
            int dy = mouseY - prevY;

            // Move rect2 by the change in coordinates
            moveBy(dx, dy);

            // Update the previous position
            prevX = mouseX;
            prevY = mouseY;
        }
    }

    // Method to handle mouse release event
    public void onMouseReleased(MouseEvent e) {
        resizing = false;
        moving = false;
    }

    @Override
    public void draw(Graphics pen) {
        super.draw(pen);
        resizer.draw(pen);
    }
}
