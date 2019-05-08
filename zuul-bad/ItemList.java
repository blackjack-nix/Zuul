// ## Imports ##
import java.util.HashMap;
import java.util.Set;

/**
 * Class for the Inventory of the project Zuul-Wars
 * @autor Théo Péresse
 * @version finale
 * Available on GitHub
 */ 

public class ItemList
{
    // ## Attributs ##
    private HashMap<String,Item> aItemListHM;
    private int aPoids;

    // ## Constructeur ##

    /**
     * Constructeur de la classe ItemList
     */
    public ItemList (){
        this.aItemListHM = new HashMap<String,Item> ();
        this.aPoids = 0;
    }//constructor()

    // ## Accesseurs ##
    /**
     * Renvoi l'item correspondant à la string
     * @param pDescription String qui correspond à la description de l'objet
     * @return Item qui correspond à la string
     */
    public Item getItem (final String pDescription){
        return this.aItemListHM.get(pDescription);
    }

    /**
     * Renvoie la HashMap complete des items de l'inventaire
     * @retrun HashMap de l'inventaire
     */
    public HashMap getItemListHM (){
        return this.aItemListHM;
    }

    /**
     * Renvoie une string qui correspond a tous les items presents dans la room
     * @return String String qui correspond au nom de tous les items presents dans la room
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

    /**
     * renvoi l'inventaire complet
     * @return ItemList inventaire
     */
    public ItemList getList(){
        return this;
    }

    /**
     * renvoie le poids total de l'inventaire
     * @return Poids
     */
    public int getPoids(){
        return this.aPoids;
    }

    // ## Modificateur ##
    /**
     * permet d'jouter un item à la hashmap
     * @param pItem Item à ajouter
     * @param pDescription Description de l'item
     */
    public void add (final Item pItem , final String pDescription){
        this.aItemListHM.put(pDescription,pItem);
    }

    /**
     * permet d'enlever des items de l'invetaire
     * @param pItem L'item à enlever
     * @param pDescription Description de l'item à enlever
     */
    public void remove (final Item pItem , final String pDescription){
        this.aItemListHM.remove(pDescription , pItem);
    }

    /**
     * Permet d'ajouter du poids à l'inventaire
     * @param pPoids ajoute le poids à l'inventaire
     */
    public void addPoids ( final int pPoids){
        this.aPoids += pPoids;
    }

    /**
     * eneleve du poids à l'inventaire
     * @param pPoids eleve le poids à l'inventaire
     */
    public void rmPoids ( final int pPoids){
        this.aPoids-= pPoids;
    }

    // ## Affichage ##
    /**
     * permet d'afficher l'inventaire et le poids
     * @return La string à afficher par la suite
     */
    public String inventory(){
        return this.getItemList() + "\n" + this.showPoids();      
    }

    /**
     * permet d'afficher le poids total de l'inventaire
     * @return String à afficher
     */
    public String showPoids(){
        String vPoids = "Le poids de votre inventaire est actuellement de : " + this.getPoids();
        return vPoids;
    }
}