package com.aleksandrovich;

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

import com.aleksandrovich.io.Datastore;
import com.aleksandrovich.io.Utils;

import static com.aleksandrovich.io.Constants.ACTIVATION_COLOR;
import static com.aleksandrovich.io.Constants.MAIN_BG_COLOR;
import static com.aleksandrovich.io.Constants.MAIN_TEXT_FIELD_COLOR;

/**
 * Release version: 2.0
 *
 * All rights reserved, 2017-2020.
 * The source code is proprietary and private property of customer.
 * Reverse engineering is restricted. All used code delivered "as-is" under Apache 2.0 license.
 *
 * @author AleksandrovichK
 * */
class MainFrame extends JFrame {
    private int width = 500;
    private int height = 270;

    private JPasswordField passwordField = new JPasswordField();

    private JLabel passLabel = new JLabel("password");
    private JLabel logo = new JLabel();

    private JButton encryptButton;
    private JButton passwordButton;

    private boolean isAccessGranted = false;
    private boolean newPasswordTyped = false;

    private License licenseFrame = new License();
    private Datastore datastore = new Datastore();

    MainFrame() {

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
                                        passLabel.setText("invalid license file");
                                        repaint();
                                        break;
                                    }
                                    case SUCCESS: {
                                        setVisible(true);
                                        break;
                                    }
                                }
                                logo.setText("Licensed for " + datastore.getActiveUser().getName() + " by Aleksandrovich K., 2017-2020");
                                repaint();
                            } catch (FileNotFoundException e1) {
                                passLabel.setText("password store is not found!");
                                repaint();
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
                                        passLabel.setText("invalid license file");
                                        repaint();
                                        break;
                                    }
                                    case SUCCESS: {
                                        setVisible(true);
                                        break;
                                    }
                                }
                                logo.setText("Licensed for " + datastore.getActiveUser().getName() + " by Aleksandrovich K., 2017-2020");
                                repaint();
                            } catch (FileNotFoundException e1) {
                                passLabel.setText("password store is not found!");
                                repaint();
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
                                        passLabel.setText("invalid license file");
                                        repaint();
                                        break;
                                    }
                                    case SUCCESS: {
                                        setVisible(true);
                                        break;
                                    }
                                }
                                logo.setText("Licensed for " + datastore.getActiveUser().getName() + " by Aleksandrovich K., 2017-2020");
                                repaint();
                            } catch (FileNotFoundException e1) {
                                passLabel.setText("password store is not found!");
                                repaint();
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
                logo.setText("Licensed for " + datastore.getActiveUser().getName() + " by Aleksandrovich K., 2017-2020");
                licenseFrame.close();

                this.setVisible(true);
                this.repaint();
                break;
            }
        }
    }

    private void settings() {
        this.setVisible(false);
        this.setUndecorated(true);

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

        this.getContentPane().setBackground(MAIN_BG_COLOR);

        this.setBounds(40 * Toolkit.getDefaultToolkit().getScreenSize().width / 100, 30 * Toolkit.getDefaultToolkit().getScreenSize().height / 100, width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        passwordField.setForeground(Color.LIGHT_GRAY);
        passwordField.setBackground(MAIN_TEXT_FIELD_COLOR);
        passwordField.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        passLabel.setForeground(Color.GRAY);
        passLabel.setFont(new Font("Microsoft JhengHei Light", Font.BOLD, 12));

        URL encryptImage = getClass().getClassLoader().getResource("res/encrypt.jpg");
        if (null != encryptImage) {
            encryptButton = new JButton(new ImageIcon(encryptImage));
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
                        if (isAccessGranted) {
                            try {
                                //TODO must return file status
                                toOutputAndEncryptFile();
                            } catch (IOException | URISyntaxException e1) {
                                passLabel.setText("encrypting your info failed due to file absence");
                                repaint();
                            }

                            try {
                                //TODO test that
                                Runtime.getRuntime().exec("cmd /c  del decrypted.txt");
                                System.exit(0);
                            } catch (Exception e1) {
                                passLabel.setText("secret file removing failed!");
                                repaint();
                            }
                        }
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

        URL resource = getClass().getClassLoader().getResource("res/sets.jpg");
        if (null != resource) {
            passwordButton = new JButton(new ImageIcon(resource));
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

                        if (!newPasswordTyped && isAccessGranted) {
                            newPasswordTyped = true;

                            passLabel.setText("input new password");
                            passwordField.setText("");
                            passwordButton.setBorder(BorderFactory.createLineBorder(new Color(234, 31, 6), 3));
                            repaint();
                            return;
                        }

                        if (newPasswordTyped && isAccessGranted) {
                            newPasswordTyped = false;

                            passLabel.setText("password");
                            datastore.setCorrectPassword(new String(passwordField.getPassword()));
                            passwordButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
                            repaint();

                            changePassword();
                        }
                    }
                    passwordButton.setBounds(passwordButton.getX(), passwordButton.getY() - 4, passwordButton.getWidth(), passwordButton.getHeight());
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
                // TODO Exception handling
                if (datastore.isPasswordCorrect(passwordField.getPassword())) {
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

        passwordField.getDocument().addDocumentListener(listener);

        this.add(passwordField).setBounds(40, 160, 400, 20);
        this.add(passLabel).setBounds(40, 180, 400, 20);
        this.add(logo).setBounds(180, 250, 340, 20);

        this.setResizable(false);
    }

    private void grantAccess() throws IOException {
        passwordField.setForeground(ACTIVATION_COLOR);

        toInputAndDecryptFile();
        isAccessGranted = true;

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
        inputStream.close();

        String lineSeparator = System.getProperty("line.separator");
        StringBuilder cryptedText = new StringBuilder();
        for (int i = 0; i < buffer.length(); i++) {
            if (buffer.charAt(i) == '\n') {
                cryptedText.append(lineSeparator);
                continue;
            }
            cryptedText.append((char) ((int) buffer.charAt(i) + 13));
        }

        URL resource = getClass().getClassLoader().getResource("/res/crypted.txt");
        if (null != resource) {
            File file = new File(resource.toURI());
            PrintStream output = new PrintStream(file);

            output.print(cryptedText);
            output.close();
        } else {
            passLabel.setText("crypted file is unable!");
            repaint();
        }
    }

    private void changePassword() {
        URL resourceUrl = getClass().getClassLoader().getResource("/res/config.txt");
        if (null != resourceUrl) {
            try {
                File file = new File(resourceUrl.toURI());
                FileOutputStream fos = new FileOutputStream(file);

                String toWrite = Utils.toSubstitute(datastore.getCorrectPassword());
                fos.write(toWrite.getBytes());
                fos.close();
            } catch (IOException | URISyntaxException e) {
                passLabel.setText("password configuration file is invalid");
                repaint();
            }
        } else {
            passLabel.setText("password configuration file is invalid");
            repaint();
        }
    }
}