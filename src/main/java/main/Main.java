package main;

import vista.VistaPrincipal;

/**
 * Clase principal del Sistema de Gestion de Tareas Academicas.
 * Patron de diseno: mvc
 *  - modelo: representa los datos y reglas del sistema
 *  - vista: interfaz grafica java Swing
 *  - controlador: conecta la vista con el modelo y gestiona todos los eventos
 */
public class Main {

    public static void main(String[] args) {
        VistaPrincipal vista = new VistaPrincipal();
        vista.setVisible(true);
    }
}
