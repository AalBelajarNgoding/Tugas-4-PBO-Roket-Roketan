import greenfoot.*;

public class Enemy2 extends Actor {
    private int moveDistance = 5;
    private int moveDirection = 1;

    public Enemy2() {
        setImage("rock.png");
    }

    public void act() {
        moveVertically();
        checkEdge();
    }

    public void moveVertically() {
        setLocation(getX(), getY() + (moveDirection * moveDistance)); // Menggerakkan ke atas atau ke bawah
    }

    public void checkEdge() {
        if (getY() <= 0 || getY() >= getWorld().getHeight() - 1) {
            // Jika musuh mencapai tepi atas atau bawah layar, balik arahnya
            moveDirection *= -1;
        }
    }
}

