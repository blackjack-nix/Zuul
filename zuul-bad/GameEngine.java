import java.util.*;
import java.io.*;

public class GameEngine {
    // attributs
    private Parser aParser;
    private UserInterface aGui;
    private HashMap<String,Room> aRooms;
    private Stack<Room> aStackRoom;
    private Player aPlayer;
    private Audio aAudio;

    /**
     * constructeur de game engine
     */
    public GameEngine(){
        aParser = new Parser();
        aRooms = new HashMap<String , Room>();
        aStackRoom = new Stack<Room>();
        this.aPlayer = new Player ("théo",aRooms.get("vOutside"));
        createRooms();
        this.aAudio = new Audio("son/sonsw.wav");
        this.aAudio.play();
        
    }

    /**
     * création de l'interface graphique
     */
    public void setGUI(UserInterface pUserInterface){
        this.aGui = pUserInterface;
        this.aPlayer.setGui(aGui);
        aPlayer.printWelcome();
    }


    /**
     * insitialise les rooms, items, sorties et la piece courante
     */
    private void createRooms(){
        // ## déclaration des items
        Item vKyber = new Item("Un crystal de Kyber",1,"Le cristal permet de concentrer l'energie brut en un rayon, ce qui en fait un composant essentiel. C'est aussi lui qui détermine la couleur du sabre.");
        Item vCellule = new Item("Une cellule d'énergie",2,"La cellule d'energie permet d'alimenter la lame du sabre.");
        Item vEmetteur = new Item("Un emetteur",3,"L'emetteur permet de concenter la puissance de la cellule en une lame droite");
        Item vLentille = new Item("Une lentille",4, "La lentille permet de regler la taille et l'epaisser de la lame");
        Item vVerre = new Item("Un verre d'eau",5, "quelqu'un a -il soif ?");
        Item vTest = new Item("un objet inutile",6,"ceci est un objet inutil au jeu" );

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
        aPlayer.changeRoom(vOutside);

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
                endGame();
        }
        else if (vCommandWord.equals("look")) aPlayer.look();
        else if (vCommandWord.equals("eat")) aPlayer.eat();
        else if (vCommandWord.equals("back"))aPlayer.back();
        else if (vCommandWord.equals("test")){
            if (vCommand.hasSecondWord())
                test(vCommand.getSecondWord());
        }
        else if (vCommandWord.equals("join_the_force")) join_the_force();
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
            } else {
                this.aStackRoom.push(aPlayer.getCurrentRoom());
                aPlayer.changeRoom(vNextRoom);

            }
        }
    }//goRoom(.)

    /**
     * Permet de quiter le jeu de facon brutale
     */
    private void endGame()
    {
        aGui.println("Thank you for playing. Good bye.");
        aGui.enable(false);
    }


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


    /** 
     * permet de lire les intructions tappées dans un fichier text
     */
    private void test (final String pFichier){
        Scanner vSc;
        String vFichier = pFichier;
        if (! vFichier.endsWith(".txt")) vFichier += ".txt";
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

    private void join_the_force (){
        aGui.println("Vous avez rejoint la force");
        endGame();
    }
}