package tp3;

import java.util.ArrayList;
import java.util.List;

public class Caminos {
	
	private GeneralTree<Integer> arbol=null;

	public Caminos(GeneralTree<Integer> arbol) {
		this.arbol = arbol;
	}
	public Caminos () {
		
	}
	public GeneralTree<Integer> getA() {
		return arbol;
	}
	public void setA(GeneralTree<Integer> arbol) {
		this.arbol = arbol;
	}
	
	public List<Integer> caminoAHojaMasLejana (){
		 List<Integer> caminoMax = new ArrayList<>();
	        if (arbol != null && !arbol.isEmpty()) {
	            caminoAHojaMasLejanaR(arbol, new ArrayList<>(), caminoMax);
	        }
	        return caminoMax;
	    }

	private void caminoAHojaMasLejanaR(GeneralTree<Integer> nodo, List<Integer> caminoActual, List<Integer> caminoMax) {
		caminoActual.add(nodo.getData());

		if (nodo.isLeaf()) {
			if (caminoActual.size() > caminoMax.size()) {
				caminoMax.clear();
				caminoMax.addAll(new ArrayList<>(caminoActual));
			}
		} else {
			for (GeneralTree<Integer> hijo : nodo.getChildren()) {
				caminoAHojaMasLejanaR(hijo, caminoActual, caminoMax);
			}
		}

		// Backtrack para explorar otros caminos
		caminoActual.remove(caminoActual.size() - 1);
	}
	
}
	

