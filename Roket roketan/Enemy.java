import greenfoot.*;

public class Enemy extends Actor {
    private int moveDistance = 5;
    private int moveDirection = 1;

    public Enemy() {
        setImage("rock.png");
    }

    public void act() {
        moveHorizontally();
        checkEdge();
    }
    
    

    public void moveHorizontally() {
        setLocation(getX() + (moveDirection * moveDistance), getY());
    }

    public void checkEdge() {
        if (isAtEdge()) {
            // Jika musuh mencapai tepi layar, balik arahnya
            moveDirection *= -1;
        }
    }
}
