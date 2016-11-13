/*
 ######322##Network Programming###################- Shared scribble  -####
 #                                                                       #
 # ######                                  #####                         #
 # #     # #    # #####    ##   #    #    #     #   ##   #    # # #    # #
 # #     # #    # #    #  #  #  #   #     #        #  #  #    #   ##   # #
 # ######  #    # #    # #    # ####       #####  #    # ###### # # #  # #      
 # #     # #    # #####  ###### #  #ID:10876041 # ###### #    # # #  # # #
 # #     # #    # #   #  #    # #   #     #     # #    # #    # # #   ## #
 # ######   ####  #    # #    # #    #     #####  #    # #    # # #    # #
 #										     #                           #
 #################################################knuth.cs.bilgi.edu.tr###

  -----------------------------------------------------------------------
    		- @Burak S. ID: 10876041
  	 		- Comp 322 - Shared scribble
  ------------------------------------------------------------------------*/

import java.applet.Applet;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



public class Test {
    private static final String TITLE  = "Scribble";	
	private static final int    HEIGHT = 400;		  // - Height size for window           - //
	private static final int    WIDTH  = 400;		  // - Width  size for window           - //
	    
public static void main(String[] args0){
	Applet newOBJ = new Sketchpad();
	Frame aFrame = new Frame(TITLE); 
	aFrame.add(newOBJ);
	aFrame.addWindowListener(new FrameListener());
	aFrame.setSize(WIDTH, HEIGHT);
	aFrame.setVisible(true);
	newOBJ.setVisible(true);
    newOBJ.start();
	newOBJ.init();
}

}

class FrameListener extends WindowAdapter
{
   public void windowClosing(WindowEvent e)
  {
    System.exit(0);
  }
}


