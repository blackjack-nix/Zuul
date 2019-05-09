/**
 * Class for the command of the project Zuul-Wars
 * @author Théo Péresse
 * @version finale
 * Available on GitHub
 */
public class Command
{
    // ## Attributs ##
    private String aCommandWord;
    private String aSecondWord;
    
    // ## Constructor ##
    /**
     * Natural constructor for command
     * @param pCommandWord 
     * @param pSecondWord
     */
    
    public Command (final String pCommandWord , final String pSecondWord){
        this.aCommandWord = pCommandWord;
        this.aSecondWord = pSecondWord;
    }//Command(..)
    
    
    // ## Accesseur ##
    
    /**
     * return a string of the command
     * @return String commandWord
     */
    
    public String getCommandWord (){
        return this.aCommandWord;
    }//getCommandWord()
    
    /**
     * return a string of the second word
     * @return String second world
     */
    
    public String getSecondWord(){
        return this.aSecondWord;
    }//getSecondWord()
    
    // ## Méthodes de test ##
    
    /**
     * return a boolean if ther is a second word
     * @return boolean if has second word
     */
    public boolean hasSecondWord(){
                return  !(this.aSecondWord == null);
    }//hasSecondWord()
    
    /**
     * return a boolean. True if the commandWord is unknow and false if it is ok
     * @return boolean
     */
    public boolean isUnknown(){
        return this.aCommandWord == null;
    }//isUnknown()
    
} // Command
