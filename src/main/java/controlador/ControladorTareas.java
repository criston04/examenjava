package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    }

    // crea la tarea con los datos del formulario y la agrega al modelo
    private void registrarTarea() {
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
}
