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

import java.applet.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
    


public class Sketchpad extends Applet implements Runnable, MouseListener, MouseMotionListener{
	
	/**
	 * This applet follows mouse drags and sketches its trace.
	 * Author: Mehmet Gencer, mgencer@cs.bilgi.edu.tr
	 *
	 * To record the line segments that make up the sketch, a linked list is used. 
	*/
	
	private static final long   serialVersionUID = 1L;
    private static final int    HEIGHT = 400;		  // - Height size for window           - //
    private static final int    WIDTH  = 400;		  // - Width  size for window           - //

    int timer_t = 50;
	LineSegment head;
	int lastX, lastY;
	connection mycon;
	private javax.swing.Timer timer;    
	
	public void init()  {
		mycon = new connection();
		mycon.start();
		head = null;
		setLayout(null); 
		//Frame aFrame = new Frame(TITLE); 
 		  setSize(WIDTH,HEIGHT); 
		  setVisible(true); 
          //aFrame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
          addMouseListener(this);
          addMouseMotionListener(this);
        timer = new javax.swing.Timer(timer_t, new MoveListener());
        timer.start();	
  
	}    
 
	
	
	public void paint(Graphics g) {
		
		for(LineSegment pointer=head;pointer!=null;pointer=pointer.next)
			pointer.drawLine(g);
		mycon.paint(g);
	}
    
    	public void mousePressed(MouseEvent event) {
		lastX=event.getX();
		lastY=event.getY();
		timer.setDelay(mycon.getTime()); // - for minimize the refresh time graphical problem - // 
    	}								 // - *some segments can be lost... - //
  
    	
    	
    	public void mouseDragged(MouseEvent event) {
    	timer.setDelay(mycon.getTime());	
		int currentX=event.getX();
		int currentY=event.getY();
		
		head=new LineSegment(lastX,lastY,currentX,currentY,head);
		lastX=currentX;
		lastY=currentY;
		try {
			mycon.Send_Data(head.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		repaint();
    	}
    	
    	public void mouseMoved(MouseEvent event) {timer.setDelay(mycon.getTime());}
    	public void mouseReleased(MouseEvent event){}
    	public void mouseClicked(MouseEvent event) {}
    	public void mouseEntered(MouseEvent event) {}
    	public void mouseExited(MouseEvent event)  {
    		
    	}



		public void run() {
			// TODO Auto-generated method stub
	
		}

		public class MoveListener implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				 
				  repaint();
				   }
				
				}
		
		public class listenW implements WindowListener {
		    @SuppressWarnings({ "static-access", "deprecation" })
			public void windowClosing(WindowEvent arg0) {
		      
		    mycon.output.println("/exit");
		    mycon.stop();	
		   
		      System.exit(0);
		      
		    }

		    public void windowOpened(WindowEvent arg0) {
		    }

		    public void windowClosed(WindowEvent arg0) {

		    }

		    public void windowIconified(WindowEvent arg0) {
		    }

		    public void windowDeiconified(WindowEvent arg0) {
		    }

		    public void windowActivated(WindowEvent arg0) {
		    }

		    public void windowDeactivated(WindowEvent arg0) {
		    }

		  }
 
}


		
		
		

