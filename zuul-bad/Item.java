import java.util.*;
public class Item
{
    private String aDescription;
    private int aPoids;
    private HashMap<Room, Item> aItemHM;
    
    public Item (final String pDescription , final int pPoids){
        this.aDescription = pDescription;
        this.aPoids = pPoids;        
    }//constructor
    
    public void addItem ( final Room pRoom ){
        aItemHM.put(pRoom,this);
    }
    
    public String getDescription (){
        return this.aDescription;
    }//accesseur description
    
    public int getPoids (final Item pItem){
        return this.aPoids;
    }//accesseur poids
    
    public String getItemDescription(){
        StringBuilder vReturnString = new StringBuilder( "" );
        for ( Room vR : aItemHM.keySet() )
            vReturnString.append( " " + vR );
        return vReturnString.toString();
    }
    
    
}
