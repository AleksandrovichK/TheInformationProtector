package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

//Сравнительно стабильная версия

class MainFrame extends JFrame {
    private int width = 500;
    private int height = 300;

    private JTextArea login = new JTextArea();
    private JPasswordField password = new JPasswordField();

    private String correctPassword;
    private String correctLogin;
    private String username;

    private JLabel logLabel = new JLabel("login");
    private JLabel passLabel= new JLabel("password");
    private JLabel logo = new JLabel();

    private License license;

    private JButton encrypt;
    private JButton setPassword;

    private boolean isTakenAccess=false;
    private boolean newPasswordTyped=false;

    MainFrame() throws IOException {

         settings();

        if (toCheckLicense()) this.setVisible(true);
    }

    private void settings() throws IOException {
        this.setVisible(false);

        toReadPassword();


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("src//com//company//res//bg.jpg")))));
        this.setBounds(40 * Toolkit.getDefaultToolkit().getScreenSize().width / 100, 30 * Toolkit.getDefaultToolkit().getScreenSize().height / 100,  width, height);
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


        ImageIcon icon3 = new ImageIcon("src//com//company//res//encrypt.jpg");
        encrypt = new JButton(icon3);
        encrypt.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
            @Override
            public void mousePressed(MouseEvent e) {
                if (!encrypt.isOpaque()) {
                    encrypt.setOpaque(true);
                    encrypt.setBounds(encrypt.getX(), encrypt.getY() + 2, encrypt.getWidth(), encrypt.getHeight());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (encrypt.isOpaque()) {
                    encrypt.setOpaque(false);
                    encrypt.setBounds(encrypt.getX(), encrypt.getY() - 2, encrypt.getWidth(), encrypt.getHeight());
                if (isTakenAccess){
                    try {toOutputAndEncryptFile();} catch (IOException e1) {e1.printStackTrace();}

                    try{
                        Process r = Runtime.getRuntime().exec("cmd /c  del src\\com\\company\\res\\decrypted.txt");
                        }
                        catch(Exception e1){
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


        ImageIcon icon4 = new ImageIcon("src//com//company//res//sets.jpg");
        setPassword = new JButton(icon4);
        setPassword.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
            @Override
            public void mousePressed(MouseEvent e) {
                if (!setPassword.isOpaque()) {
                    setPassword.setOpaque(true);
                    setPassword.setBounds(setPassword.getX(), setPassword.getY() + 2, setPassword.getWidth(), setPassword.getHeight());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (setPassword.isOpaque()) {
                    setPassword.setOpaque(false);
                    setPassword.setBounds(setPassword.getX(), setPassword.getY() - 2, setPassword.getWidth(), setPassword.getHeight());

                    if (!newPasswordTyped && isTakenAccess) {
                            passLabel.setText("input new password");
                            password.setText("");
                            newPasswordTyped = !newPasswordTyped;
                            repaint();
                            return;
                    }

                    if (newPasswordTyped && isTakenAccess)
                    {
                        newPasswordTyped = !newPasswordTyped;
                        correctPassword =  new String(password.getPassword());

                        passLabel.setText("password");
                        try {toChangePassword();} catch (IOException e1) {e1.printStackTrace();}
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

        logo.setForeground(new Color(196, 202, 198));
        logo.setFont(new Font("Microsoft JhengHei Light", Font.BOLD, 10));

        DocumentListener listener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (login.getText().equals(correctLogin) && isPasswordCorrect(password.getPassword()))  try {
                    toTakeAccess();} catch (InterruptedException e1) {e1.printStackTrace();} catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (login.getText().equals(correctLogin) && isPasswordCorrect(password.getPassword()))  try {
                    toTakeAccess();} catch (InterruptedException e1) {e1.printStackTrace();} catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (login.getText().equals(correctLogin) && isPasswordCorrect(password.getPassword()))  try {
                    toTakeAccess();} catch (InterruptedException e1) {e1.printStackTrace();} catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        };

        password.getDocument().addDocumentListener(listener);
        login.getDocument().addDocumentListener(listener);

        this.add(login).setBounds(40,100,400,20);
        this.add(password).setBounds(40,160,400,20);
        this.add(logLabel).setBounds(40,120,400,20);
        this.add(passLabel).setBounds(40,180,400,20);
        this.add(logo).setBounds(180,250,340,20);

        this.setResizable(false);
    }
    private boolean isPasswordCorrect(char[] input){
        if (input.length != correctPassword.length()) return false;

        return Arrays.equals(input, this.correctPassword.toCharArray());
    }
    private void toTakeAccess() throws InterruptedException, IOException {
       login.setForeground(new Color(85, 255, 133));
       password.setForeground(new Color(85, 255, 133));

       toInputAndDecryptFile();
       isTakenAccess = true;

      this.add(encrypt).setBounds(width-42,height-72,22,22);
      this.add(setPassword).setBounds(width-72,height-72,22,22);
    }
    private void toInputAndDecryptFile() throws IOException {
        FileInputStream inputStream = new FileInputStream("src//com//company//res//crypted.txt");
        PrintStream output = new PrintStream(new FileOutputStream("src//com//company//res//decrypted.txt"));

        Scanner sc = new Scanner(inputStream);

        StringBuilder buffer = new StringBuilder();

        String temp;
        while (sc.hasNextLine()){
            temp = sc.nextLine();
            buffer.append(temp);
            buffer.append("\n");
        }

        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0; i < buffer.length(); i++) {
            if (buffer.charAt(i) == '\n') {decryptedText.append('\n'); continue;}
            decryptedText.append((char)((int) buffer.charAt(i) - 2));
        }
        output.print(decryptedText);

        inputStream.close();
        output.close();

            try{
                Process pr = Runtime.getRuntime().exec("cmd /c src\\com\\company\\res\\decrypted.txt");
            }
            catch(Exception e1){
                System.out.println(e1.toString());
                e1.printStackTrace();
            }

    }
    private void toOutputAndEncryptFile() throws IOException {
        FileInputStream inputStream = new FileInputStream("src//com//company//res//decrypted.txt");
        PrintStream output = new PrintStream(new FileOutputStream("src//com//company//res//crypted.txt"));

        Scanner sc = new Scanner(inputStream);

        StringBuilder buffer = new StringBuilder();

        String temp;
        while (sc.hasNextLine()){
            temp = sc.nextLine();
            buffer.append(temp);
            buffer.append("\n");
        }

        StringBuilder cryptedText = new StringBuilder();
        for (int i = 0; i < buffer.length(); i++) {
            if (buffer.charAt(i) == '\n') {cryptedText.append('\n'); continue;}
            cryptedText.append((char)((int) buffer.charAt(i) + 2));
        }
        output.print(cryptedText);
        inputStream.close();
        output.close();
    }
    private boolean toCheckLicense() throws IOException {
        if ((new File("src\\com\\company\\res\\License.txt")).exists()) {
            FileInputStream in = new FileInputStream("src//com//company//res//License.txt");
            Scanner sc = new Scanner(in);

            String temp = sc.nextLine();


            if (temp.equals("Username: friend.")) {
                logo.setText("Licensed for friend by Bulbum Lab, 2017");
                correctLogin = "friend";
            }
                else{
                    logo.setText("Licensed for"+temp.substring(temp.indexOf(' '), temp.indexOf('.'))+" by Bulbum Lab, 2017");
                    correctLogin = temp.substring(10, temp.indexOf('.')).substring(0, temp.substring(10, temp.indexOf('.')).indexOf(' '));
                    }
            repaint();

            return true;
        }
         else {
             license = new License();
             license.setAlwaysOnTop(true);

             license.licenseText.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    for (int i=0; i<license.arrayOfLicenses.length; i++)
                       if (license.licenseText.getText().equals(license.arrayOfLicenses[i])){
                           try {toPrintLicense(license.arrayOfUsernames[i]);
                               username = license.arrayOfUsernames[i];

                               logo.setText("Licensed for "+username+" by Bulbum Lab, 2017");

                               if (username.equals("friend")) correctLogin = "friend";
                               else  correctLogin = username.substring(0,username.indexOf(' '));

                               repaint();


                           } catch (FileNotFoundException e1) {e1.printStackTrace();} catch (InterruptedException e1) {e1.printStackTrace();}
                       }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    for (int i=0; i<license.arrayOfLicenses.length; i++)
                        if (license.licenseText.getText().equals(license.arrayOfLicenses[i])){
                            try {toPrintLicense(license.arrayOfUsernames[i]);
                                username = license.arrayOfUsernames[i];

                                logo.setText("Licensed for "+username+" by Bulbum Lab, 2017");

                                if (username.equals("friend")) correctLogin = "friend";
                                else  correctLogin = username.substring(0,username.indexOf(' '));

                                repaint();


                            } catch (FileNotFoundException e1) {e1.printStackTrace();} catch (InterruptedException e1) {e1.printStackTrace();}
                        }
                }

                @Override
                public void changedUpdate(DocumentEvent e) {

                }
            });
             return false;
             }
    }
    private void toPrintLicense(String user) throws FileNotFoundException, InterruptedException {
        PrintStream output = new PrintStream(new FileOutputStream("src//com//company//res//License.txt"));
        output.print("Username: "+user+".\nLicensed by Bulbum Lab, Minsk. 2017. All rights reserved.\nCopying, illegal distribution of program fragments, source code, resources, and encryption algorithm is prosecuted in accordance with the legislation of the Russian Federation under the Federal Law \"On Trade Secrets\" of July 29, 2004 N 98-FZ.\nPersonal license: "+license.licenseText.getText());

        license.toClose();
        this.setVisible(true);
    }
    private void toChangePassword() throws IOException {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try
        {
            String toWrite = toSubstitute(correctPassword);
            fos = new FileOutputStream("src//com//company//res//config.bin");
            oos = new ObjectOutputStream(fos);

            oos.writeObject(toWrite);
            oos.flush();
            oos.close();
        }
        catch (FileNotFoundException e) {e.printStackTrace();}
    }
    private void toReadPassword() throws IOException {
        FileInputStream in = null;
        ObjectInputStream ins = null;

        try
        {
            in = new FileInputStream("src//com//company//res//config.bin");
            ins = new ObjectInputStream(in);
            correctPassword = (String) ins.readObject();
            correctPassword = toSubstitute(correctPassword);

            in.close();
            ins.close();
        }
        catch (FileNotFoundException e) {e.printStackTrace();}
        catch (ClassNotFoundException e){e.printStackTrace();}
    }
    private String toSubstitute(String text){
        StringBuilder substituted = new StringBuilder("");

        for (int i=0; i<text.length(); i++)
            switch (text.charAt(i)){
                case ('a'): {substituted.append('z'); break;}
                case ('b'): {substituted.append('y'); break;}
                case ('c'): {substituted.append('x'); break;}
                case ('d'): {substituted.append('w'); break;}
                case ('e'): {substituted.append('v'); break;}
                case ('f'): {substituted.append('u'); break;}
                case ('g'): {substituted.append('t'); break;}
                case ('h'): {substituted.append('s'); break;}
                case ('i'): {substituted.append('r'); break;}
                case ('j'): {substituted.append('q'); break;}
                case ('k'): {substituted.append('p'); break;}
                case ('l'): {substituted.append('o'); break;}
                case ('m'): {substituted.append('n'); break;}
                case ('n'): {substituted.append('m'); break;}
                case ('o'): {substituted.append('l'); break;}
                case ('p'): {substituted.append('k'); break;}
                case ('q'): {substituted.append('j'); break;}
                case ('r'): {substituted.append('i'); break;}
                case ('s'): {substituted.append('h'); break;}
                case ('t'): {substituted.append('g'); break;}
                case ('u'): {substituted.append('f'); break;}
                case ('v'): {substituted.append('e'); break;}
                case ('w'): {substituted.append('d'); break;}
                case ('x'): {substituted.append('c'); break;}
                case ('y'): {substituted.append('b'); break;}
                case ('z'): {substituted.append('a'); break;}

                case ('A'): {substituted.append('Z'); break;}
                case ('B'): {substituted.append('Y'); break;}
                case ('C'): {substituted.append('X'); break;}
                case ('D'): {substituted.append('W'); break;}
                case ('E'): {substituted.append('V'); break;}
                case ('F'): {substituted.append('U'); break;}
                case ('G'): {substituted.append('T'); break;}
                case ('H'): {substituted.append('S'); break;}
                case ('I'): {substituted.append('R'); break;}
                case ('J'): {substituted.append('Q'); break;}
                case ('K'): {substituted.append('P'); break;}
                case ('L'): {substituted.append('O'); break;}
                case ('M'): {substituted.append('N'); break;}
                case ('N'): {substituted.append('M'); break;}
                case ('O'): {substituted.append('L'); break;}
                case ('P'): {substituted.append('K'); break;}
                case ('Q'): {substituted.append('J'); break;}
                case ('R'): {substituted.append('I'); break;}
                case ('S'): {substituted.append('H'); break;}
                case ('T'): {substituted.append('G'); break;}
                case ('U'): {substituted.append('F'); break;}
                case ('V'): {substituted.append('E'); break;}
                case ('W'): {substituted.append('D'); break;}
                case ('X'): {substituted.append('C'); break;}
                case ('Y'): {substituted.append('B'); break;}
                case ('Z'): {substituted.append('A'); break;}

                case ('0'): {substituted.append('!'); break;}
                case ('1'): {substituted.append('\"'); break;}
                case ('2'): {substituted.append('№'); break;}
                case ('3'): {substituted.append(';'); break;}
                case ('4'): {substituted.append('%'); break;}
                case ('5'): {substituted.append(':'); break;}
                case ('6'): {substituted.append('?'); break;}
                case ('7'): {substituted.append('*'); break;}
                case ('8'): {substituted.append('('); break;}
                case ('9'): {substituted.append(')'); break;}

                case ('!'): {substituted.append('0'); break;}
                case ('\"'):{substituted.append('1'); break;}
                case ('№'):{substituted.append('2'); break;}
                case (';'): {substituted.append('3'); break;}
                case ('%'): {substituted.append('4'); break;}
                case (':'): {substituted.append('5'); break;}
                case ('?'): {substituted.append('6'); break;}
                case ('*'): {substituted.append('7'); break;}
                case ('('): {substituted.append('8'); break;}
                case (')'): {substituted.append('9'); break;}


                default: {substituted.append('-'); break;}
            }
            return substituted.toString();
    }
}