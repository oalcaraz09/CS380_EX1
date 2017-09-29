
/*  Oscar Alcaraz
	CS 380 Networks
	Exercise 1
*/


import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.net.Socket;

public final class EchoServer {

    public static void main(String[] args) throws Exception {

        try (ServerSocket serverSocket = new ServerSocket(22222)) {
			
            while (true) {
				
                try  {
                    
                    Socket socket = serverSocket.accept();
					
                    Thread thread = new Thread(new AdditionalThread(socket));
                    thread.start();

                } catch (Exception e) {}
            }
        }
    }


    static final class AdditionalThread implements Runnable {
        
        private Socket socket;
        private String address;
 
        public AdditionalThread(Socket socket) {
			
            this.socket = socket;
            address = socket.getInetAddress().getHostAddress();
			
        }

        public void run() {

            System.out.printf("Client Connected: %s%n", address);
			//System.out.print();

            try {
             
                InputStream is = socket.getInputStream();
                InputStreamReader iSR = new InputStreamReader(is, "UTF-8");
				
                BufferedReader br = new BufferedReader(iSR);
                String message = br.readLine();


                while(message != null) {
					
                    OutputStream os = socket.getOutputStream();
                    PrintStream out = new PrintStream(os, true, "UTF-8");
					
                    String serverSend = "Server >> " + message;
                    out.println(serverSend);
					
                    message = br.readLine();
                    
                }

				//System.out.print();
                System.out.printf("Client Disconnected: %s%n", address);
				
                socket.close();
				
            } 
            catch (Exception e) {}
        }
    }
    
}