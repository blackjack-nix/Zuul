public class Item
{
    private String aDescription;
    private int aPoids;
    
    public Item (final String pDescription , final int pPoids){
        this.aDescription = pDescription;
        this.aPoids = pPoids;        
    }//constructor
    
    public String getDescription (){
        return this.aDescription;
    }//accesseur description
    
    public int getPoids (final Item pItem){
        return this.aPoids;
    }//accesseur poids
    
    
}
