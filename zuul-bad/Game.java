 
public class Game
{
    private Room aCurrentRoom; // room courante
    private Parser aParser;//Parser
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
        // north ouest sud est
        vOutside.setExits("north", vCouloir);
        vCouloir.setExits("north",vHall);
        vCouloir.setExits("west",vCombat);
        vCouloir.setExits("south",vOutside);
        vCouloir.setExits("north",vFontaine);
        
        vFontaine.setExits("north",vHolocrons);
        vFontaine.setExits("west",vCouloir);
        
        vCombat.setExits("north",vArchive);
        vCombat.setExits("east",vCouloir);
        
        vHolocrons.setExits("west",vHall);
        vHolocrons.setExits("south",vFontaine);
        
        vHall.setExits("north",vConseil);
        vHall.setExits("west",vArchive);
        vHall.setExits("south",vCouloir);
        vHall.setExits("east",vHolocrons);
        
        vArchive.setExits("south",vCombat);
        vArchive.setExits("east",vHall);
        
        //vArmurerie.setExits aucune
        
        vConseil.setExits("south",vHall);
        
        // ## initialisation du lieu de départ ##
        this.aCurrentRoom = vOutside;
    }//creatRoom()
    
    public Game (){
        createRooms();
        this.aParser = new Parser();
    }//Game()
    
    
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
    private void printLocationInfo(){
        System.out.println("Vous etes " + aCurrentRoom.getDescription());
        System.out.print("Les sorties : ");
        //System.out.println(aCurrentRoom.getExitsString());
    }
    private void printWelcome(){
                System.out.println("Bienvenue dans le jeu d'aventure StarWars !");
                System.out.println("Le temple est attaqué ! construisez vite votre sabre pour defendre le temple !");
                System.out.println("Type'help' if you need help.");
                System.out.println('\n');
                printLocationInfo();
            }//printWelcome() 
        
    private void printHelp(){
        System.out.println("You are lost. You are alone");
        System.out.println("You wander around at the university");
        System.out.println('\n');
        System.out.println("Your command words are :");
        System.out.println("  go quit help");
    }//printHelp()
        
    private boolean quit (final Command pCommand){
        if ( pCommand.hasSecondWord()){
            System.out.println("Quit what ?");
        }
        return pCommand.hasSecondWord();
    }//quit()
    
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
        } else {
            return false;
        }
    }

    public void play(){
        Command vCommand;
        
        printWelcome();
        boolean vFinished = false ;
        while (vFinished == false){
            vCommand = this.aParser.getCommand();
            vFinished = this.processCommand(vCommand);
        }//while()
        System.out.println("Thank you for playing. Good bye.");
    }
    
    
    
} // Game
