package com.aleksandrovich.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.aleksandrovich.Main;
import com.aleksandrovich.User;

public class Datastore {
    private String correctPassword;
    private String correctLogin;
    private List<User> usersData = new LinkedList<>();

    public Datastore() {
    }

    public FileStatus toReadPassword() {
        InputStream inRes = getClass().getResourceAsStream("/res/config.txt");
        if (inRes != null){ // NO FILE
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inRes));
                this.correctPassword = Utils.toSubstitute(reader.readLine());

                inRes.close();
                return FileStatus.SUCCESS;
            } catch (IOException e) {
                e.printStackTrace();
                return FileStatus.FILE_IS_EMPTY;
            }
        } else {
            correctPassword = null;
            return FileStatus.FILE_IS_ABSENT;
        }
    }

    public boolean isPasswordCorrect(char[] input) {
        if (null != correctPassword && null != input) {
            return Arrays.equals(input, this.correctPassword.toCharArray());
        } else
            return false;
    }

    public boolean toFillLicenses() {
        InputStream inputStream = Main.class.getResourceAsStream("/res/kernellic.txt");

        if (inputStream != null) { // FILE IS ABSENT
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    line = Utils.toSubstitute(line);

                    String name = line.substring(line.indexOf(' ') + 1);
                    String license = line.substring(0, line.indexOf(' '));
                    String[] creds = line.split(" "); // TODO is it better?
                    this.usersData.add(new User(name, license));
                    //d4c2a1ec869e1774a2f4b81163e1a968 friend
                }
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        } else {
            return false;
        }
    }

    public FileStatus checkLicenseStatus() throws IOException {
        InputStream inRes = Main.class.getResourceAsStream("/res/License.txt");

        if (inRes != null) //FILE IS ABSENT
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inRes));
            String firstLine = reader.readLine();
            String secondLine = reader.readLine();

            if (firstLine == null) {
                return FileStatus.FILE_IS_EMPTY;
            } else {
                if (firstLine.contains(":") && secondLine.contains(":")){
                    String[] storedUsername = firstLine.split(":");
                    String[] storedLicense = secondLine.split(":");

                    if (storedUsername.length >1 && storedLicense.length >1) {
                        for (User user : this.usersData) {
                            if (user.getName().equals(storedUsername[1]) && user.getLicense().equals(storedLicense[1])) { // user is valid
                                this.correctLogin = user.getName();
                                return FileStatus.SUCCESS;
                            }
                        }
                        return FileStatus.FILE_CONTAINS_WRONG_INFO;
                    }
                }
                return FileStatus.FILE_IS_CORRUPTED;
            }
        } else {
            return FileStatus.FILE_IS_ABSENT;
        }
    }

    /**
     * @param inputtedLicense license inputted on UI
     * @return username of user with given license
     */
    public User isLicensePresent(String inputtedLicense) {
        for (User user : usersData) {
            if (inputtedLicense.equals(user.getLicense())) {
                return user;
            }
        }
        return null;
    }

    public FileStatus printLicenseToFile(String user, String license) throws FileNotFoundException {
        if (null != getClass().getResource("/res/License.txt")) {
            try {
                URL resourceUrl = getClass().getResource("/res/License.txt");
                File file = new File(resourceUrl.toURI());
                PrintStream output = new PrintStream(file);
                output.print(
                        "Username:" + user + "\n" +
                                "License:" + license + "\n" +
                                "Licensed by Aleksandrovich K., Minsk. 2017-2020. All rights reserved.\nCopying, illegal distribution of program fragments, source code, resources, and encryption algorithm is prosecuted in accordance with the legislation of the Russian Federation under the Federal Law \"On Trade Secrets\" of July 29, 2004 N 98-FZ.");
                output.close();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            return FileStatus.SUCCESS;
        }
        return FileStatus.FILE_IS_ABSENT;
    }

    public String getCorrectPassword() {
        return correctPassword;
    }

    public void setCorrectPassword(String correctPassword) {
        this.correctPassword = correctPassword;
    }

    public String getCorrectLogin() {
        return correctLogin;
    }

    public void setCorrectLogin(String correctLogin) {
        this.correctLogin = correctLogin;
    }
}
