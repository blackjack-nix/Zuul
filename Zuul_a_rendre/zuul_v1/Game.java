
public class Game
{
    private Room aCurrentRoom; // room courante
    private Parser aParser;//Parser
    /**
     * create Rooms
     * create exits for each
     * initate the starting room
     */
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

    /**
     * Game class constructor
     * launch the game engine
     */
    public Game (){
        createRooms();
        this.aParser = new Parser();
    }//Game()

    /**
     * 
     * Allow you to go in an other room
     * @param pCommand
     */

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
            }
        }
    }//goRoom(.)

    /**
     * Print infos about wher you are and the exits available
     */
    private void printLocationInfo(){
        System.out.println(aCurrentRoom.getLongDescription());
    }//printLocationInfo()

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

    /**
     * Fonction to leave the gamemmand to quit the game "quit"
     * @param pCommand command for quit
     */
    private boolean quit (final Command pCommand){
        if ( pCommand.hasSecondWord()) System.out.println("Quit what ?");
        return ! pCommand.hasSecondWord();
    }//quit()

    /**
     * Differents commands
     * @param pCommand Commands available : go + direction , help , quit.
     */
    private boolean processCommand (final Command pCommand){
        if ( pCommand.isUnknown()){
            System.out.println("I don't know what you mean");
            return false;
        }
        String vCommandWord = pCommand.getCommandWord();
        if (vCommandWord.equals("go")){
            goRoom(pCommand);
            return false;
        } else if (vCommandWord.equals("help")){
            printHelp();
            return false;
        } else if (vCommandWord.equals("quit")){
            return quit(pCommand);
        } else if (vCommandWord.equals("look")){
            look();
            return false;
        } else if (vCommandWord.equals("eat")){
            eat();
            return false;
        }else {
            return false;
        }
    }//processCommand(.)

    /**
     * Start the game with an quite infinite while
     */
    public void play(){
        Command vCommand;

        printWelcome();
        boolean vFinished = false ;
        while (vFinished == false){
            vCommand = this.aParser.getCommand();
            vFinished = this.processCommand(vCommand);
        }//while()
        System.out.println("Thank you for playing. Good bye.");
    }//play()
    
    private void look(){
        System.out.println(aCurrentRoom.getLongDescription());
    }
    
    private void eat(){
        System.out.println("You have eaten now and you are not hungry any more.");   
    }
} // Game
