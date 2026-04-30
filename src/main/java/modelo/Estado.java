package modelo;

/**
 * Estados posibles de una tarea.
 */
public enum Estado {
    PENDIENTE("Pendiente"),
    EN_PROCESO("En proceso"),
    COMPLETADA("Completada");

    private final String texto;

    Estado(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return texto;
    }
}
