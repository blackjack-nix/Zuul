/**
 * Class for the Game of the project Zuul-Wars
 * @author Théo Péresse
 * @version finale
 * Available on GitHub
 */
public class Game
{
    // ## Attributs ##
    
    private UserInterface aGui;
    private GameEngine aEngine;
    
    // ## Constructor ##
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