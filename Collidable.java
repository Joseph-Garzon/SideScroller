import java.awt.*;

public class Collidable {
    private Rectangle rect;
    private Color color;
    public static final int UP=0;
    public static final int DOWN=1;
    public static final int LEFT=2;
    public static final int RIGHT=3;
    private int direction=-1;

    public Collidable(Rectangle rect, Color color) {
        this.rect = rect;
        this.color=color;
    }

    public Collidable(Rectangle rect){
        this.rect=rect;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }
    public boolean collidesWith(Collidable c){
        return rect.intersects(c.rect);
    }
    public void changeDirection() {
        if(direction==UP || direction==DOWN)
        direction = (direction+1)%2;
        else if(direction==LEFT || direction==RIGHT){
            if(direction==LEFT)
                direction=RIGHT;
            else if(direction==RIGHT)
                direction=LEFT;
        }
    }
    public void update(){
        if(direction==UP || direction==DOWN){
        int yChange = (direction==UP)?-1:1;
        setRect(new Rectangle((int)getRect().getX(),
                (int)getRect().getY()+yChange,
                (int)getRect().getWidth(),
                (int)getRect().getHeight()));}
        if(direction==LEFT || direction==RIGHT){
            int yChange = (direction==RIGHT)?-1:1;
            setRect(new Rectangle((int)getRect().getX()+yChange,
                    (int)getRect().getY(),
                    (int)getRect().getWidth(),
                    (int)getRect().getHeight()));
    }
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}

