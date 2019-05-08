import java.util.HashMap;
import java.util.Stack;

/**
 * Class for the Player of the project Zuul-Wars
 * @autor Théo Péresse
 * @version finale
 * Available on GitHub
 */
public class Player
{
    // ## Attributs ##
    private Room aCurrentRoom;
    private String aNom;
    private UserInterface aGui;
    private Stack<Room> aStackRoom;
    private ItemList aInventaire;
    private int aPoidsMax = 15;

    // ## Constructeur(s)
    /**
     * constructeur de Player
     * @param String nom du joueur
     * @param Room room de départ
     */

    public Player (final String pNom , final Room pCurrentRoom ){
        this.aCurrentRoom = pCurrentRoom;
        this.aNom = pNom;
        this.aStackRoom = new Stack<Room>();
        this.aInventaire = new ItemList();
    }

    // ## Initialisation ##
    /**
     * Constructeur de gui
     * @param UserInterface pGui
     */
    public void setGui(final UserInterface pUserInterface)
    {
        this.aGui = pUserInterface; 
    }

    // ## Accesseurs (get) ##
    /**
     * Accesseur de l'inventaire
     * @return aInventaire
     */
    public ItemList getInventaire(){
        return this.aInventaire;
    }

    /**
     * Accesseur du poids max
     * @return aPoidsMax
     */
    public int getPoidsMax(){
        return this.aPoidsMax;
    }

    /**
     * retourne le nom du jour actuel
     * @return String nom
     */
    public String getName(){
        return this.aNom;
    }

    /**
     * retourne la room actuelle
     * @return Room aCurrentRoom
     */
    public Room getCurrentRoom()
    {
        return this.aCurrentRoom;
    }

    /**
     * Permet de retourner la stack de room
     * @return Stack room
     */
    public Stack<Room> getStackRoom(){
        return this.aStackRoom;
    }

    // Méthodes principales ##
    /**
     * Change de room
     * @param Room la room ou aller
     */

    public void changeRoom(final Room pRoom)
    {
        this.aCurrentRoom = pRoom; 
        this.aGui.println(this.aCurrentRoom.getLongDescription());
        if(this.aCurrentRoom.getImageName() != null)
            this.aGui.showImage(this.aCurrentRoom.getImageName());  
    }

    /**
     * Permet de prendre des objets disponible dans une piece dans son inventaire
     * @param pCommand
     */
    public void take (final Command pCommand){
        String vDescription = pCommand.getSecondWord();
        Item vItem = this.aCurrentRoom.getItemHM(vDescription);
        if ( vItem == null ) {
            this.aGui.println("Cet objet n'est pas la !");
            return;
        }
        if ( this.aInventaire.getPoids()+vItem.getPoids() > this.aPoidsMax){
            this.aGui.println("Votre inventaire est plein. Il faut lacher des objets pour s'alléger.");            
        }
        else {
            this.aInventaire.add(vItem,vDescription);
            this.aCurrentRoom.rmItemHM(vDescription);            
            this.aGui.println("Objets présents dans la pièce : "+ this.aCurrentRoom.getItemString());
            this.aInventaire.addPoids(vItem.getPoids());
        }
        this.aGui.println(this.aInventaire.getItemList());
        this.aGui.println(this.aCurrentRoom.getLongDescription());
    }

    /**
     * Permet de lacher des objets qui sont dans l'inventaire dans la pièce courrante
     * @param pCommand
     */
    public void drop (final Command pCommand){
        String vDescription = pCommand.getSecondWord();
        Item vItem = this.aInventaire.getItem(vDescription);
        if ( vItem == null) this.aGui.println("Vous ne portez pas cet objet");
        else {
            this.aInventaire.remove(vItem, vDescription);
            this.aCurrentRoom.addItem(vDescription, vItem);         
            this.aInventaire.rmPoids(vItem.getPoids());
        }
        this.aGui.println(this.aInventaire.getItemList());
        this.aGui.println(this.aCurrentRoom.getLongDescription());
    }

    /**
     * Permet de manger un objet (uniquement le magicCookie)
     * @param pCommand
     */
    public void eat( final Command pCommand){
        String vDescription = pCommand.getSecondWord();
        Item vItem = this.aInventaire.getItem(vDescription);
        if (vItem == null) {
            this.aGui.println("Eat quoi ?");
            return;
        }
        if ( vDescription.equals("MagicCookie") && this.aInventaire.getItemListHM().containsValue(vItem)){
            this.aPoidsMax = this.aPoidsMax * 2;
            this.aGui.println("Vous avez mangé un cookie magique. Votre inventaire vient de doubler !");
            this.aGui.println(this.aInventaire.showPoids() + "\n Le poids maximal est de " + this.aPoidsMax);
            this.aInventaire.remove(vItem, vDescription);
            this.aInventaire.rmPoids(vItem.getPoids());
        } else this.aGui.println("Vous ne pouvez pas manger cela !");          
    }

    /**
     * Permet de revenir en arriere au moyen d'une stack de room
     */

    public void back(){
        
        if (! this.aStackRoom.empty()){
            this.aCurrentRoom = this.aStackRoom.pop();
            this.changeRoom(this.aCurrentRoom);
        } else this.aGui.println("Vous ne pouvez pas aller en arrière");
    }

    /**
     * permet de clear la stack dans goroom si trap door
     */
    public void clearStack(){
        this.aStackRoom.clear();      
       
    }

    // ## Méthodes d'affichage ##
    /**
     * Affiche une description du lieu courant
     */
    public void look(){
        this.printLocationInfo();
    }

    /**
     * Print infos about wher you are and the exits available
     */
    private void printLocationInfo(){
        this.aGui.println(this.aCurrentRoom.getLongDescription());
    }//printLocationInfo()

}