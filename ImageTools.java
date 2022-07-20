import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageTools {

    public static int HORIZONTAL_FLIP = 1, VERTICAL_FLIP = 2, DOUBLE_FLIP = 3;
    /**
     * Loads an image.
     *
     * @param fileName The name of a file with an image
     * @return Returns the loaded image. null is returned if the image cannot be loaded.
     */
    public static BufferedImage load(String fileName) {
        try{
            BufferedImage img;
            String s=""+fileName;
            img= ImageIO.read((new File(s)));
            return img;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Copies and returns an image.
     *
     * @param img Receives a buffered image
     * @return Returns a copy of the received image. null is returned if the received image is null.
     */
    public static BufferedImage copy(BufferedImage img) {
        if(img!=null) {
            BufferedImage b = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
            b.getGraphics().drawImage(img, 0, 0, null);
            return b;
        }
        else{
            return null;
        }
    }

    /**
     * Returns a new image with transparency enabled.
     *
     * @param img Receives a buffered image
     * @return returns a copy of the received image with a color mode that allows transparency.
     * null is returned if the received image is null.
     */
    public static BufferedImage copyWithTransparency(BufferedImage img){
        if(img!=null) {
            BufferedImage b = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
            b.getGraphics().drawImage(img, 0, 0, null);
            return b;
        }
        else{
            return null;
        }
    }

    /**
     * Checks if the provided image has transparency.
     *
     * @param img Receives a buffered image
     * @return returns true if the image has a transparent mode and false otherwise.
     */
    public static boolean hasTransparency(BufferedImage img) {
        if(img.getType()==BufferedImage.TYPE_INT_RGB){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Scales an image.
     *
     * @param img Receives a buffered image and two positive double scale values (percentages)
     * @param horizontalScale Value to scale horizontal by.
     * @param verticalScale Value to scale vertical by.
     * @return creates and returns a scaled copy of the received image,
     * null is returned if the received image is null or if non-positive scales are provided
     */
    public static BufferedImage scale(BufferedImage img, double horizontalScale, double verticalScale) {
        BufferedImage b = new BufferedImage((int)(img.getWidth()*horizontalScale), (int)(img.getHeight()*verticalScale), BufferedImage.TYPE_INT_ARGB);
        b.getGraphics().drawImage(img, 0, 0,b.getWidth(),b.getHeight(),0, 0,img.getWidth(),img.getHeight(), null);
        return b;
    }

    /**
     * Scales an image.
     *
     * @param img Receives a buffered image
     * @param newWidth New width to scale to.
     * @param newHeight New height to scale to.
     * @return creates and returns a scaled copy of the received image,
     * null is returned if the received image is null or if non-positive dimensions are provided
     */
    public static BufferedImage scale(BufferedImage img, int newWidth, int newHeight) {
        BufferedImage b = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        b.getGraphics().drawImage(img, 0, 0,b.getWidth(),b.getHeight(),0, 0,img.getWidth(),img.getHeight(), null);
        return b;
    }

    /**
     * Rotates an image.
     *
     * @param img Receives a buffered image
     * @param angle The angle to rotate to.
     * @return The rotated image. null is returned if the received image is null.
     */
    public static BufferedImage rotate(BufferedImage img, double angle) {
        angle = angle%360;
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.setToTranslation(0,0);
        affineTransform.rotate(Math.toRadians(angle), img.getWidth()/2, img.getHeight()/2);
        int transparency = img.getColorModel().getTransparency();
        BufferedImage rotated = new BufferedImage( img.getWidth(), img.getHeight(), transparency);
        Graphics2D  g = (Graphics2D)  (rotated.getGraphics());
        g.drawImage(img, affineTransform, null);
        return rotated;
    }

    /**
     * Flips an image.
     *
     * @param img Receives a buffered image
     * @param type Type of flip (int)
     * @return Creates and returns a flipped copy of the received image.
     * null is returned if the received image is null or if an invalid flipping value is provided
     */
    public static BufferedImage flip(BufferedImage img, int type) {
        BufferedImage b = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
        if(type==1) {
            b.getGraphics().drawImage(img, b.getWidth(), 0,0,b.getHeight(),0, 0,img.getWidth(),img.getHeight(), null);
            return b;
        }
        else if(type==2){
            b.getGraphics().drawImage(img, 0, b.getHeight(),b.getWidth(),0,0, 0,img.getWidth(),img.getHeight(), null);
            return b;
        }
        else if(type==3){
            b.getGraphics().drawImage(img, b.getWidth(),b.getHeight(),0, 0,0, 0,img.getWidth(),img.getHeight(), null);
            return b;
        }
        return null;
    }

    /**
     * Blurs an image.
     *
     * @param img Receives a buffered image
     * @return creates and returns a blurred copy of the received image,
     * the blurring is created by blending each cell with its 8 neighbors.
     * Null is returned if the received image is null.
     */
    public static BufferedImage blur(BufferedImage img) {
        BufferedImage b = copyWithTransparency(img);
        for(int x=0;x<img.getHeight();x++){
            for(int y=0;y<img.getWidth();y++){
                int colorn=img.getRGB(y,x);
                Color color=new Color(colorn,true);
                if(color.getAlpha()>0){
                    int u=1;
                    int r=color.getRed();
                    int bl=color.getBlue();
                    int g=color.getGreen();
                    if(new Color(img.getRGB(y+1,x),true).getAlpha()>0 && y+1<img.getWidth()){
                        u++;
                        r=r+new Color(img.getRGB(y+1,x),true).getRed();
                        bl=bl+new Color(img.getRGB(y+1,x),true).getBlue();
                        g=g+new Color(img.getRGB(y+1,x),true).getGreen();
                    }
                    if(new Color(img.getRGB(y-1,x),true).getAlpha()>0 && y-1>-1){
                        u++;
                        r=r+new Color(img.getRGB(y-1,x),true).getRed();
                        bl=bl+new Color(img.getRGB(y-1,x),true).getBlue();
                        g=g+new Color(img.getRGB(y-1,x),true).getGreen();
                    }
                    if(new Color(img.getRGB(y,x+1),true).getAlpha()>0 && x+1<img.getHeight()){
                        u++;
                        r=r+new Color(img.getRGB(y,x+1),true).getRed();
                        bl=bl+new Color(img.getRGB(y,x+1),true).getBlue();
                        g=g+new Color(img.getRGB(y,x+1),true).getGreen();
                    }
                    if(new Color(img.getRGB(y,x-1),true).getAlpha()>0 && x-1>-1){
                        u++;
                        r=r+new Color(img.getRGB(y,x-1),true).getRed();
                        bl=bl+new Color(img.getRGB(y,x-1),true).getBlue();
                        g=g+new Color(img.getRGB(y,x-1),true).getGreen();
                    }
                    if(new Color(img.getRGB(y+1,x+1),true).getAlpha()>0 && y+1<img.getWidth()&& x+1<img.getHeight()){
                        u++;
                        r=r+new Color(img.getRGB(y+1,x+1),true).getRed();
                        bl=bl+new Color(img.getRGB(y+1,x+1),true).getBlue();
                        g=g+new Color(img.getRGB(y+1,x+1),true).getGreen();
                    }
                    if(new Color(img.getRGB(y-1,x+1),true).getAlpha()>0 && y-1>-1 && x+1<img.getHeight()){
                        u++;
                        r=r+new Color(img.getRGB(y-1,x+1),true).getRed();
                        bl=bl+new Color(img.getRGB(y-1,x+1),true).getBlue();
                        g=g+new Color(img.getRGB(y-1,x+1),true).getGreen();
                    }
                    if(new Color(img.getRGB(y-1,x-1),true).getAlpha()>0 && x+1<img.getHeight() && y-1>-1){
                        u++;
                        r=r+new Color(img.getRGB(y-1,x-1),true).getRed();
                        bl=bl+new Color(img.getRGB(y-1,x-1),true).getBlue();
                        g=g+new Color(img.getRGB(y-1,x-1),true).getGreen();
                    }
                    if(new Color(img.getRGB(y+1,x-1),true).getAlpha()>0 && x-1>-1 && y+1<img.getWidth()){
                        u++;
                        r=r+new Color(img.getRGB(y+1,x-1),true).getRed();
                        bl=bl+new Color(img.getRGB(y+1,x-1),true).getBlue();
                        g=g+new Color(img.getRGB(y+1,x-1),true).getGreen();
                    }
                    r=r/u;
                    Color co=new Color(r,(int)(g/u),(int)(bl/u),color.getAlpha());
                    b.setRGB(y,x,co.getRGB());
                }
            }
        }
        return b;
    }

    /**
     * Inverts an image's colors.
     *
     * @param img Receives a buffered image
     * @return Image with inverted colors. null is returned if the received image is null.
     */
    public static BufferedImage invertColor(BufferedImage img) {
        BufferedImage b = copyWithTransparency(img);
        for(int x=0;x<img.getHeight();x++){
            for(int y=0;y<img.getWidth();y++){
                int colorn=img.getRGB(y,x);
                Color color=new Color(colorn,true);
                int red=255-color.getRed();
                int blue=255-color.getBlue();
                int green=255-color.getGreen();
                int alpha=color.getAlpha();
                Color newColor=new Color(red,green,blue,alpha);
                b.setRGB(y,x,newColor.getRGB());
            }
        }
        return b;
    }

    /**
     * Removes a certain percentage of an image's pixels.
     *
     * @param img Receives a buffered image
     * @param percentToRemove Percent to remove of the image.
     * @return creates and returns a copy of the received image with the given
     * percentage in decimal form of the images remaining non-fully transparent
     * pixels changed to be completely transparent. null is returned if the
     * received image is null or if non-positive percentage is provided.
     */
    public static BufferedImage removePixels(BufferedImage img, double percentToRemove) {
        int n=0;
        for(int x=0;x<img.getWidth();x++){
            for(int y=0;y<img.getHeight();y++){
                int colorn=img.getRGB(x,y);
                Color color=new Color(colorn,true);
                if((img.getRGB(x,y) & 0xff000000 >> 24) != 0x00){
                    n++;
                }
            }
        }

        System.out.print(n+"   ");
        double r=1-percentToRemove;
        n=(int)(n*percentToRemove);
        System.out.print(n+"   ");
        BufferedImage b = copy(img);
       // b.getGraphics().drawImage(img, 0, 0, null);
        System.out.print(b.getWidth()*b.getHeight());
        int x;
        int y;
        while(n!=0){
            x=(int)(Math.random()*b.getWidth());
            y=(int)(Math.random()*b.getHeight());
            int colorn=b.getRGB(x,y);
            Color color=new Color(colorn,true);
            if(color.getAlpha()!=0){
                int red=color.getRed();
                int blue=color.getBlue();
                int green=color.getGreen();
                int alpha=color.getAlpha();
                if(alpha!= 0) {
                    System.out.print(" "+n);
                    Color newColor = new Color(red, green, blue, 0);
                    b.setRGB(x, y, newColor.getRGB());
                    n--;
                }
            }
        }
        System.out.print(n);
        return b;
    }

    /**
     * Removes a certain amount of pixels from an image.
     *
     * @param img Receives a buffered image
     * @param numToRemove number of pixels to remove
     * @return creates and returns a copy of the received image with the given
     * number of the images remaining pixels removed.
     * Non-fully transparent pixels are changed to be completely transparent.
     * null is returned if the received image is null or if non-positive number
     * is provided. Note: is there are not enough pixels in the image it will
     * remove as many as it can.
     */
    public static BufferedImage removePixels(BufferedImage img, int numToRemove) {
        int n=numToRemove;
        BufferedImage b = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
        b.getGraphics().drawImage(img, 0, 0, null);
        int x;
        int y;
        while(n!=0){
            x=(int)(Math.random()*b.getWidth());
            y=(int)(Math.random()*b.getHeight());
            int colorn=img.getRGB(x,y);
            Color color=new Color(colorn,true);
            if(color.getAlpha()>0){
                int red=color.getRed();
                int blue=color.getBlue();
                int green=color.getGreen();
                int alpha=0;
                Color newColor=new Color(red,green,blue,alpha);
                b.setRGB(x,y,newColor.getRGB());
                n--;
            }
        }
        return b;
    }

    /**
     * Fades an image.
     *
     * @param img Receives a buffered image
     * @param fade Double percentage to fade
     * @return Creates and returns a copy of the received image that has been
     * faded the given percentage. Fading is done by multiply the alpha value by (1-fade)
     * Null is returned if the received image is null or if non-positive fade value is provided
     * Note: any fade greater than 1 will be reduced to 1
     */
    public static BufferedImage fade(BufferedImage img, double fade) {
        BufferedImage b = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for(int x=0;x<img.getHeight();x++){
            for(int y=0;y<img.getWidth();y++){
                int colorn=img.getRGB(y,x);
                Color color=new Color(colorn,true);
                int red=color.getRed();
                int blue=color.getBlue();
                int green=color.getGreen();
                int alpha=(int)(color.getAlpha()*(1-fade));
                Color newColor=new Color(red,green,blue,alpha);
                b.setRGB(y,x,newColor.getRGB());
            }
        }
        return b;
    }

    /**
     * Lightens an image.
     *
     * @param img Receives a buffered image
     * @param lightenFactor double percentage to lighten
     * @return creates and returns a copy of the received image that has been
     * lightened by the given percentage + 1.
     * Null is returned if the received image is null or if non-positive lighten.
     * Factor value is provided.
     * Note: any colors that end up being larger than 255 will be changed to 255.
     */
    public static BufferedImage lighten(BufferedImage img, double lightenFactor) {
        BufferedImage b = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for(int x=0;x<img.getHeight();x++){
            for(int y=0;y<img.getWidth();y++){
                int colorn=img.getRGB(y,x);
                Color color=new Color(colorn,true);
                int red=(int)(color.getRed()*(1+lightenFactor));
                int blue=(int)(color.getBlue()*(1+lightenFactor));
                int green=(int)(color.getGreen()*(1+lightenFactor));
                if(red>255){
                    red=255;
                }
                if(blue>255){
                    blue=255;
                }
                if(green>255){
                    green=255;
                }
                int alpha=color.getAlpha();
                Color newColor=new Color(red,green,blue,alpha);
                b.setRGB(y,x,newColor.getRGB());
            }
        }
        return b;
    }

    /**
     * Darkens an image.
     *
     * @param img Receives a buffered image
     * @param darkenFactor double percentage to darken
     * @return creates and returns a copy of the received image that has been
     * darkened by 1 minus the given percentage.
     * null is returned if the received image is null or if non-positive.
     * Note: any colors that end up being larger than 255 will be
     * changed to 255.
     */
    public static BufferedImage darken(BufferedImage img, double darkenFactor) {
        BufferedImage b = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for(int x=0;x<img.getHeight();x++){
            for(int y=0;y<img.getWidth();y++){
                int colorn=img.getRGB(y,x);
                Color color=new Color(colorn,true);
                int red=(int)(color.getRed()*(1-darkenFactor));
                int blue=(int)(color.getBlue()*(1-darkenFactor));
                int green=(int)(color.getGreen()*(1-darkenFactor));
                if(red>255){
                    red=255;
                }
                if(blue>255){
                    blue=255;
                }
                if(green>255){
                    green=255;
                }
                int alpha=color.getAlpha();
                Color newColor=new Color(red,green,blue,alpha);
                b.setRGB(y,x,newColor.getRGB());
            }
        }
        return b;
    }
}
