import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.swing.JOptionPane;


public class ClienteUDP {

	private static int SERVER_PORT = 9091;
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//Enviar packets
		String serverAddress=JOptionPane.showInputDialog("Ingresar direccion IP :\n" + "corriendo el servicio en el puerto "+ SERVER_PORT +":");
		DatagramSocket clienteSocket = new DatagramSocket();
		byte bufferSend[] = serverAddress.getBytes();
		DatagramPacket sendPacket =  new DatagramPacket(bufferSend,bufferSend.length,InetAddress.getByName(serverAddress),SERVER_PORT);
		clienteSocket.send(sendPacket);
		
		//Recibir packets
		byte bufferReceive[] = new byte[128];
		DatagramPacket receivePacket = new DatagramPacket(bufferReceive, bufferReceive.length);
		clienteSocket.receive(receivePacket);
		
		//Transformar de bytes a STring
		InputStream myInputStream = new ByteArrayInputStream(receivePacket.getData());
		BufferedReader input = new BufferedReader(new InputStreamReader(myInputStream));
		String answer = input.readLine();
		
		//Despliega mensaje
		JOptionPane.showMessageDialog(null, answer);
		clienteSocket.close();
		System.exit(0);
	}

}
