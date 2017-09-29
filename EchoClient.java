
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
       input = new Scanner(System.in);
       
       while(!input.equals("exit")) {
    	   
    	  try (Socket socket = new Socket("localhost", 22222)) {
      
                String address = socket.getInetAddress().getHostAddress();
                
                OutputStream os = socket.getOutputStream();
                PrintStream out = new PrintStream(os, true, "UTF-8");
                
                System.out.print("\nClient> ");
                end = input.nextLine();
                
                out.println(end); 
 
 
                InputStream is = socket.getInputStream();               
                InputStreamReader iSR = new InputStreamReader(is, "UTF-8");
                
                BufferedReader br = new BufferedReader(iSR);
                String messageFromServer = br.readLine();
                
                if(messageFromServer != null)
                
                	//System.out.print();	
                  System.out.println(messageFromServer);
                
    	  }
       }
    }
}