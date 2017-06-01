package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;

class License extends JFrame {

    JTextArea licenseText = new JTextArea();
    String arrayOfLicenses[] ={"e5f6d5d1e73b288e62f5975db3e1f50e", "f57e35bb561a888513bd5926a10016dc",
                               "b1f344ebc49f50d64644177083936340", "b30e7f87f49211d1112876caad4f211a",
                               "6945f936a87bbc10df83bb0e9e505c70",
                               "10c60956ec9307ebe7b4108593363aa0", "e3c88e16015691abbdb74709c4d6b9dc",
                               "9c2c965ea1051ef58bbabfc62d848eb1", "d4c2a1ec869e1774a2f4b81163e1a968"};

    String arrayOfUsernames[] = {"Denis Zasypkin","Anastasia Navros","Andrew Komissarov", "Vladislav Nadysev", "Kirill Aleksandrovich",
                                 "friend","friend","friend","friend"};

    License() throws IOException {
        JLabel labelLicense = new JLabel("license");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("src//com//company//res//license-bg.jpg")))));
        this.setBounds(45 * Toolkit.getDefaultToolkit().getScreenSize().width / 100, 40 * Toolkit.getDefaultToolkit().getScreenSize().height / 100,  340, 150);
        this.setForeground(new Color(201, 149, 255));
        licenseText.setForeground(new Color(173, 183, 202));
        licenseText.setFont(new Font("Microsoft JhengHei Light", Font.BOLD, 10));


        labelLicense.setForeground(new Color(196, 202, 198));
        labelLicense.setFont(new Font("Microsoft JhengHei Light", Font.BOLD, 10));



        this.add(labelLicense).setBounds(130,120,50,20);
        this.add(licenseText).setBounds(30,100,280,20);
        this.setUndecorated(true);

        this.setFocusable(true);
        this.setVisible(true);
    }
    void toClose(){

        this.setVisible(false);
    }
}
