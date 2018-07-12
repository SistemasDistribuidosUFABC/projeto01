import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import interfaces.PartRepository;
import interfaces.impl.PartRepositoryImpl;

public class Servidor {
	public static void main(String args[]) {

		try {
			PartRepositoryImpl repository = new PartRepositoryImpl();
			// Criamos o stub do objeto que será registrado
			PartRepository stub = (PartRepository) UnicastRemoteObject.exportObject(repository, 0);

			// Registra (binds) o stub no registry
			Registry registry = LocateRegistry.createRegistry(9000);
			//System.setProperty("java.rmi.server.hostname","localhost");
			registry.bind("PartRepository", stub);
			System.out.println("Servidor iniciado.");
		} catch (Exception e) {
			System.err.println("Ocorreu um erro no servidor: " + e.toString());
		}
	}
}
