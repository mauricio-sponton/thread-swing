package threads;

import javax.swing.JOptionPane;

import threads.fila.TelaFilaThread;
import threads.time.TimeThread;

public class Executar {
	
	public static void main(String[] args) {
		
		
		
		String opcao = JOptionPane.showInputDialog("Digite 1 para Time Thread e 2 para Envio em massa de email");
		
		if(opcao.equals("1")) {
			var timeThread = new TimeThread();
		}else if(opcao.equals("2")) {
			var telaFilaThread = new TelaFilaThread();
		}else {
			JOptionPane.showMessageDialog(null, "Opção inválida");
		}
	}

}
