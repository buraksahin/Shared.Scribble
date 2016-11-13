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

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream; 
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.Scanner;
 
public class connection extends Thread{
	

	static String         server_A    = null;  // - get data from server as String
    static Socket         client_S    = null;  // - Client Socket
    static PrintStream    output      = null;  // - outputs
    static BufferedReader input       = null;  // - inputs
    static boolean        network_S   = true;  // - disconnect
    static String[]       segmentsXY;          // - segments
    String                delimiter   = "-";
    boolean getname;
    boolean visibleWIN = false;
    Scanner mySCN = new Scanner(System.in);
    int timeD = 500;
    //----------------//
    // - Variables - //
	//--------------//
    
	int port = 	1851;     // - * Server port address - //
	// String host = "192.168.2.5";	 // host address - //
	String host;
	LineSegment head;   
	
	public connection(){

	}
	
	public void paint(Graphics g) {
		
		for(LineSegment pointer=head;pointer!=null;pointer=pointer.next)
			pointer.drawLine(g);
 
	}
    
	 public void Send_Data (String data_head) throws IOException {
	        if(network_S) System.out.println("Disconnected..."); 
	        else 
	        output.println(data_head);  // - Send line segments - //
	    }
	 
	 public int getTime(){
		 return this.timeD;
	 }
	 
	public void exitS(){
		 try {
			 	
	        	output.println("/exit");
			    input.close();
			    output.close();
	            client_S.close();
	        	
			    
			    
	         } catch (IOException e) {
	             e.printStackTrace();
	         }}
            
	 
	public void run() {
		System.out.println("Please enter an ip number for connect the server:");
		host = mySCN.nextLine();
			
		
		System.out.println("Server ip and port is " + host + "/" + port);
		head = new LineSegment(0,0,0,0,head);
 
		 try {
			 client_S = new Socket(host, port);
             output  = new PrintStream(client_S.getOutputStream());
             input   = new BufferedReader(new InputStreamReader(client_S.getInputStream()));
         	} 
		 catch (UnknownHostException e) {System.err.println("Unknown host: "+host);} 
		 catch (IOException e) {System.err.println("Could not connect to the " + host);}
		 
		
		 String listenS = null;
		try{
		if(client_S.isConnected()){ getname = true;} 
		else{System.out.println("Couldn't connect to server...");}
		}
		catch(Exception e){
			System.out.println();
		}
		
		 while(getname){
		 
			 try {
            	 //System.out.println("Please insert a user name: ");
            	 //String uNAME = mySCN.nextLine();
				 int id = new Random().nextInt(9999);
				 String uNAME = "user_";
				 uNAME = (uNAME + Integer.toString(id));
				 
				 output.println(uNAME);
            	 listenS = input.readLine();
            	 //Welcome:
            	 System.out.println(listenS);
            	 if(listenS.equals("Welcome")){
            		 getname = false;
            		 network_S = false;
            	 }
		 }catch(IOException e){}
			 }
		
				 
		
         while(!network_S) {
             
        	 try {
        		 timeD = 800;
            	 server_A = input.readLine();

            	 // - checking inputs and catching *xYz declaring -> segment's coordinates- //
            	 // - xYz-x1-y1-x2-y2 <- data - //
            	 
            	 
            	 if(server_A.startsWith("xYz")){
            	 segmentsXY =  server_A.split(delimiter);
            	             	             	 
            	 //-@Warning!-----------------------------------------//
            	 // if cursor move out of the screen check the values //
            	 //---------------------------------------------------//
            	 boolean check_free = false; 
            	 
            	 for(int x = 1; x<segmentsXY.length; x++){
            	 if(segmentsXY[x].equals("")){
            		 check_free = true;
            	 }
            	 }
            		 
            	if(check_free){
            	 for(int x = 1; x<segmentsXY.length; x++){
            		 
            			 segmentsXY[x] = "1";
            	}
            	 check_free = false;
            	}// - end of the checking... - //
            	
            	
            	 timeD = 50;
            	 head = new LineSegment(Integer.parseInt(segmentsXY[1]),Integer.parseInt(segmentsXY[2]),  // - create a head for paint - //
            			 				Integer.parseInt(segmentsXY[3]),Integer.parseInt(segmentsXY[4]),head);
            	 }
            	 else{
            		 System.out.println(server_A);
            		 
            	 }
             } catch (IOException e) {
                 e.printStackTrace();
             }

     }}}

