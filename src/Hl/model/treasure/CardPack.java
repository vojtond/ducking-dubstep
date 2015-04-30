/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hl.model.treasure;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author xbastl03
 * Každý objekt reprezentuje balíček karet <code>TreasureCard</code>
 */

public class CardPack implements Serializable{
    public ArrayList<TreasureCard> PackOfCard=new ArrayList<TreasureCard>();
   
    private static int maxSizePack;
 
    /**
    * vytvori balicek karet 
     * @param maxSize maximální počet karet
     * @param initSize počáteční počet karet
    */
    public CardPack(int maxSize,int initSize){
        if (maxSize>=initSize){
            maxSizePack=maxSize;
                       
            for (int i=0;i<initSize;i++){  /*dokud neni initSize karet*/             
               TreasureCard TreasureIn=new TreasureCard(Treasure.getTreasure(i));
               PackOfCard.add(TreasureIn);               
            }
        }
    }
    /**
     * vybere vrchni kartu z balicku
     * @return vrací vrchní kartu z balíčku
     * @see TreasureCard
     */
    public TreasureCard popCard(){
        TreasureCard pom;
        pom=null;
       
        if (PackOfCard.size()>0){/*pokud jeste existuje karta v balicku*/
      
            pom=PackOfCard.get(0);    
            PackOfCard.remove(0);
            
        }
        return pom;
    }
    /**
     * vrati pocet karet v balicku
     * @return počet karet v balíčku
     */
    public int size(){        
        return PackOfCard.size();       
    }
    /**
     * zanicha balicek
     */
    public void shuffle(){
      Collections.shuffle(PackOfCard);
    }
}
