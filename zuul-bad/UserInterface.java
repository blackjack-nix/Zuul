import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.awt.image.*;
import java.util.*;
import java.io.*;

/**
 * This class implements a simple graphical user interface with a text entry
 * area, a text output area and an optional image.
 * 
 * @author Michael Kolling (DB edited)
 * @version 1.0 (Jan 2003)
 */
public class UserInterface implements ActionListener
{
    private GameEngine aEngine;
    private JFrame     aMyFrame;
    private JTextField aEntryField;
    private JTextArea  aLog;
    private JLabel     aImage;
    private JButton    aButtonNorth;
    private JButton    aButtonSouth;
    private JButton    aButtonWest;
    private JButton    aButtonEast;
    private JButton    aButtonLook;
    private JButton    aButtonUp;
    private JButton    aButtonDown;
    private JButton    aButtonJoin;
    private JButton    aButtonBack;
    private JButton    aButtonInventory;
    
    
    
    /**
     * Construct a UserInterface. As a parameter, a Game Engine
     * (an object processing and executing the game commands) is
     * needed.
     * 
     * @param gameEngine  The GameEngine object implementing the game logic.
     */
    public UserInterface( final GameEngine pGameEngine )
    {
        this.aEngine = pGameEngine;
        
        
        this.createGUI();
    } // UserInterface(.)

    /**
     * Print out some text into the text area.
     * @param String what to print
     */
    public void print( final String pText )
    {
        this.aLog.append( pText );
        this.aLog.setCaretPosition( this.aLog.getDocument().getLength() );
    } // print(.)

    /**
     * Print out some text into the text area, followed by a line break.
     */
    public void println( final String pText )
    {
        this.print( pText + "\n" );
    } // println(.)

    /**
     * Show an image file in the interface.
     */
    public void showImage( final String pImageName )
    {
        URL vImageURL = this.getClass().getClassLoader().getResource( pImageName );
        if ( vImageURL == null )
            System.out.println( "image not found" );
        else {
            ImageIcon vIcon = new ImageIcon( vImageURL );
            this.aImage.setIcon( vIcon );
            this.aMyFrame.pack();
        }
    } // showImage(.)

    /**
     * Enable or disable input in the input field.
     */
    public void enable( final boolean pOnOff )
    {
        this.aEntryField.setEditable( pOnOff );
        if ( ! pOnOff )
            this.aEntryField.getCaret().setBlinkRate( 0 );
    } // enable(.)

    /**
     * Set up graphical user interface.
     */
    private void createGUI()
    {
        this.aMyFrame = new JFrame( "Zuul-Wars" );
        this.aEntryField = new JTextField( 34 );

        this.aLog = new JTextArea();
        this.aLog.setEditable( false );
        JScrollPane vListScroller = new JScrollPane( this.aLog );
        vListScroller.setPreferredSize( new Dimension(200, 200) );
        vListScroller.setMinimumSize( new Dimension(100,100) );
        
        JPanel vPanel = new JPanel();
        this.aImage = new JLabel();
        
        // création des bouttons de déplacement
        this.aButtonNorth = new JButton("go nord");
        this.aButtonSouth = new JButton("go sud");
        this.aButtonWest = new JButton("go ouest");
        this.aButtonEast = new JButton("go est");
        
        //création des boutons d'action
        this.aButtonLook = new JButton("look");
        this.aButtonJoin = new JButton("join the force");
        this.aButtonBack = new JButton("back");
        this.aButtonInventory = new JButton("inventaire");
        
        //création des boutons étages
        this.aButtonUp = new JButton("up");
        this.aButtonDown = new JButton("down");

        //panel princial
        vPanel.setLayout( new BorderLayout() );
        vPanel.add( this.aImage, BorderLayout.NORTH );
        vPanel.add( vListScroller, BorderLayout.CENTER );
        vPanel.add( this.aEntryField, BorderLayout.SOUTH );
        this.aMyFrame.getContentPane().add( vPanel, BorderLayout.CENTER );
        
        
        //panel gauche
        
        JPanel vPanelAction = new JPanel();
        vPanelAction.add(this.aButtonLook);
        vPanelAction.add(this.aButtonJoin);
        vPanelAction.add(this.aButtonBack);
        vPanelAction.add(this.aButtonInventory);
        
        vPanel.add(vPanelAction, BorderLayout.WEST);
        
        
        //panel droite
        JPanel vPanelGo = new JPanel();
        vPanel.add(vPanelGo, BorderLayout.EAST);
        vPanelGo.setLayout( new BorderLayout());
        vPanelGo.add(this.aButtonNorth, BorderLayout.NORTH);
        vPanelGo.add(this.aButtonSouth, BorderLayout.SOUTH);
        vPanelGo.add(this.aButtonEast, BorderLayout.EAST);
        vPanelGo.add(this.aButtonWest, BorderLayout.WEST);
        
        
        //panel haut
        JPanel vPanelH = new JPanel();
        vPanelH.add(this.aButtonUp, BorderLayout.NORTH);
        vPanelH.add(this.aButtonDown, BorderLayout.SOUTH);
        
        
        
        
        // add some event listeners to some components
        this.aMyFrame.addWindowListener( new WindowAdapter() {
            public void windowClosing(WindowEvent e) { System.exit(0); }
        } );

        this.aEntryField.addActionListener(this);
        this.aButtonNorth.addActionListener(this);
        this.aButtonSouth.addActionListener(this);
        this.aButtonWest.addActionListener(this);
        this.aButtonEast.addActionListener(this);
        this.aButtonLook.addActionListener(this);
        this.aButtonBack.addActionListener(this);
        this.aButtonJoin.addActionListener(this);
        this.aButtonInventory.addActionListener(this);

        this.aMyFrame.pack();
        this.aMyFrame.setVisible( true );
        this.aEntryField.requestFocus();
    } // createGUI()

    /**
     * Actionlistener interface for entry textfield.
     */
    public void actionPerformed( final ActionEvent pE ) 
    {
        // no need to check the type of action at the moment.
        // there is only one possible action: text entry
        Object vSource = pE.getSource();
        if (vSource == aEntryField){
            this.processCommand();
        } // actionPerformed(.)
        if (vSource == aButtonNorth) {
            aEngine.interpretCommand("go nord");
        }
        if (vSource == aButtonSouth) {
            aEngine.interpretCommand("go sud");
        }
        if (vSource == aButtonWest) {
            aEngine.interpretCommand("go ouest");
        }
        if (vSource == aButtonEast) {
            aEngine.interpretCommand("go est");
        }
        if (vSource == aButtonLook) {
            aEngine.interpretCommand("look");
        }
        if (vSource == aButtonBack){
            aEngine.interpretCommand("back");
        }
        if (vSource == aButtonJoin){
            aEngine.interpretCommand("join_the_force");
            //aMyFrame.dispose();
        }
        if (vSource == aButtonInventory){
            aEngine.interpretCommand("inventaire");
        }
    }
    /**
     * A command has been entered. Read the command and do whatever is 
     * necessary to process it.
     */
    private void processCommand()
    {
        String vInput = this.aEntryField.getText();
        this.aEntryField.setText( "" );
        this.aEngine.interpretCommand( vInput );
    } // processCommand()
} // UserInterface 
