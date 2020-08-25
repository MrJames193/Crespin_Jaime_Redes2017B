import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;


public class ServerUDP {
	private static int PUERTO = 9091;
	public static void main(String[] args) throws IOException{
		
		DatagramSocket serverSocket = new DatagramSocket(PUERTO);
		System.err.println(" Servidor escuchando en el puerto  " + PUERTO + " usando una conexión UDP\n");
		long initialTime = System.currentTimeMillis();
		System.out.println("Tiempo inicial: "+initialTime+"\n");
		
		try{
			while(true){
				//Paquete recibido
				  byte bufferReceive[] = new byte[128];
				  DatagramPacket receivePacket = new DatagramPacket(bufferReceive,bufferReceive.length);
				  serverSocket.receive(receivePacket);
				  InetAddress clientAddress = receivePacket.getAddress();
				  int clientePuerto = receivePacket.getPort();
				  System.out.println("Puerto del cliente: "+clientePuerto+"\n");
				//Paquete enviadoas
				  String msg = "Mensaje desde la Laptop de Daniel";
				  byte bufferSend[] = msg.getBytes();
				  DatagramPacket sendPacket =  new DatagramPacket(bufferSend,bufferSend.length,clientAddress,clientePuerto);
				  serverSocket.send(sendPacket);
	
				  
			}
		}
		finally{
			serverSocket.close();
			
		}
		

	}

}
