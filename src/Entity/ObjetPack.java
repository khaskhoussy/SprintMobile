/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author cherif
 */
public class ObjetPack {
    
    private int qte ;
    private PackDecoration packDecoration ;

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public PackDecoration getPackDecoration() {
        return packDecoration;
    }

    public void setPackDecoration(PackDecoration packDecoration) {
        this.packDecoration = packDecoration;
    }

    @Override
    public String toString() {
        return "ObjetPack{" + "qte=" + qte + ", packDecoration=" + packDecoration + '}';
    }
    
    
    
}