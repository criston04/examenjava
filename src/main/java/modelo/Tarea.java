package modelo;

/**
 * Representa una tarea academica.
 */
public class Tarea {

    private String codigo;
    private String titulo;
    private String curso;
    private String fechaEntrega;
    private Estado estado;

    public Tarea(String codigo, String titulo, String curso,
                 String fechaEntrega, Estado estado) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.curso = curso;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
