package threads.fila;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ImplementacaoFilaThread extends Thread {

	private static ConcurrentLinkedQueue<Usuario> fila = new ConcurrentLinkedQueue<>();

	public void add(Usuario usuario) {
		fila.add(usuario);
	}

	@Override
	public void run() {

		while (true) {

			// Bloqueia o acesso a lista por outro processos
			synchronized (fila) {
				Iterator<Usuario> iteracao = fila.iterator();

				while (iteracao.hasNext()) {
					Usuario usuario = iteracao.next();

					System.out.println("Nome: " + usuario.getNome());
					System.out.println("Email: " + usuario.getEmail());

					iteracao.remove();

					// Descarga de memória
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

			// Descarga de memória
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

}
