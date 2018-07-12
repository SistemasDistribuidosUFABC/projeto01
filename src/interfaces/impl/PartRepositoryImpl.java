package interfaces.impl;

import java.util.ArrayList;

import interfaces.Part;
import interfaces.PartRepository;

public class PartRepositoryImpl implements PartRepository {
	private ArrayList<Part> pecas;

	public PartRepositoryImpl() {
		this.pecas = new ArrayList<>();
	}
	@Override
	public ArrayList<Part> lista() {
		return this.pecas;
	}
	@Override
	public void inserir(Part peca) {
		peca.setCodigo(this.pecas.size());
		this.pecas.add(peca);
	}
	@Override
	public Part busca(int codigo) {
		return this.pecas.get(codigo);
	}
}
