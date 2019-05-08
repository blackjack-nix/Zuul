/**
 * Class for the BeamerItem of the project Zuul-Wars
 * @autor Théo Péresse
 * @version finale
 * Available on GitHub
 */ 
public class Beamer extends Item
{
    // ## Attributs ##
    private Room aRoomPlaced;
    
    /**
     * Constructor de Beamer
     */
    public Beamer (){
        super("Téléporteur",10);
    }//constructor(.)
    
    /**
     * Permet de stocker la room
     * @param pRoom la room à stocker
     */
    public void charge(final Room pRoom){
        this.aRoomPlaced = pRoom;        
    } //charge(.)
    
    /**
     * permet de supprimer la room stockée 
     * @return Room la room stockée
     */
    public Room fire (){
        Room vRoom = this.aRoomPlaced;
        this.aRoomPlaced = null;
        return vRoom;
    }//fire()
    
}
