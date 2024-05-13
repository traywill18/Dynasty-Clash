package GAME24;

public class Collision_Detection {

    public static boolean isColliding(Rect rect1, Rect... rects) {
        for (Rect rect : rects) {
            if (rect1.x + rect1.w >= rect.x && rect1.x <= rect.x + rect.w &&
                rect1.y + rect1.h >= rect.y && rect1.y <= rect.y + rect.h) {
                return true; // Collision detected
            }
        }
        return false; // No collision detected
    }
}
