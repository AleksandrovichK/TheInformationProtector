package com.aleksandrovich;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.*;

import com.aleksandrovich.io.Constants;

import static com.aleksandrovich.io.Constants.MAIN_BG_COLOR;
import static com.aleksandrovich.io.Constants.MAIN_TEXT_FIELD_COLOR;

/**
 * Class with all licenses/passwords IO functions.
 *
 * @author AleksandrovichK
 */
class LicenseFrame extends JFrame {
    private JButton exitButton;
    private JTextArea licenseText = new JTextArea();
    private JLabel labelLicense = new JLabel("license");

    LicenseFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        this.getContentPane().setBackground(MAIN_BG_COLOR);
        this.setForeground(Color.GRAY);

        this.setBounds(45 * Toolkit.getDefaultToolkit().getScreenSize().width / 100, 40 * Toolkit.getDefaultToolkit().getScreenSize().height / 100, Constants.LICENSE_FRAME_WIDTH, Constants.LICENSE_FRAME_HEIGHT);

        this.licenseText.setBackground(MAIN_TEXT_FIELD_COLOR);
        this.licenseText.setForeground(Constants.LOGO_COLOR);
        this.licenseText.setFont(Constants.FONT_10);

        this.labelLicense.setForeground(Color.LIGHT_GRAY);
        this.labelLicense.setFont(Constants.FONT_10);

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
                    exitButton.setBounds(exitButton.getX(), exitButton.getY() + 2, exitButton.getWidth(), exitButton.getHeight());
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    exitButton.setBounds(exitButton.getX(), exitButton.getY() - 2, exitButton.getWidth(), exitButton.getHeight());
                    System.exit(0);
                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
            this.add(exitButton).setBounds(340 - 22 - 5,5,22,22);
        } else {
            this.labelLicense.setText("exit button image is not available!");
        }

        this.add(licenseText).setBounds(Constants.Bounds.LICENSE_TEXT_BOUNDS);
        this.add(labelLicense).setBounds(Constants.Bounds.LICENSE_LABEL_BOUNDS);
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
