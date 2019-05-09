import java.util.HashMap;
import java.util.Stack;

/**
 * Class for the Player of the project Zuul-Wars
 * @author Théo Péresse
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
    private int aPoidsMax = 10;
    private boolean aParleJedi;
    private boolean aParleGarde;
    private int aTimer;

    // ## Constructeur(s)
    /**
     * constructeur de Player
     * @param pNom nom du joueur
     * @param pCurrentRoom room de départ
     */

    public Player (final String pNom , final Room pCurrentRoom ){
        this.aCurrentRoom = pCurrentRoom;
        this.aNom = pNom;
        this.aStackRoom = new Stack<Room>();
        this.aInventaire = new ItemList();
        this.aParleJedi = false;
        this.aParleGarde = false;
        this.aTimer = 15;
    }

    // ## Initialisation ##
    /**
     * Constructeur de gui
     * @param pUserInterface pGui
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
     * @param pRoom la room ou aller
     */

    public void changeRoom(final Room pRoom)
    {
        this.aCurrentRoom = pRoom; 
        this.aGui.println(this.aCurrentRoom.getLongDescription());
        if(this.aCurrentRoom.getImageName() != null)
            this.aGui.showImage(this.aCurrentRoom.getImageName());  
        if (this.aTimer == 0){
            this.aGui.println("Vous vous etes trop déplacés, vous avez perdu \n \n");
            this.aGui.enable(false);
        } else {
            this.aTimer -= 1;
            this.aGui.println("Nombre de coups réstant : " + this.aTimer);
        }
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
        this.aGui.println(this.aInventaire.inventory());
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
        this.aGui.println(this.aInventaire.inventory());
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
        }
        if ( vDescription.equals("MagicCookie") && this.aInventaire.getItemListHM().containsValue(vItem)){
            this.aPoidsMax = this.aPoidsMax * 2;
            this.aGui.println("Vous avez mangé un cookie magique. Votre inventaire vient de doubler !");
            this.aInventaire.remove(vItem, vDescription);
            this.aInventaire.rmPoids(vItem.getPoids());
            this.aGui.println(this.aInventaire.inventory());
        } else this.aGui.println("Vous ne pouvez pas manger cela !");          
    }

    /**
     * permet de parler avec un PNJ
     * @param pCommand la commande qui contient el nom du PNJ
     */
    public void parler (final Command pCommand){
        String vS = pCommand.getSecondWord();
        PNJ vPNG = this.aCurrentRoom.getPNJ(vS);
        if (vPNG == null){
            this.aGui.println("Cette personne n'est pas ici");
            return;
        }

        if (vPNG == this.aCurrentRoom.getPNJ("Jedi")){
            if (!aParleJedi) {
                this.aGui.println("Bonjour jeune Padawan. Pour contruire ton sabre, tu aura besoin d'un crystal de Kyber. Je sens que tu as un don. Tiens, prends ton crystal. \n");
                this.aCurrentRoom.addItem("Crystal",vPNG.getItem());
                this.aGui.println(this.aCurrentRoom.getLongDescription());
                this.aParleJedi = true;
            } else {
                this.aGui.println("Tu es prometteur, jeune Padawan, mais tu as la memoire bien courte ...");
            }
            return;
        }

        if (vPNG == this.aCurrentRoom.getPNJ("Garde")){
            if (! this.aInventaire.getItemListHM().containsKey("Verre")){
                this.aGui.println("Va me chercher une verre d'eau je meurs de soif");   
                return;
            }
            if (!aParleGarde){
                this.aGui.println("Merci garcon. Tiens prend cette cellule d'energie que j'ai trouvé sur un droid");
                this.aCurrentRoom.addItem("Cellule",vPNG.getItem());
                this.aInventaire.rmPoids(this.aInventaire.getItem("Verre").getPoids());
                this.aInventaire.remove(this.aInventaire.getItem("Verre"), "Verre");
                this.aParleGarde = true;
            } else {
                this.aGui.println("Hey ! que fais tu la ! tu n'a rien à faire ici !");
            }
        }
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