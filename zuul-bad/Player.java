
import java.util.*;

public class Player
{
    private Room aCurrentRoom;
    private String aNom;
    private UserInterface aGui;
    private int aPoidMAX = 100;
    private Stack<Room> aStackRoom;
    private Item aItemPorte;
    /**
     * constructeur de Player
     * @param String nom du joueur
     * @param Room room de départ
     */
    
    public Player (final String pNom , final Room pCurrentRoom ){
        this.aCurrentRoom = pCurrentRoom;
        this.aNom = pNom;
        aStackRoom = new Stack<Room>();
        
    }

    /**
     * Constructeur de gui
     * @param UserInterface pGui
     */
    public void setGui(final UserInterface pUserInterface)
    {
        this.aGui = pUserInterface; 
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
    public void eat(){
        aGui.println("You have eaten now and you are not hungry any more.");   
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
        if ( !this.aStackRoom.empty()){
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
        aGui.println("\n");
        aGui.println(aCurrentRoom.getLongDescription());
        aGui.showImage(aCurrentRoom.getImageName());
    }//printWelcome() 
    
    /*/**
     * permet de deposer l'item porté par le joueur dans la piece
     */
    /*public void drop(){
        if (this.aItemPorte != null){
            aItemPorte.addItem(this.aCurrentRoom);
            this.aItemPorte = null;
        } else aGui.println("Vous ne portez pas d'item");
        
    }
    
    public void take(Command pCommand){
    
        if(!pCommand.hasSecondWord()) {
            aGui.println("Que veux tu prendre ?");
        }
        else {
                String vObjet = pCommand.getSecondWord();
                Item vItem = this.aCurrentRoom.getItems().getObjet(vObjet);
                if ( vItem == null){
                aGui.println(
                    "Cet objet n'est pas dans cette pièce");
                }
                else {                    
                        this.aItemPorte = vItem;
                        this.aCurrentRoom.getItems().removeItem(vObjet);
                        aGui.println ("Vous avez pris " + vItem.getDescription()+" !");
                    }
        }
    }
        */
}

