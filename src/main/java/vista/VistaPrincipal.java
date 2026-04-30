package vista;

import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import modelo.Estado;
import modelo.Tarea;

/**
 * Ventana principal del sistema. Construida sin paleta grafica.
 */
public class VistaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;

    // colores que se reusan
    private final Color colorPrimario = new Color(33, 64, 154);
    private final Color colorFondo = new Color(240, 242, 245);
    private final Font fuenteEtiqueta = new Font("Arial", Font.BOLD, 13);

    private JTextField txtCodigo;
    private JTextField txtTitulo;
    private JTextField txtCurso;
    private JDateChooser dateChooser;
    private JComboBox<Estado> cmbEstado;

    private JButton btnRegistrar;
    private JButton btnEliminar;
    private JButton btnCambiarEstado;
    private JButton btnBuscar;

    private JTextField txtBuscar;
    private JTable tablaTareas;
    private DefaultTableModel modeloTabla;

    public VistaPrincipal() {
        setTitle("Sistema de Gestion de Tareas Academicas");
        setSize(900, 620);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(colorFondo);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        // titulo arriba
        JLabel lblTitulo = new JLabel("GESTION DE TAREAS ACADEMICAS", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(colorPrimario);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(12, 0, 8, 0));

        // formulario
        JPanel pnlFormulario = new JPanel(new GridLayout(5, 2, 8, 8));
        pnlFormulario.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Datos de la tarea"),
                BorderFactory.createEmptyBorder(10, 12, 10, 12)));
        pnlFormulario.setBackground(Color.WHITE);

        JLabel lblCod = new JLabel("Codigo:");
        lblCod.setFont(fuenteEtiqueta);
        pnlFormulario.add(lblCod);
        txtCodigo = new JTextField();
        estilizarTextField(txtCodigo);
        pnlFormulario.add(txtCodigo);

        JLabel lblTit = new JLabel("Titulo:");
        lblTit.setFont(fuenteEtiqueta);
        pnlFormulario.add(lblTit);
        txtTitulo = new JTextField();
        estilizarTextField(txtTitulo);
        pnlFormulario.add(txtTitulo);

        JLabel lblCur = new JLabel("Curso:");
        lblCur.setFont(fuenteEtiqueta);
        pnlFormulario.add(lblCur);
        txtCurso = new JTextField();
        estilizarTextField(txtCurso);
        pnlFormulario.add(txtCurso);

        JLabel lblFec = new JLabel("Fecha entrega:");
        lblFec.setFont(fuenteEtiqueta);
        pnlFormulario.add(lblFec);
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy");
        dateChooser.setFont(new Font("Arial", Font.PLAIN, 13));
        dateChooser.setBackground(Color.WHITE);
        dateChooser.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 1));
        pnlFormulario.add(dateChooser);

        JLabel lblEst = new JLabel("Estado:");
        lblEst.setFont(fuenteEtiqueta);
        pnlFormulario.add(lblEst);
        cmbEstado = new JComboBox<>(Estado.values());
        pnlFormulario.add(cmbEstado);

        // botones con colores
        btnRegistrar = new JButton("Registrar");
        estilizarBoton(btnRegistrar, new Color(76, 175, 80));   // verde

        btnEliminar = new JButton("Eliminar");
        estilizarBoton(btnEliminar, new Color(229, 57, 53));    // rojo

        btnCambiarEstado = new JButton("Cambiar estado");
        estilizarBoton(btnCambiarEstado, new Color(255, 152, 0)); // naranja

        JPanel pnlBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        pnlBotones.setBackground(colorFondo);
        pnlBotones.add(btnRegistrar);
        pnlBotones.add(btnEliminar);
        pnlBotones.add(btnCambiarEstado);

        // panel arriba (titulo + formulario + botones)
        JPanel pnlNorte = new JPanel(new BorderLayout(5, 5));
        pnlNorte.setBackground(colorFondo);
        pnlNorte.add(lblTitulo, BorderLayout.NORTH);
        pnlNorte.add(pnlFormulario, BorderLayout.CENTER);
        pnlNorte.add(pnlBotones, BorderLayout.SOUTH);
        pnlNorte.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));

        // tabla
        String[] columnas = {"Codigo", "Titulo", "Curso", "Fecha entrega", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaTareas = new JTable(modeloTabla);
        tablaTareas.setRowHeight(24);
        tablaTareas.setFont(new Font("Arial", Font.PLAIN, 12));
        tablaTareas.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        tablaTareas.getTableHeader().setBackground(colorPrimario);
        tablaTareas.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollTabla = new JScrollPane(tablaTareas);
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Lista de tareas"));

        JPanel pnlCentro = new JPanel(new BorderLayout());
        pnlCentro.setBackground(colorFondo);
        pnlCentro.setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 12));
        pnlCentro.add(scrollTabla, BorderLayout.CENTER);

        // busqueda
        JLabel lblBuscar = new JLabel("Buscar (codigo o titulo):");
        lblBuscar.setFont(fuenteEtiqueta);

        txtBuscar = new JTextField(22);
        estilizarTextField(txtBuscar);
        btnBuscar = new JButton("Buscar");
        estilizarBoton(btnBuscar, new Color(33, 150, 243)); // azul

        JPanel pnlBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        pnlBusqueda.setBackground(colorFondo);
        pnlBusqueda.setBorder(BorderFactory.createEmptyBorder(0, 12, 10, 12));
        pnlBusqueda.add(lblBuscar);
        pnlBusqueda.add(txtBuscar);
        pnlBusqueda.add(btnBuscar);

        add(pnlNorte, BorderLayout.NORTH);
        add(pnlCentro, BorderLayout.CENTER);
        add(pnlBusqueda, BorderLayout.SOUTH);
    }

    // aplica el estilo (color, letra blanca, sin borde de foco) a un boton
    private void estilizarBoton(JButton boton, Color color) {
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial", Font.BOLD, 12));
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setOpaque(true);
    }

    // pone borde visible y padding interno a un campo de texto
    private void estilizarTextField(JTextField tf) {
        tf.setBackground(Color.WHITE);
        tf.setFont(new Font("Arial", Font.PLAIN, 13));
        tf.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(150, 150, 150), 1),
                BorderFactory.createEmptyBorder(6, 10, 6, 10)
        ));
    }

    // getters de los datos del formulario

    public String getCodigo() {
        return txtCodigo.getText().trim();
    }

    public String getTitulo() {
        return txtTitulo.getText().trim();
    }

    public String getCurso() {
        return txtCurso.getText().trim();
    }

    public String getFecha() {
        Date date = dateChooser.getDate();
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }

    public Estado getEstadoSeleccionado() {
        return (Estado) cmbEstado.getSelectedItem();
    }

    public String getTextoBusqueda() {
        return txtBuscar.getText().trim();
    }

    // getters de los componentes (para que el controlador les ponga listeners)

    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JButton getBtnCambiarEstado() {
        return btnCambiarEstado;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JTable getTablaTareas() {
        return tablaTareas;
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    // pinta en la tabla la lista de tareas que recibe del controlador
    public void cargarTareas(List<Tarea> tareas) {
        modeloTabla.setRowCount(0);
        for (Tarea t : tareas) {
            modeloTabla.addRow(new Object[]{
                t.getCodigo(),
                t.getTitulo(),
                t.getCurso(),
                t.getFechaEntrega(),
                t.getEstado()
            });
        }
    }

    public void limpiarFormulario() {
        txtCodigo.setText("");
        txtTitulo.setText("");
        txtCurso.setText("");
        dateChooser.setDate(null);
        cmbEstado.setSelectedIndex(0);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // muestra un Si/No y devuelve true si el usuario eligio Si
    public boolean confirmar(String mensaje) {
        int respuesta = JOptionPane.showConfirmDialog(
                this, mensaje, "Confirmar", JOptionPane.YES_NO_OPTION);
        return respuesta == JOptionPane.YES_OPTION;
    }

    // dialogo para escoger un nuevo estado de la lista
    public Estado seleccionarEstado(Estado actual) {
        return (Estado) JOptionPane.showInputDialog(
                this,
                "Selecciona el nuevo estado:",
                "Cambiar estado",
                JOptionPane.QUESTION_MESSAGE,
                null,
                Estado.values(),
                actual
        );
    }
}
