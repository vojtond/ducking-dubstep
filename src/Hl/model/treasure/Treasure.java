/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hl.model.treasure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 *
 * @author xbastl03
 * Každý objekt je reprezentací pokladu
 */
public class Treasure implements Serializable{
    final public int code;
     private int Gcode;
    static Treasure[] poklady; 
   
    static int pocet=24;     
    private Treasure(int code){
        this.code=code;   
    }
    /**
     * Vytvori poklady
     */
    public static void createSet(){
          List<Integer> randomTreasureImage=new ArrayList<>(); 
        for (int i=1;i<49;i++){
            randomTreasureImage.add(i);
        }
        poklady=new Treasure[pocet];   
        Random random=new Random();
        int index=0;
        for (int i=0;i<pocet;i++){   
           poklady[i]=new Treasure(i);
           index=random.nextInt(randomTreasureImage.size());
           poklady[i].Gcode=randomTreasureImage.get(index);
           randomTreasureImage.remove(index);
        }
    }
    /**
     * Vratí poklad s kodem <code>code</code>
     * @param code jednoznačný identifikátor pokladu
     * @return  poklad s identifikátorem <code>code</code>
     */
    public static Treasure getTreasure(int code){
        if (code<pocet){
            return poklady[code];
        }else return null;
    }

    /**
     *vrátí jaký obrázek pokladu reprezentuje poklad
     * @return číslo obrzáku pokladu
     */
    public int getGCode(){
        return this.Gcode;
    }
}
