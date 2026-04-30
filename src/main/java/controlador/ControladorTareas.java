package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Estado;
import modelo.GestorTareas;
import modelo.Tarea;
import vista.VistaPrincipal;

/**
 * Controlador: conecta la vista con el modelo y maneja los eventos.
 */
public class ControladorTareas {

    private GestorTareas modelo;
    private VistaPrincipal vista;

    public ControladorTareas(GestorTareas modelo, VistaPrincipal vista) {
        this.modelo = modelo;
        this.vista = vista;

        registrarEventos();
    }

    // asocia los listeners a cada boton de la vista
    private void registrarEventos() {
        vista.getBtnRegistrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarTarea();
            }
        });

        vista.getBtnEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarTarea();
            }
        });

        vista.getBtnCambiarEstado().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarEstadoTarea();
            }
        });

        vista.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarTarea();
            }
        });
    }

    // crea la tarea con los datos del formulario y la agrega al modelo
    private void registrarTarea() {
        // validar campos vacios
        if (vista.getCodigo().isEmpty()
                || vista.getTitulo().isEmpty()
                || vista.getCurso().isEmpty()
                || vista.getFecha().isEmpty()) {
            vista.mostrarError("Todos los campos son obligatorios.");
            return;
        }

        // validar codigo duplicado
        if (modelo.existeCodigo(vista.getCodigo())) {
            vista.mostrarError("Ya existe una tarea con el codigo " + vista.getCodigo() + ".");
            return;
        }

        Tarea nueva = new Tarea(
                vista.getCodigo(),
                vista.getTitulo(),
                vista.getCurso(),
                vista.getFecha(),
                vista.getEstadoSeleccionado()
        );
        modelo.agregar(nueva);
        vista.cargarTareas(modelo.listar());
        vista.limpiarFormulario();
        vista.mostrarMensaje("Tarea registrada correctamente.");
    }

    // elimina la tarea de la fila seleccionada en la tabla
    private void eliminarTarea() {
        int fila = vista.getTablaTareas().getSelectedRow();
        if (fila == -1) {
            vista.mostrarError("Selecciona una tarea de la tabla.");
            return;
        }
        String codigo = (String) vista.getModeloTabla().getValueAt(fila, 0);

        if (vista.confirmar("Deseas eliminar la tarea con codigo " + codigo + "?")) {
            modelo.eliminarPorCodigo(codigo);
            vista.cargarTareas(modelo.listar());
            vista.mostrarMensaje("Tarea eliminada.");
        }
    }

    // cambia el estado de la tarea seleccionada usando un dialogo
    private void cambiarEstadoTarea() {
        int fila = vista.getTablaTareas().getSelectedRow();
        if (fila == -1) {
            vista.mostrarError("Selecciona una tarea de la tabla.");
            return;
        }
        String codigo = (String) vista.getModeloTabla().getValueAt(fila, 0);
        Tarea tarea = modelo.buscarPorCodigo(codigo);
        if (tarea == null) {
            vista.mostrarError("No se encontro la tarea.");
            return;
        }

        Estado nuevoEstado = vista.seleccionarEstado(tarea.getEstado());
        if (nuevoEstado != null) {
            tarea.setEstado(nuevoEstado);
            vista.cargarTareas(modelo.listar());
            vista.mostrarMensaje("Estado actualizado.");
        }
    }

    // busca por codigo o titulo. Si el campo esta vacio muestra todas
    private void buscarTarea() {
        String texto = vista.getTextoBusqueda();
        if (texto.isEmpty()) {
            vista.cargarTareas(modelo.listar());
        } else {
            vista.cargarTareas(modelo.buscarPorTexto(texto));
        }
    }
}
