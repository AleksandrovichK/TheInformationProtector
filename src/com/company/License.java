package com.company;

import java.awt.*;

import javax.swing.*;


class License extends JFrame {
    private JTextArea licenseText = new JTextArea();
    private JLabel labelLicense = new JLabel("license");


    License() {
        ClassLoader cl = this.getClass().getClassLoader();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        if (null != cl.getResource("res/license-bg.jpg"))
            // TODO exception handling
        this.setContentPane(new JLabel(new ImageIcon(cl.getResource("res/license-bg.jpg"))));

        this.setBounds(45 * Toolkit.getDefaultToolkit().getScreenSize().width / 100, 40 * Toolkit.getDefaultToolkit().getScreenSize().height / 100,  340, 150);
        this.setForeground(new Color(201, 149, 255));
        licenseText.setForeground(new Color(173, 183, 202));
        licenseText.setFont(new Font("Microsoft JhengHei Light", Font.BOLD, 10));

        labelLicense.setForeground(new Color(196, 202, 198));
        labelLicense.setFont(new Font("Microsoft JhengHei Light", Font.BOLD, 10));

        this.add(labelLicense).setBounds(130,120,220,20);
        this.add(licenseText).setBounds(30,100,280,20);
        this.setUndecorated(true);

        this.setFocusable(true);
        this.setVisible(true);
    }

    void close(){
        this.setVisible(false);
    }


    JTextArea getLicenseText() {
        return licenseText;
    }

    public void setLicenseText(JTextArea licenseText) {
        this.licenseText = licenseText;
    }

    JLabel getLabelLicense() {
        return labelLicense;
    }

    public void setLabelLicense(JLabel labelLicense) {
        this.labelLicense = labelLicense;
    }
}
