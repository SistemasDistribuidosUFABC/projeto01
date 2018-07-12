import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;

import interfaces.Part;
import interfaces.PartRepository;
import interfaces.impl.PartImpl;

public class Cliente {
	public static void main(String[] args) {
		try {

			// Localiza o registry. É possível usar endereço/IP porta
			Registry registry = LocateRegistry.getRegistry("127.0.1.1", 9000, null);

			PartRepository repository = (PartRepository) registry.lookup("PartRepository");

			Part peca = new PartImpl("Teste", "Desc");
			Part peca2 = new PartImpl("Teste 2", "DESC", new HashMap<Part, Integer>() {
				/**
				* 
				*/
				private static final long serialVersionUID = 1L;

				{
					put(peca, 2);
				}
			});
			repository.inserir(peca);
			repository.inserir(peca2);
			for (Part p : repository.lista()) {
				System.out.println(p);
			}

			// A partir deste momento, cahamadas à Caluladora podem ser
			// feitas como qualquer chamada a métodos

			// System.out.println("Resultados obtidos do servidor:" +
			// "\n\t+:" + soma.getValor() +
			// "\n\t-:" + sub.getValor() +
			// "\n\t*:" + mult.getValor() +
			// "\n\t/:" + div.getValor());

		} catch (Exception e) {
			System.err.println("Ocorreu um erro no cliente: " + e.toString());
		}
	}
}