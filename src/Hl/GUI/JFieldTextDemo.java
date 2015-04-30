/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hl.GUI;

import Hl.model.Game;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;


public class JFieldTextDemo extends JFrame {
        public static int hracuchybi=-1; 
         public static int pocethracu=-1; 
         public List<String> figuraname=new ArrayList<>();
         public List<String> figurobr=new ArrayList<>();
	//Class Declarations
         JLayeredPane pane;
	JTextField jtfText1;
        JLabel click;
	String disp = "";
        private int pocetpokladu;
	TextHandler handler = null;
        private JLabel obr[] ;
        private Rectangle obrclick[] ;
       
        public  int hrac;
        public static int velikost;
	//Constructor
	public JFieldTextDemo(int pocethracu, int velikost,int hrac,int pocetpoklad) {
		super("Zadejte Jmeno hrace"+(hrac+1));
                this.pocetpokladu=pocetpoklad;
                obr=new JLabel[4];
                this.setPreferredSize(new Dimension(250, 150));
                this.setSize(new Dimension(250, 150));
                this.setResizable(false);
                obrclick=new Rectangle[4];
                for (int i=0;i<4;i++){
                     Rectangle rect2;
                     obrclick[i]=new Rectangle(); 
                     ImageIcon icon=new ImageIcon("figur"+i+".png");
                    obr[i]=new JLabel();
                    obr[i].setName("figur"+i+".png");
                    obr[i].setIcon(icon);
                    obrclick[i].setRect(60*(i), 20 , 60,60 );
                    this.add(obr[i]);
                    obr[i].setBounds(60*(i), 0 , 60,60 );
              
                }
                
                this.hrac=hrac;
                JFieldTextDemo.velikost=velikost;
                hracuchybi=pocethracu;
                    JFieldTextDemo.pocethracu=pocethracu;
		Container container = getContentPane();
		
		jtfText1 = new JTextField(7);
                this.setAlwaysOnTop (true);
		this.setLayout(null);
		container.add(jtfText1);
		JFieldTextDemo.this.jtfText1.selectAll();
		handler = new TextHandler();
		jtfText1.addActionListener(handler);
		jtfText1.setDocument(new JTextFieldLimit(7));
                jtfText1.setText("player"+(this.hrac+1));
                jtfText1.setLocation(60, 70);
                 jtfText1.setSize(120, 20);
                 
		//setSize(300, 190);
		setVisible(true);
                 MListener ml=new MListener();
                this.addMouseListener(ml);
                this.addMouseMotionListener(ml);
               JFieldTextDemo.this.jtfText1.selectAll();
                JFieldTextDemo.this.click=obr[hrac];
                JFieldTextDemo.this.click.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                
                
               
          
	}
	//Inner Class TextHandler
        private class MListener extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e){
            for (int i=0;i<4;i++){
           if (JFieldTextDemo.this.obrclick[i].contains(e.getPoint())){
               JFieldTextDemo.this.click=obr[i];
               JFieldTextDemo.this.obr[i].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
               System.out.print(i+"\n");
           }else 
           {JFieldTextDemo.this.obr[i].setBorder(null);}
            }
        }
       }
	private class TextHandler implements ActionListener {

                @Override
		public void actionPerformed(ActionEvent e) {
			//if (e.getSource() == jtfText1) {
                        
                                JFieldTextDemo.hracuchybi--;
                                JFieldTextDemo.this.hrac++;
                                JFieldTextDemo.this.figuraname.add(e.getActionCommand());
                                JFieldTextDemo.this.figurobr.add( JFieldTextDemo.this.click.getName());
                                JFieldTextDemo.this.click.setVisible(false);
                               
                                if (JFieldTextDemo.hracuchybi>0){
                                    int i=hrac;
                                    if (!obr[i].isVisible()){
                                        
                                        i=0;
                                        while  (!obr[i].isVisible()){
                                            i++;
                                        }
                                    }
                                  JFieldTextDemo.this.click=obr[i];
                                   JFieldTextDemo.this.click.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                                  JFieldTextDemo.this.jtfText1.setText("player"+(JFieldTextDemo.this.hrac+1));
                                  JFieldTextDemo.this.setTitle("Zadejte Jmeno hrace"+(JFieldTextDemo.this.hrac+1));
                                  JFieldTextDemo.this.jtfText1.selectAll();
                                }else {
                                   //  JFieldTextDemo.this.figuraname.add(e.getActionCommand());
                                    JFieldTextDemo.this.setVisible(false);
                                    
                                 
                                        GameAppFrame.Appframe.newAppFrame(Game.NewPokus(JFieldTextDemo.pocethracu,JFieldTextDemo.velikost,JFieldTextDemo.this.figuraname,JFieldTextDemo.this.figurobr,JFieldTextDemo.this.pocetpokladu));
                                 
                                }
                                
                              
			//} 
			
		}
	}
        class JTextFieldLimit extends PlainDocument {
  private int limit;
  JTextFieldLimit(int limit) {
    super();
    this.limit = limit;
  }

  JTextFieldLimit(int limit, boolean upper) {
    super();
    this.limit = limit;
  }

  public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
    if (str == null)
      return;

    if ((getLength() + str.length()) <= limit) {
      super.insertString(offset, str, attr);
    }
  }
}

}
