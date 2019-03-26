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
        aGui.println(aCurrentRoom.getLongDescription());
        aGui.showImage(aCurrentRoom.getImageName());
    }//printWelcome() 
    
    private void createRooms(){
        
        
        
        // ## déclaration des rooms ##
        Room vOutside = new Room("devant l'entrée du temple","/Image/entree_gardee.jpg");
        Room vCouloir = new Room("dans le couloir principal","/Image/couloir.jpg");
        Room vFontaine = new Room("dans la salle de la fontaine","/Image/fontaine.jpg");
        Room vCombat = new Room("dans la salle d'entrainement aux arts Jedi","/Image/kanan_jarrus.jpg");
        Room vHolocrons = new Room("dans la salle des holocrons","/Image/holocrons.jpg.jpg");
        Room vHall = new Room("dans le hall des Jedi","/Image/hall.jpg");
        Room vArchive = new Room("dans la salle des archives Jedi","/Image/bibliotheque.jpg");
        Room vArmurerie = new Room("dans l'armurerie secrète du temple","/Image/hall.jpg");
        Room vConseil = new Room("dans la salle du conseil Jedi","/Image/conseil_full.jpg");

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
    }
    
    /**
     * Print help tips (where you are, exits, commands...)
     */
    private void printHelp(){
        System.out.println("You are " + aCurrentRoom.getDescription() +". May the force be with you");
        System.out.println("Sorties : " + aCurrentRoom.getExitString());
        System.out.println('\n');
        System.out.println("Your command words are :");
        System.out.println(aParser.showCommands());
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
                System.out.println(aCurrentRoom.getLongDescription());
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
        System.out.println(aCurrentRoom.getLongDescription());
    }
    
    private void eat(){
        System.out.println("You have eaten now and you are not hungry any more.");   
    }
    
    /**
     * Print infos about wher you are and the exits available
     */
    private void printLocationInfo(){
        System.out.println(aCurrentRoom.getLongDescription());
    }//printLocationInfo()
}