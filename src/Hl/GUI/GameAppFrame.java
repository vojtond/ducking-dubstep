/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hl.GUI;
import javax.swing.JFrame;
import Hl.model.Game;


import Hl.GameSave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import Hl.Main;
import Hl.model.board.MazeCard;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;




/**
 * třída pro popis menu a odchytávání klávesnice. Implementuje návrhový vzor Jedináček
 * @author Pikachu
 */
public final class GameAppFrame {
    private JFrame frame;
    private Game game;
    private GGame gp;
    private static GameSave save;
    private final JMenuBar menuBar;
    public static GameAppFrame Appframe ;
    private GameAppFrame.figurKeyEvent f;
    private   byte[] byteData;
    
    /**
     *
     * @return vrací buď nově vytvořený objekt GameAppFrame nebo vrátí již existující
     * @throws IOException
     */
    public static GameAppFrame  newGameAppFrame() throws IOException{
      //  if (Appframe==null){
            Appframe=new GameAppFrame();
        //}
        return Appframe;
    }    

    /**
     *vytvoří nové grafické rozhraní pro hru game
     * @param game
     * @see Game
     */
    public void  newAppFrame(Game game){
        this.game=game;
        gp=GGame.newGPokus(game);
        frame.setContentPane(gp);
        frame.pack();
        frame.setFocusable(true);
        frame.requestFocusInWindow();
        frame.removeKeyListener(f);    
        f=new GameAppFrame.figurKeyEvent();
        frame.addKeyListener(f);
         frame.setResizable(false);
    }
    private GameAppFrame() throws IOException{
        
     /*   ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this.game);
        oos.flush();
        oos.close();
        bos.close();
        this.byteData = bos.toByteArray();*/
     
        GameAppFrame.save=new GameSave();
        save.game=this.game;
         double ratio,rH,rW;
        int gH,gW;
        f=null;
        
                JMenu menu;
        JMenuItem menuItem;
        frame=new JFrame("vojtíšek + mareček");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(0,0);
        JPanel panel=new JPanel();
       
        rH=(double)500/500;
        rW=(double)500/500;
        ratio=rH>1 ? 1:rH;
        ratio=rW>1 ? ratio : ( rW > rH ? rH : rW);
        gH=(int)(500*ratio);
        gW=(int)(500*ratio);
        Dimension dim=new Dimension (gH,gW);
       
        frame.getContentPane().setPreferredSize(dim);
        frame.getContentPane().setSize(dim);

   
        
        frame.setVisible(true);
        frame.pack();
         menuBar = new JMenuBar();
        menu = new JMenu("File");
        menuBar.add(menu);
        JMenu submenu;
        submenu = new JMenu("New Game");
         ButtonGroup group1=new ButtonGroup();
          JRadioButton radiobutton;
         radiobutton=new JRadioButton("12 pokladů");
        radiobutton.setActionCommand("12");
        group1.add(radiobutton);
        submenu.add(radiobutton);
        
        
        radiobutton=new JRadioButton("24 pokladů");
        radiobutton.setSelected(true);
        radiobutton.setActionCommand("24");
        group1.add(radiobutton);
        submenu.add(radiobutton);
       submenu.addSeparator();
        ButtonGroup group=new ButtonGroup();
        
        radiobutton=new JRadioButton("velikost 5");
        radiobutton.setActionCommand("5");
        group.add(radiobutton);
        submenu.add(radiobutton);
        radiobutton=new JRadioButton("velikost 7");
        radiobutton.setSelected(true);
 
        radiobutton.setActionCommand("7");
        group.add(radiobutton);
        submenu.add(radiobutton);
        radiobutton=new JRadioButton("velikost 9");
   
        radiobutton.setActionCommand("9");
    
        group.add(radiobutton);
        submenu.add(radiobutton);
         radiobutton=new JRadioButton("velikost 11");
     
         radiobutton.setActionCommand("11");
     
        group.add(radiobutton);
        submenu.add(radiobutton);
       
     
         submenu.addSeparator();
       menuItem = new JMenuItem("2 hraci");
         menuItem.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                 GameAppFrame.this.game=null;
                 GameAppFrame.this.gp=null;
                 System.gc();
             
                 
                 
          int j = Integer.parseInt(group1.getSelection().getActionCommand());
                int i = Integer.parseInt(group.getSelection().getActionCommand());
              JFieldTextDemo k=new JFieldTextDemo(2,i,0,j);
                    //GameAppFrame.this.newAppFrame(Game.NewPokus(2,i)) ;
               
    
             
            }
            
        });
         
         submenu.add(menuItem);
         menuItem = new JMenuItem("3 hraci");
         menuItem.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                 GameAppFrame.this.game=null;
                 GameAppFrame.this.gp=null;
                 System.gc();
            int j = Integer.parseInt(group1.getSelection().getActionCommand());
                  int i = Integer.parseInt(group.getSelection().getActionCommand());
              JFieldTextDemo k=new JFieldTextDemo(3,i,0,j);
    
             
            }
            
        });
         
         submenu.add(menuItem);
         menuItem = new JMenuItem("4 hraci");
         menuItem.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
               
                 GameAppFrame.this.game=null;
                 GameAppFrame.this.gp=null;
                 System.gc();
                // System.gc();
                 int j = Integer.parseInt(group1.getSelection().getActionCommand());
              int i = Integer.parseInt(group.getSelection().getActionCommand());
         
              JFieldTextDemo k=new JFieldTextDemo(4,i,0,j);
    
             
            }
            
        });
         
         submenu.add(menuItem);
        menu.addSeparator();
        menuItem = new JMenuItem("Exit");
       
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            
                frame.setVisible(false);
                System.exit(0);
            }
            
        });
        menu.add(menuItem);
         menu.add(submenu);
        menu.addSeparator();
        menuItem = new JMenuItem("Save Game");
       
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if (GameAppFrame.this.game!=null){
                try {
                   
                  // ByteArrayInputStream bais = new ByteArrayInputStream(GameAppFrame.this.byteData);
                 //  Game savegame = null;
                  // savegame = (Game) new ObjectInputStream(bais).readObject();
                   GameAppFrame.save.game=GameAppFrame.this.game;
                   Main.serializaceDataOut(GameAppFrame.save);
                } catch (IOException ex) {
                    Logger.getLogger(GameAppFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
            }
            
        });
        menu.add(menuItem);
         menu.add(menuItem);
        menu.addSeparator();
        menuItem = new JMenuItem("Load Game");
       
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                   GameAppFrame.this.game=null;
                    GameAppFrame.this.gp=null;
                    System.gc();
                    GameSave S=Main.serializeDataIn();
                    GameAppFrame.this.newAppFrame(S.game);
                    
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(GameAppFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        menu.add(menuItem);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);
    }
 
   private class figurKeyEvent extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
             
            switch (e.getKeyCode()){
                case 10:                   
                     
                     
                    game.changePlayer();
                    
                    MazeCard carda=game.getBoard().get(game.getFigur().x,game.getFigur().y).getCard();
                    if (game.getFigur().treasure.equals(carda.getTreasure())){
               
                            game.getFigur().AddObtainedTreasure(carda.getTreasure());
                            carda.putTreasure(null);
                            GameAppFrame.this.gp.Gdeska.getField(game.getFigur().x-1,game.getFigur().y-1).setImage(carda);
                             game.changePlayer();
                          
                             frame.repaint();
                       }
             
                    
                break;
                case 37:
                  
                    if (game.getFigur().move(MazeCard.CANGO.LEFT)){
                   
                       game.setFaze(Game.fazeofgame.TURN);                      
                       MazeCard card=game.getBoard().get(game.getFigur().x,game.getFigur().y).getCard();
                       if (game.getFigur().treasure.equals(card.getTreasure())){
                            game.getFigur().AddObtainedTreasure(game.getFigur().treasure);
                             
                            game.getBoard().get(game.getFigur().x,game.getFigur().y).getCard().putTreasure(null);
                          
                          GameAppFrame.this.gp.Gdeska.getField(game.getFigur().x-1,game.getFigur().y-1).setImage(card);
                            game.changePlayer();                      
                            frame.repaint();
                       }
                    }
                  
                break;
                case 38:
                     
                    if (game.getFigur().move(MazeCard.CANGO.UP)){
                        
                        game.setFaze(Game.fazeofgame.TURN);
                         MazeCard card=game.getBoard().get(game.getFigur().x,game.getFigur().y).getCard();
                        if (game.getFigur().treasure.equals(card.getTreasure())){
                             game.getFigur().AddObtainedTreasure(game.getFigur().treasure);
                             
                            game.getBoard().get(game.getFigur().x,game.getFigur().y).getCard().putTreasure(null);
                          
                          GameAppFrame.this.gp.Gdeska.getField(game.getFigur().x-1,game.getFigur().y-1).setImage(card);
                            game.changePlayer();                      
                            frame.repaint();
                        }
                        }
                
                break;
                case 39:
                    
                    if (game.getFigur().move(MazeCard.CANGO.RIGHT)){
                       
                        game.setFaze(Game.fazeofgame.TURN);
                         MazeCard card=game.getBoard().get(game.getFigur().x,game.getFigur().y).getCard();
                        if (game.getFigur().treasure.equals(card.getTreasure())){
                             game.getFigur().AddObtainedTreasure(game.getFigur().treasure);
                         
                           
                            game.getBoard().get(game.getFigur().x,game.getFigur().y).getCard().putTreasure(null);
                     
                           GameAppFrame.this.gp.Gdeska.getField(game.getFigur().x-1,game.getFigur().y-1).setImage(card);
                             game.changePlayer();
                             
                
                    frame.repaint();
                        }
                    }
                  
                break;
                case 40:
                    
                    if (game.getFigur().move(MazeCard.CANGO.DOWN)){
                      
                        game.setFaze(Game.fazeofgame.TURN);
                         MazeCard card=game.getBoard().get(game.getFigur().x,game.getFigur().y).getCard();
                        if (game.getFigur().treasure.equals(card.getTreasure())){
                             game.getFigur().AddObtainedTreasure(game.getFigur().treasure);
                         
                            game.getBoard().get(game.getFigur().x,game.getFigur().y).getCard().putTreasure(null);  
                         
                            GameAppFrame.this.gp.Gdeska.getField(game.getFigur().x-1,game.getFigur().y-1).setImage(card);
                             game.changePlayer();
                             
                             frame.repaint();
                        }
                    }
               
                break;
            }
        }
    }
   
}
