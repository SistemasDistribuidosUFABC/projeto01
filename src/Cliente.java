import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JOptionPane;

import interfaces.Part;
import interfaces.PartRepository;
import interfaces.impl.PartImpl;

public class Cliente {

	// adiciona uma nova peça no repositório escolhido
	public static void novaPeca(PartRepository repository, String nomeRepository, Registry registry)
			throws NotBoundException, RemoteException {
		String nome = JOptionPane.showInputDialog("Informe o nome da Peça:");
		String descricao = JOptionPane.showInputDialog("Informe o descrição da Peça:");
		HashMap<Part, Integer> subs = new HashMap<>();
		while (true) {
			if (JOptionPane.showConfirmDialog(null, "Adicionar subcomponente?", "Adicionar subcomponente",
					JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE) == 1)
				break;
			if (totalComponentes(registry) != null) {
				PartImpl peca = (PartImpl) JOptionPane.showInputDialog(null, "Selecionar peça", "Selecionar peça",
						JOptionPane.QUESTION_MESSAGE, null, totalComponentes(registry), totalComponentes(registry)[0]);
				int qtde = Integer.parseInt(JOptionPane.showInputDialog("Informe a quantidade:"));
				subs.put(peca, qtde);
			}
		}
		PartImpl peca = new PartImpl(nome, descricao, subs);
		peca.setNomeRepo(nomeRepository);
		repository.inserir(peca);
	}

	// imprime no console as peças incluídas até o momento no repositório escolhido
	public static void listarPecas(PartRepository repository) throws RemoteException {
		for (Part p : repository.lista()) {
			System.out.println(p);
		}
	}

	// busca uma peça pelo código
	public static void buscarPeca(PartRepository repository) throws RemoteException {
		int codigo = Integer.parseInt(JOptionPane.showInputDialog("Informe o código:"));
		JOptionPane.showMessageDialog(null, repository.busca(codigo));
	}

	// retorna os componentes de todos os repositorys
	public static Object[] totalComponentes(Registry registry) throws RemoteException, NotBoundException {
		ArrayList<Part> lista = new ArrayList<>();
		for (String repository : registry.list()) {
			lista.addAll(((PartRepository) registry.lookup(repository)).lista());
		}
		return lista.toArray();
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		try {

			Registry registry = LocateRegistry.getRegistry("127.0.1.1", 9000, null);

			String nomeRepository = (String) JOptionPane.showInputDialog(null, "Selecionar repository",
					"Selecionar repository", JOptionPane.QUESTION_MESSAGE, null, registry.list(), registry.list()[0]);

			PartRepository repository = (PartRepository) registry.lookup(nomeRepository);

			int action;
			String actions[] = { "Incluir peça", "Listar peças", "Buscar por código", "Alterar Repositório", "Sair" };

			while (true) {
				action = JOptionPane.showOptionDialog(null, "Indique a ação a ser realizada",
						"Repositório " + nomeRepository, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
						null, actions, actions[0]);
				if (action == actions.length - 1)
					break;
				switch (action) {
				case 0:
					novaPeca(repository, nomeRepository, registry);
					break;
				case 1:
					listarPecas(repository);
					break;
				case 2:
					buscarPeca(repository);
					break;
				case 3:
					nomeRepository = (String) JOptionPane.showInputDialog(null, "Selecionar repository",
							"Selecionar repository", JOptionPane.QUESTION_MESSAGE, null, registry.list(),
							registry.list()[0]);

					repository = (PartRepository) registry.lookup(nomeRepository);
					break;
				}
			}

		} catch (Exception e) {
			System.err.println("Ocorreu um erro no cliente: " + e.toString());
		}
	}
}