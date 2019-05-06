import java.util.HashMap;
import java.util.Set;
public class ItemList
{
    public HashMap<String,Item> aItemListHM;
    private int aPoids;
    
    
    public ItemList (){
        this.aItemListHM = new HashMap<String,Item> ();
        this.aPoids = 0;
    }
    
    public void add (final Item pItem , final String pDescription){
        this.aItemListHM.put(pDescription,pItem);
    }
    
    public void remove (final Item pItem , final String pDescription){
        this.aItemListHM.remove(pDescription , pItem);
    }
    
    public String inventory(){
         return this.getItemList() + "\n" + this.showPoids();
                
    }
    
    /**
     * Renvoie une string qui correspond a tous les items presents dans la room
     * @return String String qui correspond au nom de ous les items presents dans la room
     */
    public String getItemList(){
        if (this.aItemListHM.isEmpty()){
            return "Votre inventaire est vide.";
        }
        StringBuilder vReturnString = new StringBuilder( "Votre inventaire : " );
        Set<String> keys = aItemListHM.keySet();
        for ( String vS : keys )vReturnString.append( " " + vS );
        return vReturnString.toString();
    }
    
    public ItemList getList(){
        return this;
    }
    
    public void addPoids ( final int pPoids){
        this.aPoids += pPoids;
    }
    
    public void rmPoids ( final int pPoids){
        this.aPoids-= pPoids;
    }
    
    public int getPoids(){
        return this.aPoids;
    }
    
    public String showPoids(){
        String vPoids = "Le poids de votre inventaire est actuellement de : " + this.getPoids() + "\n" + "Le poids maximum est de 10";
        return vPoids;
    }
}