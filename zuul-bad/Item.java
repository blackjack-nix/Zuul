import java.util.*;
public class Item
{
    private String aDescription;
    private String aNom;
    private int aPoids;
    private HashMap<Room, Item> aItemHM;
    
    public Item (final String pNom ,  final int pPoids , final String pDescription){
        this.aDescription = pDescription;
        this.aPoids = pPoids;     
        this.aNom = pNom;
    }//constructor
    
    public void addItem ( final Room pRoom ){
        aItemHM.put(pRoom,this);
    }//addItem()
    
    public String getDescription (){
        return this.aDescription;
    }//accesseur description
    
    public String getNom (){
        return this.aNom;
    } //getNom()
    
    public int getPoids (final Item pItem){
        return this.aPoids;
    }//accesseur poids
    
    
    
    
}
