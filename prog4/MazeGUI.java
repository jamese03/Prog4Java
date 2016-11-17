/**
 * MazeGUI - GUI for the Maze escape program CS416 Spring 2013
 * 
 * 02/18/13
 * rdb
 */
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MazeGUI extends JPanel implements Animated
{
  //---------------- class variables ------------------------------
  
  //--------------- instance variables ----------------------------
  Maze        _maze;
  Random      _rng;
  int         _moveTimeDelay = 1000;   
  FrameTimer  _timer;
  JPanel      _drawArea;
  Room        _rm, nextRoom;
  ArrayList<Room>   _path, _fPath;
  int nextR, nextC;
  
  /////////////////////////////////////////////////
  // You'll need more instance variables 
  /////////////////////////////////////////////////
  //------------------ constructor ---------------------------------
  public MazeGUI()     
  {
    this.setLayout( new BorderLayout() );
    _timer = new FrameTimer( _moveTimeDelay, this );
    _rng = new Random( MazeApp.seed );
    
    //////////////////////////////////////////////////////////////
    // You need to create a Vector or ArrayList to store your path
    //   and perhaps other variables you'll want or need.
    //////////////////////////////////////////////////////////////
    _path = new ArrayList<Room>( );
    
    buildGUI();
    buildGraphics();      
  }
  
  //----------------------- buildGUI() ------------------------------
  /**
   * Create 3 buttons in the North panel; that's all we need
   */
  private void buildGUI( )
  {
    //////////////////////////////////////////////
    // You shouldn't need to change this method
    //////////////////////////////////////////////
    JPanel buttonPanel = new JPanel();
    
    JButton placeButton = new JButton( "Place player" ); 
    placeButton.addActionListener( 
                                  // This code shows an example of an "anonymous" inner class 
                                  // that extends ActionListener. After the invocation of the 
                                  // ActionListener constructor, a set of braces indicates that the
                                  // object to be created should be an instance of a new "unnamed"
                                  // class that extends ActionListener and overrides the actionPerformed
                                  // method as specified in the { ... } block that follows the new clause.
                                  new ActionListener()
                                    {
      public void actionPerformed( ActionEvent ae ) 
      { 
        placePlayerAction();
      } 
    }
    );
    buttonPanel.add( placeButton );
    
    JButton keyButton = new JButton( "Magic key escape" ); 
    keyButton.addActionListener( 
                                new ActionListener()
                                  {  public void actionPerformed( ActionEvent ae ) 
      {  magicEscapeAction(); } 
    }
    );
    buttonPanel.add( keyButton );
    
    JButton escButton = new JButton( "Escape" );
    buttonPanel.add( escButton );
    escButton.addActionListener( 
                                new ActionListener()
                                  {  public void actionPerformed( ActionEvent ae ) 
      {  searchEscapeAction(); } 
    }
    );
    
    this.add( buttonPanel, BorderLayout.NORTH );
  }
  //----------------------- buildGraphics() --------------------------
  /**
   * create a draw area, add the maze to it.
   * create a player position and tell the maze about it.
   */
  private void buildGraphics()
  {    
    //////////////////////////////////////////////
    // You shouldn't need to change this method
    //////////////////////////////////////////////
    _drawArea = new JPanel();
    _drawArea.setLayout( null );
    add( _drawArea );                                             
    _maze = new Maze();
    _drawArea.add( _maze );
    placePlayerAction();
  }
  //-------------- placePlayerAction() -------------------------------
  /**
   * generate a random player position in the maze of rooms and 
   * tell the maze about it.
   */
  private void placePlayerAction()
  {
    // Do not change the next 2 lines.
    int row = _rng.nextInt( _maze.getRows() );
    int col = _rng.nextInt( _maze.getCols() );
    
    /////////////////////////////////////////////////////////
    // finish this method
    ////////////////////////////////////////////////////////
    
    _maze.setPlayerRoom( _maze.getRoom( row, col ) );
    _rm = _maze.getRoom( row, col );
    System.out.println( "Random Player placed at : " + row + ", " + col );
    _maze.cleanRooms( );
    _path.clear( );
    
  }
  //-------------- magicEscapeAction() -------------------------------
  /**
   * Respond to magic key escape button
   */
  private void magicEscapeAction()
  {
    System.out.println( "Use that key and get out fast!" );
    /////////////////////////////////////////////////////////
    // finish this method, which may require other methods
    ////////////////////////////////////////////////////////
    int rLoc = _rm.getRow( );
    int cLoc = _rm.getCol( );
    _rm.setColor( Color.CYAN );
    
    while( rLoc <= 4 && cLoc <= 4 && rLoc <= cLoc && rLoc >= 0 )
    {
      rLoc--;
      Room r = _maze.getRoom( rLoc, cLoc );
      if( r == null )
      {
        r = new Room( rLoc, cLoc ); 
      }
      r.setColor( Color.CYAN );
      _maze.setPlayerRoom( r );
      updateDisplay( _drawArea, _moveTimeDelay );
      
      // case 1
    }
    
    while( rLoc <= 4 && cLoc > 4 && 9 - cLoc >= rLoc && rLoc >= 0 )
    {
      rLoc--;
      Room r = _maze.getRoom( rLoc, cLoc );
      if( r == null )
      {
        r = new Room( rLoc, cLoc ); 
      }
      r.setColor( Color.CYAN );
      _maze.setPlayerRoom( r );
      updateDisplay( _drawArea, _moveTimeDelay );
      
      // case 2
    }
    
    while( rLoc > 4 && cLoc <= 4 && rLoc + cLoc >= 9 && rLoc <= 9 )
    {
      rLoc++;
      Room r = _maze.getRoom( rLoc, cLoc );
      if( r == null )
      {
        r = new Room( rLoc, cLoc ); 
      }
      r.setColor( Color.CYAN );
      _maze.setPlayerRoom( r );
      updateDisplay( _drawArea, _moveTimeDelay );
      
      // case 3
    }
    
    while( rLoc > 4 && cLoc > 4 && rLoc >= cLoc && rLoc <= 9 )
    {
      rLoc++;
      Room r = _maze.getRoom( rLoc, cLoc );
      if( r == null )
      {
        r = new Room( rLoc, cLoc ); 
      }
      r.setColor( Color.CYAN );
      _maze.setPlayerRoom( r );
      updateDisplay( _drawArea, _moveTimeDelay );
      
      // case 4
    }
    
    while( rLoc <= 4 && cLoc <=4 && rLoc > cLoc && cLoc >= 0 )
    {
      cLoc--;
      Room r = _maze.getRoom( rLoc, cLoc );
      if( r == null )
      {
        r = new Room( rLoc, cLoc ); 
      }
      r.setColor( Color.CYAN );
      _maze.setPlayerRoom( r );
      updateDisplay( _drawArea, _moveTimeDelay );
      
      // case 5
    }
    
    while( rLoc <= 4 && cLoc > 4 && rLoc + cLoc > 9 && cLoc <= 9 )
    {
      cLoc++;
      Room r = _maze.getRoom( rLoc, cLoc );
      if( r == null )
      {
        r = new Room( rLoc, cLoc ); 
      }
      r.setColor( Color.CYAN );
      _maze.setPlayerRoom( r );
      updateDisplay( _drawArea, _moveTimeDelay );
      
      // case 6
    }
    
    while( rLoc > 4 && cLoc <= 4 && rLoc + cLoc < 9 && cLoc >= 0 )
    {
      cLoc--;
      Room r = _maze.getRoom( rLoc, cLoc );
      if( r == null )
      {
        r = new Room( rLoc, cLoc ); 
      }
      r.setColor( Color.CYAN );
      _maze.setPlayerRoom( r );
      updateDisplay( _drawArea, _moveTimeDelay );
      
      // case 7
    }
    
    while( rLoc > 4 && cLoc > 4 && rLoc < cLoc && cLoc <= 9 )
    {
      cLoc++;
      Room r = _maze.getRoom( rLoc, cLoc );
      if( r == null )
      {
        r = new Room( rLoc, cLoc ); 
      }
      r.setColor( Color.CYAN );
      _maze.setPlayerRoom( r );
      updateDisplay( _drawArea, _moveTimeDelay );
      
      // case 8
    }
    
  }
  //-------------- searchEscapeAction() -------------------------------
  /**
   * Respond to  escape button
   */
  private void searchEscapeAction()
  {
    System.out.println( "Search for an escape path" );
    /////////////////////////////////////////////////////////
    // finish this method, which will certainly require at least
    //    one more method -- the one that actually does the recursion.
    ////////////////////////////////////////////////////////
    escape( _rm, _path );
    newFrame( );
    printPath( _path );
  }
  //---------------- boolean escape----------------------------------
  /**
   * recursive algorithm for finding escape path
   */
  private boolean escape( Room r, ArrayList<Room> p )
  {
    int rLoc = r.getRow( );
    int cLoc = r.getCol( );
    
    if( p.contains( r ) )
    {
      return false;
    }
    p.add( r );
    
    if( r.isDummy( ) )
    {
      nextR = r.getRow( );
      nextC = r.getCol( );
      return true;
    }
    
    for( Room.Door door:  Room.Door.values() )
    {
      if( r.doorIsOpen( door ) )
      {
        nextR = r.getRow( );
        nextC = r.getCol( );
        switch( door )
        {
          case N :
            nextR = r.getRow( ) - 1;
            break;
          case E :
            nextC = r.getCol( ) + 1;
            break;
          case W :
            nextC = r.getCol( ) - 1;
            break;
          case S :
            nextR = r.getRow( ) + 1;
            break;
        }
        nextRoom = _maze.getRoom( nextR, nextC );
        if( nextRoom == null )
        {
          nextRoom = new Room( nextR, nextC );
          return true;
        }
        if( escape( nextRoom, p ) )
          return true;
      }
    }
  return false;
  }

//--------------------PrintPath---------------------------------
  /**
   * 
   * 
   */
private void printPath( ArrayList<Room> p )
{
 for( int i = 0; i < p.size( ); i++ )
 {
  System.out.print( p.get( i ) + " " ); 
 }
}

//----------------------- updateDisplay ---------------------------
/**
 * This method invokes a special Swing method that immediately does a
 * repaint of the specified region of the specified component.
 * It's just what we need to update the display during recursion
 * We'll always do the entire component. This will also sleep
 * for the specified msecs.
 */
private void updateDisplay( JComponent comp, int msec )
{
  comp.paintImmediately( comp.getBounds() );
  try 
  { 
    Thread.sleep( msec ); 
  } 
  catch (InterruptedException ie ) 
  {
    System.err.println( "updateDisplay InterruptedException: " +
                       ie.getMessage() );
  }
}
//+++++++++++++++++++++ animated interface ++++++++++++++++++++++++++++++
//------------------------ newFrame ----------------------------------
/**
 * Here you need to implement the post-search traversal of your
 * final search path.
 */
public void newFrame() 
{
  ////////////////////////////////////////////////////////
  // On each new frame, you'll get another room from your
  //   path array, color it, tell the maze that this is where
  //   the player now should be displayed and then return.
  // You also need to find out if you've shown all rooms on
  //   the path so you can turn the interval timer off.
  ////////////////////////////////////////////////////////
  for( int i = 0; i < _path.size( ); i++ )
  {
   _path.get( i ).setColor( Color.BLUE );
//   if( _path.get( i ).getColor( ) == Color.BLUE && _path.get( i ).doorIsOpen( door ) == false )
//     _path.get( i ).setColor( Color.MAGENTA );
   _maze.setPlayerRoom( _path.get( i ) );
   updateDisplay( _drawArea, _moveTimeDelay );
  }
  
  
  this.repaint();   
}
//----------------- isAnimated -----------------------------------
private boolean animated = true;

public boolean isAnimated()
{
  return animated;
}
//---------------- setAnimated ------------------------------------
public void setAnimated( boolean onOff )
{
  animated = onOff;
}
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//------------------------------- main -------------------------------
public static void main( String [] args ) 
{
  MazeApp app = new MazeApp( "MazeApp demo", args );
}
}
