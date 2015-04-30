/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Hl.model;

import java.io.Serializable;

/**
 *
 * @author koci
 */
public final class Position implements Serializable {
    
    private int x, y;
    
    public Position(Position p) {
        this.x = p.x;
        this.y = p.y;
    }
    
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
