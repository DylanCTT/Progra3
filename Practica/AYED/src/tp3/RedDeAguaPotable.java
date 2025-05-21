package tp3;

public class RedDeAguaPotable {

    private GeneralTree<Character> red;

    public RedDeAguaPotable(GeneralTree<Character> red) {
        this.red = red;
    }

    public double minimoCaudal(double caudalInicial) {
        return calcularMinimoCaudal(this.red, caudalInicial);
    }

    private double calcularMinimoCaudal(GeneralTree<Character> nodo, double caudalActual) {
        if (nodo.isLeaf()) {
            return caudalActual;
        }
        //el corte de recurcion

        int hijos = nodo.getChildren().size();
        //cantidad de hijos de un padre
        double caudalPorHijo = caudalActual / hijos;
        //el caudal que le toca a cada hijo
        double minimo = 999999;
        //donde guardo mi minimo
        for (GeneralTree<Character> hijo : nodo.getChildren()) {
            double caudalHijo = calcularMinimoCaudal(hijo, caudalPorHijo);
            //para cada hijo guardo mi caudal
            if (caudalHijo < minimo) {
                minimo = caudalHijo;
            }
            //cuando salga de la recurcion comparo para saber el minimo
        }

        return minimo;
    }
}
