package threads;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TimeThread extends JDialog {
	private static final long serialVersionUID = 1L;

	// Painel de componentes
	private JPanel jPanel = new JPanel(new GridBagLayout());

	private JLabel labelPrimeiraThread = new JLabel("Time Thread 1");
	private JTextField inputPrimeiraThread = new JTextField();

	private JLabel labeSegundaThread = new JLabel("Time Thread 2");
	private JTextField inputSegundaThread = new JTextField();

	private JButton startButton = new JButton("Start");
	private JButton stopButton = new JButton("Stop");

	private Runnable primeiraRunThread = new Runnable() {

		@Override
		public void run() {
			while (true) {
				inputPrimeiraThread
						.setText(new SimpleDateFormat("dd/MM/yyyy hh:mm.ss").format(Calendar.getInstance().getTime()));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};
	
	private Runnable segundaRunThread = new Runnable() {

		@Override
		public void run() {
			while (true) {
				inputSegundaThread
						.setText(new SimpleDateFormat("dd/MM/yyyy hh:mm.ss").format(Calendar.getInstance().getTime()));
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};
	
	private Thread primeiraThread;
	private Thread segundaThread;

	public TimeThread() {
		setTitle("Time Thread");
		setSize(new Dimension(240, 240));
		setLocationRelativeTo(null);
		setResizable(false);

		var gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.insets = new Insets(5, 10, 5, 5);
		gridBagConstraints.anchor = GridBagConstraints.WEST;

		configurarComponente(labelPrimeiraThread, 200, 25, gridBagConstraints, DirecaoGrid.VAZIO);
		jPanel.add(labelPrimeiraThread, gridBagConstraints);

		configurarComponente(inputPrimeiraThread, 200, 25, gridBagConstraints, DirecaoGrid.Y);
		inputPrimeiraThread.setEditable(false);
		jPanel.add(inputPrimeiraThread, gridBagConstraints);

		configurarComponente(labeSegundaThread, 200, 25, gridBagConstraints, DirecaoGrid.Y);
		jPanel.add(labeSegundaThread, gridBagConstraints);

		configurarComponente(inputSegundaThread, 200, 25, gridBagConstraints, DirecaoGrid.Y);
		inputSegundaThread.setEditable(false);
		jPanel.add(inputSegundaThread, gridBagConstraints);

		gridBagConstraints.gridwidth = 1;

		configurarComponente(startButton, 92, 25, gridBagConstraints, DirecaoGrid.Y);
		jPanel.add(startButton, gridBagConstraints);

		configurarComponente(stopButton, 92, 25, gridBagConstraints, DirecaoGrid.X);
		jPanel.add(stopButton, gridBagConstraints);
		
		startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				primeiraThread = new Thread(primeiraRunThread);
				primeiraThread.start();
				
				segundaThread = new Thread(segundaRunThread);
				segundaThread.start();
				
				stopButton.setEnabled(true);
				startButton.setEnabled(false);
			}
		});
		
		stopButton.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				primeiraThread.stop();
				segundaThread.stop();
				startButton.setEnabled(true);
				stopButton.setEnabled(false);
			}
		});
		
		stopButton.setEnabled(false);

		add(jPanel, BorderLayout.WEST);
		setVisible(true);
	}

	private void configurarComponente(JComponent componente, int largura, int altura,
			GridBagConstraints gridBagConstraints, DirecaoGrid direcao) {
		componente.setPreferredSize(new Dimension(largura, altura));

		switch (direcao.getDescricao()) {
		case "X":
			gridBagConstraints.gridx++;
			break;
		case "Y":
			gridBagConstraints.gridy++;
			break;
		}
	}

}
