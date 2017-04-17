/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daovang;

import java.awt.Image;



/**
 *
 * @author ceol1
 */
public class Player extends Actor {
    
    
    
    public Player(int x, int y) {
        super(x, y);
        //this.setImage(image);
    }

    public void move(int x, int y) {
        int nx = this.x() + x;
        int ny = this.y() + y;
        this.setX(nx);
        this.setY(ny);
    }
    
}
