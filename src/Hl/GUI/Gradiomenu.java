/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hl.GUI;

import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

public class Gradiomenu extends JRadioButtonMenuItem {

    public Gradiomenu(String text) {
        super(text);
    }

    public Gradiomenu() {
        super();
    }

    @Override
    protected void processMouseEvent(MouseEvent e) {
        if (isEnabled()) super.processMouseEvent(e);
    }
}
