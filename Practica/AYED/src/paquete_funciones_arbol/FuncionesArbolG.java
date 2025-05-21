package paquete_funciones_arbol;
/*
 * Operaciones:
 * - cargar = se elige el nodo actual para cargar datos
 * - salir = deja el arbol así como está, si no se cargó nada queda en null
 * - volver = vuelve al estado anterior borrando hijos si se fueron hechos (hace tope con la raiz, si se quiere poner el estado
 anterior de la raiz hay que volver a cargar)
 * - imprimir = imprime estado del arból y marca el nodo con '-'
 * - h = se mueve al hijo izq y si no existe lo crea y pide cargarlo
 * - hsig = se mueve al nodo de la derecha del nodo actual(a lo largo de la lista childre), en caso de que no exista lo crea y pide cargarlo
 * - p = vuelve al padre
 */

import java.util.Scanner;

import tp1Ej8.DoubleEndedQueue;
import tp3.GeneralTree;
import java.util.LinkedList;

public class FuncionesArbolG{
	
	
	public void cargarArbolString(GeneralTree<String> ag,Scanner in) {
		if(ag!=null) {
			
			DoubleEndedQueue<LinkedList<GeneralTree<String>>> lista_de_lista_de_padre = new DoubleEndedQueue<LinkedList<GeneralTree<String>>>();
			//Es una lista que coincide con los padres, siendo su contenido la lista sobre la que se encuentra el nodo padre
			DoubleEndedQueue<Integer> lista_de_indices_padre= new DoubleEndedQueue<Integer>();
			//Funciona junto con lista de listas de los nodos padre
			
			DoubleEndedQueue<LinkedList<GeneralTree<String>>> lista_de_listas_ant = new DoubleEndedQueue<LinkedList<GeneralTree<String>>>();
			//La uso para conocer la lista de hijos sobre la que me estaba moviendo anteriormente, sirve para la operación volver
			DoubleEndedQueue<Integer> lista_de_indices_ant= new DoubleEndedQueue<Integer>();
			//Funciona junto con lista de listas anteriores
			
			LinkedList<GeneralTree<String>> aux_lista=null;
			//La uso para crear una lista de hijos cuando no existen
			
			LinkedList<GeneralTree<String>> lista_act=null; 
			int indice_act=0;
			//Uso la lista_act e indice_act para operacion hsig
			DoubleEndedQueue<String> ops_ant = new DoubleEndedQueue<String>();
			DoubleEndedQueue<GeneralTree<String>> nodos_ant = new DoubleEndedQueue<GeneralTree<String>>();
			DoubleEndedQueue<String> cargas_ant = new DoubleEndedQueue<String>();
			//Ops_ant, nodos_ant y cargas_ant sirven para la operacion volver
			
			LinkedList<GeneralTree<String>> nodos_padre = new LinkedList<GeneralTree<String>>();
			/*Nodos_padre sirve para la operacion p, no es lo mismo mantener una lista de nodos anteriores en los que
			se estuvo a mantener una lista fija de padres hasta llegar al padre de mi nodo actual*/
			
			GeneralTree<String> aux_ag = null;
			//Lo uso para operaciones h,p y volver
			
			String aux_op="";
			//Guarda la ultima operacion ingresada
			
			GeneralTree<String> nodo_act=ag;
			//Guarda el nodo actual
			
			System.out.print("Operaciones: Cargar, Salir: ");
			aux_op = in.next();
			aux_op= aux_op.toLowerCase();
			if(aux_op.equals("cargar")) {
				cargarS(nodo_act,in);
			}
			while(!aux_op.equals("salir")) {
				System.out.print("Operaciones(Nodo act:"+nodo_act.getData()+"): Cargar, Salir, Volver, Imprimir, H, Hsig, P: ");
				aux_op = in.next();
				aux_op=aux_op.toLowerCase();
				switch (aux_op){
				
				case "cargar":
					cargas_ant.enqueueFirst(nodo_act.getData());
					//Guardo estado anterior del nodo
					cargarS(nodo_act,in);
					ops_ant.enqueueFirst(aux_op);
					//Encolo op
					break;
						
				case "h":
					if(nodo_act!=ag) {
						lista_de_listas_ant.enqueueFirst(lista_act);
						lista_de_lista_de_padre.enqueueFirst(lista_act);
						lista_de_indices_padre.enqueueFirst(indice_act);
						lista_de_indices_ant.enqueueFirst(indice_act);
					}
					nodos_padre.addFirst(nodo_act);
					//Como me movi a mis hijos, guardo mi nodo actual como padre
					nodos_ant.enqueueFirst(nodo_act);
					//Como me desplace guardo mi nodo actual como futuro nodo anterior en el que estuve
					
					if(!nodo_act.hasChildren()) {
						//Si no tiene lista de hijos
						aux_lista = new LinkedList<GeneralTree<String>>();
						nodo_act.setChildren(aux_lista);
						cargarSH(nodo_act,in);
						ops_ant.enqueueFirst("h_cargar");
						//Guardo que se creo un hijo, para que el volver sepa si tiene que borrarlo
					}
					else ops_ant.enqueueFirst("h_mover");
					//Guardo que solamente elegí moverme al hijo, el volver no borra nada en ese caso
					aux_ag=nodo_act;
					//Me guardo el nodo en el que estaba para mas tarde acceder a la nueva lista de hijos
					nodo_act = nodo_act.getChildren().get(0);
					//Mi nodo actual pasa a ser el hijo 0 de la lista
					lista_act = aux_ag.getChildren();
					//Guardo lista sobre la que me voy a mover
					indice_act=0;
					/*Cuando creo una nueva lista de hijos o me muevo a una ya existente
					el primer hijo al que me muevo siempre va estar en el indice 0 de la lista*/
					break;
				
				case "hsig":
					if(lista_act!=null) {
						//Si no existe lista actual significa que soy raiz por lo tanto ignoro operacion
						nodos_ant.enqueueFirst(nodo_act);
						if(lista_act.size()==indice_act+1) {
							cargarSH(nodos_padre.getFirst(),in);
							ops_ant.enqueueFirst("hsig_cargar");
						}
						else ops_ant.enqueueFirst("hsig_mover");
						//Guardo que solamente elegí moverme al hijo, el volver no borra nada en ese caso
						indice_act++;
						//Incremento mi posicion en la lista de hijos
						nodo_act = lista_act.get(indice_act);
						//Cambio mi nodo actual al siguiente
					}
					break;
				case "p":
					if(nodos_padre.size()!=0) {
						/*Si la lista de nodos padre es de tamaño 0 significa
						que soy raiz y por lo tanto ignoro operacion*/
						aux_ag=nodo_act;
						//Guardo nodo actual antes de subir al padre para encolarlo en nodos_ant
						nodo_act=nodos_padre.remove(0);
						lista_de_listas_ant.enqueueFirst(lista_act);
						lista_de_indices_ant.enqueueFirst(indice_act);
						if(!lista_de_lista_de_padre.isEmpty()) {
							lista_act=lista_de_lista_de_padre.dequeue();
							indice_act=lista_de_indices_padre.dequeue();
						}
						/*Si la lista de nodos padre aun no está vacía signfica que no soy raiz 
						y que la lista actual de hijos del mismo nivel sobre la que me muevo existe*/
						else lista_act=null;
						//Sino, seteo lista actual en nulo
						ops_ant.enqueueFirst("p_mover");
						//Guardo que me moví al padre para la op volver
						nodos_ant.enqueueFirst(aux_ag);
						//Guardo mi el nodo en el que estaba antes de subir a su padre
					}
					break;
					
				case "volver":
					if(!ops_ant.isEmpty()){
						/*Si operaciones anteriores está vacía entonces significa que llegué
						al momento antes de cargar la raiz e ignoro la operacion*/
						switch(ops_ant.dequeue()) {
						case "cargar":
							nodo_act.setData(cargas_ant.dequeue());
							break;
						case "h_cargar":
							nodo_act=nodos_ant.dequeue();
							nodos_padre.removeFirst();
							//Vuelvo a nodo anterior(padre), por lo tanto lo borro de la lista de padres
							if(!lista_de_listas_ant.isEmpty()) {
								lista_act=lista_de_listas_ant.dequeue();
								indice_act=lista_de_indices_ant.dequeue();
							}
							//Recupero lista anterior (de mi nodo padre)
							nodo_act.setChildren(null);
							//Deshago lista creada y dejo seteada la lista children en null
							break;
						case "h_mover":
							lista_act=lista_de_listas_ant.dequeue();
							indice_act=lista_de_indices_ant.dequeue();
							nodo_act=nodos_ant.dequeue();
							break;
						case "hsig_cargar":
							nodo_act=nodos_ant.dequeue();
							//Vuelvo a nodo anterior en la lista
							indice_act--;
							//Decremento indice actual
							lista_act.removeLast();
							//Deshago la creacion de la lista de hijos
							break;
						case "hsig_mover":
							nodo_act=nodos_ant.dequeue();
							break;
						case "p_mover":
							aux_ag=nodo_act;
							nodo_act=nodos_ant.dequeue();
							nodos_padre.addFirst(aux_ag);
							lista_act=lista_de_listas_ant.dequeue();
							indice_act=lista_de_indices_ant.dequeue();
							break;
						}	
					}
				case "salir":
					break;
				case "imprimir":
					imprimirArbolP(ag,0,nodo_act);
					break;
				default:
					System.out.print("Operación no válida");
				}
			
			}
		}
	}
	private void cargarS(GeneralTree<String> nodo_act,Scanner in) {
		System.out.print("Cargar Dato: ");
		String aux_data = in.next();
		nodo_act.setData(aux_data);
	}
	private void cargarSH(GeneralTree<String> nodo_act,Scanner in) {
		GeneralTree<String> aux_ab = new GeneralTree<String>();
		cargarS(aux_ab,in);
		nodo_act.addChild(aux_ab);
	}
	
	public void cargarArbolInteger(GeneralTree<Integer> ag,Scanner in) {
		if(ag!=null) {
			DoubleEndedQueue<LinkedList<GeneralTree<Integer>>> lista_de_lista_de_padre = new DoubleEndedQueue<LinkedList<GeneralTree<Integer>>>();
			DoubleEndedQueue<Integer> lista_de_indices_padre= new DoubleEndedQueue<Integer>();
			DoubleEndedQueue<LinkedList<GeneralTree<Integer>>> lista_de_listas_ant = new DoubleEndedQueue<LinkedList<GeneralTree<Integer>>>();
			DoubleEndedQueue<Integer> lista_de_indices_ant= new DoubleEndedQueue<Integer>();
			LinkedList<GeneralTree<Integer>> aux_lista=null;
			LinkedList<GeneralTree<Integer>> lista_act=null; 
			int indice_act=0;
			DoubleEndedQueue<String> ops_ant = new DoubleEndedQueue<String>();
			DoubleEndedQueue<GeneralTree<Integer>> nodos_ant = new DoubleEndedQueue<GeneralTree<Integer>>();
			DoubleEndedQueue<Integer> cargas_ant = new DoubleEndedQueue<Integer>();
			LinkedList<GeneralTree<Integer>> nodos_padre = new LinkedList<GeneralTree<Integer>>();
			GeneralTree<Integer> aux_ag = null;
			String aux_op="";
			GeneralTree<Integer> nodo_act=ag;
			System.out.print("Operaciones: Cargar, Salir: ");
			aux_op = in.next();
			aux_op= aux_op.toLowerCase();
			if(aux_op.equals("cargar")) {
				cargarI(nodo_act,in);
			}
			while(!aux_op.equals("salir")) {
				System.out.print("Operaciones(Nodo act:"+nodo_act.getData()+"): Cargar, Salir, Volver, Imprimir, H, Hsig, P: ");
				aux_op = in.next();
				aux_op=aux_op.toLowerCase();
				switch (aux_op){
				case "cargar":
					cargas_ant.enqueueFirst(nodo_act.getData());
					cargarI(nodo_act,in);
					ops_ant.enqueueFirst(aux_op);
					break;		
				case "h":
					if(nodo_act!=ag) {
						lista_de_listas_ant.enqueueFirst(lista_act);
						lista_de_lista_de_padre.enqueueFirst(lista_act);
						lista_de_indices_padre.enqueueFirst(indice_act);
						lista_de_indices_ant.enqueueFirst(indice_act);
					}
					nodos_padre.addFirst(nodo_act);
					nodos_ant.enqueueFirst(nodo_act);
					if(!nodo_act.hasChildren()) {
						aux_lista = new LinkedList<GeneralTree<Integer>>();
						nodo_act.setChildren(aux_lista);
						cargarIH(nodo_act,in);
						ops_ant.enqueueFirst("h_cargar");
					}
					else ops_ant.enqueueFirst("h_mover");
					aux_ag=nodo_act;
					nodo_act = nodo_act.getChildren().get(0);
					lista_act = aux_ag.getChildren();
					indice_act=0;
					break;
				case "hsig":
					if(lista_act!=null) {
						nodos_ant.enqueueFirst(nodo_act);
						if(lista_act.size()==indice_act+1) {
							cargarIH(nodos_padre.getFirst(),in);
							ops_ant.enqueueFirst("hsig_cargar");
						}
						else ops_ant.enqueueFirst("hsig_mover");
						indice_act++;
						nodo_act = lista_act.get(indice_act);
					}
					break;
				case "p":
					if(nodos_padre.size()!=0) {
						aux_ag=nodo_act;
						nodo_act=nodos_padre.remove(0);
						lista_de_listas_ant.enqueueFirst(lista_act);
						lista_de_indices_ant.enqueueFirst(indice_act);
						if(!lista_de_lista_de_padre.isEmpty()) {
							lista_act=lista_de_lista_de_padre.dequeue();
							indice_act=lista_de_indices_padre.dequeue();
						}
						else {
							lista_act=null;
							indice_act=0;
						}
						
						ops_ant.enqueueFirst("p_mover");
						nodos_ant.enqueueFirst(aux_ag);
					}
					break;
					
				case "volver":
					if(!ops_ant.isEmpty()){
						switch(ops_ant.dequeue()) {
						case "cargar":
							nodo_act.setData(cargas_ant.dequeue());
							break;
						case "h_cargar":
							nodo_act=nodos_ant.dequeue();
							nodos_padre.removeFirst();
							if(!lista_de_listas_ant.isEmpty()) {
								lista_act=lista_de_listas_ant.dequeue();
								indice_act=lista_de_indices_ant.dequeue();
							}
							nodo_act.setChildren(null);
							break;
						case "h_mover":
							lista_act=lista_de_listas_ant.dequeue();
							indice_act=lista_de_indices_ant.dequeue();
							nodo_act=nodos_ant.dequeue();
							break;
						case "hsig_cargar":
							nodo_act=nodos_ant.dequeue();
							indice_act--;
							lista_act.removeLast();
							break;
						case "hsig_mover":
							nodo_act=nodos_ant.dequeue();
							break;
						case "p_mover":
							aux_ag=nodo_act;
							nodo_act=nodos_ant.dequeue();
							nodos_padre.addFirst(aux_ag);
							lista_act=lista_de_listas_ant.dequeue();
							indice_act=lista_de_indices_ant.dequeue();
							break;
						}	
					}
				case "salir":
					break;
				case "imprimir":
					imprimirArbolP(ag,0,nodo_act);
					break;
				default:
					System.out.print("Operación no válida");
				}
			
			}
		}
	}
	private void cargarI(GeneralTree<Integer> nodo_act,Scanner in) {
		System.out.print("Cargar Dato: ");
		int aux_data = in.nextInt();
		nodo_act.setData(aux_data);
	}
	private void cargarIH(GeneralTree<Integer> nodo_act,Scanner in) {
		GeneralTree<Integer> aux_ab = new GeneralTree<Integer>();
		cargarI(aux_ab,in);
		nodo_act.addChild(aux_ab);
	}
	
	private void imprimirArbolP(GeneralTree<?> ag,int i,GeneralTree<?> act) {
		/*Primero llega al hijo más derecho y lo imprime con una cantidad de espacios que fue aumentando
		mientrass bajaba en profundiad, así va imprimiendose en manera triangular horizontal el árbol. Además 
		cuando el nodo es coincidente con el nodo_act agrega un '-' para indicar posición*/
		
			if(ag!=null&&!ag.isEmpty()) {
			int a=0;
			if(ag.getChildren()!=null) {
				int j=0,dim=ag.getChildren().size()-1;
				while(j<=dim) {
					this.imprimirArbolP(ag.getChildren().get(dim-j),i+1,act);
					if(j==dim&&dim>0) {
						System.out.println("");
					}
					if(j==dim/2) {
						if(ag==act) {
							System.out.print("-  ");
							a++;
						}
						for(;a<i+1;a++) {
							System.out.print("   ");
						}
						System.out.println(ag.getData());
					}
					j++;
				}
			}
			else {
				if(ag==act) {
					System.out.print("-  ");
					a++;
				}
				for(;a<i+1;a++) {
					System.out.print("   ");
				}
				System.out.println(ag.getData());
			}
		}
	}
	
	public  void imprimirArbol(GeneralTree<?> ag,int i) {
		//Mismo que imprimirArbolP pero no indica nodo actual
		if(ag!=null&&!ag.isEmpty()) {
			int a=0;
			if(ag.getChildren()!=null) {
				int j=0,dim=ag.getChildren().size()-1;
				while(j<=dim) {
					this.imprimirArbol(ag.getChildren().get(dim-j),i+1);
					if(j==dim&&dim>0) {
						System.out.println("");
					}
					if(j==dim/2) {
						for(;a<i+1;a++) {
							System.out.print("   ");
						}
						System.out.println(ag.getData());
					}
					j++;
				}
			}
			else {
				for(;a<i+1;a++) {
					System.out.print("   ");
				}
				System.out.println(ag.getData());
			}
		}
	}
	
	public void imprimirArbolGPorNiveles(GeneralTree<Integer> ag) {
		if(ag!=null) {
			int indice_act=0;
			//Indice del nodo actual en la lista de hijos
			int dim_lista;
			//Dimension de la lista de hijos
			LinkedList<LinkedList<GeneralTree<Integer>>> lista_de_listas = new LinkedList<LinkedList<GeneralTree<Integer>>>();
			//Lista donde guardo los hijos del siguiente nivel sobre el que me estoy moviendo
			LinkedList<GeneralTree<Integer>> lista_actual=null;
			//Lista de hijos actual sobre la que me muevo
			GeneralTree<Integer> nodo_act=ag;
			//Nodo actual
			System.out.print(nodo_act.getData());
			System.out.println();
			//Si cumple condicion, lo encolo
			lista_actual=nodo_act.getChildren();
			//Asigno lista actual sobre la que me voy a mover
			if(nodo_act.hasChildren()) {
				lista_de_listas.add(nodo_act.getChildren());
				lista_de_listas.add(null);
			}
			while(!lista_de_listas.isEmpty()) {
					indice_act=0;
					//Seteo el indice actual en 0 para cada loop
				    lista_actual=lista_de_listas.remove(0);
				    //Actualizo lista actual
				    if(lista_actual!=null) {
						dim_lista=lista_actual.size();
						//Dimension de lista actual
						while(indice_act<dim_lista) {
							//Condicion de corte de control: llegar al final de la lista
							nodo_act=lista_actual.get(indice_act);
							//Nodo actual pasa a ser el primer(cuando recien entro al loop) o siguiente hijo de la lista
							if(nodo_act.hasChildren())lista_de_listas.add(nodo_act.getChildren());
							//Si mi nodo actual tiene lista de hijos la añado a la lista de listas de hijos (siguiente nivel)
							indice_act++;
							//Aumento indice actual sobre la lista para la siguiente vuelta
							System.out.print(nodo_act.getData());
						}
				    }
				    else if(!lista_de_listas.isEmpty()) {
				    	lista_de_listas.add(null);
				    	System.out.println();
				    }
					
			}
		}
	}
}