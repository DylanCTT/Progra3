package tp3;

public class PruebaEj11 {
    public static void main(String[] args) {
    	
        // Árbol que CUMPLE la condición
        GeneralTree<Integer> arbolBien = new GeneralTree<>(1);
        
        GeneralTree<Integer> hijo1 = new GeneralTree<>(2);
        GeneralTree<Integer> hijo2 = new GeneralTree<>(3);
        
        arbolBien.addChild(hijo1);
        arbolBien.addChild(hijo2);
        
        // Nivel 2: 3 nodos
        GeneralTree<Integer> nieto1 = new GeneralTree<>(4);
        GeneralTree<Integer> nieto2 = new GeneralTree<>(5);
        GeneralTree<Integer> nieto3 = new GeneralTree<>(6);
        
        hijo1.addChild(nieto1);
        hijo1.addChild(nieto2);
        hijo2.addChild(nieto3);
        
        // Nivel 3: 4 nodos
        nieto1.addChild(new GeneralTree<>(7));
        nieto1.addChild(new GeneralTree<>(8));
        
        nieto2.addChild(new GeneralTree<>(9));
        
        nieto3.addChild(new GeneralTree<>(10));
        
        System.out.println("Árbol que cumple condición: " + ParcialEj11.resolver(arbolBien)); // debería dar true
        
        // --------------------------------
        
        // Árbol que NO CUMPLE la condición
        GeneralTree<Integer> arbolMal = new GeneralTree<>(100);
        
        GeneralTree<Integer> hijoM1 = new GeneralTree<>(200);
        GeneralTree<Integer> hijoM2 = new GeneralTree<>(300);
        
        arbolMal.addChild(hijoM1);
        arbolMal.addChild(hijoM2);
        
        // Nivel 2: sólo 2 nodos otra vez (mal, debería haber 3)
        hijoM1.addChild(new GeneralTree<>(400));
        hijoM2.addChild(new GeneralTree<>(500));
        
        System.out.println("Árbol que NO cumple condición: " + ParcialEj11.resolver(arbolMal)); // debería dar false
    }
}
