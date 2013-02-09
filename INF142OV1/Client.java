import java.io.*;
import java.net.*;

public class Client {
	//Teste commit
	//Legge til ny comment 2
	private int clientId;
	private String serverIP = "";
	private int serverPort;
	private Socket clientSoc = null;
	private PrintWriter out = null;
	private BufferedReader in = null;
	public Client(String ip, int port){
		serverIP = ip;
		serverPort = port;
	}
	public Client(){
		serverIP = "127.0.0.1";
		serverPort = 8080;
	}
	public int connect() throws IOException{
		try{
			clientSoc = new Socket(serverIP, serverPort);
			out = new PrintWriter(clientSoc.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSoc.getInputStream()));
			
		}catch (UnknownHostException e){
			System.err.println("Unkown host: "+serverIP+":"+serverPort);
			return 1;
		}catch (IOException e){
			System.err.println("Couldn't get I/O for the connection to: "
								+serverIP+":"+serverPort);
			return 2;
		}
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));	
		out.println("getID");
		clientId = Integer.parseInt(in.readLine());
		stdIn.close();
		return 0;
	}
	public String talk(String send) throws IOException{
		String response = "";
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		while ((send = stdIn.readLine()) != null){
			out.println(send);
			response += in.readLine();
		}
		stdIn.close();
		return response;
	}
	
	public int close() throws IOException{
		out.close();
		in.close();
		clientSoc.close();
		return 0;
	}
	
}
	

