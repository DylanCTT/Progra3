package tp5Ej2;

import java.util.LinkedList;
import java.util.List;

import tp1Ej8.DoubleEndedQueue;
import tp5Ej1.AdjListGraph;
import tp5Ej1.AdjListVertex;
import tp5Ej1.Edge;
import tp5Ej1.Vertex;

public class Recorridos<T> {

	
	public List<T> dfs(AdjListGraph<T> grafo) {
		if(grafo!=null && !grafo.isEmpty()) {
			boolean [] visitados =new boolean [grafo.getSize()];  //en este vector guardo las visitas de los vertices
			LinkedList<T> aux=new LinkedList<T>();
			int i;
			for (i=0; i<grafo.getSize(); i++) {
				if (!visitados[i]) {
					System.out.println("larco con: "+ grafo.getVertex(i).getData());
					dfs((AdjListVertex<T>) grafo.getVertices().get(i),visitados, aux);
				}
			}
			return aux;
		}
		return null;
	}
	
	private void dfs(AdjListVertex<T> V, boolean[] visitados ,List<T> listD) {
		visitados[V.getPosition()] = true;
		listD.add(V.getData());
		
		List<Edge<T>> adyacentes = V.getEdges(); //declaro lista de adyacentes de el vertice
		for (Edge<T> e: adyacentes){  //para cada adyacente guardo en lista
			if (!visitados[e.getTarget().getPosition()]) {  //cheque si ya pase por el vertice para agregarlo
				dfs((AdjListVertex<T>) e.getTarget(), visitados, listD);
			}
		}
	}
	
	public List<T> Bfs(AdjListGraph<T> grafo){
		if(grafo!=null&&!grafo.isEmpty()) {
			DoubleEndedQueue<Vertex<T>> cola = new DoubleEndedQueue<Vertex<T>>();
			List<Vertex<T>> listaV = grafo.getVertices();
			List<Edge<T>> listaE;
			boolean visitados[]= new boolean[grafo.getSize()];
			List<T> listaR = new LinkedList<T>();
		
			Vertex<T> act;
			for(int j=0;j<listaV.size();j++) {
				if(!visitados[listaV.get(j).getPosition()]) {
					cola.enqueue(listaV.get(j));
					while(!cola.isEmpty()) {
						act = cola.dequeue();
						listaR.add(act.getData());
						listaE = grafo.getEdges(act);
						for(Edge<T> e : listaE) {
							if(!visitados[e.getTarget().getPosition()]) {
								cola.enqueue(e.getTarget());
								visitados[e.getTarget().getPosition()]=true;
							}
						}
					}
				}
			}
			return listaR;
		}
		return null;
	}
	
	
	

}
