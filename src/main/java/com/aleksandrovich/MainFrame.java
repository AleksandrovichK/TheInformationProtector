package com.aleksandrovich;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.aleksandrovich.io.Constants;
import com.aleksandrovich.io.Datastore;
import com.aleksandrovich.io.Status;
import com.aleksandrovich.io.User;
import com.aleksandrovich.io.Utils;

import static com.aleksandrovich.io.Constants.ACTIVATION_COLOR;
import static com.aleksandrovich.io.Constants.Bounds.EXIT_BUTTON_BOUNDS;
import static com.aleksandrovich.io.Constants.Bounds.LOGO_BOUNDS;
import static com.aleksandrovich.io.Constants.Bounds.PASSFIELD_BOUNDS;
import static com.aleksandrovich.io.Constants.Bounds.PASSLABEL_BOUNDS;
import static com.aleksandrovich.io.Constants.Bounds.PASSWORD_BUTTON_BOUNDS;
import static com.aleksandrovich.io.Constants.FONT_10;
import static com.aleksandrovich.io.Constants.FONT_12;
import static com.aleksandrovich.io.Constants.LINE_SEPARATOR;
import static com.aleksandrovich.io.Constants.LOGO_COLOR;
import static com.aleksandrovich.io.Constants.MAIN_BG_COLOR;
import static com.aleksandrovich.io.Constants.MAIN_TEXT_FIELD_COLOR;

/**
 * Main frame class.
 *
 * @author AleksandrovichK
 */
class MainFrame extends JFrame {
    private JPasswordField passwordField = new JPasswordField();

    private JLabel passLabel = new JLabel("password");
    private JLabel logo = new JLabel();

    private JButton exitButton;
    private JButton passwordButton;

    private boolean isAccessGranted = false;
    private boolean newPasswordTyped = false;

    private LicenseFrame licenseFrame = new LicenseFrame();
    private Datastore datastore = new Datastore();

    MainFrame() {

        settings();

        //d4c2a1ec869e1774a2f4b81163e1a968 friend
        DocumentListener invalidLicenseListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                User foundUser = datastore.isLicensePresent(licenseFrame.getLicenseText().getText());

                if (foundUser != null) {
                    licenseFrame.close();
                    switch (datastore.printLicenseToStore(foundUser.getName(), foundUser.getLicense())) {
                        case REGISTRY_SEGMENT_IS_NOT_AVAILABLE: {
                            passLabel.setText("registry segment for license writing is unavailable");
                            setVisible(true);
                            repaint();
                            break;
                        }
                        case SUCCESS: {
                            setVisible(true);

                            switch (datastore.initStore()) {
                                case REGISTRY_SEGMENT_IS_NOT_AVAILABLE: {
                                    passLabel.setText("registry segment for password writing is unavailable");
                                    break;
                                }
                                case SUCCESS: {
                                }
                            }
                            repaint();
                            break;
                        }
                    }
                    passLabel.setText("password");
                    logo.setText("Licensed for " + foundUser.getName() + " by Aleksandrovich K., 2017-2020");
                    repaint();
                } else {
                    passLabel.setText("user not found!");
                    repaint();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                this.insertUpdate(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        };

        boolean isLicensesAvailable = false;
        switch (this.datastore.toFillLicenses()) {
            case FILE_IS_NOT_AVAILABLE: {
                this.licenseFrame.getLicenseText().setEditable(false);
                this.licenseFrame.getLabelLicense().setText("licenses file is not available");
                break;
            }
            case SUCCESS: {
                isLicensesAvailable = true;
            }

        }

        if (isLicensesAvailable) {
            switch (datastore.checkLicenseStatus()) {
                case REGISTRY_SEGMENT_IS_EMPTY: {
                    licenseFrame.setAlwaysOnTop(true);
                    licenseFrame.getLicenseText().getDocument().addDocumentListener(invalidLicenseListener);
                    licenseFrame.setVisible(true);
                    licenseFrame.repaint();
                    break;
                }
                case REGISTRY_CONTAINS_WRONG_INFO: {
                    licenseFrame.setAlwaysOnTop(true);
                    licenseFrame.getLicenseText().setText("There is something wrong with configuration segment!");
                    licenseFrame.getLabelLicense().setText("insert correct license");
                    licenseFrame.getLabelLicense().setForeground(Color.ORANGE);
                    licenseFrame.getLabelLicense().setBounds(90, 120, 220, 20);
                    licenseFrame.getLicenseText().getDocument().addDocumentListener(invalidLicenseListener);
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

    }

    private void settings() {
        this.setVisible(false);
        this.setUndecorated(true);

        switch (this.datastore.toReadPassword()) {
            case REGISTRY_SEGMENT_IS_EMPTY: {
                passLabel.setText("password configuration is not available!");
                break;
            }
            case SUCCESS: {
                break;
            }
        }

        this.repaint();

        this.getContentPane().setBackground(MAIN_BG_COLOR);
        this.setBounds(40 * Toolkit.getDefaultToolkit().getScreenSize().width / 100, 30 * Toolkit.getDefaultToolkit().getScreenSize().height / 100, Constants.MAIN_FRAME_WIDTH, Constants.MAIN_FRAME_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        this.passwordField.setForeground(Color.LIGHT_GRAY);
        this.passwordField.setBackground(MAIN_TEXT_FIELD_COLOR);
        this.passwordField.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        this.passLabel.setForeground(Color.GRAY);
        this.passLabel.setFont(FONT_12);

        URL encryptImage = getClass().getResource("/exit_dark.jpg");
        if (null != encryptImage) {
            exitButton = new JButton(new ImageIcon(encryptImage));
            exitButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 0));
            exitButton.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    if (!exitButton.isOpaque()) {
                        exitButton.setOpaque(true);
                        exitButton.setBounds(exitButton.getX(), exitButton.getY() + 2, exitButton.getWidth(), exitButton.getHeight());
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (exitButton.isOpaque()) {
                        exitButton.setOpaque(false);
                        exitButton.setBounds(exitButton.getX(), exitButton.getY() - 2, exitButton.getWidth(), exitButton.getHeight());
                        if (isAccessGranted) {
                            switch (writeAndEncryptFile()) {
                                case CRYPTED_DATA_IS_NOT_AVAILABLE: {
                                    passLabel.setText("encrypting failed as crypted file not available!");
                                    break;
                                }
                                case FILE_IS_TOO_LONG: {
                                    passLabel.setText("File is too long!");
                                    break;
                                }
                                case SUCCESS: {
                                    try {
                                        if (System.getProperty("os.name").contains("Windows")) {
                                            Runtime.getRuntime().exec("cmd /c  del decrypted.txt");
                                        } else {
                                            // TODO Make MacOS proper command
                                            Runtime.getRuntime().exec(new String[] { "rm", "\\decrypted.txt" });
                                        }
                                        System.exit(0);
                                    } catch (Exception exp) {
                                        passLabel.setText("removing of decrypted file failed!");
                                        repaint();
                                    }
                                }
                            }
                            repaint();
                        } else {
                            System.exit(0);
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
            this.add(exitButton).setBounds(EXIT_BUTTON_BOUNDS);
        } else {
            this.passLabel.setText("exit button image is not available!");
        }

        URL resource = getClass().getResource("/sets_dark.jpg");
        if (null != resource) {
            passwordButton = new JButton(new ImageIcon(resource));
            passwordButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 0));
            passwordButton.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    if (!passwordButton.isOpaque()) {
                        passwordButton.setOpaque(true);
                    }
                    passwordButton.setBounds(passwordButton.getX(), passwordButton.getY() + 1, passwordButton.getWidth(), passwordButton.getHeight());
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (passwordButton.isOpaque()) {
                        passwordButton.setOpaque(false);

                        if (!newPasswordTyped && isAccessGranted) {
                            newPasswordTyped = true;

                            passLabel.setText("input new password");
                            passwordField.setText("");
                            repaint();
                            return;
                        }

                        if (newPasswordTyped && isAccessGranted) {
                            if (!new String(passwordField.getPassword()).isEmpty()) {
                                newPasswordTyped = false;

                                passLabel.setText("password");
                                datastore.setCorrectPassword(new String(passwordField.getPassword()));
                                repaint();

                                switch (changePassword()) {
                                    case REGISTRY_SEGMENT_IS_NOT_AVAILABLE: {
                                        passLabel.setText("password configuration segment is unavailable");
                                        repaint();
                                    }
                                    case SUCCESS: {

                                    }
                                }
                            } else {
                                passLabel.setText("password cannot be empty!");
                                repaint();
                            }
                        }
                    }
                    passwordButton.setBounds(passwordButton.getX(), passwordButton.getY() - 1, passwordButton.getWidth(), passwordButton.getHeight());
                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
            passwordButton.setVisible(false);
            this.add(passwordButton).setBounds(PASSWORD_BUTTON_BOUNDS);
        } else {
            this.passLabel.setText("exit password button image is not available!");
        }

        this.logo.setForeground(LOGO_COLOR);
        this.logo.setFont(FONT_10);

        this.passwordField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (datastore.isPasswordCorrect(passwordField.getPassword())) {
                    grantAccess();
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
        });

        this.add(passwordField).setBounds(PASSFIELD_BOUNDS);
        this.add(passLabel).setBounds(PASSLABEL_BOUNDS);
        this.add(logo).setBounds(LOGO_BOUNDS);

        this.setResizable(false);
    }

    private void grantAccess() {
        isAccessGranted = true;
        passwordField.setForeground(ACTIVATION_COLOR);

        switch (readAndDecryptFile()) {
            case CRYPTED_DATA_IS_NOT_AVAILABLE: {
                this.isAccessGranted = false;
                passLabel.setText("crypted file is not available!");
                break;
            }
            case FILE_DECRYPTED_IS_NOT_AVAILABLE: {
                this.isAccessGranted = false;
                passLabel.setText("decrypted file is not available!");
                break;
            }
            case ERROR_WHILE_EXECUTING: {
                this.isAccessGranted = false;
                passLabel.setText("error while executing command!");
                break;
            }
            case SUCCESS: {
                this.passwordButton.setVisible(true);
            }
        }
        repaint();
    }

    private Status readAndDecryptFile() {
        String cryptedData = Datastore.store.get("data", "");
        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0; i < cryptedData.length(); i++) {
            decryptedText.append((char) ((int) cryptedData.charAt(i) - 13));
        }

        try {
            PrintStream outputStream = new PrintStream(new FileOutputStream("decrypted.txt"));
            outputStream.print(decryptedText);
            outputStream.close();
        } catch (IOException e) {
            return Status.FILE_DECRYPTED_IS_NOT_AVAILABLE;
        }

        try {
            if (System.getProperty("os.name").contains("Windows")) {
                Runtime.getRuntime().exec("cmd /c decrypted.txt");
            } else {
                // TODO Make MacOS proper command
                Runtime.getRuntime().exec(new String[] { "open", "\\decrypted.txt" });
            }
            return Status.SUCCESS;
        } catch (Exception e1) {
            return Status.ERROR_WHILE_EXECUTING;
        }
    }

    private Status writeAndEncryptFile() {
        try {
            FileInputStream inputStream = new FileInputStream("decrypted.txt");
            Scanner sc = new Scanner(inputStream);
            StringBuilder buffer = new StringBuilder();

            String temp;
            while (sc.hasNextLine()) {
                temp = sc.nextLine();
                buffer.append(temp);
                buffer.append(LINE_SEPARATOR);
            }
            inputStream.close();

            StringBuilder cryptedText = new StringBuilder();
            for (int i = 0; i < buffer.length(); i++) {
                cryptedText.append((char) ((int) buffer.charAt(i) + 13));
            }

            Datastore.store.put("data", cryptedText.toString());
            return Status.SUCCESS;
        } catch (Exception e) {
            return Status.FILE_IS_TOO_LONG;
        }
    }

    private Status changePassword() {
        try {
            String toWrite = Utils.toSubstitute(datastore.getCorrectPassword());
            Datastore.store.put("password", toWrite);
            return Status.SUCCESS;
        } catch (Exception e) {
            return Status.REGISTRY_SEGMENT_IS_NOT_AVAILABLE;
        }
    }
}