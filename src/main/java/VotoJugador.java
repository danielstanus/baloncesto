public class VotoJugador {
    private String nombre;
    private int votos;

    public VotoJugador(String nombre, int votos) {
        this.nombre = nombre;
        this.votos = votos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVotos() {
        return votos;
    }

    public void setVotos(int votos) {
        this.votos = votos;
    }

    @Override
    public String toString() {
        return "VotoJugador{" +
               "nombre='" + nombre + '\'' +
               ", votos=" + votos +
               '}';
    }
}
