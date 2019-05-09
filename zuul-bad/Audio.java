import javax.sound.sampled.*;
/**
 * Class for the Sound of the project Zuul-Wars
 * @author Théo Péresse et Quentin Martins
 * @version finale
 * Available on GitHub
 */ 
public class Audio {
    // ## Attributs ##
    private Clip aClip;
    
    // ## Constructor ##
    
    /**
     * Contructeur de l'audio
     * @param pSon le chemin vers l'audio. ! ne prend que les .wav
     */
    public Audio (final String pSon){
        try {
            AudioInputStream vAudio = AudioSystem.getAudioInputStream(getClass().getResource(pSon));
            this.aClip = AudioSystem.getClip();
            this.aClip.open(vAudio);                       
        }
        catch (final Exception pEx){
            System.out.println("Erreur son");            
        }              
    }          
    
    /**
     * lance le son sur les enceintes du pc
     */
    public void play() {this.aClip.start();}
    
    /**
     * Coupe le son en cours
     */
    public void stop() {this.aClip.stop();}
    
    /**
     * creer un son puis le lance
     * @param pSon String qui correspond au chemin du son
     */
    public static void playSound (final String pSon){
        Audio vS = new Audio(pSon);
        vS.play();
    }
    /**
     * Renvoie le boolean qui vaut vrai si la musique est finie et faux si elle est en cours
     * @return Boolean estFinit
     */
    public boolean isFinished(){
        return ! aClip.isActive();        
    }
}