package hito2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.JOptionPane;

public class FileViewer extends JFrame {

    private JComboBox<String> fileComboBox;
    private JTextArea textArea;
    private JTextArea infoTextArea;
    private JButton clearButton, closeButton;

    public FileViewer() {

        setTitle("Test Events: Files");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));

        String[] files = {"python.txt", "c.txt", "java.txt"};
        fileComboBox = new JComboBox<>(files);
        fileComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        fileComboBox.setPreferredSize(new Dimension(250, 25));

        fileComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedFile = (String) fileComboBox.getSelectedItem();
                displayFileContent(selectedFile);
                displayLanguageInfo(selectedFile); // Mostrar información sobre el lenguaje
            }
        });

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(new Color(238, 238, 238));

        infoTextArea = new JTextArea();
        infoTextArea.setEditable(false);
        infoTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        infoTextArea.setLineWrap(false);
        infoTextArea.setWrapStyleWord(false);
        infoTextArea.setBackground(new Color(245, 245, 245));
        infoTextArea.setText("Selecciona un lenguaje para ver más información.");


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));


        clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Arial", Font.PLAIN, 14));
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                infoTextArea.setText("Selecciona un lenguaje para ver más información.");
            }
        });


        closeButton = new JButton("Close");
        closeButton.setFont(new Font("Arial", Font.PLAIN, 14));
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });



        buttonPanel.add(closeButton);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        topPanel.setBackground(new Color(245, 245, 245));
        topPanel.add(fileComboBox);
        topPanel.add(clearButton);

        JScrollPane leftScroll = new JScrollPane(textArea);
        leftScroll.setPreferredSize(new Dimension(300, 100));

        JScrollPane rightScroll = new JScrollPane(infoTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        rightScroll.setPreferredSize(new Dimension(300, 100)); // o el ancho que quieras para que ocupe "la mitad"

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftScroll, rightScroll);
        splitPane.setResizeWeight(0.5);
        splitPane.setDividerSize(5);
        splitPane.setContinuousLayout(true);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(splitPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }

    private void displayFileContent(String fileName) {
        try {
            File file = new File(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            StringBuilder content = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }

            textArea.setText(content.toString());
            reader.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Archivo no encontrado: " + fileName,
                    "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al leer el archivo: " + fileName,
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void displayLanguageInfo(String fileName) {
        String info = "";
        switch (fileName) {
            case "python.txt":
                info = "Python es un lenguaje de programación de alto nivel, interpretado y de propósito general. Es conocido por su sintaxis clara y su enfoque en la legibilidad del código.";
                break;
            case "c.txt":
                info = "C es un lenguaje de programación de propósito general, desarrollado en los años 70. Ha sido muy influyente y se utiliza en sistemas embebidos y programación de bajo nivel.";
                break;
            case "java.txt":
                info = "Java es un lenguaje de programación orientado a objetos, concurrente y basado en clases. Es conocido por su lema 'escribe una vez, ejecuta en cualquier lugar'.";
                break;
        }
        infoTextArea.setText(info);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FileViewer frame = new FileViewer();
                frame.setVisible(true);
            }
        });
    }
}
