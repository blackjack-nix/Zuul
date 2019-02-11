package v1;


public class Command
{
    private String aCommandWord;
    private String aSecondWord;
    
    public Command (final String pCommandWord , final String pSecondWord){
        this.aCommandWord = pCommandWord;
        this.aSecondWord = pSecondWord;
    }//Command(..)
    
    public String getCommandWord (){
        return this.aCommandWord;
    }//getCommandWord()
    
    public String getSecondWord(){
        return this.aSecondWord;
    }//getSecondWord()
    
    public boolean hasSecondWord(){
                return  !(this.aSecondWord == null);
    }//hasSecondWord()
    
    public boolean isUnknown(){
        return this.aCommandWord == null;
    }//isUnknown()
    
    
    
} // Command
