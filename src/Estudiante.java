import java.io.Serializable;

public class Estudiante implements Serializable {
    private static final long serialVersionUID=1L;
    private String nombres;
    private String apellidos;
    private String cedula;
    private String codigo;
    private String fecha_naci;
    private String signo;
    private String color_fav;
    private String casado;

    public Estudiante(String nombres, String apellidos, String cedula, String codigo) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.cedula = cedula;
        this.codigo = codigo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getFecha_naci() {
        return fecha_naci;
    }

    public void setFecha_naci(String fecha_naci) {
        this.fecha_naci = fecha_naci;
    }

    public String getSigno() {
        return signo;
    }

    public void setSigno(String signo) {
        this.signo = signo;
    }

    public String getColor_fav() {
        return color_fav;
    }

    public void setColor_fav(String color_fav) {
        this.color_fav = color_fav;
    }

    public String getCasado() {
        return casado;
    }

    public void setCasado(String casado) {
        this.casado = casado;
    }

    @Override
    public String toString(){
        return "\nNombres: " + nombres + "\nApellidos: " + apellidos + "\nCédula: " + cedula + "\nCódigo: " + codigo
                + "\nFecha de Nacimiento: " + fecha_naci + "\nSigno: " + signo +
                "\nColor favorito: " + color_fav + "\nCasado: " + casado + "\n";
    }
}
