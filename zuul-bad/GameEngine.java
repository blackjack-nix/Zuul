import java.util.HashMap;
import java.util.Scanner;
import java.io.InputStream;

/**
 * Class for the GameEngine of the project Zuul-Wars
 * @autor Théo Péresse
 * @version finale
 * Available on GitHub
 */

public class GameEngine {
    // ## Attributs ##
    private Parser aParser;
    private UserInterface aGui;
    private HashMap<String,Room> aRooms;
    private Player aPlayer;
    private Audio aAudio;

    // ## Constructeur(s) ##
    /**
     * constructeur de game engine
     */
    public GameEngine(){
        this.aParser = new Parser();
        this.aRooms = new HashMap<String , Room>();
        this.createRooms();
        this.aAudio = new Audio("son/imperial.wav");
        this.aAudio.play();
    }

    // ## Initialisation ##
    /**
     * insitialise les rooms, items, sorties et la piece courante
     */
    private void createRooms(){
        // ## déclaration des items ##
        Item vKyber = new Item("Crystal",5);
        Item vCellule = new Item("Cellule",5);
        Item vEmetteur = new Item("Emetteur",5);
        Item vLentille = new Item("Lentille",5);
        Item vVerre = new Item("Verre",5);
        Item vMagicCookie = new Item("MagicCookie",10);

        // ## déclaration des rooms ##
        Room vOutside = new Room("devant l'entrée du temple","Image/entree_gardee.jpg");
        Room vCouloir = new Room("dans le couloir principal","Image/couloir.jpg");
        Room vFontaine = new Room("dans la salle de la fontaine","Image/fontaine.jpg");
        Room vCombat = new Room("dans la salle d'entrainement aux arts Jedi","Image/combat.png");
        Room vHolocrons = new Room("dans la salle des holocrons","Image/holocrons.jpg");
        Room vHall = new Room("dans le hall des Jedi","Image/hall.jpg");
        Room vArchive = new Room("dans la salle des archives Jedi","Image/archive.png");
        Room vArmurerie = new Room("dans l'armurerie secrète du temple","Image/machine.jpg");
        Room vConseil = new Room("dans la salle du conseil Jedi","Image/conseil.jpg");

        //attribution des items
        //vConseil.addItem("Crystal",vKyber);
        //vOutside.addItem("Cellule",vCellule);
        vHall.addItem("Emetteur", vEmetteur);
        vHolocrons.addItem("Lentille",vLentille);
        vFontaine.addItem("Verre",vVerre);
        vFontaine.addItem("MagicCookie",vMagicCookie);

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

        vHolocrons.setExits("sud",vFontaine);//salle des holocrons trap door
        vHolocrons.setExits("est",vHall);//salle des holocrons

        vHall.setExits("bas",vConseil);//hall des jedi
        vHall.setExits("sud",vCouloir);
        vHall.setExits("ouest",vHolocrons);

        vArchive.setExits("sud",vCombat);//archive
        vArchive.setExits("est",vArmurerie);

        vArmurerie.setExits("ouest",vArchive); //armurerie (locked room)

        vConseil.setExits("up",vHall); //conseil jedi

        // ## déclaration des doors ##
        Door vLockedDoor = new Door(true,false,false);
        Door vTrapDoorFontaine = new Door(false,true,false);
        Door vTrapDoorCouloir = new Door(false,true,true);

        // ## Attribution des doors
        vArchive.addDoor("est", vLockedDoor);
        vFontaine.addDoor("est",vTrapDoorFontaine);
        vCouloir.addDoor("ouest",vTrapDoorCouloir);
        
        // ## Création des PNJ ##
        PNJ vJedi = new PNJ ("Jedi", vKyber , vConseil);
        PNJ vGarde = new PNJ ("Garde" , vCellule , vOutside);
        vConseil.addPNJ("Jedi", vJedi);
        vOutside.addPNJ("Garde", vGarde);

        // ## demande du nom au début du jeu
        String vPrenom = javax.swing.JOptionPane.showInputDialog( "Padawan, quel est ton prenom ?" );
        this.aPlayer = new Player (vPrenom,vOutside);

        // ## HashMap rooms
        aRooms.put("vOutside" , vOutside);
        aRooms.put("vCouloir",vCouloir);
        aRooms.put("vFontaine",vFontaine);
        aRooms.put("vCombate",vCombat);
        aRooms.put("vHolocrons",vHolocrons);
        aRooms.put("vHall",vHall);
        aRooms.put("vArchive",vArchive);
        aRooms.put("vArmurerie",vArmurerie);
        aRooms.put("vConseil",vConseil);
    }//creatRoom()

    /**
     * création de l'interface graphique
     * @param UserInterface 
     */
    public void setGUI(UserInterface pUserInterface){
        this.aGui = pUserInterface;
        this.aPlayer.setGui(aGui);
        this.printWelcome();
    }

    // ## Methodes principales ##
    /**
     * permet d'interpreter les commandes passées en parametres
     * @param String qui est la commande
     */
    public void interpretCommand(String pCommandLine){
        aGui.println(pCommandLine);
        Command vCommand = aParser.getCommand(pCommandLine);
        if(vCommand.isUnknown()) {
            aGui.println("I don't know what you mean...");
            return;
        }
        String vCommandWord = vCommand.getCommandWord();
        if (vCommandWord.equals("help"))
            this.printHelp();
        else if (vCommandWord.equals("go"))
            goRoom(vCommand);
        else if (vCommandWord.equals("quit")) {
            if(vCommand.hasSecondWord())
                aGui.println("Quit what?");
            else
                join_the_force();
        }
        else if (vCommandWord.equals("look")) aPlayer.look();
        else if (vCommandWord.equals("eat")) aPlayer.eat(vCommand);
        else if (vCommandWord.equals("back"))aPlayer.back();
        else if (vCommandWord.equals("test")){
            if (vCommand.hasSecondWord())
                test(vCommand.getSecondWord());
        }
        else if (vCommandWord.equals("join_the_force")) join_the_force();
        else if (vCommandWord.equals("drop")) aPlayer.drop(vCommand);
        else if (vCommandWord.equals("take")) aPlayer.take(vCommand);
        else if (vCommandWord.equals("inventaire")) this.aGui.println(this.aPlayer.getInventaire().inventory());
        else if (vCommandWord.equals("construire")) this.construire();
        else if (vCommandWord.equals("parler")) this.aPlayer.parler(vCommand);

    }

    /**
     * Permet de changer de room 
     * @ param Commande
     */
    private void goRoom ( final Command pCommand){

        if (!  pCommand.hasSecondWord()){
            aGui.println("Go where ?");
            return;
        } //mot pas bon apres le go
        else {
            Room vNextRoom = null;
            String vDirection = pCommand.getSecondWord();
            vNextRoom = aPlayer.getCurrentRoom().getExit(vDirection);

            if (vNextRoom == null){
                aGui.println("There is no door !");
                return;
            }
            this.aPlayer.getStackRoom().push(this.aPlayer.getCurrentRoom());
            Door vDoor = this.aPlayer.getCurrentRoom().getDoor(vDirection);
            if ( vDoor != null){ // si c'est une porte spéciale
                if (vDoor.isTrap()){ // si c'est une trap
                    if (! vDoor.canGo()){
                        this.aGui.println("It' a trap ! Vous ne pouvez pas passer par une porté piégée");
                        return;
                    }
                    else {
                        this.aPlayer.clearStack();
                    }
                }
                else if (vDoor.isLocked()){
                    HashMap vHMobj = this.aPlayer.getInventaire().getItemListHM();//hash map des objets de l'inventaire pour aller plus vite
                    if (vHMobj.containsKey("Crystal") && vHMobj.containsKey("Cellule") && vHMobj.containsKey("Emetteur") && vHMobj.containsKey("Lentille")){
                        vDoor.setLocked(false);
                        this.aGui.println("Vous avez trouvé l'armurerie secrète");
                    }
                    else {
                        this.aGui.println("Vous ne pouvez pas ouvrir cette porte ...");
                        return;
                    }
                }
            }

                
            this.aPlayer.changeRoom(vNextRoom);
            

        }
        if (this.aAudio.isFinished()){
            this.aAudio.playSound("son/imperial.wav");
        }

    }//goRoom(.)

    // ## Methodes secondaires ##
    /**
     * permet de quitter le jeu en affichant un message, avec un delais d'attente, en stoppant la musique et en fermant la page
     */
    private void join_the_force () {
        aGui.println("Thank you for playing. Good bye.");
        aGui.println("Vous avez rejoint la force");
        this.aAudio.stop();
        aGui.enable(false);
    }

    /** 
     * permet de lire les intructions tappées dans un fichier texte
     * @param String le chemin du fichier
     */
    private void test (final String pFichier){
        Scanner vSc;
        String vFichier = pFichier;
        if (! vFichier.endsWith(".txt")) vFichier += ".txt";
        if (! vFichier.startsWith("test/")) vFichier = "test/"+vFichier;
        try { 
            InputStream vIs = getClass().getResourceAsStream(vFichier);
            vSc = new Scanner( vIs );

            while ( vSc.hasNextLine() ) {
                String vLigne = vSc.nextLine();
                this.interpretCommand(vLigne);
            } // while()

        } // try
        catch ( final Exception pE) {
            this.aGui.println("Erreur dans le fichier" + pE.getMessage());
        }// catch

    }//test

    public void construire(){
        if (this.aPlayer.getStackRoom().pop().getDescription().equals("dans l'armurerie secrète du temple")){
            aGui.println("Vous ne pouvez pas construire ici !");
            return;
        }
        if (this.aPlayer.getCurrentRoom().getDescription().equals("dans l'armurerie secrète du temple")){
            this.aGui.println("Bravo, vous avez construit votre sabre lasert et finit le jeu !");
            this.join_the_force();            
        }
    }
    
    

    // ## Méthodes d'affichage ##
    /**
     * Print the welcome tips
     */
    public void printWelcome(){
        this.aGui.println("Player : " + this.aPlayer.getName());
        this.aGui.println("Bienvenue dans le jeu d'aventure StarWars !");
        this.aGui.println("Ta formation est bientôt terminé jeune Padawan. Récpuère les objets nécéssaires à la création de ton sabre lasert pour enfin devenir un vrai Jedi et finir ce jeu");
        this.aGui.println("Tappe help si tu as besoin d'aide !");
        this.aGui.println(this.aPlayer.getInventaire().getItemList());
        this.aGui.println(this.aPlayer.getCurrentRoom().getLongDescription());
        this.aGui.showImage(this.aPlayer.getCurrentRoom().getImageName());
    }//printWelcome()

    /**
     * Print help tips (where you are, exits, commands...)
     */
    public void printHelp(){
        aGui.println("Vous etes " + aPlayer.getCurrentRoom().getDescription() +". Que la force soit avec vous.");
        aGui.println("Sorties : " + aPlayer.getCurrentRoom().getExitString());
        aGui.println("\n");
        aGui.println("Your command words are :");
        aGui.println(aParser.showCommands());
    }//printHelp()
}