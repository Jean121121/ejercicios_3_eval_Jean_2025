package Hito4;

public class Pintor {
    private int idPintor;
    private String nombre;
    private boolean premiado;

    public Pintor(int idPintor, String nombre, boolean premiado) {
        this.idPintor = idPintor;
        this.nombre = nombre;
        this.premiado = premiado;
    }

    public int getIdPintor() {
        return idPintor;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isPremiado() {
        return premiado;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
