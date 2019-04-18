import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Audio {
    private Clip aClip;
    /**
     * Contructeur de l'audio
     * @param String le chemin vers l'audio. ! ne prend que les .wav
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
     * Coupe le son en cour
     */
    public void stop() {this.aClip.stop();}
    
    /**
     * creer un son puis le lance
     */
    public static void playSound (final String pSon){
        Audio vS = new Audio(pSon);
        vS.play();
    }
    public boolean isFinished(){
        return ! aClip.isActive();        
    }
}