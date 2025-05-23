package Hito3_con_imagenes;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ImagenViewer extends JFrame implements ActionListener {

    private static final String IMAGE_FOLDER = "/Hito3_con_imagenes/imagenes";
    private JComboBox<String> combo;
    private JLabel label;
    private JCheckBox checkBox;
    private JTextField commentField;
    private JButton saveButton;

    public ImagenViewer() {
        super("Visor de imágenes");

        combo = new JComboBox<>();
        label = new JLabel();
        checkBox = new JCheckBox("Guardar comentario", true);
        commentField = new JTextField(20);
        saveButton = new JButton("GUARDAR");

        combo.setBorder(new EmptyBorder(5, 5, 10, 5));
        combo.setPreferredSize(new Dimension(250, combo.getPreferredSize().height));
        combo.setAlignmentX(Component.LEFT_ALIGNMENT);

        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        checkBox.setAlignmentX(Component.LEFT_ALIGNMENT);

        setLayout(new BorderLayout(10,10));

        // Panel izquierdo con BoxLayout vertical
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(new EmptyBorder(0,0,0,0));

        leftPanel.add(combo);
        leftPanel.add(label);
        leftPanel.add(Box.createVerticalStrut(10)); // espacio
        leftPanel.add(checkBox);

        add(leftPanel, BorderLayout.WEST);

        // Panel inferior para botón GUARDAR y campo texto juntos centrados
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        commentField.setPreferredSize(new Dimension(250, 25));

        bottomPanel.add(saveButton);
        bottomPanel.add(commentField);

        add(bottomPanel, BorderLayout.SOUTH);

        load_combo();

        if (combo.getItemCount() > 0) {
            combo.setSelectedIndex(0);
            loadImage((String)combo.getSelectedItem());
        }

        combo.addActionListener(new ComboListener());
        saveButton.addActionListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 450);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void load_combo() {
        combo.addItem("finca.jpg");
        combo.addItem("depa.jpg");
        combo.addItem("casas.jpg");
        combo.addItem("mansiones.jpg");
    }

    private void loadImage(String imageName) {
        try {
            java.net.URL imgURL = getClass().getResource(IMAGE_FOLDER + "/" + imageName);
            if (imgURL != null) {
                ImageIcon icon = new ImageIcon(imgURL);
                label.setIcon(icon);
                label.setText("");
            } else {
                label.setIcon(null);
                label.setText("Imagen no encontrada");
            }
        } catch (Exception e) {
            label.setIcon(null);
            label.setText("Error cargando imagen");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            if (checkBox.isSelected()) {
                String imageName = (String) combo.getSelectedItem();
                String comment = commentField.getText();
                try (FileWriter fw = new FileWriter(imageName + ".txt", true);
                     BufferedWriter bw = new BufferedWriter(fw)) {
                    bw.write(imageName + ": " + comment);
                    bw.newLine();
                    JOptionPane.showMessageDialog(this, "Comentario guardado.");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error guardando comentario.");
                }
            }
        }
    }

    private class ComboListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String selected = (String) combo.getSelectedItem();
            loadImage(selected);
        }
    }

    public static void main(String[] args) {
        String pass = JOptionPane.showInputDialog(null, "Ingrese la contraseña:");
        if (!"damocles".equals(pass)) {
            JOptionPane.showMessageDialog(null, "Contraseña incorrecta. Cerrando aplicación.");
            System.exit(0);
        }

        SwingUtilities.invokeLater(() -> new ImagenViewer());
    }
}
