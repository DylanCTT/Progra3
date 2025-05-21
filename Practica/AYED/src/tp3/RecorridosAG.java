package tp3;

import java.util.LinkedList;
import java.util.List;

import tp1Ej8.Queue;

public class RecorridosAG {
	public List<Integer> numerosImparesMayoresQuePreOrden (GeneralTree <Integer> a, Integer n){
		List<Integer> aux_list = new LinkedList<Integer>();
		numerosImparesMayoresQuePreOrdenR(a,n, aux_list);
		return aux_list;
	}
	private void numerosImparesMayoresQuePreOrdenR (GeneralTree<Integer> a, Integer n, List<Integer> aux) {
		if (a!=null) {
			if (a.getData()%2!=0 && a.getData()>n) {
				aux.add(a.getData());
			}
			if(a.hasChildren()) {
				int i=0,dimf=a.getChildren().size();
				while(i<dimf) {
					numerosImparesMayoresQuePreOrdenR(a.getChildren().get(i),n,aux);
					i++;
				}
			}
		}
	}
	
	public List<Integer> numerosImparesMayoresQueInOrden (GeneralTree <Integer> a, Integer n){
		LinkedList<Integer> aux_list = new LinkedList<Integer>();
		numerosImparesMayoresQueInOrdenR(a,n, aux_list);
		return aux_list;
	}
	
	private void numerosImparesMayoresQueInOrdenR (GeneralTree<Integer> a ,Integer n,LinkedList<Integer>aux) {
		if(a != null) {
			if(a.hasChildren()) {
				if(a.getData() % 2 != 0 && a.getChildren().get(0).getData() > n)
					aux.add(a.getChildren().get(0).getData());  //  Procesa el hijo izquierdo
			}
			
			if(a.getData() % 2 != 0 && a.getData() > n) 
				aux.add(a.getData());
			
			if(a.hasChildren()) {
				int i = 1,dimf = a.getChildren().size();
				while(i < dimf) {  //procesa los hermanos
					numerosImparesMayoresQueInOrdenR(a.getChildren().get(i),n,aux);
					i++;
				}
			}
		}
	}
	
	public List<Integer> numerosImparesMayoresQuePosOrden (GeneralTree <Integer> a, Integer n){
		LinkedList<Integer> aux_list = new LinkedList<Integer>();
		numerosImparesMayoresQuePosOrdenR(a,n, aux_list);
		return aux_list;
	}
	
	private void numerosImparesMayoresQuePosOrdenR (GeneralTree<Integer> a, Integer n, LinkedList<Integer> aux) {
		if (a != null) {
			List<GeneralTree<Integer>> children = a.getChildren();
			for (GeneralTree<Integer> child: children) { //como que no lo uso?
				numerosImparesMayoresQuePosOrdenR(child,n,aux); //preguntar
			}
			if (a.getData()%2!=0 && a.getData()>n) {
				aux.add(a.getData());  //  proceso al ultimo hermano y sigo  
			}
			
		}
	}
	
	public List<Integer>  numerosImparesMayoresQueEntreNiveles (GeneralTree<Integer> a, Integer n) {
		List<Integer> result = new LinkedList<Integer>();
		GeneralTree<Integer> tree_aux;
		Queue<GeneralTree<Integer>> queue = new Queue<GeneralTree<Integer>>();
		queue.enqueue(a);
		while (!queue.isEmpty()) {
			tree_aux = queue.dequeue();
			if (a.getData()%2!=0 && a.getData()>n)
				result.add(tree_aux.getData());
			List<GeneralTree<Integer>> children = tree_aux.getChildren();
			for (GeneralTree<Integer> child: children) {
				queue.enqueue(child);
			}
		}
		return result;
	}
}
