package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface PartRepository extends Remote{

	public ArrayList<Part> lista() throws RemoteException;
	public void inserir(Part peca) throws RemoteException;
	public Part busca(int codigo) throws RemoteException;
}
