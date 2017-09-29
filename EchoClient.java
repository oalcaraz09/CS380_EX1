
/*  Oscar Alcaraz
	CS 380 Networks
	Exercise 1
*/


import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public final class EchoClient {

	private static Scanner input;

    public static void main(String[] args) throws Exception {
       
        String end = ""; 
        boolean start = true;
		
        input = new Scanner(System.in);
        
        	try (Socket socket = new Socket("localhost", 22222)) {
        
                 while(!end.equals("exit")) {                    
				 
                    String address = socket.getInetAddress().getHostAddress();
					
					//Send Message
                    OutputStream os = socket.getOutputStream();
                    PrintStream out = new PrintStream(os, true, "UTF-8");
                    
                    System.out.print("\nClient> ");
                    end = input.nextLine();
					
                    out.println(end); 

                    // Get Message 
                    InputStream is = socket.getInputStream();
                    InputStreamReader iSR = new InputStreamReader(is, "UTF-8");
					
                    BufferedReader br = new BufferedReader(iSR);
                    String messageFromServer = br.readLine();
					
                    if(messageFromServer != null && !messageFromServer.equals("Server> exit"))
                    	
						//System.out.print();
                        System.out.println(messageFromServer);
					
                }
        	}
        
    }

}
