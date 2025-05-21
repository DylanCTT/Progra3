package paquete_funciones_arbol;
/*
 * Operaciones:
 * - cargar = se elige el nodo actual para cargar datos
 * - salir = deja el arbol así como está, si no se cargó nada queda en null
 * - volver = vuelve al estado anterior borrando hijos si se fueron hechos (hace tope con la raiz, si se quiere poner el estado
 anterior de la raiz hay que volver a cargar)
 * - imprimir = imprime estado del arból y marca el nodo con '-'
 * - hd/hi = se mueve al hijo derecho/hijo izquierdo del nodo y si no existe lo crea y pide cargarlo
 * - p = vuelve al padre
 */

import java.util.Scanner;

import tp1Ej8.DoubleEndedQueue;
import tp2.BinaryTree;
import tp2.ValoresEj9;
public class FuncionesArbol{
	
	
	public void cargarArbolString(BinaryTree<String> ab,Scanner in) {
		if(ab!=null) {
			DoubleEndedQueue<String> ops_ant = new DoubleEndedQueue<String>();
			DoubleEndedQueue<BinaryTree<String>> nodos_ant = new DoubleEndedQueue<BinaryTree<String>>();
			//Tanto nodos_ant como ops_ant sirven para la operacion volver
			DoubleEndedQueue<BinaryTree<String>> nodos_padre = new DoubleEndedQueue<BinaryTree<String>>();
			BinaryTree<String> aux_ab = null;
			/*Nodos_padre sirve para la operacion p, no es lo mismo mantener una lista de nodos anteriores en los que
			se estuvo a mantener una lista fija de padres hasta llegar al padre de mi nodo actual
			  Por otro lado aux_ab sirve para guardar momentaneamente el valor de mi nodo actual cuando utilizo
			la operacion p, asiganar a nodo_act su padre y aux_ab(nodo actual anterior)encolarlo en nodos_ant*/
			DoubleEndedQueue<String> cargas_ant = new DoubleEndedQueue<String>();
			//Cola que el estado anterior de un nodo, sirve para la op volver
			String aux_op="";
			BinaryTree<String> nodo_act=ab;
			System.out.print("Operaciones: Cargar, Salir: ");
			aux_op = in.next();
			aux_op= aux_op.toLowerCase();
			if(aux_op.equals("cargar")) {
				cargarS(nodo_act,in);
			}
			while(!aux_op.equals("salir")) {
				System.out.print("Operaciones(Nodo act:"+nodo_act.getData()+"): Cargar, Salir, Volver, Imprimir, Hd, Hi, P: ");
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
					
				case "hi":	
				case "hd":					
					nodos_padre.enqueueFirst(nodo_act);
					nodos_ant.enqueueFirst(nodo_act);
					/*Cuando paso a un hijo, exista ya o se vaya a crear, guardo como padre
					el nodo en el que estaba y paso a uno de sus hijos, además también lo guardo
					en nodos_ant en caso de que quiera utilizar la op volver al estado anterior*/
					if(aux_op.equals("hi")) {
						if(!nodo_act.hasLeftChild()) {
							//Si no tiene hijo izq lo creo
							cargarSH(nodo_act,in,true);
							ops_ant.enqueueFirst("hi_cargar");
							//Guardo que se creo un hijo, para que el volver sepa si tiene que borrarlo
						}
						else ops_ant.enqueueFirst("hi_mover");
						//Guardo que solamente elegí moverme al hijo, el volver no borra nada en ese caso
						nodo_act = nodo_act.getLeftChild();
					}
					else {
						if(!nodo_act.hasRightChild()) {
							//Ídem hijo izq
							cargarSH(nodo_act,in,false);
							ops_ant.enqueueFirst("hd_cargar");
							//Ídem hijo izq
						}
						else ops_ant.enqueueFirst("hd_mover");
						nodo_act = nodo_act.getRightChild();
						//Ídem hijo izq
					}
					break;
					
				case "p":
					if(!nodos_padre.isEmpty()) {
						aux_ab=nodo_act;
						nodo_act=nodos_padre.dequeue();
						//Paso al padre de nodo_act
						ops_ant.enqueueFirst("p_mover");
						//Guardo que me moví al padre para la op volver
						nodos_ant.enqueueFirst(aux_ab);
						//Guardo mi el nodo en el que estaba antes de subir a su padre
					}
					break;
					
				case "volver":
					if(!ops_ant.isEmpty()){
						String op_ant=(ops_ant.dequeue());
						if(!nodos_ant.isEmpty()&&!op_ant.equals("cargar")) {
							if(nodos_ant.head()==nodo_act.getLeftChild()||nodos_ant.head()==nodo_act.getRightChild())nodos_padre.enqueueFirst(nodo_act);
							/*Si en la cabeza de la cola de nodos anteriores hay alguno de los hijos de mi nodo actual entonces significa
							que antes estuve en unos de mis nodos hijo y realicé una operación p en la cual nodo actual pasó a ser el primer
							elemento de la cola nodos_padre, o sea, subí a mi padre, por lo tanto si lo quiero revertir tengo que reencolar al
							principio de la cola al que había sido padre(nodo_act) */
							nodo_act=nodos_ant.dequeue();
							//Vuelvo a nodo anterior, sea padre o hijo
							if(op_ant.equals("hd_cargar")||op_ant.equals("hi_cargar")) {
								
						        if(op_ant.equals("hi_cargar"))nodo_act.removeLeftChild();
						        else nodo_act.removeRightChild();
							}
						}
						/*Unicos casos de operaciones que cambian algo más al volver
						son cuando un hijo fue creado y cargado o un nodo fue reescrito, entonces se borran*/
						if(op_ant.equals("cargar")) {
						nodo_act.setData(cargas_ant.dequeue());
						}
					}
				case "salir":
					break;
				case "imprimir":
					imprimirArbolP(ab,0,nodo_act);
					break;
				default:
					System.out.print("Operación no válida");
				}
			
			}
		}
	}
	private void cargarS(BinaryTree<String> nodo_act,Scanner in) {
		System.out.print("Cargar Dato: ");
		String aux_data = in.next();
		nodo_act.setData(aux_data);
	}
	private void cargarSH(BinaryTree<String> nodo_act,Scanner in,boolean hi) {
		BinaryTree<String> aux_ab = new BinaryTree<String>();
		cargarS(aux_ab,in);
		if(hi)nodo_act.addLeftChild(aux_ab);
		else nodo_act.addRightChild(aux_ab);
	}
	
	public void cargarArbolInteger(BinaryTree<Integer> ab,Scanner in) {
		if(ab!=null) {
			DoubleEndedQueue<String> ops_ant = new DoubleEndedQueue<String>();
			DoubleEndedQueue<BinaryTree<Integer>> nodos_ant = new DoubleEndedQueue<BinaryTree<Integer>>();
			DoubleEndedQueue<BinaryTree<Integer>> nodos_padre = new DoubleEndedQueue<BinaryTree<Integer>>();
			BinaryTree<Integer> aux_ab = null;
			DoubleEndedQueue<Integer> cargas_ant = new DoubleEndedQueue<Integer>();
			String aux_op="";
			BinaryTree<Integer> nodo_act=ab;
			System.out.print("Operaciones: Cargar, Salir: ");
			aux_op = in.next();
			aux_op= aux_op.toLowerCase();
			if(aux_op.equals("cargar")) {
				cargarI(nodo_act,in);
			}
			while(!aux_op.equals("salir")) {
				System.out.print("Operaciones(Nodo act:"+nodo_act.getData()+"): Cargar, Salir, Volver, Imprimir, Hd, Hi, P: ");
				aux_op = in.next();
				aux_op=aux_op.toLowerCase();
				switch (aux_op){
				
				case "cargar":
					cargas_ant.enqueueFirst(nodo_act.getData());
					cargarI(nodo_act,in);
					ops_ant.enqueueFirst(aux_op);
					break;
					
				case "hi":	
				case "hd":					
					nodos_padre.enqueueFirst(nodo_act);
					nodos_ant.enqueueFirst(nodo_act);
					if(aux_op.equals("hi")) {
						if(!nodo_act.hasLeftChild()) {
							cargarIH(nodo_act,in,true);
							ops_ant.enqueueFirst("hi_cargar");
						}
						else ops_ant.enqueueFirst("hi_mover");
						nodo_act = nodo_act.getLeftChild();
					}
					else {
						if(!nodo_act.hasRightChild()) {
							cargarIH(nodo_act,in,false);
							ops_ant.enqueueFirst("hd_cargar");
						}
						else ops_ant.enqueueFirst("hd_mover");
						nodo_act = nodo_act.getRightChild();
					}
					break;
					
				case "p":
					if(!nodos_padre.isEmpty()) {
						aux_ab=nodo_act;
						nodo_act=nodos_padre.dequeue();
						ops_ant.enqueueFirst("p_mover");
						nodos_ant.enqueueFirst(aux_ab);
					}
					break;
					
				case "volver":
					if(!ops_ant.isEmpty()){
						String op_ant=(ops_ant.dequeue());
						if(!nodos_ant.isEmpty()&&!op_ant.equals("cargar")) {
							if(nodos_ant.head()==nodo_act.getLeftChild()||nodos_ant.head()==nodo_act.getRightChild())nodos_padre.enqueueFirst(nodo_act);
							nodo_act=nodos_ant.dequeue();
							if(op_ant.equals("hd_cargar")||op_ant.equals("hi_cargar")) {
						        if(op_ant.equals("hi_cargar"))nodo_act.removeLeftChild();
						        else nodo_act.removeRightChild();
							}
						}
						if(op_ant.equals("cargar")) {
						nodo_act.setData(cargas_ant.dequeue());
						}
					}
				case "salir":
					break;
				case "imprimir":
					imprimirArbolP(ab,0,nodo_act);
					break;
				default:
					System.out.print("Operación no válida");
				}
			
			}
		}
	}
	private void cargarI(BinaryTree<Integer> nodo_act,Scanner in) {
		System.out.print("Cargar Dato: ");
		Integer aux_data = in.nextInt();
		nodo_act.setData(aux_data);
	}
	private void cargarIH(BinaryTree<Integer> nodo_act,Scanner in,boolean hi) {
		BinaryTree<Integer> aux_ab = new BinaryTree<Integer>();
		cargarI(aux_ab,in);
		if(hi)nodo_act.addLeftChild(aux_ab);
		else nodo_act.addRightChild(aux_ab);
	}	
	
	public void cargarArbolCharacter(BinaryTree<Character> ab,Scanner in) {
		if(ab!=null) {
			DoubleEndedQueue<String> ops_ant = new DoubleEndedQueue<String>();
			DoubleEndedQueue<BinaryTree<Character>> nodos_ant = new DoubleEndedQueue<BinaryTree<Character>>();
			DoubleEndedQueue<BinaryTree<Character>> nodos_padre = new DoubleEndedQueue<BinaryTree<Character>>();
			BinaryTree<Character> aux_ab = null;
			DoubleEndedQueue<Character> cargas_ant = new DoubleEndedQueue<Character>();
			String aux_op="";
			BinaryTree<Character> nodo_act=ab;
			System.out.print("Operaciones: Cargar, Salir: ");
			aux_op = in.next();
			aux_op= aux_op.toLowerCase();
			if(aux_op.equals("cargar")) {
				cargarC(nodo_act,in);
			}
			while(!aux_op.equals("salir")) {
				System.out.print("Operaciones(Nodo act:"+nodo_act.getData()+"): Cargar, Salir, Volver, Imprimir, Hd, Hi, P: ");
				aux_op = in.next();
				aux_op=aux_op.toLowerCase();
				switch (aux_op){
				
				case "cargar":
					cargas_ant.enqueueFirst(nodo_act.getData());
					cargarC(nodo_act,in);
					ops_ant.enqueueFirst(aux_op);
					break;
					
				case "hi":	
				case "hd":					
					nodos_padre.enqueueFirst(nodo_act);
					nodos_ant.enqueueFirst(nodo_act);
					if(aux_op.equals("hi")) {
						if(!nodo_act.hasLeftChild()) {
							cargarCH(nodo_act,in,true);
							ops_ant.enqueueFirst("hi_cargar");
						}
						else ops_ant.enqueueFirst("hi_mover");
						nodo_act = nodo_act.getLeftChild();
					}
					else {
						if(!nodo_act.hasRightChild()) {
							cargarCH(nodo_act,in,false);
							ops_ant.enqueueFirst("hd_cargar");
						}
						else ops_ant.enqueueFirst("hd_mover");
						nodo_act = nodo_act.getRightChild();
					}
					break;
					
				case "p":
					if(!nodos_padre.isEmpty()) {
						aux_ab=nodo_act;
						nodo_act=nodos_padre.dequeue();
						ops_ant.enqueueFirst("p_mover");
						nodos_ant.enqueueFirst(aux_ab);
					}
					break;
					
				case "volver":
					if(!ops_ant.isEmpty()){
						String op_ant=(ops_ant.dequeue());
						if(!nodos_ant.isEmpty()&&!op_ant.equals("cargar")) {
							if(nodos_ant.head()==nodo_act.getLeftChild()||nodos_ant.head()==nodo_act.getRightChild())nodos_padre.enqueueFirst(nodo_act);
							nodo_act=nodos_ant.dequeue();
							if(op_ant.equals("hd_cargar")||op_ant.equals("hi_cargar")) {
						        if(op_ant.equals("hi_cargar"))nodo_act.removeLeftChild();
						        else nodo_act.removeRightChild();
							}
						}
						if(op_ant.equals("cargar")) {
						nodo_act.setData(cargas_ant.dequeue());
						}
					}
				case "salir":
					break;
				case "imprimir":
					imprimirArbolP(ab,0,nodo_act);
					break;
				default:
					System.out.print("Operación no válida");
				}
			
			}
		}
	}
	private void cargarC(BinaryTree<Character> nodo_act,Scanner in) {
		System.out.print("Cargar Dato: ");
		String aux = in.next();
		Character aux_data = aux.charAt(0);
		nodo_act.setData(aux_data);
	}
	private void cargarCH(BinaryTree<Character> nodo_act,Scanner in,boolean hi) {
		BinaryTree<Character> aux_ab = new BinaryTree<Character>();
		cargarC(aux_ab,in);
		if(hi)nodo_act.addLeftChild(aux_ab);
		else nodo_act.addRightChild(aux_ab);
	}
	
	private void imprimirArbolP(BinaryTree<?> ab,int i,BinaryTree<?> act) {
		/*Primero llega al hijo más derecho y lo imprime con una cantidad de espacios que fue aumentando
		mientrass bajaba en profundiad, así va imprimiendose en manera triangular horizontal el árbol. Además 
		cuando el nodo es coincidente con el nodo_act agrega un '-' para indicar posición*/
		if(ab!=null&&!ab.isEmpty()) {
			this.imprimirArbolP(ab.getRightChild(),i+1,act);
			int a=0;
			if(ab==act) {
				System.out.print("-  ");
				a++;
			}
			for(;a<i+1;a++) {
				System.out.print("   ");
			}
			System.out.println(ab.getData());
			this.imprimirArbolP(ab.getLeftChild(),i+1,act);
		}
	}
	
	public  void imprimirArbol(BinaryTree<?> ab,int i) {
		/*Primero llega al hijo más derecho y lo imprime con una cantidad de espacios que fue aumentando
		mientrass bajaba en profundiad, así va imprimiendose en manera triangular horizontal el árbol*/
		if(ab!=null&&!ab.isEmpty()) {
			this.imprimirArbol(ab.getRightChild(),i+1);
			int a=0;
			for(;a<i+1;a++) {
				System.out.print("   ");
			}
			System.out.println(ab.getData());
			this.imprimirArbol(ab.getLeftChild(),i+1);
		}
	}
	
	public void imprimirArbolV(BinaryTree<ValoresEj9> ab,int i) {
		if(ab!=null&&!ab.isEmpty()) {
			this.imprimirArbolV(ab.getRightChild(),i+1);
			int a=0;
			for(;a<i+1;a++) {
				System.out.print("   ");
			}
			System.out.println(ab.getData().toString());
			this.imprimirArbolV(ab.getLeftChild(),i+1);
		}
	}

}