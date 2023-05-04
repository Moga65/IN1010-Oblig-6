import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameOfLifeGui extends JFrame implements ActionListener {
    private Verden verden;
    private Timer timer;
    private int delay = 200;
    private JPanel spill;
    private JPanel buttonPanel;
    private JButton start_knapp;
    private JButton stop_knapp;

    public GameOfLifeGui(int rad, int kol) {
        this.verden = new Verden(rad, kol);
        this.timer = new Timer(delay, this);
        this.spill = new JPanel();
        this.buttonPanel = new JPanel();
        this.start_knapp = new JButton("Start");
        this.stop_knapp = new JButton("Stop");
        this.start_knapp.addActionListener(this);
        this.stop_knapp.addActionListener(this);
        this.spill.setLayout(new GridLayout(rad, kol));
        this.buttonPanel.setLayout(new GridLayout(1, 2));
        this.buttonPanel.add(start_knapp);
        this.buttonPanel.add(stop_knapp);
        this.add(spill, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setSize(500, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.lagBrett();
    }

    public void lagBrett() {
        for (int rad = 0; rad < verden.rad; rad++) {
            for (int kol = 0; kol < verden.kol; kol++) {
                Celle celle = verden.rutenett.hentCelle(rad, kol);
                JLabel label = new JLabel();
                label.setOpaque(true);
                label.setBackground(Color.WHITE);
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                label.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON1) {
                            celle.settLevende();
                            label.setBackground(Color.BLACK);
                        } else if (e.getButton() == MouseEvent.BUTTON3) {
                            celle.settDoed();
                            label.setBackground(Color.WHITE);
                        }
                    }
                });
                spill.add(label);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start_knapp) {
            timer.start();
        } else if (e.getSource() == stop_knapp) {
            timer.stop();
        } else if (e.getSource() == timer) {
            verden.oppdatering();
            for (int rad = 0; rad < verden.rad; rad++) {
                for (int kol = 0; kol < verden.kol; kol++) {
                    Celle celle = verden.rutenett.hentCelle(rad, kol);
                    JLabel label = (JLabel) spill.getComponent(rad * verden.kol + kol);
                    if (celle.erLevende()) {
                        label.setBackground(Color.BLACK);
                    } else {
                        label.setBackground(Color.WHITE);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new GameOfLifeGui(20, 20);
    }

}
