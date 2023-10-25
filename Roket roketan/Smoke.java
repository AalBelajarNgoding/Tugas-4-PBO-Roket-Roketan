import greenfoot.*;

public class Smoke extends Actor {
    public Smoke() {
        GreenfootImage particleImage = new GreenfootImage(10, 10);
        particleImage.setColor(Color.WHITE); // Ubah warna partikel sesuai keinginan
        particleImage.fillOval(0, 0, 10, 10);
        setImage(particleImage);
    }

    public void act() {
        if (getImage().getTransparency() > 5) {
            getImage().setTransparency(getImage().getTransparency() - 5);
        } else {
            getWorld().removeObject(this);
        }
    }
}
