// InputMapper.java
package GAME24;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputMapper implements KeyListener {

    boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_UP) {
            upPressed = true;
        }
        if(code == KeyEvent.VK_DOWN) {
            downPressed = true;
        }
        if(code == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
        if(code == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if(code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if(code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
        if(code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
    }
}
