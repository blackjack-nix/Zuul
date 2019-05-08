/**
 * Class for the Room of the project Zuul-Wars
 * @autor Théo Péresse
 * @version finale
 * Available on GitHub
 */    

import java.util.HashMap; 
import java.util.Set;
import java.util.Iterator;

public class Room
{
    // ## Attributs ##
    private HashMap <String , Room>  aSortieHM;
    private HashMap <String , Item> aItemHM;
    private HashMap<String , Door> aDoorHM;
    private HashMap<String , PNJ> aPNJHM;
    private String aDescription;
    private String aImageName;

    // ## Constructeur(s) ##
    /**
     * Create rooms with a string description and create a HashMap of the exits for each
     * @param pDescription String qui est la description de la room
     * @param pImage String qui est le chemin vers l'image
     */
    public Room (final String pDescription , final String pImage){
        this.aDescription = pDescription ;
        this.aDoorHM = new HashMap<String,Door>();
        this.aSortieHM = new HashMap <String , Room> ();
        this.aItemHM = new HashMap <String, Item>();
        this.aPNJHM = new HashMap <String, PNJ>();
        this.aImageName = pImage;
    }//Room(.)

    // ## Accesseurs (get) ##
    /**
     * Renvoi les doors selon la direction passée en parmatre
     * @param pDirection String qui représente la direction
     * @return Door qui correspond à la direction
     */
    public Door getDoor(final String pDirection){
        return this.aDoorHM.get(pDirection);
    }

    /**
     * Rnvoi l'item correspondant à la string
     * @param vDescription String qui correspond à la description de l'objet
     * @return Item correspondant à la description passée en parametre
     */
    public Item getItemHM (final String vDescription){
        return this.aItemHM.get(vDescription);
    }

    /**
     * return a string of the room description
     * @return  Return a String description of the room
     */
    public String getDescription (){
        return this.aDescription;
    }//getDescription(.)

    /**
     * return la HashMap avec les sorties
     * @return HashMap de sortie/room
     */
    public HashMap getRooms(){
        return this.aSortieHM;
    }

    /**
     * return les items présents dans la salle
     * @return Item présents
     */
    public Item getItems(){
        return this.aItemHM.get(this);
    }

    public PNJ getPNJ(final String pDescription){
        return this.aPNJHM.get(pDescription);
    }
    
    /**
     * Return the exit of the room
     * @return Return the room after taking the direction
     * @param pDirection Direction you want to take in the room
     */
    public Room getExit(final String pDirection){
        return this.aSortieHM.get(pDirection);
    }//getExit(.)

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return String des sorties de la room
     */
    public String getExitString()
    {
        StringBuilder vReturnString = new StringBuilder( "" );
        for ( String vS : aSortieHM.keySet() )vReturnString.append( " " + vS );
        return vReturnString.toString();
    }
    
    /**
     * Renvoie une string qui correspond a tous les items presents dans la room
     * @return String String qui correspond au nom de ous les items presents dans la room
     */
    public String getItemString(){
        StringBuilder vReturnString = new StringBuilder( "" );
        for ( String vS : aItemHM.keySet() )vReturnString.append( " " + vS );
        return vReturnString.toString();
    }

    public String getPNJString(){
        StringBuilder vReturnString = new StringBuilder("");
        for (String vS : aPNJHM.keySet() ) vReturnString.append( " " + vS);
        return vReturnString.toString();        
    }
    
    /**
     * Renvoie une description détillée de cette piece sous la forme :
     *      vous etes dans + piece
     *      sorties : 
     * @return Une description de la piece avec les sorties
     */
    public String getLongDescription(){
        String vS = "\n Vous etes "+this.aDescription + "\n";
        vS += "Sorties disponibles : " + this.getExitString() + "\n";
        vS += "Objet disponible dans cette salle : " + this.getItemString() + "\n";
        vS += "Personnes présentes : " + this.getPNJString() + "\n";
        return  vS;
    }

    /**
     * Return the description of the room (the one that was defined in the
     * constructor).
     * @return String short description of the room
     */
    public String getShortDescription()
    {
        return this.aDescription;
    }

    /**
     * Renvoie le chemin de la room
     * @return String Chemin de l'image
     */
    public String getImageName(){
        return this.aImageName;
    }

    /**
     * Renvoie la HashMap d'items
     * @return HashMap d'item
     */
    public HashMap getItemHM(){
        return this.aItemHM;
    }

    
    // ## Modificateur / initialisation ##
    
    /**
     * Permet d'jouter des Doors sur certaines pices et direction
     * @param pDirection qui correspond à la direction par rapport à la piece actuelle
     * @param pDoor Door locked ou trap
     */
    public void addDoor (final String pDirection, final Door pDoor){
        this.aDoorHM.put(pDirection,pDoor);
    }

    /**
     * change the exit of a room
     * @param pDirection A String for the direection you want to go
     * @param pRoom Change the room
     */
    public void setExits (final String pDirection, final Room pRoom){
        aSortieHM.put(pDirection,pRoom);
    }//setExit(....)

    /**
     * Permet d'ajouter un objet a la room
     * @param String description de l'objet
     * @param Item item à ajouter
     */
    public void addItem (final String pDescription , final Item pItem){
        this.aItemHM.put(pDescription , pItem);  
    }

    /**
     * Permet de supprimer des Items de la HashMap deffinitivement
     * @param pDescription de l'objet à enlever
     */
    public void rmItemHM (final String pDescription){
        this.aItemHM.remove(pDescription);
    }

    public void addPNJ (final String pNom , final PNJ pPNJ){
        this.aPNJHM.put(pNom,pPNJ);
    }
} // Room
