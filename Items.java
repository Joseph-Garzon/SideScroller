public class Items {
    Collidable item;
    char type;

    public Items(Collidable item, char type) {
        this.item = item;
        this.type = type;
    }

    public Collidable getItem() {
        return item;
    }

    public void setItem(Collidable item) {
        this.item = item;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }
}
