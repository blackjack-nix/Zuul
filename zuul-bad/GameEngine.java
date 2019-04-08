import java.util.*;
public class GameEngine {
    
    private Parser aParser;
    private Room aCurrentRoom;
    private UserInterface aGui;
    private HashMap<Room, String> aRooms;
    private Stack<Room> aStackRoom;
    
    
    public GameEngine(){
        aParser = new Parser();
        aRooms = new HashMap<Room,String>();
        aStackRoom = new Stack<Room>();
        createRooms();
    }
    
    public void setGUI(UserInterface pUserInterface){
        aGui = pUserInterface;
        printWelcome();
    }
    
    /**
     * Print the welcome tips
     */
    private void printWelcome(){
        aGui.println("Bienvenue dans le jeu d'aventure StarWars !");
        aGui.println("Ta formation est bientôt terminé jeune Padawan. Récpuère les objets nécéssaires à la création de ton sabre lasert pour enfin devenir un vrai Jedi et finir ce jeu");
        aGui.println("Tappe help si tu as besoin d'aide !");
        aGui.println("\n");
        aGui.println(aCurrentRoom.getLongDescription());
        aGui.showImage(aCurrentRoom.getImageName());
    }//printWelcome() 
    
    
    
    private void createRooms(){
        // ## déclaration des items
        Item vKyber = new Item("Un crysat de Kyber",1);
        Item vCellule = new Item("Une cellule d'énergie",2);
        Item vEmetteur = new Item("Un emetteur",3);
        Item vLentille = new Item("Une lentille",4);
        Item vVerre = new Item("Un verre d'eau",5);
        Item vTest = new Item("un objet inutile",6);
        
        // ## déclaration des rooms ##
        Room vOutside = new Room("devant l'entrée du temple","Image/entree_gardee.jpg");
        Room vCouloir = new Room("dans le couloir principal","Image/couloir.jpg");
        Room vFontaine = new Room("dans la salle de la fontaine","Image/fontaine.jpg");
        Room vCombat = new Room("dans la salle d'entrainement aux arts Jedi","Image/kanan_jarrus.JPG");
        Room vHolocrons = new Room("dans la salle des holocrons","Image/holocrons.jpg");
        Room vHall = new Room("dans le hall des Jedi","Image/hall.jpg");
        Room vArchive = new Room("dans la salle des archives Jedi","Image/bibliotheque.jpg");
        Room vArmurerie = new Room("dans l'armurerie secrète du temple","Image/machine.jpg");
        Room vConseil = new Room("dans la salle du conseil Jedi","Image/conseil_full.jpg");
        
        
        //attribution des items
                       
        vConseil.addItem("Un crysat de Kyber",vKyber);
        vOutside.addItem("Une cellule d'énergie",vCellule);
        vHall.addItem("Un emetteur", vEmetteur);
        vHolocrons.addItem("Une lentille",vLentille);
        vFontaine.addItem("Un verre d'eau",vVerre);
        vFontaine.addItem("Un objet inutile",vTest);
        
        
        // ## déclaration des sorties ##

        vOutside.setExits("nord", vCouloir); //entrée gardée

        vCouloir.setExits("nord",vHall); //couloir
        vCouloir.setExits("ouest",vFontaine);
        vCouloir.setExits("sud",vOutside);
        vCouloir.setExits("est",vCombat);

        vFontaine.setExits("nord",vHolocrons);//fontaine
        vFontaine.setExits("est",vCouloir);

        vCombat.setExits("nord",vArchive);//salle de combat
        vCombat.setExits("ouest",vCouloir);
    
        vHolocrons.setExits("sud",vFontaine);//salle des holocrons

        vHall.setExits("bas",vConseil);//hall des jedi
        vHall.setExits("sud",vCouloir);

        vArchive.setExits("sud",vCombat);//archive
        //vArchive.setExits("est",vArmurerie);

        //vArmurerie.setExits("ouest",vArchive); // salle bloquée pour le moment

        vConseil.setExits("up",vHall);
        
        
        
        // ## initialisation du lieu de départ ##
        this.aCurrentRoom = vOutside;
        
        // ## HashMap rooms
        aRooms.put(vOutside,"vOutside");
        aRooms.put(vCouloir,"vCouloir");
        aRooms.put(vFontaine,"vFontaine");
        aRooms.put(vCombat,"vCombate");
        aRooms.put(vHolocrons,"vHolocrons");
        aRooms.put(vHall,"vHall");
        aRooms.put(vArchive,"vArchive");
        aRooms.put(vArmurerie,"vArmurerie");
        aRooms.put(vConseil,"vConseil");
    }//creatRoom()
    
    public void interpretCommand(String pCommandLine){
        aGui.println(pCommandLine);
        Command vCommand = aParser.getCommand(pCommandLine);
        if(vCommand.isUnknown()) {
            aGui.println("I don't know what you mean...");
            return;
        }
        String vCommandWord = vCommand.getCommandWord();
        if (vCommandWord.equals("help"))
            printHelp();
        else if (vCommandWord.equals("go"))
            goRoom(vCommand);
        else if (vCommandWord.equals("quit")) {
            if(vCommand.hasSecondWord())
                aGui.println("Quit what?");
            else
                endGame();
        }
        else if (vCommandWord.equals("look")) look();
        else if (vCommandWord.equals("eat")) eat();
        else if (vCommandWord.equals("back"))back();
    }
    
    /**
     * Print help tips (where you are, exits, commands...)
     */
    private void printHelp(){
        aGui.println("Vous etes " + aCurrentRoom.getDescription() +". Que la force soit avec vous.");
        aGui.println("Sorties : " + aCurrentRoom.getExitString());
        aGui.println("\n");
        aGui.println("Your command words are :");
        aGui.println(aParser.showCommands());
    }//printHelp()
    
    private void goRoom ( final Command pCommand){

        if (!  pCommand.hasSecondWord()){
            aGui.println("Go where ?");
            return;
        } //mot pas bon apres le go
        else {
            Room vNextRoom = null;
            String vDirection = pCommand.getSecondWord();
            vNextRoom = aCurrentRoom.getExit(vDirection);

            if (vNextRoom == null){
                aGui.println("There is no door !");
            } else {
                this.aStackRoom.push(this.aCurrentRoom);
                this.aCurrentRoom = vNextRoom;
                aGui.println(aCurrentRoom.getLongDescription());
                if (aCurrentRoom.getImageName() != null) {
                    aGui.showImage(aCurrentRoom.getImageName());
                }
                
            }
        }
    }//goRoom(.)
    
    private void endGame()
    {
        aGui.println("Thank you for playing.  Good bye.");
        aGui.enable(false);
    }
    
    private void look(){
        aGui.println(aCurrentRoom.getLongDescription());
    }
    
    private void eat(){
        aGui.println("You have eaten now and you are not hungry any more.");   
    }
    
    /**
     * Print infos about wher you are and the exits available
     */
    private void printLocationInfo(){
        aGui.println(aCurrentRoom.getLongDescription());
    }//printLocationInfo()
    
    private void back(){
        if ( !this.aStackRoom.empty()){
            this.aCurrentRoom = this.aStackRoom.pop();
            this.aGui.println(this.aCurrentRoom.getLongDescription());
            if (this.aCurrentRoom.getImageName() != null){
            this.aGui.showImage(this.aCurrentRoom.getImageName());
            }
        } else aGui.println("Vous ne pouvez pas aller en arrière");
    }
}