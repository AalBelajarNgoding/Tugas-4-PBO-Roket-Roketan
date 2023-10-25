import greenfoot.*;


public class Character extends Actor {
    private int score = 0;
    private int speed = 5;
    private int lives = 3;
    private GifImage myGif = new GifImage("rocket2.gif");

    public Character() {
        GreenfootImage characterImage = myGif.getCurrentImage();
        characterImage.scale(110, 55); // Ganti ukuran sesuai yang Anda inginkan
        setImage(characterImage);
    }

    public void act() {
        handleKeyPress();
        move(3);
        checkForCollision();
        addSmokes();
    }

    public void checkForCollision() {
        Actor object = getOneIntersectingObject(ObjectSpecial.class);
        if (object != null) {
            increaseScore();
            getWorld().removeObject(object);
        }

        // Musuh menjadi diam dan mengakibatkan karakter kehilangan nyawa saat bersentuhan
        Actor enemy = getOneIntersectingObject(Enemy.class);
        if (enemy != null) {
            takeDamage();
        }
        Actor enemy2 = getOneIntersectingObject(Enemy2.class);
        if (enemy2 != null) {
            takeDamage();
        }
    }

    public void increaseScore() {
        score += 10;
        getWorld().showText("Score: " + score, 50, 25);
    }
    
    public void handleKeyPress() {
        if (Greenfoot.isKeyDown("left")) {
            setLocation(getX() - speed, getY());
            setRotation(180); // Menghadap ke kiri
        }
        if (Greenfoot.isKeyDown("right")) {
            setLocation(getX() + speed, getY());
            setRotation(0); // Menghadap ke kanan
        }
        if (Greenfoot.isKeyDown("up")) {
            setLocation(getX(), getY() - speed);
            setRotation(270); // Menghadap ke atas
        }
        if (Greenfoot.isKeyDown("down")) {
            setLocation(getX(), getY() + speed);
            setRotation(90); // Menghadap ke bawah
        }
    }
    
    public void addSmokes() {
        // Tambahkan partikel saat karakter bergerak
        if (Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("down")) {
            World world = getWorld();
            if (world != null) {
                int particleX = getX() + Greenfoot.getRandomNumber(20) - 10;
                int particleY = getY() + Greenfoot.getRandomNumber(20) - 10;
                world.addObject(new Smoke(), particleX, particleY);
            }
        }
    }
    
    public void takeDamage() {
        lives--;
        if (lives <= 0) {
            World world = getWorld();
            if (world != null) {
                world.showText("Game Over - You Lose!", world.getWidth() / 2, world.getHeight() / 2);
                Greenfoot.stop();
            }
        }
    }
}

