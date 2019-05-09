// ## Import ##
import java.util.HashMap;

/**
 * Class for the Items of the project Zuul-Wars
 * @author Théo Péresse
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
     * @param pNom nom de l'objet
     * @param pPoids Poind de l'objet
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
     * @return int le poid de l'objet passé en parametre
     */
    public int getPoids (){
        return this.aPoids;
    }//accesseur poids
    
    
}
