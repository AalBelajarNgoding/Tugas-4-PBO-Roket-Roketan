import greenfoot.*;

public class ObjectSpecial extends Actor {
    private GreenfootSound collectSound = new GreenfootSound("radiasi.mp3");

    public ObjectSpecial() {
        GreenfootImage image = new GreenfootImage("nuklir.png");
        image.scale(50, 50); // Ganti ukuran sesuai yang Anda inginkan
        setImage(image);
    }

    public void act() {
        checkForCollision();
    }

    public void checkForCollision() {
        Actor character = getOneIntersectingObject(Character.class);
        if (character != null) {
            ((Character) character).increaseScore();
            getWorld().removeObject(this);
            playCollectSound();
        }
    }

    public void playCollectSound() {
        collectSound.play();
    }
}
