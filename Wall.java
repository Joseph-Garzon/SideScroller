import java.awt.*;

public class Wall extends Collidable {
    private Color color;
    private int x;
    private int y;
    public Wall(Rectangle rect,Color color) {
        super(rect, color);
        this.color = color;
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public boolean collidesWith(Collidable c) {
        return super.collidesWith(c);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
