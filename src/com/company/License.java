package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Vector;


class License extends JFrame {

    JTextArea licenseText = new JTextArea();
    JLabel labelLicense = new JLabel("license");
    Vector<String> arrayOfLicenses = new Vector<>();
    Vector<String> arrayOfUsernames = new Vector<>();

    License() throws IOException {
        toFillLicenses();


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



        this.add(labelLicense).setBounds(130,120,220,20);
        this.add(licenseText).setBounds(30,100,280,20);
        this.setUndecorated(true);

        this.setFocusable(true);
        this.setVisible(true);
    }
    void toClose(){

        this.setVisible(false);
    }
    void toFillLicenses() throws IOException {
        InputStream inputStream = Main.class.getResourceAsStream("/res/kernellic.txt");

        if (inputStream != null) //NO FILE WITH SECRETS
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null)
            {
                line = MainFrame.toSubstitute(line);
                arrayOfLicenses.addElement(line.substring(0, line.indexOf(' ')));
                arrayOfUsernames.addElement(line.substring(line.indexOf(' ')+1, line.length()));

                //d4c2a1ec869e1774a2f4b81163e1a968 friend

            }

            inputStream.close();
        }
        else {
            labelLicense.setText("licenses registration is unable");
            repaint();
        }
    }
}
