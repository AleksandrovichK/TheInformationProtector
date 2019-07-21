package com.company;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.company.io.Datastore;
import com.company.io.Utils;

// 2.0 Release version

public class MainFrame extends JFrame {
    private int width = 500;
    private int height = 300;

    private JTextArea login = new JTextArea();
    private JPasswordField password = new JPasswordField();

    private String username;

    private JLabel logLabel = new JLabel("login");
    private JLabel passLabel = new JLabel("password");
    private JLabel logo = new JLabel();

    private JButton encryptButton;
    private JButton passwordButton;

    private boolean isTakenAccess = false;
    private boolean newPasswordTyped = false;

    private License licenseFrame = new License();
    private Datastore datastore = new Datastore();

    private ClassLoader cl = this.getClass().getClassLoader();

    MainFrame() throws IOException {

        settings();

        //d4c2a1ec869e1774a2f4b81163e1a968 friend
        switch (datastore.checkLicenseStatus()) {
            case FILE_IS_ABSENT: {
                licenseFrame.setAlwaysOnTop(true);
                licenseFrame.getLicenseText().setText("Repair configuration or the program won't work!");
                licenseFrame.getLabelLicense().setText("configuration is not existing!");
                licenseFrame.getLabelLicense().setBounds(90, 120, 220, 20);
                licenseFrame.getLabelLicense().setForeground(Color.ORANGE);
                licenseFrame.repaint();
                break;
            }
            case FILE_IS_EMPTY: {
                licenseFrame.setAlwaysOnTop(true);
                licenseFrame.getLicenseText().getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        User foundUser = datastore.isLicensePresent(licenseFrame.getLicenseText().getText());
                        if (foundUser != null) {
                            licenseFrame.close();

                            try {
                                switch (datastore.printLicenseToFile(foundUser.getName(), foundUser.getLicense())) {
                                    case FILE_IS_ABSENT: {
                                        logLabel.setText("invalid license file");
                                        passLabel.setText("invalid license file");
                                        repaint();
                                        break;
                                    }
                                    case SUCCESS: {
                                        setVisible(true);
                                        break;
                                    }
                                }
                                username = foundUser.getName();
                                logo.setText("Licensed for " + username + " by Aleksandrovich K., 2017-2020");
                                if (username.equals("friend")) {
                                    datastore.setCorrectLogin("friend");
                                } else {
                                    datastore.setCorrectLogin(username.substring(0, username.indexOf(' ')));
                                }
                                repaint();
                                // TODO Exception catching
                            } catch (FileNotFoundException e1) {
                                e1.printStackTrace();
                            }
                        }

                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        this.insertUpdate(e);
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {

                    }
                });
                licenseFrame.setVisible(true);
                licenseFrame.repaint();
                break;
            }
            case FILE_CONTAINS_WRONG_INFO: {
                licenseFrame.setAlwaysOnTop(true);
                licenseFrame.getLicenseText().setText("Your existing license is fake!");
                licenseFrame.getLabelLicense().setText("insert correct license");
                licenseFrame.getLabelLicense().setForeground(Color.ORANGE);
                licenseFrame.getLabelLicense().setBounds(90, 120, 220, 20);
                licenseFrame.getLicenseText().getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        User foundUser = datastore.isLicensePresent(licenseFrame.getLicenseText().getText());
                        if (foundUser != null) {
                            licenseFrame.close();

                            try {
                                switch (datastore.printLicenseToFile(foundUser.getName(), foundUser.getLicense())) {
                                    case FILE_IS_ABSENT: {
                                        logLabel.setText("invalid license file");
                                        passLabel.setText("invalid license file");
                                        repaint();
                                        break;
                                    }
                                    case SUCCESS: {
                                        setVisible(true);
                                        break;
                                    }
                                }
                                username = foundUser.getName();
                                logo.setText("Licensed for " + username + " by Aleksandrovich K., 2017-2020");
                                if (username.equals("friend")) {
                                    datastore.setCorrectLogin("friend");
                                } else {
                                    datastore.setCorrectLogin(username.substring(0, username.indexOf(' ')));
                                }
                                repaint();
                                // TODO Exception catching
                            } catch (FileNotFoundException e1) {
                                e1.printStackTrace();
                            }
                        }

                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        this.insertUpdate(e);
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {

                    }
                });
                licenseFrame.setVisible(true);
                licenseFrame.repaint();
                break;
            }
            case FILE_IS_CORRUPTED: {
                licenseFrame.setAlwaysOnTop(true);
                licenseFrame.getLabelLicense().setText("insert license again");
                licenseFrame.getLabelLicense().setForeground(Color.ORANGE);
                licenseFrame.getLabelLicense().setBounds(110, 120, 220, 20);
                licenseFrame.getLicenseText().setText("There is something wrong with configuration file!");
                licenseFrame.getLicenseText().getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        User foundUser = datastore.isLicensePresent(licenseFrame.getLicenseText().getText());
                        if (foundUser != null) {
                            licenseFrame.close();

                            try {
                                switch (datastore.printLicenseToFile(foundUser.getName(), foundUser.getLicense())) {
                                    case FILE_IS_ABSENT: {
                                        logLabel.setText("invalid license file");
                                        passLabel.setText("invalid license file");
                                        repaint();
                                        break;
                                    }
                                    case SUCCESS: {
                                        setVisible(true);
                                        break;
                                    }
                                }
                                username = foundUser.getName();
                                logo.setText("Licensed for " + username + " by Aleksandrovich K., 2017-2020");
                                if (username.equals("friend")) {
                                    datastore.setCorrectLogin("friend");
                                } else {
                                    datastore.setCorrectLogin(username.substring(0, username.indexOf(' ')));
                                }
                                repaint();
                                // TODO Exception catching
                            } catch (FileNotFoundException e1) {
                                e1.printStackTrace();
                            }
                        }

                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        this.insertUpdate(e);
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {

                    }
                });
                licenseFrame.setVisible(true);
                licenseFrame.repaint();
                break;
            }
            case SUCCESS: {
                logo.setText("Licensed for " + datastore.getCorrectLogin() + " by Aleksandrovich K., 2017-2020");
                licenseFrame.close();
                this.setVisible(true);
                this.repaint();
                break;
            }
        }
    }

    private void settings() {
        this.setVisible(false);

        switch (this.datastore.toReadPassword()) {
            case FILE_IS_ABSENT: {
            }
            case FILE_IS_EMPTY: {
                passLabel.setText("password configuration is not exist");
                break;
            }
            case SUCCESS: {
                break;
            }
        }

        this.repaint();

        if (!this.datastore.toFillLicenses()) {
            this.licenseFrame.getLabelLicense().setText("licenses registration is unable");
        }
        this.repaint();

        if (null != cl.getResource("res/bg.jpg")) {
            this.setContentPane(new JLabel(new ImageIcon(cl.getResource("res/bg.jpg"))));
        }

        this.setBounds(40 * Toolkit.getDefaultToolkit().getScreenSize().width / 100, 30 * Toolkit.getDefaultToolkit().getScreenSize().height / 100, width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        login.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        login.setLineWrap(true);
        login.setWrapStyleWord(true);
        login.setOpaque(true);

        login.setForeground(new Color(43, 33, 202));
        login.setFont(new Font("Microsoft JhengHei Light", Font.BOLD, 12));

        password.setForeground(new Color(132, 125, 202));
        password.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        logLabel.setForeground(new Color(196, 202, 198));
        logLabel.setFont(new Font("Microsoft JhengHei Light", Font.BOLD, 12));

        passLabel.setForeground(new Color(196, 202, 198));
        passLabel.setFont(new Font("Microsoft JhengHei Light", Font.BOLD, 12));

        if (null != cl.getResource("res/encrypt.jpg")) {
            ImageIcon icon3 = new ImageIcon(cl.getResource("res/encrypt.jpg"));
            encryptButton = new JButton(icon3);
            encryptButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
            encryptButton.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    if (!encryptButton.isOpaque()) {
                        encryptButton.setOpaque(true);
                        encryptButton.setBounds(encryptButton.getX(), encryptButton.getY() + 2, encryptButton.getWidth(), encryptButton.getHeight());
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (encryptButton.isOpaque()) {
                        encryptButton.setOpaque(false);
                        encryptButton.setBounds(encryptButton.getX(), encryptButton.getY() - 2, encryptButton.getWidth(), encryptButton.getHeight());
                        if (isTakenAccess) {
                            try {
                                toOutputAndEncryptFile();
                            } catch (IOException | URISyntaxException e1) {
                                e1.printStackTrace();
                            }

                            try {
                                Runtime.getRuntime().exec("cmd /c  del decrypted.txt");
                            } catch (Exception e1) {
                                System.out.println(e1.toString());
                                e1.printStackTrace();
                            }
                        }
                        System.exit(0);
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
        }

        if (null != cl.getResource("res/sets.jpg")) {
            ImageIcon icon4 = new ImageIcon(cl.getResource("res/sets.jpg"));
            passwordButton = new JButton(icon4);
            passwordButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
            passwordButton.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    if (!passwordButton.isOpaque()) {
                        passwordButton.setOpaque(true);
                    }
                    passwordButton.setBounds(passwordButton.getX(), passwordButton.getY() + 2, passwordButton.getWidth(), passwordButton.getHeight());
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (passwordButton.isOpaque()) {
                        passwordButton.setOpaque(false);

                        if (!newPasswordTyped && isTakenAccess) {
                            passLabel.setText("input new password");
                            password.setText("");
                            newPasswordTyped = !newPasswordTyped;
                            passwordButton.setBorder(BorderFactory.createLineBorder(new Color(234, 31, 6), 3));
                            repaint();
                            return;
                        }

                        if (newPasswordTyped && isTakenAccess) {
                            newPasswordTyped = !newPasswordTyped;

                            passwordButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
                            datastore.setCorrectPassword(new String(password.getPassword()));
                            passLabel.setText("password");
                            repaint();
                            try {
                                toChangePassword();
                            } catch (IOException | URISyntaxException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                    passwordButton.setBounds(passwordButton.getX(), passwordButton.getY() - 2, passwordButton.getWidth(), passwordButton.getHeight());
                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
        }

        logo.setForeground(new Color(196, 202, 198));
        logo.setFont(new Font("Microsoft JhengHei Light", Font.BOLD, 10));

        DocumentListener listener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (login.getText().equals(datastore.getCorrectLogin()) && datastore.isPasswordCorrect(password.getPassword())) {
                    try {
                        grantAccess();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                insertUpdate(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                insertUpdate(e);
            }
        };

        password.getDocument().addDocumentListener(listener);
        login.getDocument().addDocumentListener(listener);

        this.add(login).setBounds(40, 100, 400, 20);
        this.add(password).setBounds(40, 160, 400, 20);
        this.add(logLabel).setBounds(40, 120, 400, 20);
        this.add(passLabel).setBounds(40, 180, 400, 20);
        this.add(logo).setBounds(180, 250, 340, 20);

        this.setResizable(false);
    }

    private void grantAccess() throws IOException {
        login.setForeground(new Color(85, 255, 133));
        password.setForeground(new Color(85, 255, 133));

        toInputAndDecryptFile();
        isTakenAccess = true;

        if (null != encryptButton && null != passwordButton) {
            this.add(encryptButton).setBounds(width - 42, height - 72, 22, 22);
            this.add(passwordButton).setBounds(width - 72, height - 72, 22, 22);
        } else {
            passLabel.setText("buttons are not available!");
            repaint();
        }
    }

    private void toInputAndDecryptFile() throws IOException {
        InputStream inputStream = Main.class.getResourceAsStream("/res/crypted.txt");

        if (inputStream != null) //NO FILE WITH SECRETS
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder buffer = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
                buffer.append("\n");
            }

            String lineSeparator = System.getProperty("line.separator");
            StringBuilder decryptedText = new StringBuilder();
            for (int i = 0; i < buffer.length(); i++) {
                if (buffer.charAt(i) == '\n') {
                    decryptedText.append(lineSeparator);
                    continue;
                }
                decryptedText.append((char) ((int) buffer.charAt(i) - 13));
            }

            PrintStream output = new PrintStream(new FileOutputStream("decrypted.txt"));
            output.print(decryptedText);
            inputStream.close();
            output.close();

            try {
                Runtime.getRuntime().exec("cmd /c decrypted.txt");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } else {
            passLabel.setText("secret file is not found!");
            repaint();
        }
    }

    private void toOutputAndEncryptFile() throws IOException, URISyntaxException {
        FileInputStream inputStream = new FileInputStream("decrypted.txt");
        Scanner sc = new Scanner(inputStream);

        StringBuilder buffer = new StringBuilder();

        String temp;
        while (sc.hasNextLine()) {
            temp = sc.nextLine();
            buffer.append(temp);
            buffer.append("\n");
        }
        inputStream.close();    //inputting ended

        StringBuilder cryptedText = new StringBuilder();
        for (int i = 0; i < buffer.length(); i++) {
            if (buffer.charAt(i) == '\n') {
                cryptedText.append('\n');
                continue;
            }
            cryptedText.append((char) ((int) buffer.charAt(i) + 13));
        }  //handling ended

        if (null != getClass().getResource("/res/crypted.txt")) {
            URL resourceUrl = getClass().getResource("/res/crypted.txt");
            File file = new File(resourceUrl.toURI());
            PrintStream output = new PrintStream(file);

            output.print(cryptedText);
            output.close();
        } else {
            logLabel.setText("crypted file is unable");
            passLabel.setText("crypted file is unable");
            repaint();
        }
    }

    private void toChangePassword() throws IOException, URISyntaxException {
        if (null != getClass().getResource("/res/config.txt")) {
            try {
                URL resourceUrl = getClass().getResource("/res/config.txt");
                File file = new File(resourceUrl.toURI());
                FileOutputStream fos = new FileOutputStream(file);

                String toWrite = Utils.toSubstitute(datastore.getCorrectPassword());
                fos.write(toWrite.getBytes());
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            passLabel.setText("password configuration is invalid");
            repaint();
        }
    }
}