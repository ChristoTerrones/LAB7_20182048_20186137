package Beans;

public class reporte {
    private int id;
    private String Nombre;
    private int Ncategorias;
    private int Npeliculas;

    public reporte(int id, String nombre, int ncategorias, int npeliculas) {
        this.id = id;
        Nombre = nombre;
        Ncategorias = ncategorias;
        Npeliculas = npeliculas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getNcategorias() {
        return Ncategorias;
    }

    public void setNcategorias(int ncategorias) {
        Ncategorias = ncategorias;
    }

    public int getNpeliculas() {
        return Npeliculas;
    }

    public void setNpeliculas(int npeliculas) {
        Npeliculas = npeliculas;
    }
}
