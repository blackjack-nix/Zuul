import java.util.HashMap;
import java.util.Stack;


public class Player
{
    private Room aCurrentRoom;
    private String aNom;
    private UserInterface aGui;
    private Stack<Room> aStackRoom;
    private ItemList aInventaire;
    private int aPoidsMax = 15;
    /**
     * constructeur de Player
     * @param String nom du joueur
     * @param Room room de départ
     */

    public Player (final String pNom , final Room pCurrentRoom ){
        this.aCurrentRoom = pCurrentRoom;
        this.aNom = pNom;
        aStackRoom = new Stack<Room>();
        this.aInventaire = new ItemList();
    }

    /**
     * Constructeur de gui
     * @param UserInterface pGui
     */
    public void setGui(final UserInterface pUserInterface)
    {
        this.aGui = pUserInterface; 
    }

    public ItemList getItemList(){
        return this.aInventaire;
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
     * Affiche une description du lieu courant
     */
    public void look(){
        printLocationInfo();
    }

    /**
     * manger, pas utile dans ce jeu mais demandé
     */
    public void eat( final Command pCommand){
        String vDescription = pCommand.getSecondWord();
        Item vItem = this.aInventaire.aItemListHM.get(vDescription);
        if (vItem == null) {
            aGui.println("Eat quoi ?");
            return;
        }
        if ( pCommand.getSecondWord().equals("MagicCookie") && this.aInventaire.aItemListHM.containsValue(vItem)){
            this.aPoidsMax = this.aPoidsMax * 2;
            aGui.println("Vous avez mangé un cookie magique. Votre inventaire vient de doubler !");
            aGui.println(this.aInventaire.showPoids() + "\n Le poids maximal est de " + this.aPoidsMax);
            this.aInventaire.remove(vItem, vDescription);
            this.aInventaire.rmPoids(10);
        } else this.aGui.println("Vous ne pouvez pas manger cela !");          
    }

    /**
     * Print infos about wher you are and the exits available
     */
    private void printLocationInfo(){
        aGui.println(aCurrentRoom.getLongDescription());
    }//printLocationInfo()

    /**
     * retourne le nom du jour actuel
     * @return String nom
     */
    public String getName(){
        return this.aNom;
    }

    /**
     * Permet de revenir en arriere au moyen d'une stack
     */

    public void back(){
        
            
            this.aStackRoom.removeAllElements();
            aGui.println("Cette sortie est piégée ! Sortez d'une autre manière");
            
        
        if (! this.aStackRoom.empty()){
            this.aCurrentRoom = this.aStackRoom.pop();
            changeRoom(aCurrentRoom);
        } else aGui.println("Vous ne pouvez pas aller en arrière");
    }

    /**
     * Permet de retourner la stack de room
     * @return Stack room
     */
    public Stack<Room> getStackRoom(){
        return this.aStackRoom;
    }

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
     * Print the welcome tips
     */
    public void printWelcome(){
        aGui.println("Player : " + this.getName());
        aGui.println("Bienvenue dans le jeu d'aventure StarWars !");
        aGui.println("Ta formation est bientôt terminé jeune Padawan. Récpuère les objets nécéssaires à la création de ton sabre lasert pour enfin devenir un vrai Jedi et finir ce jeu");
        aGui.println("Tappe help si tu as besoin d'aide !");
        this.aGui.println(this.aInventaire.getItemList());
        this.aGui.println(aCurrentRoom.getLongDescription());
        aGui.showImage(aCurrentRoom.getImageName());
    }//printWelcome() 

    public int getPoidsMax(){
        return this.aPoidsMax;
    }
    
    public void take (final Command pCommand){
        String vDescription = pCommand.getSecondWord();
        Item vItem = this.aCurrentRoom.aItemHM.get(vDescription);
        if ( vItem == null ) {
            aGui.println("Cet objet n'est pas la !");
            return;
        }
        if ( this.aInventaire.getPoids()+vItem.getPoids() > this.aPoidsMax){
            this.aGui.println("Votre inventaire est plein. Il faut lacher des objets pour s'alléger.");            
        }
        else {
            this.aInventaire.add(vItem,vDescription);
            this.aCurrentRoom.aItemHM.remove(vDescription);            
            aGui.println("Objets présents dans la pièce : "+ this.aCurrentRoom.getItemName());
            this.aInventaire.addPoids(vItem.getPoids());
        }
        this.aGui.println(this.aInventaire.getItemList());
        this.aGui.println(this.aCurrentRoom.getLongDescription());
    }

    public void drop (final Command pCommand){
        String vDescription = pCommand.getSecondWord();
        Item vItem = this.aInventaire.aItemListHM.get(vDescription);
        if ( vItem == null) aGui.println("Vous ne portez pas cet objet");
        else {
            this.aInventaire.remove(vItem, vDescription);
            this.aCurrentRoom.addItem(vDescription, vItem);         
            this.aInventaire.rmPoids(vItem.getPoids());
        }
        this.aGui.println(this.aInventaire.getItemList());
        this.aGui.println(this.aCurrentRoom.getLongDescription());
    }

}