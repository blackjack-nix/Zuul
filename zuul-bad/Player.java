import java.util.*;

public class Player
{
    private Room aCurrentRoom;
    private String aNom;
    private UserInterface aGui;
    private int aPoidMAX = 100;
    private Stack<Room> aStackRoom;
    
    public Player (final String pNom , final Room pCurrentRoom ){
        this.aCurrentRoom = pCurrentRoom;
        
        this.aNom = pNom;
        aStackRoom = new Stack<Room>();
    }
    
    public void setGui(final UserInterface pUserInterface)
    {
        this.aGui = pUserInterface; 
    }
    
    public Room getCurrentRoom()
    {
        return this.aCurrentRoom;
    }
    
    /**
     * Affiche une description du lieu courant
     */
    public void look(){
        aGui.println(aCurrentRoom.getLongDescription());
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
    
    public String getName(){
        return this.aNom;
    }
    
    /**
     * Permet de revenir en arriere au moyen d'une stack
     */

    public void back(){
        if ( !this.aStackRoom.empty()){
            this.aCurrentRoom = this.aStackRoom.pop();
            this.aGui.println(this.aCurrentRoom.getLongDescription());
            if (this.aCurrentRoom.getImageName() != null){
                this.aGui.showImage(this.aCurrentRoom.getImageName());
            }
        } else aGui.println("Vous ne pouvez pas aller en arrière");
    }
    
    
    
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
}
