import java.util.*;
import java.io.*;

public class GameEngine {
    // attributs
    private Parser aParser;
    private UserInterface aGui;
    private HashMap<String,Room> aRooms;
    private Player aPlayer;
    private Audio aAudio;
    private int aTimer = 30;


    /**
     * constructeur de game engine
     */
    public GameEngine(){
        aParser = new Parser();
        aRooms = new HashMap<String , Room>();
        createRooms();
        this.aAudio = new Audio("son/imperial.wav");
        this.aAudio.play();

    }

    /**
     * création de l'interface graphique
     * @param UserInterface 
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
        Room vCombat = new Room("dans la salle d'entrainement aux arts Jedi","Image/kanan_jarrus.JPG");
        Room vHolocrons = new Room("dans la salle des holocrons","Image/holocrons.jpg");
        Room vHall = new Room("dans le hall des Jedi","Image/hall.jpg");
        Room vArchive = new Room("dans la salle des archives Jedi","Image/bibliotheque.jpg");
        Room vArmurerie = new Room("dans l'armurerie secrète du temple","Image/machine.jpg");
        Room vConseil = new Room("dans la salle du conseil Jedi","Image/conseil_full.jpg");

        //attribution des items
        vConseil.addItem("Crystal",vKyber);
        vOutside.addItem("Cellule",vCellule);
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

        vHolocrons.setExits("sud",vFontaine);//salle des holocrons
        vHolocrons.setExits("est",vHall);//salle des holocrons

        
        vHall.setExits("bas",vConseil);//hall des jedi
        vHall.setExits("sud",vCouloir);
        vHall.setExits("ouest",vHolocrons);


        vArchive.setExits("sud",vCombat);//archive
        //vArchive.setExits("est",vArmurerie);

        //vArmurerie.setExits("ouest",vArchive); // salle bloquée pour le moment

        vConseil.setExits("up",vHall);

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
        else if (vCommandWord.equals("inventaire")) this.aGui.println(this.aPlayer.getItemList().inventory());

    }

    public void showPoids (){
        String vS = "Le poids de votre inventaire est de :";
        vS += aPlayer.getItemList().getPoids();
        vS += "\n Le poids maximum est de 10";
        this.aGui.println(vS);
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
                aPlayer.getStackRoom().push(aPlayer.getCurrentRoom());
                aPlayer.changeRoom(vNextRoom);
                if (this.aTimer == 0){
                    this.aGui.println("Vous vous etes trop déplacés, vous avez perdu");
                    join_the_force();
                } else {
                    this.aTimer -= 1;
                    this.aGui.println("Nombre de coups réstant : " + this.aTimer);
                }
            }
        }
        if (this.aAudio.isFinished()){
            this.aAudio.playSound("son/imperial.wav");
        }

    }//goRoom(.)

   
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

    /**
     * permet de quitter le jeu en affichant un message, avec un delais d'attente, en stoppant la musique et en fermant la page
     */
    private void join_the_force () {
        aGui.println("Thank you for playing. Good bye.");
        aGui.println("Vous avez rejoint la force");
        this.aAudio.stop();
        aGui.enable(false);
    }

    
}