import greenfoot.*;

public class LevelComplete extends Actor {
    private int level;
    
    public LevelComplete(int level) {
        this.level = level;
        setImage(new GreenfootImage("Level " + level + " Complete", 24, Color.WHITE, Color.BLACK));
    }
    
    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            getWorld().removeObject(this);
        }
    }
}
