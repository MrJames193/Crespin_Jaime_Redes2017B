import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ServerTCP {

	private static int PORT = 9090;

	public static void main(String[] args) throws IOException {

		ServerSocket serverSocket = new ServerSocket(PORT);
		String clientSentence;
		ArrayList suma = new ArrayList<>();
		System.out.println("Servidor escuchando en el puerto : " + PORT);

		try {
			while (true) {
				Socket socket = serverSocket.accept();
				try {
					
					BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());
					clientSentence = inFromClient.readLine();
					StringTokenizer token = new StringTokenizer(clientSentence, " ");
					for (int i = 0; i < 2; i++) {
						suma.add(Integer.parseInt(token.nextToken()));
					}
					String out = "El resultado de la suma de a + b  = "
							+ (Integer.parseInt(suma.get(0).toString()) + Integer.parseInt(suma.get(1).toString()));
					outToClient.writeBytes(out);
				} finally {
					socket.close();
				}
			}
		} finally {
			serverSocket.close();
		}

	}

}