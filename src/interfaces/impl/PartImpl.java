package interfaces.impl;

import java.util.HashMap;
import java.util.Map;

import interfaces.Part;

public class PartImpl implements Part{

	private String nome;
	private int codigo;
	private String descricao;
	private HashMap<Part, Integer> subcomponentes;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PartImpl(String nome, String descricao, HashMap<Part, Integer> subcomponentes) {
		this.nome = nome;
		this.descricao = descricao;
		this.subcomponentes = subcomponentes;
	}

	public PartImpl(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	@Override
	public String getNome() {
		return this.nome;
	}

	@Override
	public int getCodigo() {
		return this.codigo;
	}

	@Override
	public String getDescricao() {
		return this.descricao;
	}

	@Override
	public HashMap<Part, Integer> getSubcomponentes() {
		return this.subcomponentes;
	}
	
	public void addSubcomponente(Part subParte, int quant) {
		if( this.subcomponentes == null){
			this.subcomponentes = new HashMap<Part, Integer>();
		}
		this.subcomponentes.put(subParte, quant);
	}
	
	
	public String toString() {
		String saida = this.getCodigo() + " - " + this.getNome() + ": " + this.getDescricao();
		
		if(getSubcomponentes() != null)
		for (Map.Entry<Part,Integer> p : this.subcomponentes.entrySet()) {
			saida += "\nPossui " + p.getValue() + " subComponentes " + p.getKey().getNome();
		}else{
			saida += "\nNão possui subComponentes";
		}
		return saida;
	}
//	public String toString() {
//		String saida = this.getCodigo() + " - " + this.getNome() + ": " + this.getDescricao();
////		for (Map.Entry<Part,Integer> p : this.subcomponentes.entrySet()) {
////			saida += "\n" + p.getValue() + '-' + p.getKey();
////		}
//		return saida;
//	}

}
