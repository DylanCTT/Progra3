package parcial2020;

import java.util.LinkedList;
import java.util.List;

import tp5Ej1.Graph;
import tp5Ej1.Vertex;

public class Parcial {
	
	
	public List<String> resolver (Graph<String> ciudades, String origen, String destino, List<String> pasandoPor){
		
		Vertex<String> inicio= ciudades.search(origen);
		if (inicio ==null) {
			return null;
		}
		Vertex<String> fin= ciudades.search(destino);
		if (fin==null) {
			return null;
		}
		
		boolean[] visitados = new boolean[ciudades.getSize()];
		List<String> caminoActual = new LinkedList<>();
        List<String> mejorCamino = new LinkedList<>();
        int[] maxCiudadesVisitadas = {0};
        resolverDFS(inicio, fin, visitados, caminoActual, mejorCamino, pasandoPor, 0, maxCiudadesVisitadas);

        return mejorCamino.isEmpty() ? null : mejorCamino;

        
	}
	
	
	private void resolverDFS(Vertex<String> actual, Vertex<String> destino, boolean[] visitados, List<String> caminoActual, List<String> mejorCamino, List<String> ciudadesObligatorias, int ciudadesVisitadas, int[] maxCiudadesVisitadas) {
        caminoActual.add(actual.getData());
        visitados[actual.getPosition()] = true;
	}
	
}
