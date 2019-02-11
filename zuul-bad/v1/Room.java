package v1;

public class Room
{
    private String aDescription = "Cette salle est la salle la plus au sud du temple. Elle est gardée par 2 gardes ainsi qu'un garde d'élite.";//description succinte de la salle
    public Room aNorthExit = null;
    public Room aEastExit = null;
    public Room aSouthExit = null;
    public Room aWestExit = null;
    
    public Room (final String pDescription){
        this.aDescription = pDescription ;
    }//Room(.)
    
    public String getDescription (){
        return this.aDescription;
    }//getDescription(.)
    
    public void setExits (final Room pNorthExit , final Room pEastExit , final Room pSouthExit ,final Room pWestExit){
        this.aNorthExit = pNorthExit; 
        this.aEastExit = pEastExit;
        this.aSouthExit = pSouthExit;
        this.aWestExit = pWestExit;
    }//setExit(....)
    
    public Room getExit(final String pDirection){
        if (pDirection.equals("nord")) return this.aNorthExit;
        if (pDirection.equals("est")) return this.aWestExit;
        if (pDirection.equals("sud")) return this.aSouthExit;
        if (pDirection.equals("ouest")) return this.aWestExit;
        return null;
    }//getExit(.)
} // Room
