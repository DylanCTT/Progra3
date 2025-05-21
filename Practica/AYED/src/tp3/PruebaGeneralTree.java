package tp3;
import java.util.Scanner;
import paquete_funciones_arbol.FuncionesArbol;
public class PruebaGeneralTree {
	 	public static void main(String[] args) {
	 		BinaryTree<Integer> ab = new BinaryTree<Integer>();
	 		Scanner in =new Scanner(System.in);
	 		FuncionesArbol F= new FuncionesArbol();
	 		F.cargarArbolInteger(ab,in);
	 	}
}
