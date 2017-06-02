package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Vector;

/*
e5f6d5d1e73b288e62f5975db3e1f50e Denis Zasypkin
f57e35bb561a888513bd5926a10016dc Anastasia Navros
b1f344ebc49f50d64644177083936340 Andrew Komissarov
b30e7f87f49211d1112876caad4f211a Vladislav Nadysev
6945f936a87bbc10df83bb0e9e505c70 Kirill Aleksandrovich
10c60956ec9307ebe7b4108593363aa0 friend
e3c88e16015691abbdb74709c4d6b9dc friend
9c2c965ea1051ef58bbabfc62d848eb1 friend
d4c2a1ec869e1774a2f4b81163e1a968 friend
*/

class License extends JFrame {

    JTextArea licenseText = new JTextArea();
    Vector<String> arrayOfLicenses = new Vector<>();
    Vector<String> arrayOfUsernames = new Vector<>();

    License() throws IOException {
        toFillLicenses();

        JLabel labelLicense = new JLabel("license");
        ClassLoader cl = this.getClass().getClassLoader();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        if (null != cl.getResource("res/license-bg.jpg"))
        this.setContentPane(new JLabel(new ImageIcon(cl.getResource("res/license-bg.jpg"))));

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
    void toFillLicenses() throws IOException {
        InputStream inputStream = Main.class.getResourceAsStream("/res/kernellic.bin");

        if (inputStream != null) //NO FILE WITH SECRETS
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder buffer = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null)
            {
                arrayOfLicenses.addElement(line.substring(0, line.indexOf(' ')));

                buffer.append(line);
                buffer.append("\n");
            }

            inputStream.close();
        }
    }
}
