package threads.fila;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TelaFilaThread extends JDialog {
	private static final long serialVersionUID = 1L;

	// Painel de componentes
	private JPanel jPanel = new JPanel(new GridBagLayout());

	private JLabel labelNome = new JLabel("Nome");
	private JTextField inputNome = new JTextField();

	private JLabel labelEmail = new JLabel("Email");
	private JTextField inputEmail = new JTextField();

	private JButton addButton = new JButton("Adicionar");
	private JButton stopButton = new JButton("Parar");
	
	private ImplementacaoFilaThread fila = new ImplementacaoFilaThread();
	
	public TelaFilaThread() {
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

		labelNome.setPreferredSize(new Dimension(200, 25));
		jPanel.add(labelNome, gridBagConstraints);

		inputNome.setPreferredSize(new Dimension(200, 25));
		gridBagConstraints.gridy ++;
		jPanel.add(inputNome, gridBagConstraints);

		labelEmail.setPreferredSize(new Dimension(200, 25));
		gridBagConstraints.gridy ++;
		jPanel.add(labelEmail, gridBagConstraints);

		inputEmail.setPreferredSize(new Dimension(200, 25));
		gridBagConstraints.gridy ++;
		jPanel.add(inputEmail, gridBagConstraints);

		gridBagConstraints.gridwidth = 1;

		addButton.setPreferredSize(new Dimension(92, 25));
		gridBagConstraints.gridy ++;
		jPanel.add(addButton, gridBagConstraints);

		stopButton.setPreferredSize(new Dimension(92, 25));
		gridBagConstraints.gridx ++;
		jPanel.add(stopButton, gridBagConstraints);
		
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(fila == null) {
					fila = new ImplementacaoFilaThread();
					fila.start();
				}
				
				//Simulando envio de 100 emails
				for(int quantidade = 0; quantidade <= 100; quantidade ++) {
					
					var usuario = new Usuario();
					usuario.setNome(inputNome.getText());
					usuario.setEmail(inputEmail.getText());
					
					fila.add(usuario);
				}
			}
		});
		
		stopButton.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				fila.stop();
				fila = null;
			}
		});
		
		fila.start();
		add(jPanel, BorderLayout.WEST);
		setVisible(true);
	}

}
