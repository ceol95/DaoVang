/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daovang; //n*(x-1)+y

import daovang.config;
import java.awt.Image;
import java.awt.Graphics2D;
/**
 *
 * @author ceol1
 */
public class Actor {
    protected config con = new config();
    private final int SPACE = con.getSpace();

    private int x;
    private int y;
    private Image image;
    
    public Actor(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Image getImage() {
        return this.image;
    }

    public void setImage(Image img) {
        image = img;
    }

    public int x() {
        return this.x;
    }

    public int y() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    public boolean isCollision(Actor actor) {
        if (((this.x() - SPACE) == actor.x()) &&
            (this.y() == actor.y())) //va cham trai
            return true;
        
        if (((this.x() + SPACE) == actor.x())
                && (this.y() == actor.y())) //va cham phai
            return true;
        
        return false;
        
    }
    
    
    public void drawImageDown(Graphics2D g2d){
        
    }
}
