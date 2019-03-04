 


public class Command
{
    private String aCommandWord;
    private String aSecondWord;
    
    /**
     * Natural constructor for command
     * @param pCommandWord && String pSecondWord
     */
    
    public Command (final String pCommandWord , final String pSecondWord){
        this.aCommandWord = pCommandWord;
        this.aSecondWord = pSecondWord;
    }//Command(..)
    
    /**
     * return a string of the command
     */
    
    public String getCommandWord (){
        return this.aCommandWord;
    }//getCommandWord()
    
    /**
     * return a string of the second word
     */
    
    public String getSecondWord(){
        return this.aSecondWord;
    }//getSecondWord()
    
    
    /**
     * return a boolean if ther is a second word
     */
    public boolean hasSecondWord(){
                return  !(this.aSecondWord == null);
    }//hasSecondWord()
    
    /**
     * return a boolean. True if the commandWord is unknow and false if it is ok
     */
    public boolean isUnknown(){
        return this.aCommandWord == null;
    }//isUnknown()
    
    
    
} // Command
