
public class Cercle extends Forme
{
    // ## Attributs ##
    private int aR;
    
    // ## constructeur naturel ##
    public Cercle(final int pX , final int pY , final int pR){
        super(pX,pY);
        this.aR=pR;
    }// constructor
    
    // ## override ##
    @Override public double perimetre(){
        return this.aR * Math.PI * 2 ;
    }
    
    @Override public double surface() {
        return this.aR * this.aR * Math.PI;
    }
}
