package interfaces;

import java.io.Serializable;
import java.util.HashMap;

public interface Part extends Serializable{
	String getNome();
	int getCodigo();
	void setCodigo(int codigo);
	String getDescricao();
	HashMap<Part, Integer> getSubcomponentes();
}
