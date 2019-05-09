/**
 * Class for the Doors of the project Zuul-Wars
 * @author Théo Péresse
 * @version finale
 * Available on GitHub
 */ 
 
public class Door
{
    // ## Attributs ##
    private boolean isLocked;
    private boolean isTrap;
    private boolean isGoodGirection;
    
    
    // ## Constructor ## 
    
    /**
     * constructeur naturel
     * @param pLocked boolean qui dit si la door est locked
     * @param pTrap boolean qui dit si la door est trap
     * @param pGoodDirection (si trop only) boolean qui dit si bonne direction de passage
     */
    public Door (final boolean pLocked , final boolean pTrap , final boolean pGoodDirection){
        this.isLocked = pLocked;
        this.isTrap = pTrap;
        this.isGoodGirection = pGoodDirection;        
    }
    
    
    // ## Accesseur ##
    
    /**
     * Accesseur de lock
     * @return boolean si la door est locked
     */
    public boolean isLocked(){
        return this.isLocked;
    }
    
    /**
     * Accesseur de trap
     * @return boolean si la door est trap
     */
    public boolean isTrap(){
        return this.isTrap;
    }
    
    /**
     * Accesseur de cango
     * @return boolean si la door trap peut etre franchie dans ce sens
     */
    public boolean canGo(){
        return this.isGoodGirection;
    }
    
    
    // ## Modificateurs ##
    
    /**
     * setteur de locked. Permet de deverouiller une piece
     * @param pLocked permet de debloquer une door 
     */
    public void setLocked (final boolean pLocked){
        this.isLocked = pLocked;
    }
    
    /**
     * setteur de cango
     * @param pGoodDirection boolean qui permet de set gooddirection
     */
    public void setCanGo(final boolean pGoodDirection){
        this.isGoodGirection = pGoodDirection;
    }
}
