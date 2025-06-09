package tp5Ej4;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import tp5Ej1.AdjListVertex;
import tp5Ej1.Edge;
import tp5Ej1.Graph;
import tp5Ej1.Vertex;

public class VisitaOslo {
	
	public List<String> paseoEnBici(Graph<String> lugares, String destino, int maxTiempo, List<String> lugaresRestringidos){
		List<String> ciudades = new LinkedList<String>();
		
		if (destino == null || lugares.search (destino)==null) return ciudades;
		
		int pos = lugares.search("Ayuntamiento").getPosition(); //Asumo que ayuntamiento existe en mi grafo
		boolean[] visitados = new boolean[lugares.getSize()];
		paseoEnBiciDFS(pos, destino, maxTiempo, lugaresRestringidos, visitados, ciudades, lugares);
		return ciudades;
		
	}
	
	private void paseoEnBiciDFS(int pos, String destino, int maxTiempo, List<String> lugaresRestringidos, boolean[] visitados, List<String> ciudades, Graph<String> lugares) {
		
		Vertex<String> vertice=lugares.getVertex(pos);
		ciudades.add(vertice.getData());
		
		if(vertice.getData().equals(destino)) return;
		
		visitados[pos] =true;
		
		Iterator<Edge<String>> iterador = ((AdjListVertex<String>) vertice).getEdges().iterator();
		
		while (iterador.hasNext()) {
            Edge<String> sendero = iterador.next();
            Vertex<String> vecino = sendero.getTarget();

            if (!visitados[vecino.getPosition()] && !lugaresRestringidos.contains(vecino.getData()) && (maxTiempo - sendero.getWeight())>=0) {
            	paseoEnBiciDFS(vecino.getPosition(), destino, maxTiempo- sendero.getWeight(), lugaresRestringidos, visitados, ciudades, lugares);
            }
        }
		ciudades.remove(ciudades.size() - 1);
		visitados[vertice.getPosition()] = false;
    
	}
	
}

