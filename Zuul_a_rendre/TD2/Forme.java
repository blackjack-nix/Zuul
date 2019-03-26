public abstract class Forme implements Comparable
{
    // ## Attributs ##
    private int aX;
    private int aY;
    
    // ## Constructeur naturel ##
    public Forme(final int pX,final int pY){
        this.aX = pX;
        this.aY = pY;
    }//constructor
  
    // ## methodes ##
    public abstract double perimetre();
    public abstract double surface();
    public double calcRatio(){
        return this.perimetre()/this.surface();
    }
    
    @Override public int compareTo(final Object pObj){
        if ( pObj == this) return 0;
        if (pObj.getClass() == this.getClass()){
            Forme vForme = (Forme)pObj;
            Double vDouble = new Double(vForme.surface());
            return vDouble.compareTo(this.surface());
        } else return 99;
    }
}
