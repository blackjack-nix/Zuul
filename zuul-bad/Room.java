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
    private String aDescription = "Cette salle est la salle la plus au sud du temple. Elle est gardée par 2 gardes ainsi qu'un garde d'élite.";//description succinte de la salle
    private String aImageName;
    
    /**
     * Create rooms with a string description
     *  and create a HashMap of the exits for each
     */
    public Room (final String pDescription , final String pImage){
        this.aDescription = pDescription ;
        aSortieHM = new HashMap <String , Room> ();
        aImageName = pImage;
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
        StringBuilder vReturnString = new StringBuilder( "Exits:" );
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
        return "Vous etes "+this.aDescription+". Sorties : "+this.getExitString();
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
