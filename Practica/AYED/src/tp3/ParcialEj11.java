package tp3;

import tp1Ej8.Queue;

public class ParcialEj11 {
	
	public ParcialEj11() {
		
	}
	
	public static boolean resolver(GeneralTree<Integer> arbol) {
		Queue<GeneralTree<Integer>> cola= new Queue<GeneralTree<Integer>>();
		cola.enqueue(arbol);
		cola.enqueue(null);
		int i=0;
		int ant=1;
		while (!cola.isEmpty()) {
			GeneralTree<Integer> nodo= cola.dequeue();
			if (nodo!=null) {
    			i++;
    			for (GeneralTree<Integer> hijo : nodo.getChildren()) {
    				cola.enqueue(hijo);
    			}
			}
    		else {
    			if (i!=ant) 
    				return false;
    				else {
    					ant=i+1;
    					i=0;
    				}
    			if(!cola.isEmpty()) {
    				cola.enqueue(null);
    			}
    		}
			
		}
		return true;
	}
}
