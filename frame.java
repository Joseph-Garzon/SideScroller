//import sun.plugin.dom.css.Rect;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class frame extends JFrame implements Runnable, KeyListener {
    public static final int UPS=50;
    BufferedImage back=null;
    BufferedImage fl=null;
    BufferedImage fr=null;
    BufferedImage waluario=null;
    private BufferedImage buffer;
    private double timeBetweenUpdates=1000.0/UPS;
    int movedx=0;
    int cx=145;
    int cy=192;
    int yin=0;
    int cc=0;
    int cd=0;
    int points=0;
    public int lazyCounter=0;
    boolean space=false;
    boolean left=false;
    boolean right=false;
    boolean stan=false;
    boolean inv=false;
    boolean flip=false;
    boolean small=false;
    boolean state=true;
    boolean L=false;
    boolean W=false;
    ArrayList <Items> stuff=new ArrayList<>();
    ArrayList <Color> order=new ArrayList<>();
    ArrayList <Items> display=new ArrayList<>();
    public frame(){
        super();
        try{
            back= ImageIO.read(new File("ba.jpg"));
            fl= ImageIO.read(new File("fl.png"));
            fr= ImageIO.read(new File("fr.png"));
        }catch(Exception e){
            e.printStackTrace();
        }
        for(int c=0;c<4;c++){
            int ran=(int)(Math.random()*4)+1;
            if(ran==1){
                order.add(Color.blue);
            }
            else if(ran==2){
                order.add(Color.GREEN);
            }
            else if(ran==3){
                order.add(Color.RED);
            }
            else if(ran==4){
                order.add(Color.YELLOW);
            }
        }
        stuff.add(new Items(new Collidable(new Rectangle(0,0,600,337)),'k'));
        stuff.add(new Items(new Collidable(new Rectangle(600,0,600,337)),'k'));
        stuff.add(new Items(new Collidable(new Rectangle(155,192,70,100)),'p'));
        stuff.add(new Items(new Collidable(new Rectangle(185,100,30,30),Color.gray),'z'));
        //stuff.add(new Items(new Collidable(new Rectangle(500,50,30,30),Color.black),'b'));
        //stuff.add(new Items(new Collidable(new Rectangle(1000,50,30,30),Color.PINK),'w'));
        //stuff.add(new Items(new Collidable(new Rectangle(290,150,30,30),Color.gray),'s'));
        //stuff.add(new Items(new Collidable(new Rectangle(220,200,30,30),Color.CYAN),'i'));
        //stuff.add(new Items(new Collidable(new Rectangle(310,220,30,50),Color.MAGENTA),'r'));
        //stuff.add(new Items(new Collidable(new Rectangle(300,150,30,30),Color.MAGENTA),'f'));
        //stuff.add(new Items(new Collidable(new Rectangle(520,150,30,30),Color.GREEN),'m'));
        addKeyListener(this);
        setSize(400,337);
        buffer = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_4BYTE_ABGR);
        waluario=fr;
        setUndecorated(true);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
        Thread t=new Thread(this);
        t.start();
    }

    public void paint(Graphics g){
        Graphics bg=buffer.getGraphics();
        bg.setColor(Color.CYAN);
        bg.fillRect(0,0,getWidth(),getHeight());
        if(state==true) {
            //bg.drawImage(back,0-movedx,0,null);
            //bg.drawImage(back,600-movedx,0,null);
            for (int x = 0; x < stuff.size(); x++) {
                if (stuff.get(x).getItem().getRect().getX() < (getWidth() + movedx) && stuff.get(x).getItem().getRect().getX() + stuff.get(x).getItem().getRect().getWidth() >= movedx && stuff.get(x).getType() == 'k') {
                    bg.drawImage(back, (int) stuff.get(x).getItem().getRect().getX() - movedx, (int) stuff.get(x).getItem().getRect().getY(), null);
                } else if (stuff.get(x).getItem().getRect().getX() < (getWidth() + movedx) && stuff.get(x).getItem().getRect().getX() + stuff.get(x).getItem().getRect().getX() > movedx && stuff.get(x).getType() == 'm') {
                    bg.setColor(stuff.get(x).getItem().getColor());
                    bg.drawRect((int) stuff.get(x).getItem().getRect().getX() - movedx, (int) stuff.get(x).getItem().getRect().getY(), (int) stuff.get(x).getItem().getRect().getWidth(), (int) stuff.get(x).getItem().getRect().getHeight());
                } else if (stuff.get(x).getItem().getRect().getX() < (getWidth() + movedx) && stuff.get(x).getItem().getRect().getX() + stuff.get(x).getItem().getRect().getX() > movedx && stuff.get(x).getType() == 'f') {
                    bg.setColor(stuff.get(x).getItem().getColor());
                    bg.drawRect((int) stuff.get(x).getItem().getRect().getX() - movedx, (int) stuff.get(x).getItem().getRect().getY(), (int) stuff.get(x).getItem().getRect().getWidth(), (int) stuff.get(x).getItem().getRect().getHeight());
                } else if (stuff.get(x).getItem().getRect().getX() < (getWidth() + movedx) && stuff.get(x).getItem().getRect().getX() + stuff.get(x).getItem().getRect().getX() > movedx && stuff.get(x).getType() == 'i') {
                    bg.setColor(stuff.get(x).getItem().getColor());
                    bg.drawRect((int) stuff.get(x).getItem().getRect().getX() - movedx, (int) stuff.get(x).getItem().getRect().getY(), (int) stuff.get(x).getItem().getRect().getWidth(), (int) stuff.get(x).getItem().getRect().getHeight());
                } else if (stuff.get(x).getItem().getRect().getX() < (getWidth() + movedx) && stuff.get(x).getItem().getRect().getX() + stuff.get(x).getItem().getRect().getX() > movedx && stuff.get(x).getType() == 'r') {
                    bg.setColor(stuff.get(x).getItem().getColor());
                    bg.fillOval((int) stuff.get(x).getItem().getRect().getX() - movedx, (int) stuff.get(x).getItem().getRect().getY(), (int) stuff.get(x).getItem().getRect().getWidth(), (int) stuff.get(x).getItem().getRect().getHeight());
                } else if (stuff.get(x).getItem().getRect().getX() < (getWidth() + movedx) && stuff.get(x).getItem().getRect().getX() + stuff.get(x).getItem().getRect().getX() > movedx && !(stuff.get(x).getType() == 'k') && !(stuff.get(x).getType() == 'p')) {
                    bg.setColor(stuff.get(x).getItem().getColor());
                    bg.fillRect((int) stuff.get(x).getItem().getRect().getX() - movedx, (int) stuff.get(x).getItem().getRect().getY(), (int) stuff.get(x).getItem().getRect().getWidth(), (int) stuff.get(x).getItem().getRect().getHeight());
                }
            }
            for (int x = 0; x < stuff.size(); x++) {
                if (stuff.get(x).getItem().getRect().getX() < (getWidth() + movedx) && stuff.get(x).getItem().getRect().getX() + stuff.get(x).getItem().getRect().getX() > movedx && stuff.get(x).getType() == 'm') {
                    bg.setColor(stuff.get(x).getItem().getColor());
                    bg.drawRect((int) stuff.get(x).getItem().getRect().getX() - movedx, (int) stuff.get(x).getItem().getRect().getY(), (int) stuff.get(x).getItem().getRect().getWidth(), (int) stuff.get(x).getItem().getRect().getHeight());
                } else if (stuff.get(x).getItem().getRect().getX() < (getWidth() + movedx) && stuff.get(x).getItem().getRect().getX() + stuff.get(x).getItem().getRect().getX() > movedx && stuff.get(x).getType() == 'f') {
                    bg.setColor(stuff.get(x).getItem().getColor());
                    bg.drawRect((int) stuff.get(x).getItem().getRect().getX() - movedx, (int) stuff.get(x).getItem().getRect().getY(), (int) stuff.get(x).getItem().getRect().getWidth(), (int) stuff.get(x).getItem().getRect().getHeight());
                } else if (stuff.get(x).getItem().getRect().getX() < (getWidth() + movedx) && stuff.get(x).getItem().getRect().getX() + stuff.get(x).getItem().getRect().getX() > movedx && stuff.get(x).getType() == 'i') {
                    bg.setColor(stuff.get(x).getItem().getColor());
                    bg.drawRect((int) stuff.get(x).getItem().getRect().getX() - movedx, (int) stuff.get(x).getItem().getRect().getY(), (int) stuff.get(x).getItem().getRect().getWidth(), (int) stuff.get(x).getItem().getRect().getHeight());
                }
            }
            for(int u=0;u<display.size();u++){
                if (display.get(u).getType() == 'd') {
                    bg.setColor(display.get(u).getItem().getColor());
                    bg.fillRect((int) display.get(u).getItem().getRect().getX(), (int) display.get(u).getItem().getRect().getY(), (int) display.get(u).getItem().getRect().getWidth(), (int) display.get(u).getItem().getRect().getHeight());
                }
            }
            if (small) {
                bg.drawImage(ImageTools.scale(waluario, 25, 25), (0 + cx) - movedx, cy + 75, null);
                stuff.get(2).getItem().getRect().width = 25;
                stuff.get(2).getItem().getRect().height = 25;
            } else {
                bg.drawImage(waluario, (0 + cx) - movedx, cy, null);
            }
            if (flip) {
                g.drawImage(ImageTools.flip(buffer, 2), 0, 0, null);
            } else {
                g.drawImage(buffer, 0, 0, null);
            }
            bg.setColor(Color.black);
            bg.drawString("Score:"+points, 0, 0);
        }
        //lossing stuff
        if(state==false && L) {
            bg.setColor(Color.CYAN);
            bg.fillRect(0, 0, getWidth(), getHeight());
            bg.setColor(Color.black);
            bg.drawString("You Lost!", 175, 175);
            g.drawImage(buffer, 0, 0, null);
        }
        //winning stuff
        if(state==false && W) {
            bg.setColor(Color.CYAN);
            bg.fillRect(0, 0, getWidth(), getHeight());
            bg.setColor(Color.WHITE);
            bg.drawString("You Won!", 175, 175);
            g.drawImage(buffer, 0, 0, null);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(cc==5) {
            if (e.getKeyChar() == 'l' && movedx < (1200 - getWidth())) {
                movedx++;
            }
            if (e.getKeyChar() == 'k' && movedx > 0) {
                movedx--;
            }
            //l arrow
            if (inv == false) {
                if (e.getKeyChar() == 'a' && cx > -1) {
                    waluario = fl;
                    left = true;
                } else {
                    left = false;
                }
                //r arrow
                if (e.getKeyChar() == 'd' && cx < 1100) {
                    waluario = fr;
                    right = true;
                } else {
                    right = false;
                }
            } else {
                if (e.getKeyChar() == 'a' && cx < 1100) {
                    waluario = fr;
                    right = true;
                } else {
                    right = false;
                }
                //r arrow
                if (e.getKeyChar() == 'd' && cx > -1) {
                    waluario = fl;
                    left = true;
                } else {
                    left = false;
                }


            }
            //System.out.print("t");
            for (int x = 3; x < stuff.size(); x++) {
                //if(small==false) {

                if (e.getKeyCode() == KeyEvent.VK_SPACE && cy >= 192) {
                    space = true;
                } else if (e.getKeyCode() == KeyEvent.VK_SPACE && (cy == 50 || cy == 49 || cy==51) && stuff.get(x).getType() == 's' && stan) {
                    space = true;
                } else if (e.getKeyCode() == KeyEvent.VK_SPACE && yin >= 150 && small == false) {
                    space = false;
                } else if (e.getKeyCode() == KeyEvent.VK_SPACE && space) {
                    space = true;
                }
                // }else if(small){
                //      if (e.getKeyCode() == KeyEvent.VK_SPACE && cy == 267) {
                //          space = true;
                //      } else if (e.getKeyCode() == KeyEvent.VK_SPACE && cy == 125 && stuff.get(x).getType() == 's' && stan) {
                //System.out.print("as");
                //          space = true;
                //      } else if (e.getKeyCode() == KeyEvent.VK_SPACE && space) {
                //           space = true;
                //       }
                //   }
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(cc==5) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                space = false;
            }
            if (inv == false) {
                if (e.getKeyChar() == 'a') {
                    left = false;
                }
                if (e.getKeyChar() == 'd') {
                    right = false;
                }
            } else {
                if (e.getKeyChar() == 'a') {
                    right = false;
                }
                if (e.getKeyChar() == 'd') {
                    left = false;
                }
            }
        }
    }

    @Override
    public void run() {
        long startTime=System.nanoTime();
        long updatesDone=0;
        while (true){
            long updatesNeed=(long)(((System.nanoTime()-startTime)/1000000)/timeBetweenUpdates);
            boolean shouldRepaint=false;
            for(;updatesDone<updatesNeed;updatesDone++){
                lazyCounter++;
                if(lazyCounter%80==0){
                    //System.out.print(stan+" ");
                    if(cc<4) {
                        stuff.get(3).getItem().setColor(order.get(cc));
                        display.add(new Items(new Collidable(new Rectangle(125+(50*cc),300,20,20),order.get(cc)),'d'));
                        cc++;
                    }else if(cc==4){
                        stuff.remove(3);
                        cc++;
                        for(int x=0;x<3;x++){
                            int ran=(int)(Math.random()*4)+1;
                            if(ran==1){
                                order.add((int)(Math.random()*order.size()),Color.blue);
                            }
                            else if(ran==2){
                                order.add((int)(Math.random()*order.size()),Color.GREEN);
                            }
                            else if(ran==3){
                                order.add((int)(Math.random()*order.size()),Color.RED);
                            }
                            else if(ran==4){
                                order.add((int)(Math.random()*order.size()),Color.YELLOW);
                            }
                        }
                        for(int y=0;y<7;y++){
                            int ran=(int)(Math.random()*3)+1;
                            int in=(int)(Math.random()*8)+1;
                            int fl=(int)(Math.random()*8)+1;
                            int sm=(int)(Math.random()*8)+1;
                            if(ran==1 || ran==3) {
                                stuff.add(new Items(new Collidable(new Rectangle(400 + (120 * y), 150, 30, 30), order.get(y)), 'b'));
                                if(in==1){
                                    stuff.add(new Items(new Collidable(new Rectangle(400 + (120 * y),150,30,30),Color.CYAN),'i'));
                                }
                                if(fl==1){
                                    stuff.add(new Items(new Collidable(new Rectangle(400 + (120 * y),150,30,30),Color.CYAN),'f'));
                                }
                                if(sm==1){
                                    stuff.add(new Items(new Collidable(new Rectangle(400 + (120 * y),150,30,30),Color.CYAN),'m'));
                                }
                            }
                            else if(ran==2){
                                stuff.add(new Items(new Collidable(new Rectangle(400+(120*y),-5,30,30),order.get(y)),'b'));
                                stuff.add(new Items(new Collidable(new Rectangle(400+(120*y),150,30,30),Color.gray),'s'));
                                stuff.add(new Items(new Collidable(new Rectangle(400+(120*y),220,30,50),Color.MAGENTA),'r'));
                                if(in==1){
                                    stuff.add(new Items(new Collidable(new Rectangle(400 + (120 * y),-5,30,30),Color.CYAN),'i'));
                                }
                                if(fl==1){
                                    stuff.add(new Items(new Collidable(new Rectangle(400 + (120 * y),-5,30,30),Color.CYAN),'f'));
                                }
                                if(sm==1){
                                    stuff.add(new Items(new Collidable(new Rectangle(400 + (120 * y),-5,30,30),Color.CYAN),'m'));
                                }
                            }
                        }

                    }
                }
                if(cc==5 && lazyCounter%5==0 && movedx<(1200-getWidth())){
                    movedx+=2;
                }
                update();
                shouldRepaint=true;}
            if(shouldRepaint)
                repaint();
            try{
                Thread.sleep(25);
            }catch (Exception e){

            }
        }
    }

    public void update(){
        if(state==true){
        for(int x=3;x<stuff.size();x++){
            if(stuff.get(2).getItem().collidesWith(stuff.get(x).item) && stuff.get(x).getType()=='r' && small==false){
                yin=145;
                cy=1;
                stuff.get(2).getItem().getRect().y=stuff.get(2).getItem().getRect().y=0;
            }
            else if(stuff.get(2).getItem().collidesWith(stuff.get(x).item) && stuff.get(x).getType()=='r' && small){
                cy=-75;
                stuff.get(2).getItem().getRect().y=0;
            }
            else if(stuff.get(2).getItem().collidesWith(stuff.get(x).item) && stuff.get(x).getType()=='b'){
                //System.out.print(stuff.get(x).getItem().getColor().toString()+","+display.get(0).getItem().getColor().toString()+" ");
                if(stuff.get(x).getItem().getColor().equals(display.get(0).getItem().getColor())){
                    display.remove(0);
                    points++;
                }else{
                    state=false;
                    L=true;
                }
                stuff.remove(x);

            }
            else if(stuff.get(2).getItem().collidesWith(stuff.get(x).item) && stuff.get(x).getType()=='w'){
                stuff.remove(x);
            }
            else if(stuff.get(2).getItem().collidesWith(stuff.get(x).item) && stuff.get(x).getType()=='f'){
                stuff.remove(x);
                flip=true;
            }
            else if(stuff.get(2).getItem().collidesWith(stuff.get(x).item) && stuff.get(x).getType()=='m'){
                stuff.remove(x);
                small=true;
                stuff.get(2).getItem().getRect().width=25;
                stuff.get(2).getItem().getRect().height=25;
                stuff.get(2).getItem().getRect().y+=75;
                stuff.get(2).getItem().getRect().x-=15;
            }
            else if(stuff.get(2).getItem().collidesWith(stuff.get(x).item) && stuff.get(x).getType()=='i'){
                stuff.remove(x);
                inv=true;
            }
        }
        if(cx>(movedx+400)-90 && small==false){
            //System.out.print("g");
            cx=(movedx+400)-90;
            stuff.get(2).getItem().getRect().x=(movedx+400)-80;
        }
        else if(cx>(movedx+400)-25 && small){
            cx=(movedx+400)-25;
            stuff.get(2).getItem().getRect().x=(movedx+400)-25;
        }
        if(cx<(movedx)-90){
            state=false;
            L=true;
        }
        if(cy<=0 && small==false){
            space=false;
        }
        else if(yin>=192 && small==false && stan==false){
            space=false;
        }
        else if(cy<=42 && small==false && stan==true){
            //System.out.print("te");
            space=false;
        }
        else if(cy<-75 && stan==false){
            space=false;
        }
        if(space==true){
            yin+=2;
            cy-=2;
            stuff.get(2).getItem().getRect().y=stuff.get(2).getItem().getRect().y-2;
        }
        if(space==false && cy<192){
            yin-=2;
            cy+=2;
            stuff.get(2).getItem().getRect().y=stuff.get(2).getItem().getRect().y+2;
        }
        if (left == true && cx > 0) {
            cx -= 2;
            stuff.get(2).getItem().getRect().x = stuff.get(2).getItem().getRect().x - 2;
        }
        if (right == true && cx < (1100)) {
            cx += 2;
            stuff.get(2).getItem().getRect().x = stuff.get(2).getItem().getRect().x + 2;
        }
        for(int x=3;x<stuff.size();x++) {
            if (stuff.get(2).getItem().collidesWith(new Collidable(new Rectangle(stuff.get(x).item.getRect().x, stuff.get(x).item.getRect().y + 3, 1, stuff.get(x).item.getRect().height - 4))) && stuff.get(x).getType() == 's' && right) {
                cx -= 2;
                stuff.get(2).getItem().getRect().x = stuff.get(2).getItem().getRect().x - 2;
            }
            if (stuff.get(2).getItem().collidesWith(new Collidable(new Rectangle(stuff.get(x).item.getRect().x + stuff.get(x).item.getRect().width - 1, stuff.get(x).item.getRect().y + 3, 1, stuff.get(x).item.getRect().height - 4))) && stuff.get(x).getType() == 's' && left) {
                cx += 2;
                stuff.get(2).getItem().getRect().x = stuff.get(2).getItem().getRect().x + 2;
            }
            if (stuff.get(2).getItem().collidesWith(new Collidable(new Rectangle(stuff.get(x).item.getRect().x + 1, stuff.get(x).item.getRect().y, stuff.get(x).item.getRect().width - 2, 1))) && stuff.get(x).getType() == 's' && !space) {
                cy -= 2;
                yin = 0;
                stuff.get(2).getItem().getRect().y = stuff.get(2).getItem().getRect().y - 2;
                stan = true;
            }
            if (stuff.get(2).getItem().collidesWith(new Collidable(new Rectangle(stuff.get(x).item.getRect().x + 1, stuff.get(x).item.getRect().y + stuff.get(x).item.getRect().height, stuff.get(x).item.getRect().width - 2, 1))) && stuff.get(x).getType() == 's' && space) {
                cy += 2;
                yin -= 2;
                stuff.get(2).getItem().getRect().y = stuff.get(2).getItem().getRect().y + 2;
                //System.out.print("k");
                space = false;
            }
            if (yin < 0) {
                yin = 0;
            }
            if (cy < 49) {
                stan = false;
                //System.out.print("fix");
            }
            if(movedx==800){
                state=false;
                L=true;
            }
        }

        if(display.isEmpty() && cc>=5){
            state=false;
            W=true;
        }
        }
    }
}
