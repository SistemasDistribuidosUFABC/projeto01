import java.rmi.registry.Registry;
import java.rmi.AlreadyBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.ExportException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import interfaces.PartRepository;
import interfaces.impl.PartRepositoryImpl;

public class Servidor {

	public static String getNomeServidor(Scanner sc, Registry registry, PartRepository stub) {
		System.out.println("Entre com o nome do servidor:");
		String nomeServidor = sc.next();
		try {
			registry.bind(nomeServidor, stub);
		} catch (AlreadyBoundException e) {
			System.out.println("Repositório " + nomeServidor + " já iniciado, utilize o mesmo ou tente outro nome.");
			return getNomeServidor(sc, registry, stub);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return nomeServidor;
	}

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		Registry registry;
		String nomeServidor;
		try {
			PartRepositoryImpl repository = new PartRepositoryImpl();
			// Criamos o stub do objeto que será registrado
			PartRepository stub = (PartRepository) UnicastRemoteObject.exportObject(repository, 0);

			// Registra (binds) o stub no registry
			try {
				registry = LocateRegistry.createRegistry(9000);
			} catch (ExportException e) {
				registry = LocateRegistry.getRegistry(9000);
			}
			nomeServidor = getNomeServidor(sc, registry, stub);
			System.out.println("Servidor " + nomeServidor + " iniciado.");
		} catch (Exception e) {
			System.err.println("Ocorreu um erro no servidor: " + e.toString());
		} finally {
			sc.close();
		}
	}
}
