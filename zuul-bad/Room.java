import java.util.HashMap; 

public class Room
{
    public HashMap <String , Room>  aSortieHM;
    private String aDescription = "Cette salle est la salle la plus au sud du temple. Elle est gardée par 2 gardes ainsi qu'un garde d'élite.";//description succinte de la salle

    public Room (final String pDescription){
        this.aDescription = pDescription ;
        aSortieHM = new HashMap <String , Room> ();
    }//Room(.)
    
    public String getDescription (){
        return this.aDescription;
    }//getDescription(.)
    
    public void setExits (final String pDirection, final Room pRoom){
        aSortieHM.put(pDirection,pRoom);
    }//setExit(....)
    
    public Room getExit(final String pDirection){
        if (pDirection.equals("nord")) return aSortieHM.get(pDirection);
        if (pDirection.equals("est")) return aSortieHM.get(pDirection);
        if (pDirection.equals("sud")) return aSortieHM.get(pDirection);
        if (pDirection.equals("ouest")) return aSortieHM.get(pDirection);
        return null;
    }//getExit(.)
    
    public String getExitsString(){
        String sortie = "";
        if (this.aNorthExit != null ) sortie += "nord ";
        if (this.aEastExit != null ) sortie += "est ";
        if (this.aSouthExit != null ) sortie += "sud ";
        if (this.aWestExit != null ) sortie += "ouest ";
        
        return sortie;
    }
} // Room
