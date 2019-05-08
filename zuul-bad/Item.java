// ## Import ##
import java.util.HashMap;

/**
 * Class for the Items of the project Zuul-Wars
 * @autor Théo Péresse
 * @version finale
 * Available on GitHub
 */ 

public class Item
{
    // ## Attributs ##
    private String aNom;
    private int aPoids;
    private HashMap<Room, Item> aItemHM;
    
    // ## Constructeur ##
    
    /**
     * Constructeur d'item
     * @param String nom de l'objet
     * @param int Poind de l'objet
     */
    public Item (final String pNom ,  final int pPoids){
        this.aPoids = pPoids;     
        this.aNom = pNom;
    }//constructor(..)
    
    
    // ## Acesseurs ##
    
    public Item getObjet (final String pObjetDescription){
        return this.aItemHM.get(pObjetDescription);
    }//getObjet(.)
    
    /**
     * renvoie le nom de l'objet
     * @return String Le nom de l'objet
     */
    public String getNom (){
        return this.aNom;
    } //getNom()
    
    /**
     * renvoie le poid de l'objet passé en parametre
     * @param Item Item dont on veut connaitre le poid
     * @return int le poid de l'objet passé en parametre
     */
    public int getPoids (){
        return this.aPoids;
    }//accesseur poids
    
    
    // ## Modificateur ##
    
    /**
     * Ajoute un item dans la piece passé en parametre
     * @param Room la room dans laquelle on veut placer l'objet
     */
    public void addItem ( final Room pRoom ){
        aItemHM.put(pRoom, this);
    }//addItem()
    
    /**
     * Permet de supprimer un objet de la piece
     * @param String de la description de l'objet
     */
    public void removeItem (final String pDescrition){
        this.aItemHM.remove(pDescrition);
    }//removeItem(.)
}
