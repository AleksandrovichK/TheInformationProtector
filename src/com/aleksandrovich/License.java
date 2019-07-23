package com.aleksandrovich;

import java.awt.*;

import javax.swing.*;

import static com.aleksandrovich.io.Constants.MAIN_BG_COLOR;
import static com.aleksandrovich.io.Constants.MAIN_TEXT_FIELD_COLOR;

class License extends JFrame {
    private JTextArea licenseText = new JTextArea();
    private JLabel labelLicense = new JLabel("license");

    License() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        this.getContentPane().setBackground(MAIN_BG_COLOR);
        this.setForeground(Color.GRAY);

        this.setBounds(45 * Toolkit.getDefaultToolkit().getScreenSize().width / 100, 40 * Toolkit.getDefaultToolkit().getScreenSize().height / 100, 340, 150);
        licenseText.setBackground(MAIN_TEXT_FIELD_COLOR);

        licenseText.setForeground(new Color(173, 183, 202));
        licenseText.setFont(new Font("Microsoft JhengHei Light", Font.BOLD, 10));

        labelLicense.setForeground(Color.LIGHT_GRAY);
        labelLicense.setFont(new Font("Microsoft JhengHei Light", Font.BOLD, 10));

        this.add(labelLicense).setBounds(130, 120, 220, 20);
        this.add(licenseText).setBounds(30, 100, 280, 20);
        this.setUndecorated(true);

        this.setFocusable(true);
        this.setVisible(true);
    }

    void close() {
        this.setVisible(false);
    }

    JTextArea getLicenseText() {
        return licenseText;
    }

    JLabel getLabelLicense() {
        return labelLicense;
    }
}
