
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.net.Socket;

public final class EchoServer {
 
     public static void main(String[] args) throws Exception {
 
         boolean start = true;
         
         try (ServerSocket serverSocket = new ServerSocket(22222)) {
        	 
             while (true) {
            	 
                 try (Socket socket = serverSocket.accept()) {
                     
                     String address = socket.getInetAddress().getHostAddress();
 

                     if(start) {
                    	 
                         System.out.printf("Client onnected: %s%n", address);
                       //System.out.print();
                         start = false;
                     }
 
                     InputStream is = socket.getInputStream();
                     InputStreamReader isr = new InputStreamReader(is, "UTF-8");
                     
                     BufferedReader br = new BufferedReader(isr);
                     String message = br.readLine();
 
                     if(!message.equals("exit")) { 
                    	 
                         OutputStream os = socket.getOutputStream();
                         PrintStream out = new PrintStream(os, true, "UTF-8");
                         
                         String serverSend = "\nServer> " + message;
                         out.println(serverSend);
                         
                     } else {
                    	 
                    	//System.out.print();
                         System.out.printf("Client Disconnected: %s%n", address); // User exited, print that the client disconnected
                         start = true;
                     }
 
                 }
             }
         }
     }
     
 }