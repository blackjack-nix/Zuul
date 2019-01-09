import java.awt.Polygon;

/**
 * Un triangle qui peut etre manipule et qui se dessine sur une toile (canvas).
 * 
 * @author  Michael Kolling and David J. Barnes
 * @author  mod.by Denis BUREAU
 * @version 2006.03.30/2018.09.10
 */

public class Triangle
{
    private int     aLargeur;
    private int     aHauteur;
    private int     aXPosition;
    private int     aYPosition;
    private String  aCouleur;
    private boolean aEstVisible;

    /**
     * Cree un nouveau triangle avec les position et couleur par defaut.
     */
    public Triangle()
    {
        this.aLargeur=   40;
        this.aHauteur=   30;
        this.aXPosition= 50;
        this.aYPosition= 15;
        this.aCouleur=   "green";
        this.aEstVisible= false;
    } // Triangle()

    /**
     * Rend ce triangle visible.
     */
    public void rendVisible()
    {
        this.aEstVisible= true;
        this.dessine();
    } // rendVisible()
    
    /**
     * Rend ce triangle invisible.
     */
    public void rendInvisible()
    {
        this.efface();
        this.aEstVisible= false;
    } // rendInvisible()
    
    /**
     * Deplace le triangle de quelques pixels vers la droite.
     */
    public void vaDroite()
    {
        this.depHorizontal( 20 );
    } // vaDroite()

    /**
     * Deplace le triangle de quelques pixels vers la gauche.
     */
    public void vaGauche()
    {
        this.depHorizontal( -20 );
    } // vaGauche()

    /**
     * Deplace le triangle de quelques pixels vers le haut.
     */
    public void vaHaut()
    {
        this.depVertical( -20 );
    } // vaHaut()

    /**
     * Deplace le triangle de quelques pixels vers le bas.
     */
    public void vaBas()
    {
        this.depVertical( 20 );
    } // vaBas()

    /**
     * Deplace le triangle horizontalement de 'pDistance' pixels.
     * @param pDistance nb de pixels dont il faut se deplacer vers la droite
     */
    public void depHorizontal( final int pDistance )
    {
        this.efface();
        this.aXPosition= this.aXPosition + pDistance;
        this.dessine();
    } // depHorizontal()

    /**
     * Deplace le triangle verticalement de 'pDistance' pixels.
     * @param pDistance nb de pixels dont il faut se deplacer vers le bas
     */
    public void depVertical( final int pDistance )
    {
        this.efface();
        this.aYPosition= this.aYPosition + pDistance;
        this.dessine();
    } // depVertical()

    /**
     * Deplace lentement le triangle horizontalement de 'pDistance' pixels.
     * @param pDistance nb de pixels dont il faut se deplacer vers la droite
     */
//    public void depLentHorizontal(final int pDistance)
//    NON IMPLEMENTE

    /**
     * Deplace lentement le triangle verticalement de 'pDistance' pixels.
     * @param pDistance nb de pixels dont il faut se deplacer vers le bas
     */
//    public void depLentVertical(final int pDistance)
//    NON IMPLEMENTE

    /**
     * Remplace l'ancienne taille par la nouvelle (en pixels).
     * Les valeurs doivent etre >= 0.
     * @param pNouvLargeur largeur souhaitee pour ce triangle
     * @param pNouvHauteur hauteur souhaitee pour ce triangle
     */
    public void changeTaille( final int pNouvLargeur, final int pNouvHauteur )
    {
        this.efface();
        this.aLargeur= pNouvLargeur;
        this.aHauteur= pNouvHauteur;
        this.dessine();
    } // changeTaille()


    /**
     * Change la couleur. Les couleurs valides sont
     * "red", "yellow", "blue", "green", "magenta" and "black".
     * @param pNewColor couleur souhaitee pour ce triangle
     */
    public void changeCouleur( final String pNouvCouleur )
    {
        // this.efface(); // pourquoi est-ce inutile ici?
        this.aCouleur= pNouvCouleur;
        this.dessine();
    } // changeCouleur()

    /**
     * Dessine sur l'ecran le triangle avec ses specifications courantes
     */
    private void dessine()
    {
        if ( this.aEstVisible ) {
            Canvas vCanvas; // classe compliquee a ne pas regarder pour le moment
            vCanvas= Canvas.getCanvas();
         // int[] : type special qui sera explique au cours 5
            int[] xPoints ={ this.aXPosition, this.aXPosition + (this.aLargeur/2), this.aXPosition - (this.aLargeur/2) };
            int[] yPoints ={ this.aYPosition, this.aYPosition + this.aHauteur,     this.aYPosition + this.aHauteur     };
            vCanvas.draw( this, this.aCouleur, new Polygon(xPoints, yPoints, 3) );
            vCanvas.waitALittle();
        } // if
    } // dessine()

    /**
     * Efface le triangle sur l'ecran.
     */
    private void efface()
    {
        if (this.aEstVisible) {
            Canvas vCanvas;
            vCanvas= Canvas.getCanvas();
            vCanvas.erase( this );
        } // if
    } // efface()

} // Triangle
