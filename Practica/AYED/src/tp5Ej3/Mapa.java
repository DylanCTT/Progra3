package tp5Ej3;

import java.util.ArrayList;
import java.util.List;

import tp5Ej1.AdjListGraph;
import tp5Ej1.AdjListVertex;
import tp5Ej1.Edge;
import tp5Ej1.Vertex;

public class Mapa {
	
	private AdjListGraph<String> mapaCiudades;

	public void setMapaCiudades(AdjListGraph<String> mapaCiudades) {
		this.mapaCiudades = mapaCiudades;
	}
	
	 public List<String> devolverCaminoDFS(String ciudad1, String ciudad2) {
	        Vertex<String> origen = this.mapaCiudades.search(ciudad1);
	        AdjListVertex<String> destino = (AdjListVertex<String>) this.mapaCiudades.search(ciudad2);

	        if (origen == null || destino == null) {
	            return null; // Si alguna ciudad no está en el grafo
	        }

	        boolean[] visitados = new boolean[this.mapaCiudades.getSize()];
	        List<String> camino = new ArrayList<>();

	        if (dfs(origen, destino, visitados, camino)) {
	            return camino; // Retorna el camino si lo encuentra
	        }
	        
	        return null; // No hay camino entre ciudad1 y ciudad2
	    }

	    private boolean dfs(Vertex<String> actual, AdjListVertex<String> destino, boolean[] visitados, List<String> camino) {
	        camino.add(actual.getData());
	        visitados[actual.getPosition()] = true;

	        if (actual.equals(destino)) {
	            return true; // Se encontró el camino
	        }
	        
	        
	        for (Edge<String> e : this.mapaCiudades.getEdges(actual)) {
	            AdjListVertex<String> vecino = (AdjListVertex<String>) e.getTarget();
	            if (!visitados[vecino.getPosition()]) {
	                if (dfs(vecino, destino, visitados, camino)) {
	                    return true;  // implementar con un while y un iterator
	                }
	            }
	        }

	        camino.remove(camino.size() - 1); // Retrocedemos si no encontramos un camino válido
	        return false;
	    }
	    
	    public List<String> devolverCaminoExceptuando (String ciudad1, String ciudad2, List<String> ciudades){
	    	
	    }



	
	
	

}
