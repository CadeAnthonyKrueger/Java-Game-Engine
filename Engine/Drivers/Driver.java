package Engine.Drivers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class Driver {

    public Driver() {
        Initialize();
    }

    private JFrame jFrame;
    private JPanel jPanel;

    public static void main(String[] args) {
        new Driver();
    }

    private void Initialize() {
        SwingUtilities.invokeLater(() -> {
            jFrame = new JFrame();
            jPanel = new JPanel();

            jPanel.setPreferredSize(new Dimension(600, 600));
            jFrame.getContentPane().add(jPanel);
            jFrame.pack();


            jFrame.setTitle("My Java Sim");
            jFrame.setLocationRelativeTo(null);
            jFrame.setResizable(false);
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame.setVisible(true);
            AntGame antGame = new AntGame(jPanel);
            jFrame.add(antGame);
            jFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowOpened(WindowEvent e) {
                    try {
                        antGame.start();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
        });
    }
}