package tp3;

public class AreaEmpresa {
    private String identificacion;
    private int tiempo;

    public AreaEmpresa(String identificacion, int tiempo) {
        this.identificacion = identificacion;
        this.tiempo = tiempo;
    }

    public int getTiempo() {
        return tiempo;
    }

    public String getIdentificacion() {
        return identificacion;
    }
    
    @Override
    public String toString() {
        return identificacion + " (" + tiempo + ")";
    }
}

