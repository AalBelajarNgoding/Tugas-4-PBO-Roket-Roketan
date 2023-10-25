import greenfoot.*;
import java.util.List;

public class MyWorld extends World {
    private int currentLevel = 1;
    private int score = 0;
    private int lives = 3;
    private int requiredSpecialObjects = 10;

    public MyWorld() {
        super(800, 600, 1);
        prepare();
    }

    public void act() {
        if (getObjects(ObjectSpecial.class).isEmpty()) {
            if (currentLevel < 3) { // Ganti angka ini sesuai jumlah level
                currentLevel++;
                addObject(new LevelComplete(currentLevel), getWidth() / 2, getHeight() / 2);
                Greenfoot.delay(100);
                nextLevel();
            } else {
                showText("Game Over - You Win!", getWidth() / 2, getHeight() / 2);
                Greenfoot.stop();
            }
        }
    }

    public void prepare() {
        addObject(new Character(), getRandomX(), getRandomY());
        addObject(new ObjectSpecial(), getRandomX(), getRandomYAvoidEnemies());
        addObject(new Enemy(), getRandomX(), getRandomYAvoidEnemies());
        addObject(new Enemy2(), getRandomX(), getRandomYAvoidEnemies());
    }

    public void nextLevel() {
        removeObjects(getObjects(ObjectSpecial.class));
        removeObjects(getObjects(LevelComplete.class));
        prepareNextLevel();
    }
    
    public void decreaseLives() {
        lives--;
        if (lives <= 0) {
            showText("Game Over - You Lose!", getWidth() / 2, getHeight() / 2);
            Greenfoot.stop();
        }
    }

    public int getLives() {
        return lives;
    }

    public void increaseScore() {
        score += 10;
        showText("Score: " + score, 50, 25);
    }
    
    public void prepareNextLevel() {
        addObject(new ObjectSpecial(), getRandomX(), getRandomYAvoidEnemies());
        addObject(new Enemy(), getRandomX(), getRandomYAvoidEnemies());
        addObject(new Enemy2(), getRandomX(), getRandomYAvoidEnemies());
    }

    public int getRandomX() {
        return Greenfoot.getRandomNumber(getWidth());
    }

    public int getRandomY() {
        return Greenfoot.getRandomNumber(getHeight());
    }

    public int getRandomYAvoidEnemies() {
        int newY;
        do {
            newY = getRandomY();
        } while (isTooCloseToEnemies(newY));
        return newY;
    }

    public boolean isTooCloseToEnemies(int newY) {
    List<Enemy> enemies = getObjects(Enemy.class);
    List<Enemy2> enemies2 = getObjects(Enemy2.class);

    // Inisialisasi variabel penanda
    boolean isTooClose = false;

    // Periksa jarak dengan musuh pertama
    for (Enemy enemy : enemies) {
        if (Math.abs(newY - enemy.getY()) < 50) {
            isTooClose = true;
            break; // Hentikan pencarian setelah menemukan yang terlalu dekat
        }
    }

    // Periksa jarak dengan musuh kedua jika belum terlalu dekat
    if (!isTooClose) {
        for (Enemy2 enemy2 : enemies2) {
            if (Math.abs(newY - enemy2.getX()) < 50) {
                isTooClose = true;
                break; // Hentikan pencarian setelah menemukan yang terlalu dekat
            }
        }
    }

    return isTooClose; // Kembalikan status jarak terlalu dekat
}

}

