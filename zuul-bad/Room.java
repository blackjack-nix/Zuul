/**
 * Game engine of the project Zuul-Bad
 * @autor Théo Péresse
 * @version 1.0
 * Available on GitHub
 */    

import java.util.HashMap; 
import java.util.Set;
import java.util.Iterator;

public class Room
{
    private HashMap <String , Room>  aSortieHM;
    private HashMap <Item , Room> aItemHM;
    private String aDescription;
    private String aImageName;
    private Item aItem; 
    
    /**
     * Create rooms with a string description
     *  and create a HashMap of the exits for each
     */
    public Room (final String pDescription , final String pImage , final Item pItem){
        this.aDescription = pDescription ;
        aSortieHM = new HashMap <String , Room> ();
        aItemHM = new HashMap <Item , Room> ();
        aImageName = pImage;
        aItem = pItem;
    }//Room(.)

    /**
     * return a string of the room description
     * @return  Return a String description of the room
     */
    public String getDescription (){
        return this.aDescription;
    }//getDescription(.)

    /**
     * change the exit of a room
     * @param pDirection A String for the direection you want to go
     * @param pRoom Change the room
     */
    public void setExits (final String pDirection, final Room pRoom){
        aSortieHM.put(pDirection,pRoom);
    }//setExit(....)
    
    public void setItem (final Item pItem , final Room pRoom){
        aItemHM.put(pItem,pRoom);        
    }//setItem
    
      /**
     * Return the exit of the room
     * @return Return the room after taking the direction
     * @param pDirection Direction you want to take in the room
     */
    public Room getExit(final String pDirection){
        return aSortieHM.get(pDirection);
    }//getExit(.)

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     */
    public String getExitString()
    {
        StringBuilder vReturnString = new StringBuilder( "" );
        for ( String vS : aSortieHM.keySet() )
            vReturnString.append( " " + vS );
        return vReturnString.toString();
    }
    
    /**
     * Renvoie une description détillée de cette piece sous la forme :
     *      vous etes dans + piece
     *      sorties : 
     * @return Une description de la piece avec les sorties
     */
    public String getLongDescription(){
        String vGetLongDescription = "\n"+"Vous etes "+this.aDescription+". Sorties : "+this.getExitString() + "\n" + "Objet disponible dans cette salle : ";
        if (this.aItem != null) vGetLongDescription += this.aItem.getDescription();
        else vGetLongDescription += "Il n'y en a pas";
        return vGetLongDescription;
        
    }
    
    /**
     * Return the description of the room (the one that was defined in the
     * constructor).
     */
    public String getShortDescription()
    {
        return this.aDescription;
    }
    
    public String getImageName(){
        return this.aImageName;
    }
} // Room
