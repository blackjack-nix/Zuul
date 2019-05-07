

public class Door
{
    private boolean isLocked;
    private boolean isTrap;
    private boolean isGoodGirection;
    
    public Door (final boolean pLocked , final boolean pTrap , final boolean pGoodDirection){
        this.isLocked = pLocked;
        this.isTrap = pTrap;
        this.isGoodGirection = pGoodDirection;        
    }
    
    public boolean isLocked(){
        return this.isLocked;
    }
    
    public boolean isTrap(){
        return this.isTrap;
    }
    
    public boolean canGo(){
        return this.isGoodGirection;
    }
    
    public void setLocked (final boolean pLocked){
        this.isLocked = pLocked;
    }
    
    public void setCanGo(final boolean pGoodDirection){
        this.isGoodGirection = pGoodDirection;
    }
}
