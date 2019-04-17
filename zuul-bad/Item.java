import java.util.*;
public class Item
{
    private String aDescription;
    private String aNom;
    private int aPoids;
    private HashMap<Room, Item> aItemHM;
    
    /**
     * Constructeur d'item
     * @param String nom de l'objet
     * @param int Poind de l'objet
     * @param String desciption de l'objet
     */
    public Item (final String pNom ,  final int pPoids , final String pDescription){
        this.aDescription = pDescription;
        this.aPoids = pPoids;     
        this.aNom = pNom;
    }//constructor
    
    /**
     * Ajoute un item dans la piece passé en parametre
     * @param Room la room dans laquelle on veut placer l'objet
     */
    public void addItem ( final Room pRoom ){
        aItemHM.put(pRoom,this);
    }//addItem()
    
   
    /**
     * renvoie une description de l'objet
     * @return String Une description de l'objet
     */
    public String getDescription (){
        return this.aDescription;
    }//accesseur description()
    
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
    public int getPoids (final Item pItem){
        return this.aPoids;
    }//accesseur poids
}
