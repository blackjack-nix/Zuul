import java.util.HashMap;
/**
 * Class for the PNJ of the project Zuul-Wars
 * @author Théo Péresse
 * @version finale
 * Available on GitHub
 */ 
public class PNJ
{
    private String aNom;
    private Room aRoom;
    private Item aItem;
    
    /**
     * Constructeur naturel
     * @param pNom String
     * @param pItem Item
     * @param pRoom Room
     */
    public PNJ(final String pNom , final Item pItem , final Room pRoom){
        this.aNom = pNom;
        this.aItem = pItem;
        this.aRoom = pRoom;
    }
    
    /**
     * @return le nom
     */
    public String getNom(){
        return this.aNom;
    }
    
    /**
     * @return room du PNJ
     */
    public Room getRoom(){
        return this.aRoom;
    }
    
    /**
     * @return item porté par le pNJ
     */
    public Item getItem(){
        return this.aItem;
    }
}
