public class Game
{
    private UserInterface aGui;
    private GameEngine aEngine;
    
    /**
     * Game class constructor
     * launch the game engine
     */
    public Game (){
        aEngine = new GameEngine();
        aGui = new UserInterface(aEngine);
        aEngine.setGUI(aGui);
    }//Game()
} // Game