package epn.edu.ec;
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

		private static int PUERTO_SERVIDOR = 9091;
		
		public static void main(String[] args) throws IOException {
			//Enviar paquetes
			String serverAddress=JOptionPane.showInputDialog("Ingrese la dirección IP :\n" + "corriendo el servicio en el puerto "+ PUERTO_SERVIDOR  +":");
			DatagramSocket clienteSocket = new DatagramSocket();
			byte bufferSend[] = serverAddress.getBytes();
			DatagramPacket sendPacket =  new DatagramPacket(bufferSend,bufferSend.length,InetAddress.getByName(serverAddress),PUERTO_SERVIDOR );
			clienteSocket.send(sendPacket);
			
			//Recibir paquetes
			byte bufferReceive[] = new byte[128];
			DatagramPacket receivePacket = new DatagramPacket(bufferReceive, bufferReceive.length);
			clienteSocket.receive(receivePacket);
			
			//Transformar de bytes a String
			InputStream myInputStream = new ByteArrayInputStream(receivePacket.getData());
			BufferedReader input = new BufferedReader(new InputStreamReader(myInputStream));
			String answer = input.readLine();
			
			//Despliega mensaje
			JOptionPane.showMessageDialog(null, answer);
			clienteSocket.close();
			System.exit(0);
		}

	}


