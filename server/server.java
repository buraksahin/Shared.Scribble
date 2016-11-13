
import java.io.*;
import java.net.*;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class server {
	static  Socket                clientS  = null;
    static  ServerSocket          serverS  = null;
    static  Collection<serverT>   CoS      = new ArrayList<serverT>();   
    
    public static void main(String args[]) {

        int port= 1851; // - server port address - //
        
        try {
        	serverS = new ServerSocket(port);
          }
          catch (IOException e) {System.out.println(e);}
         
         System.out.println("Server has started to running on the "+port+" port.");
         
         while(true){
             try {
                 clientS           = serverS.accept();
                 serverT newServer = new serverT(clientS,CoS); 
                 newServer.start();
                 check_Users   xs    = new check_Users(CoS);
                 xs.start();
             }
             catch (IOException e) {System.out.println(e);}
         }
         }
} 


class check_Users extends Thread{
	@SuppressWarnings("rawtypes")
	Collection s;
	Iterator<serverT> nameSTEP;
	@SuppressWarnings("rawtypes")
	public check_Users(Collection c){
		this.s = c;
	}
	@SuppressWarnings("unchecked")
	public void run(){
		
		while(true){
		 Iterator<serverT> it0 = s.iterator();
		serverT soct;
         while(it0.hasNext()) {
             soct = it0.next();
             if(!soct.isAlive()) {
            	 System.out.println(""+ soct.name + " left the server...");
				 s.remove(soct);
				 System.out.println(s.size() + " user online...\n");
				 it0 = s.iterator();
			} 

         }try {
			check_Users.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
		 

     }
	}

    
class serverT extends Thread{
    
    BufferedReader input   = null;
    PrintStream    output  = null;
    Socket         clientS = null;       
    @SuppressWarnings("rawtypes")
	Collection     CoS; 
    String         name    = null;
    String         bufferX = null;
    boolean check = false;
    
    int ID = new Random().nextInt(99999);
    
    
    Iterator<serverT> nameSTEP;
    String onBellek;
    
    
    // - Constructor - //
    @SuppressWarnings("rawtypes")
	public serverT(Socket clientS, Collection CoS){
    this.clientS = clientS;
    this.CoS = CoS;
    }


    
    @SuppressWarnings("unchecked")
	public void run() 
    {
        String space;
    try{
        input  = new BufferedReader(new InputStreamReader(clientS.getInputStream())); 
        output = new PrintStream(clientS.getOutputStream());
        

        while(true) {
            
        	this.name = input.readLine(); 
            nameSTEP  = CoS.iterator();
     
            
            while(nameSTEP.hasNext()) {

                serverT sCK = nameSTEP.next();           
                
                if(sCK.name.equals(this.name) && !sCK.equals(this) ) {
                    this.output.println("" + this.name + " name had been using...");
                    check=true;
                }
 
                System.out.println("equals: "+sCK.equals(this));
                System.out.println("id: "+sCK.ID);
                 

            }
            if(check) {
                System.out.println("This user name had been choosed...\n"); 
                check = false;
                continue; 
            
            }
            
            if(this.name.length() < 4 || this.name == null)  {
                this.output.println("Please enter a long user name...");
                continue;
            }
            System.out.println(this.name);
            break;
        }
        CoS.add(this);
        
        this.output.println("Welcome"); 
        
        Iterator<serverT> newUSER = CoS.iterator();
        // - Declare new user - //
        while(newUSER.hasNext()) {
            serverT sCK2= newUSER.next();
            if(sCK2!=this)
            	sCK2.output.println("Connected " + name);
            	
        }
        System.out.println(CoS.size() + " user online...\n");
        
      
        while(true) {
            space = input.readLine();
            
           
            if(space.startsWith("/exit")) {
            	System.out.println(this.name+" disconnected...\n");
                Iterator<serverT> it0 = CoS.iterator();
                while(it0.hasNext()) {
                    serverT soct = it0.next();
                    if(soct != this)
                    soct.output.println(this.name+" disconnected...\n");
                    
                   
                }
   
                this.input.close();
                this.output.close();
                this.clientS.close();
                CoS.remove(this);
                System.out.println(CoS.size() + " user online...\n");
                break;
            }
            
            if(space.startsWith("/whois")) {
                bufferX=" Now, "+CoS.size()+" user online...\n";
                
                Iterator<serverT> it1 = CoS.iterator();
                while(it1.hasNext()) {
                    serverT soct1 = it1.next();
                    bufferX = bufferX + soct1.name+", ";
                }
                
                this.output.println(bufferX);
                continue;
                
            }
            
            Iterator<serverT> it3 = CoS.iterator();
            while(it3.hasNext()) {
                serverT soct2 = it3.next();
                if(!soct2.equals(this))
                soct2.output.println(""+space);
            }
            
            
        }
    }  catch(IOException e){};
    }
}