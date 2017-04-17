/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daovang;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author ceol1
 */
public class config {
    /**
     * file lưu toàn bộ các biến cố định khởi tạo trong chương trình
     */
    
    /**
     * Hình ảnh cho monster
     */
    private final int SPACE = 40;// Khoảng cách 1 hình
    private final int OFFSET = 45;// Khoảng cách của giao diện so với lề
    private String monUp = "image/monUp.png";
    
    /**
     * Hình ảnh cho lisa
     */
    private String lisaUp = "image/lisaUp.png";
    private String lisaDown = "image/lisaDown.png";
    private String lisaLeft = "image/lisaLeft.png";
    private String lisaRight = "image/lisaRight.png";
    /**
     * Hình ảnh cho cỏ, tường
     */
    private String grass = "image/grass.png";
    private String wall = "image/wall.png";
    
    
    private String monDown = "image/monDown.png";
    private String monLeft = "image/monLeft.png";
    private String monRight = "image/monRight.png";
    
    
    private Image getImage(String url){
        URL loc = this.getClass().getResource("image/wall.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        return image;
    }
    public Image getMonUp() {
        return getImage(monUp);
    }

    public Image getLisaUp() {
        return getImage(lisaUp);
    }

    public Image getLisaDown() {
        return getImage(lisaDown);
    }

    public Image getLisaLeft() {
        return getImage(lisaLeft);
    }

    public Image getLisaRight() {
        return getImage(lisaRight);
    }

    public Image getGrass() {
        return getImage(grass);
    }

    public Image getWall() {
        return getImage(wall);
    }
    public int getSpace(){
        return SPACE;
    }
    public int getOffset(){
        return OFFSET;
    }
   
}
