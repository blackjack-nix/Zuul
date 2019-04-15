import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Audio {
    private Clip aClip;
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
    public Clip getClip() {return this.aClip;}
    public void play() {this.aClip.start();}
    public void stop() {this.aClip.stop();}
    public static void playSound (final String pSon){
        Audio vS = new Audio(pSon);
        vS.play();
    }
    public boolean isFinished(){
        return aClip.isActive();        
    }
}