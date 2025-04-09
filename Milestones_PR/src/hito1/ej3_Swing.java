package hito1;

import javax.swing.*;
import java.awt.*;

public class ej3_Swing {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Try yourself");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());


        JPanel panelNorte = new JPanel(new FlowLayout());
        panelNorte.add(new JCheckBox("Katniss"));
        panelNorte.add(new JCheckBox("Peeta"));
        frame.add(panelNorte, BorderLayout.NORTH);


        JPanel panelEste = new JPanel();
        panelEste.setPreferredSize(new Dimension(250, 0));
        panelEste.setLayout(null);


        JPanel panelRadios = new JPanel();
        panelRadios.setLayout(new BoxLayout(panelRadios, BoxLayout.Y_AXIS));

        JRadioButton[] radios = new JRadioButton[3];
        ButtonGroup grupoRadios = new ButtonGroup();
        for (int i = 0; i < radios.length; i++) {
            radios[i] = new JRadioButton("OPT" + (i + 1));
            grupoRadios.add(radios[i]);
            panelRadios.add(radios[i]);
        }
        radios[0].setSelected(true);

        panelRadios.setBounds(20, 20, 100, 100);
        panelEste.add(panelRadios);
        frame.add(panelEste, BorderLayout.EAST);


        JPanel panelSur = new JPanel();
        panelSur.setPreferredSize(new Dimension(0, 50)); // Altura fija
        panelSur.setLayout(new BoxLayout(panelSur, BoxLayout.X_AXIS));

        panelSur.add(new JButton("But 1"));
        panelSur.add(new JButton("But 2"));

        frame.add(panelSur, BorderLayout.SOUTH);


        JPanel panelCentro = new JPanel(new GridLayout(2, 2));
        ImageIcon imagen = new ImageIcon("C:/Users/ik012982i9/Documents/Ferrari.jpg");

        for (int i = 0; i < 4; i++) {
            panelCentro.add(new JLabel(imagen));
        }

        frame.add(panelCentro, BorderLayout.CENTER);


        frame.setVisible(true);
    }

}

