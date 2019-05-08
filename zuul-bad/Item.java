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
    
    
}
