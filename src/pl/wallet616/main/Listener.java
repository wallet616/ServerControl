package pl.wallet616.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
 
public class Listener {
 
    private static Socket socket;
 
    public static void run()
    {
        try
        {
        	// Port definition and creation socket
            int port = 25000;
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("> Server listening started at the port: " + port + ".");
 
            while(true)
            {
                // Reading the message from the client
                socket = serverSocket.accept();
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String commandInput = br.readLine();
 
                // Formatting received message
                String returnMessage = "";
                String[] selectedFunctionBuffer = {"", "", ""};
                Boolean vailCommand = false;
                
                String decodedInput = Decode.decode(commandInput);
                
                try 
    			{
                	if ((decodedInput.length() - decodedInput.replaceAll(":", "").length()) >= 2)
    				{
                		selectedFunctionBuffer = decodedInput.split(":", 3);
                		vailCommand = true;
    				} else {
    					selectedFunctionBuffer[0] = decodedInput;
    					vailCommand = false;
    				}
    			}
    			catch (Exception e)
    		    {
    				vailCommand = false;
    			}
                
                if (vailCommand) 
                {
                	if (selectedFunctionBuffer[0].equals(DataRead.readData(0))) {
                		if (selectedFunctionBuffer[1].equals("security")) {
                			System.out.println("> Security command received.");
                			if (Security.security(selectedFunctionBuffer[2]))
                			{
                				returnMessage = "VaildSucces";
                			} else {
                				returnMessage = "VaildFalse";
                			}
                		} else if (selectedFunctionBuffer[1].equals("ts3")) {
                			System.out.println("> Ts3 server command received.");
                			if (Ts3.ts3(selectedFunctionBuffer[2]))
                			{
                				returnMessage = "VaildSucces";
                			} else {
                				returnMessage = "VaildFalse";
                			}
                		} else {
                			System.out.println("> Unrecognised command received.");
                			returnMessage = "InvaildCommand";
                		}
                	} else {
                		returnMessage = "InvaildKey";
                		System.out.println("> Invaild security key received.");
                	}
                } else {
                	returnMessage = "InvaildFormat";
                	System.out.println("> Invaild command format received.");
                }
 
                //Sending the response back to the client.
                if (vailCommand) {
                	returnMessage = Encode.encode(returnMessage);
                }
                OutputStream os = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter bw = new BufferedWriter(osw);
                bw.write(returnMessage + "\n");
                bw.flush();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}