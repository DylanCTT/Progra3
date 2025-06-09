package tp5Ej3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
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
	
	 public List<String> devolverCamino(String ciudad1, String ciudad2) {
	        Vertex<String> origen = this.mapaCiudades.search(ciudad1);
	        AdjListVertex<String> destino = (AdjListVertex<String>) this.mapaCiudades.search(ciudad2);

	        if (origen == null || destino == null) {
	            return null; // Si alguna ciudad no está en el grafo
	        }

	        boolean[] visitados = new boolean[this.mapaCiudades.getSize()];
	        List<String> camino = new ArrayList<>();

	        if (devolverCaminoDFS(origen, destino, visitados, camino)) {
	            return camino; // Retorna el camino si lo encuentra
	        }
	        
	        return null; // No hay camino entre ciudad1 y ciudad2
	    }

	    private boolean devolverCaminoDFS(Vertex<String> actual, AdjListVertex<String> destino, boolean[] visitados, List<String> camino) {
	        camino.add(actual.getData());
	        visitados[actual.getPosition()] = true;

	        if (actual.equals(destino)) {
	            return true; // Se encontró el camino
	        }
	        
	        
	        for (Edge<String> e : this.mapaCiudades.getEdges(actual)) {
	            AdjListVertex<String> vecino = (AdjListVertex<String>) e.getTarget();
	            if (!visitados[vecino.getPosition()]) {
	                if (devolverCaminoDFS(vecino, destino, visitados, camino)) {
	                    return true;  // implementar con un while y un iterator
	                }
	            }
	        }

	        camino.remove(camino.size() - 1); // Retrocedemos si no encontramos un camino válido
	        return false;
	    }
	    
	    public List<String> devolverCaminoExceptuando (String ciudad1, String ciudad2, List<String> ciudadesNo){
			List<String> ciudades = new LinkedList<String>();
			
			if (ciudad1 == null || ciudad2== null) return ciudades;	//Chequeo que podria NO estar
			
			//Chequeos que DEBEN estar para hacer operaciones inecesarias
			Vertex<String> origen = mapaCiudades.search(ciudad1);
			if (origen == null) return ciudades;	
			Vertex<String> destino = mapaCiudades.search(ciudad2);
			if (destino == null) return ciudades;
			
			boolean[] marca = new boolean[mapaCiudades.getSize()]; //Inicializacion del vector de visitados
			List<Edge<String>> aristas = mapaCiudades.getEdges(origen);	//Lista de las aristas de la ciudad origen
			
			for(int i = 0; i<aristas.size(); i++) {
				Vertex<String> actual = aristas.get(i).getTarget();
				
				if (!marca[actual.getPosition()] && !ciudadesNo.contains(actual.getData())) {
					busquedaConExceptuandoDFS(ciudades, actual.getPosition(), ciudad2, marca, ciudadesNo);
				}
				
				if (!ciudades.isEmpty()) {
					if (ciudades.get(ciudades.size()-1).equals(ciudad2)) {
						ciudades.remove(ciudades.size()-1);	//Si la ultima ciudad de la lista es CIUDAD2, la remuevo de la lista y termine con el bucle
						break;
					}
					
					else ciudades.clear(); //Limpió la lista del DFS anterior porque la busqueda no resultó
				}
				
			}
			
			return ciudades;
		}
		
		private void busquedaConExceptuandoDFS(List<String> ciudades, int pos, String ciudad2, boolean[] marca, List<String> ciudadesNo) {
			
			Vertex<String> v = mapaCiudades.getVertex(pos);
			marca[pos] = true;
			ciudades.add(v.getData());
			if (v.getData().equals(ciudad2)) return;
			
			for (Edge<String> e : mapaCiudades.getEdges(v)) {
				int aux = e.getTarget().getPosition();
				
				if (!marca[aux] && !ciudadesNo.contains(e.getTarget().getData())) {
					
					busquedaConExceptuandoDFS(ciudades,aux,ciudad2,marca, ciudadesNo);
					if (ciudades.get(ciudades.size()-1).equals(ciudad2)) break; //Si la ultima ciudad es CIUDAD2 corto con el bucle y con el metodo privado
					ciudades.remove(ciudades.size()-1); //Si la ultima ciudad NO es CIUDAD2, elimino el ultimo elemento de la lista  continuo con los siguientes vertices adyacentes
				
				}

			}
			
		}
		
		public List<String> caminoMasCorto (String ciudad1, String ciudad2){
			Vertex<String> origen = this.mapaCiudades.search(ciudad1);
	        Vertex<String> destino = this.mapaCiudades.search(ciudad2);

	        if (origen == null || destino == null) {
	            return null; // Si alguna ciudad no está en el grafo
	        }
	        
	        boolean[] visitados = new boolean[this.mapaCiudades.getSize()];
	        List<String> caminoActual = new ArrayList<>();
	        List<String> mejorCamino = new ArrayList<>();
	        int[] menorDistancia = {Integer.MAX_VALUE}; // Variable para almacenar la menor distancia encontrada

	        caminoMasCortoDFS(origen, destino, visitados, caminoActual, mejorCamino, 0, menorDistancia);

	        return mejorCamino.isEmpty() ? null : mejorCamino;


			
			
		}
		
		private void caminoMasCortoDFS (Vertex<String> actual, Vertex<String> destino, boolean[] visitados, List<String> caminoActual, List<String> mejorCamino, int distanciaActual, int[] menorDistancia) {
			caminoActual.add(actual.getData());
	        visitados[actual.getPosition()] = true;

	        if (actual.equals(destino)) {
	            if (distanciaActual < menorDistancia[0]) {
	                menorDistancia[0] = distanciaActual;
	                mejorCamino.clear();
	                mejorCamino.addAll(caminoActual);
	            }
	        } else {
	            Iterator<Edge<String>> iterador = ((AdjListVertex<String>) actual).getEdges().iterator();

	            while (iterador.hasNext()) {
	                Edge<String> sendero = iterador.next();
	                Vertex<String> vecino = sendero.getTarget();

	                if (!visitados[vecino.getPosition()]) {
	                    caminoMasCortoDFS(vecino, destino, visitados, caminoActual, mejorCamino, distanciaActual + sendero.getWeight(), menorDistancia);
	                }
	            }
	        }

	        caminoActual.remove(caminoActual.size() - 1); // Retrocedemos
	        visitados[actual.getPosition()] = false; // Permitimos revisitar en otros caminos
	    }
		
		public List<String> caminoSinCargarCombustible (String ciudad1, String ciudad2, int tanqueAuto){
			Vertex<String> origen = this.mapaCiudades.search(ciudad1);
	        Vertex<String> destino = this.mapaCiudades.search(ciudad2);

	        if (origen == null || destino == null) {
	            return null; // Si alguna ciudad no está en el grafo
	        }
	        
	        boolean[] visitados = new boolean[this.mapaCiudades.getSize()];
	        List<String> camino = new ArrayList<>();
	        caminoSinCargarCombustibleDFS(origen, destino,visitados, camino, tanqueAuto, 0);
	        return camino.isEmpty() ? null : camino;
		}
		
		private void caminoSinCargarCombustibleDFS( Vertex<String> actual, Vertex<String> destino, boolean[] visitados, List<String> camino, int tanque, int contGastado) {
			camino.add(actual.getData());
	        visitados[actual.getPosition()] = true; 
			
			if (actual.equals(destino)) {
				 if (contGastado> tanque) {
					 camino.remove(camino.size() - 1);
				     visitados[actual.getPosition()] = false;

				 }
			}
			else {
				Iterator<Edge<String>> iterador = ((AdjListVertex<String>) actual).getEdges().iterator();

	            while (iterador.hasNext()) {
	                Edge<String> sendero = iterador.next();
	                Vertex<String> vecino = sendero.getTarget();

	                if (!visitados[vecino.getPosition()] && contGastado + sendero.getWeight() < tanque) {
	                	caminoSinCargarCombustibleDFS(vecino, destino, visitados, camino, tanque, contGastado + sendero.getWeight());
	                }
	            }
			}
			camino.remove(camino.size() - 1);
	        visitados[actual.getPosition()] = false;

		}

}
