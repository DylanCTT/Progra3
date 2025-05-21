package TP1;

import java.util.Scanner;

public class Ejercicio2 {
	public static void crearVector (int n) {
		
	}
	
	public static int contar42 ( ) {
		Scanner s = new Scanner(System.in) ;
		int cantidad = 1 ;
		while (s.nextInt() != 42) {
		cantidad++;
		}
		return cantidad;
		}
	
}

