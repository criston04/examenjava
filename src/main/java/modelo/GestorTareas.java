package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Maneja la lista de tareas.
 */
public class GestorTareas {

    private List<Tarea> tareas;

    public GestorTareas() {
        tareas = new ArrayList<>();
    }

    public void agregar(Tarea tarea) {
        tareas.add(tarea);
    }

    // elimina la tarea con ese codigo. Devuelve true si la encontro
    public boolean eliminarPorCodigo(String codigo) {
        Tarea t = buscarPorCodigo(codigo);
        if (t != null) {
            tareas.remove(t);
            return true;
        }
        return false;
    }

    public Tarea buscarPorCodigo(String codigo) {
        for (Tarea t : tareas) {
            if (t.getCodigo().equalsIgnoreCase(codigo)) {
                return t;
            }
        }
        return null;
    }

    // verifica si ya existe ese codigo (para no duplicar)
    public boolean existeCodigo(String codigo) {
        return buscarPorCodigo(codigo) != null;
    }

    // busca tareas que contengan el texto en codigo o titulo
    public List<Tarea> buscarPorTexto(String texto) {
        List<Tarea> resultado = new ArrayList<>();
        String q = texto.toLowerCase();
        for (Tarea t : tareas) {
            if (t.getCodigo().toLowerCase().contains(q)
                    || t.getTitulo().toLowerCase().contains(q)) {
                resultado.add(t);
            }
        }
        return resultado;
    }

    public List<Tarea> listar() {
        return tareas;
    }
}
