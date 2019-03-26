public class GameEngine {
    
    private Parser aParser;
    private Room aCurrentRoom;
    private UserInterface aGui;
    
    public GameEngine(){
        aParser = new Parser();
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
        System.out.println("Bienvenue dans le jeu d'aventure StarWars !");
        System.out.println("Ta formation est bientôt terminé jeune Padawan. Récpuère les objets nécéssaires à la création de ton sabre lasert pour enfin devenir un vrai Jedi et finir ce jeu");
        System.out.println("Tappe help si tu as besoin d'aide !");
        System.out.println('\n');
        printLocationInfo();
    }//printWelcome() 
    
    private void createRooms(){
        
        
        
        // ## déclaration des rooms ##
        Room vOutside = new Room("devant l'entrée du temple");
        Room vCouloir = new Room("dans le couloir principal");
        Room vFontaine = new Room("dans la salle de la fontaine");
        Room vCombat = new Room("dans la salle d'entrainement aux arts Jedi");
        Room vHolocrons = new Room("dans la salle des holocrons");
        Room vHall = new Room("dans le hall des Jedi");
        Room vArchive = new Room("dans la salle des archives Jedi");
        Room vArmurerie = new Room("dans l'armurerie secrète du temple");
        Room vConseil = new Room("dans la salle du conseil Jedi");

        // ## déclaration des sorties ##

        vOutside.setExits("north", vCouloir);

        vCouloir.setExits("north",vHall);
        vCouloir.setExits("west",vFontaine);
        vCouloir.setExits("south",vOutside);
        vCouloir.setExits("east",vCombat);

        vFontaine.setExits("north",vHolocrons);
        vFontaine.setExits("east",vCouloir);

        vCombat.setExits("north",vArchive);
        vCombat.setExits("west",vCouloir);
    
        vHolocrons.setExits("south",vFontaine);

        vHall.setExits("down",vConseil);
        vHall.setExits("south",vCouloir);

        vArchive.setExits("south",vCombat);
        vArchive.setExits("east",vArmurerie);

        //vArmurerie.setExits("west",vArchive); // salle bloquée pour le moment

        vConseil.setExits("up",vHall);

        // ## initialisation du lieu de départ ##
        this.aCurrentRoom = vCombat;
    }//creatRoom()
    
    public void interpretCommand(String pCommandLine){
        aGui.println(pCommandLine);
        Command vCommand = aParser.getCommand(pComandLine);
        if(vCommand.isUnknown()) {
            gui.println("I don't know what you mean...");
            return;
        }
        String vCommandWord = vCommand.getCommandWord();
        if (vCommandWord.equals("help"))
            printHelp();
        else if (vCommandWord.equals("go"))
            goRoom(command);
        else if (vCommandWord.equals("quit")) {
            if(vCommand.hasSecondWord())
                aGui.println("Quit what?");
            else
                endGame();
        }
    }
    
    /**
     * Print help tips (where you are, exits, commands...)
     */
    private void printHelp(){
        System.out.println("You are " + aCurrentRoom.getDescription() +". May the force be with you");
        System.out.println("Sorties : " + aCurrentRoom.getExitsString());
        System.out.println('\n');
        System.out.println("Your command words are :");
        aParser.showCommands();
    }//printHelp()
    
    private void goRoom ( final Command pCommand){

        if (!  pCommand.hasSecondWord()){
            System.out.println("Go where ?");
            return;
        } //mot pas bon apres le go
        else {
            Room vNextRoom = null;
            String vDirection = pCommand.getSecondWord();
            vNextRoom = aCurrentRoom.getExit(vDirection);

            if (vNextRoom == null){
                System.out.println("There is no door !");
            } else {
                this.aCurrentRoom = vNextRoom;
                printLocationInfo();
                if (aCurrentRoom.getImageName() != null) {
                    aGui.showImage(currentRoom.getImageName());
                }
            }
        }
    }//goRoom(.)
    
    private void endGame()
    {
        aGui.println("Thank you for playing.  Good bye.");
        aGui.enable(false);
    }
    
}