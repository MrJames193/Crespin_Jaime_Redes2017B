
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.management.StringValueExp;
import javax.swing.JOptionPane;

public class ClienteTCP {

	private static int SERVER_PORT = 9090;

	public static void main(String[] args) throws UnknownHostException,
			IOException {

		String serverAddress = JOptionPane
				.showInputDialog("Ingrese la dirección IP de la computadora que está\n"
						+ " corriendo el servicio en el puerto: " + SERVER_PORT);

		Socket clientSocket = new Socket(serverAddress, SERVER_PORT);
		
		DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());
		
		String a = JOptionPane.showInputDialog("Ingrese el valor de a: ");
		String b = JOptionPane.showInputDialog("Ingrese el valor de b: ");
		
		String out = a + " " + b + "\n"; 
		outputStream.writeBytes(out);

		//Obtener el paquete que me envia el servidor
		InputStreamReader inputStream = new InputStreamReader(clientSocket.getInputStream());
		
		//Leyendo el mensaje
		BufferedReader input = new BufferedReader(inputStream);
		String answer = input.readLine();

		JOptionPane.showMessageDialog(null, answer);
		clientSocket.close();
	}

}