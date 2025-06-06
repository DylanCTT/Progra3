package tpEj6;

import java.util.LinkedList;
import java.util.Iterator;
import java.util.List;

import tp5Ej1.AdjListGraph;
import tp5Ej1.AdjListVertex;
import tp5Ej1.Edge;
import tp5Ej1.Vertex;

public class caperusita {
	
	private AdjListGraph<String> bosque;
	
	public List<List<String>> recorridosMasSeguros() {
		Vertex<String> origen = bosque.search("casaCaperucita");
        Vertex<String> destino = bosque.search("casaAbuelita");

        if (origen == null || destino == null) {
            return null; // Si no existe alguna de las casas, no hay caminos
        }

        List<List<String>> caminosSeguros = new LinkedList<>();
        boolean[] visitados = new boolean[bosque.getSize()];
        List<String> caminoActual = new LinkedList<>();

        recorridosMasSegurosDFS(origen, destino, visitados, caminoActual, caminosSeguros);

        return caminosSeguros;
    }

	
	 private void recorridosMasSegurosDFS(Vertex<String> actual, Vertex<String> destino, boolean[] visitados, List<String> caminoActual, List<List<String>> caminosSeguros) {
	        caminoActual.add(actual.getData());
	        visitados[actual.getPosition()] = true;
	        
	        if (actual.equals(destino)) {
	            caminosSeguros.add(new LinkedList<>(caminoActual)); // Agrega el camino encontrado
	        } else {
	            Iterator<Edge<String>> iterador = ((AdjListVertex<String>) actual).getEdges().iterator();
	            boolean encontrado = false;
	            
	            while (iterador.hasNext() && !encontrado) {
	                Edge<String> sendero = iterador.next();
	                Vertex<String> vecino = sendero.getTarget();

	                if (!visitados[vecino.getPosition()] && sendero.getWeight() < 5) { // Chequea si el camino es seguro y si es asi lo recorre, si no ni lo recorre
	                	recorridosMasSegurosDFS(vecino, destino, visitados, caminoActual, caminosSeguros);
	                }
	            }
	        }

	        caminoActual.remove(caminoActual.size() - 1); // Retrocede al finalizar la exploración
	        visitados[actual.getPosition()] = false; // Permite visitar este nodo en otros recorridos
	    }
}
