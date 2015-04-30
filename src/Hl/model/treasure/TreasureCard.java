/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hl.model.treasure;

import java.io.Serializable;
import java.util.Objects;

/**
 *každý objekt reprezentuje Kartu na které se nachází poklad
 * @author xbastl03
 */
public class TreasureCard implements Serializable {    
    public Treasure TreasureOnCard;   
    /**
     * konstruktor jedne karty
     * @param tr poklad který se má nacházet na kartě
     * @see Treasure
     */
    public TreasureCard(Treasure tr){
        TreasureOnCard=tr;
    }   
    /**
     * override metody equals
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TreasureCard other = (TreasureCard) obj;
        return Objects.equals(this.TreasureOnCard, other.TreasureOnCard);
    }
    /**
     * Override metody hashCode
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.TreasureOnCard);
        return hash;
    }
    
}
