package tp3;
	
import tp1Ej8.Queue;

	public class AnalizadorArbol {
		
	    public int devolverMaximoPromedioNivelSize(GeneralTree<AreaEmpresa> arbol) {
	        
	    	if (arbol == null || arbol.isEmpty()) return 0;
	    	//pregunto caso base

	        Queue<GeneralTree<AreaEmpresa>> cola = new Queue<>();
	        cola.enqueue(arbol);

	        int maxPromedio = 0;

	        while (!cola.isEmpty()) {
	            int nivelSize = cola.size(); // cantidad de nodos en este nivel
	            int suma = 0;

	            for (int i = 0; i < nivelSize; i++) {
	                GeneralTree<AreaEmpresa> nodo = cola.dequeue();
	                suma += nodo.getData().getTiempo();

	                for (GeneralTree<AreaEmpresa> hijo : nodo.getChildren()) {
	                    cola.enqueue(hijo);
	                }
	            }

	            int promedio = suma / nivelSize;
	            if (promedio > maxPromedio) maxPromedio = promedio;
	        }

	        return maxPromedio;
	    }
	    
	    public int devolverMaximoPromedioColaNulls(GeneralTree<AreaEmpresa> arbol) {
	    	Queue<GeneralTree<AreaEmpresa>> cola =new Queue<GeneralTree<AreaEmpresa>>();
	    	cola.enqueue(arbol);
	    	cola.enqueue(null);
	    	int suma=0;
	    	int maxPromedio=0;
	    	int cantHijos=0;
	    	while(!cola.isEmpty()) {
	    		GeneralTree<AreaEmpresa> nodo = cola.dequeue();
	    		if (nodo!=null) {
	    			suma += nodo.getData().getTiempo();
	    			cantHijos++;

	    			for (GeneralTree<AreaEmpresa> hijo : nodo.getChildren()) {
	    				cola.enqueue(hijo);
	    			}
	    		}
	    		else {
	    			int promedio=suma/cantHijos;
	    			cantHijos=0;
	    			suma=0;
	    			if (promedio > maxPromedio) maxPromedio = promedio; 
	    			if(!cola.isEmpty()) {
	    				cola.enqueue(null);
	    			}
	    		}
	    	}
	    	return maxPromedio;
	    }    
	}
