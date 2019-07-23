package com.aleksandrovich;

import java.awt.*;
import java.net.URL;

import javax.swing.*;

class License extends JFrame {
    private JTextArea licenseText = new JTextArea();
    private JLabel labelLicense = new JLabel("license");

    License() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        URL resource = getClass().getResource("res/license-bg.jpg");
        if (null != resource) {
            this.setContentPane(new JLabel(new ImageIcon(resource)));
        }

        this.setBounds(45 * Toolkit.getDefaultToolkit().getScreenSize().width / 100, 40 * Toolkit.getDefaultToolkit().getScreenSize().height / 100, 340, 150);
        this.setForeground(new Color(201, 149, 255));
        licenseText.setForeground(new Color(173, 183, 202));
        licenseText.setFont(new Font("Microsoft JhengHei Light", Font.BOLD, 10));

        labelLicense.setForeground(new Color(196, 202, 198));
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
