package Hito4;

import Hito4.Pintor;
import Hito4.PintoresBD;
import Hito4.Pinturas;
import Hito4.PinturasBD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

public class GaleriaApp extends JFrame {
    private JComboBox<Pintor> comboPintores;
    private JSpinner dateSpinner;
    private JCheckBox chkUsarFecha;
    private JList<Pinturas> listaPinturas;
    private DefaultListModel<Pinturas> modeloLista;
    private JLabel etiquetaImagen;

    private PintoresBD pintorDAO;
    private PinturasBD pinturaDAO;

    public GaleriaApp() {
        setTitle("Galería de Arte");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Conexión
        Connection conn = ConexionDB.conectar();
        pintorDAO = new PintoresBD(conn);
        pinturaDAO = new PinturasBD(conn);

        // Panel superior con ComboBox y Spinner
        JPanel panelSuperior = new JPanel();
        comboPintores = new JComboBox<>();

        SpinnerDateModel modelFecha = new SpinnerDateModel();
        dateSpinner = new JSpinner(modelFecha);
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd"));

        chkUsarFecha = new JCheckBox("Filtrar por fecha");

        panelSuperior.add(new JLabel("Pintor:"));
        panelSuperior.add(comboPintores);
        panelSuperior.add(chkUsarFecha);
        panelSuperior.add(dateSpinner);

        add(panelSuperior, BorderLayout.NORTH);

        // Centro con la lista
        modeloLista = new DefaultListModel<>();
        listaPinturas = new JList<>(modeloLista);
        add(new JScrollPane(listaPinturas), BorderLayout.CENTER);

        // Imagen en la parte inferior
        etiquetaImagen = new JLabel("", JLabel.CENTER);
        etiquetaImagen.setPreferredSize(new Dimension(400, 300));
        add(etiquetaImagen, BorderLayout.SOUTH);

        // Cargar pintores
        cargarPintores();

        // Listeners
        comboPintores.addActionListener(e -> actualizarLista());
        chkUsarFecha.addActionListener(e -> actualizarLista());
        dateSpinner.addChangeListener(e -> actualizarLista());

        listaPinturas.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    Pinturas seleccionada = listaPinturas.getSelectedValue();
                    if (seleccionada != null) {
                        mostrarImagen(seleccionada);
                        pinturaDAO.incrementarVisitas(seleccionada);
                    }
                }
            }
        });
    }

    private void cargarPintores() {
        List<Pintor> pintores = pintorDAO.obtenerTodos();
        for (Pintor p : pintores) {
            comboPintores.addItem(p);
        }
    }

    private void actualizarLista() {
        Pintor seleccionado = (Pintor) comboPintores.getSelectedItem();
        if (seleccionado != null) {
            Date fecha = chkUsarFecha.isSelected() ? (Date) dateSpinner.getValue() : null;
            List<Pinturas> pinturas = pinturaDAO.obtenerPorPintorYFecha(seleccionado.getIdPintor(), fecha);

            modeloLista.clear();
            for (Pinturas p : pinturas) {
                modeloLista.addElement(p);
            }
        }
    }

    private void mostrarImagen(Pinturas p) {
        try {
            String ruta = "imagenes/" + p.getArchivo();
            ImageIcon icon = new ImageIcon(ruta);
            Image img = icon.getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH);
            etiquetaImagen.setIcon(new ImageIcon(img));
            etiquetaImagen.setText("");
        } catch (Exception ex) {
            etiquetaImagen.setIcon(null);
            etiquetaImagen.setText("No se pudo cargar la imagen");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GaleriaApp().setVisible(true));
    }
}
