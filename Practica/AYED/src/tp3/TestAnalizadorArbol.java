package tp3;

public class TestAnalizadorArbol {
    public static void main(String[] args) {
        // Crear nodos hoja
        GeneralTree<AreaEmpresa> nodoE = new GeneralTree<>(new AreaEmpresa("E", 5));
        GeneralTree<AreaEmpresa> nodoF = new GeneralTree<>(new AreaEmpresa("F", 15));
        GeneralTree<AreaEmpresa> nodoG = new GeneralTree<>(new AreaEmpresa("G", 10));

        // Crear nodo intermedio con hijos
        GeneralTree<AreaEmpresa> nodoB = new GeneralTree<>(new AreaEmpresa("B", 20));
        nodoB.addChild(nodoE);
        nodoB.addChild(nodoF);

        GeneralTree<AreaEmpresa> nodoC = new GeneralTree<>(new AreaEmpresa("C", 12));
        GeneralTree<AreaEmpresa> nodoD = new GeneralTree<>(new AreaEmpresa("D", 16));
        nodoD.addChild(nodoG);

        // Raíz con tres hijos
        GeneralTree<AreaEmpresa> raiz = new GeneralTree<>(new AreaEmpresa("A", 14));
        raiz.addChild(nodoB);
        raiz.addChild(nodoC);
        raiz.addChild(nodoD);

        // Probar método
        AnalizadorArbol analizador = new AnalizadorArbol();
        int resultado1 = analizador.devolverMaximoPromedioNivelSize(raiz);
        System.out.println("El mayor promedio por nivel es: " + resultado1);
        int resultado2 = analizador.devolverMaximoPromedioColaNulls(raiz);

        System.out.println("El mayor promedio por nivel es: " + resultado2);
    }
}