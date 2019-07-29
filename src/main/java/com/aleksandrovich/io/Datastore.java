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

/**
 * Class with all licenses/passwords IO functions.
 * @author AleksandrovichK
 */
public class Datastore {
    private User activeUser;
    private String correctPassword;
    private List<User> usersData = new LinkedList<>();

    public Datastore() {
    }

    public User getActiveUser() {
        return activeUser;
    }

    public Status toReadPassword() {
        InputStream inRes = getClass().getResourceAsStream("/config.txt");
        if (inRes != null) { // NO FILE
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inRes));
                String line = reader.readLine();
                if (line != null && !line.isEmpty()) {
                    this.correctPassword = Utils.toSubstitute(line);
                } else {
                    inRes.close();
                    return Status.FILE_IS_EMPTY;
                }
                inRes.close();
                return Status.SUCCESS;
            } catch (IOException e) {
                e.printStackTrace();
                return Status.FILE_IS_NOT_AVAILABLE;
            }
        } else {
            correctPassword = null;
            return Status.FILE_IS_NOT_AVAILABLE;
        }
    }

    public boolean isPasswordCorrect(char[] input) {
        if (null != correctPassword && null != input) {
            return Arrays.equals(input, this.correctPassword.toCharArray());
        } else
            return false;
    }

    public Status toFillLicenses() {
        InputStream inputStream = getClass().getResourceAsStream("/kernellic.txt");

        if (inputStream != null) { // FILE IS ABSENT
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    line = Utils.toSubstitute(line);

                    String[] creds = line.split(":");
                    this.usersData.add(new User(creds[1], creds[0]));
                }
                inputStream.close();
            } catch (IOException e) {
                return Status.FILE_IS_NOT_AVAILABLE;
            }
            return Status.SUCCESS;
        } else {
            return Status.FILE_IS_NOT_AVAILABLE;
        }
    }

    public Status checkLicenseStatus() {
        InputStream inRes = getClass().getResourceAsStream("/License.txt");

        if (inRes != null) //FILE IS ABSENT
        {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inRes));
                String firstLine = reader.readLine();
                String secondLine = reader.readLine();
                if (firstLine == null) {
                    return Status.FILE_IS_EMPTY;
                } else {
                    if (firstLine.contains(":") && secondLine.contains(":")) {
                        String[] storedUsername = firstLine.split(":");
                        String[] storedLicense = secondLine.split(":");

                        if (storedUsername.length > 1 && storedLicense.length > 1) {
                            for (User user : this.usersData) {
                                if (user.getName().equals(storedUsername[1]) && user.getLicense().equals(storedLicense[1])) { // user is valid
                                    this.activeUser = user;
                                    return Status.SUCCESS;
                                }
                            }
                            return Status.FILE_CONTAINS_WRONG_INFO;
                        }
                    }
                    return Status.FILE_IS_CORRUPTED;
                }
            } catch (IOException e) {
                return Status.FILE_IS_NOT_AVAILABLE;
            }
        } else {
            return Status.FILE_IS_NOT_AVAILABLE;
        }
    }

    public User isLicensePresent(String inputtedLicense) {
        for (User user : usersData) {
            if (inputtedLicense.equals(user.getLicense())) {
                return user;
            }
        }
        return null;
    }

    public Status printLicenseToFile(String user, String license) {
        URL resourceUrl = getClass().getResource("/License.txt");
        if (null != resourceUrl) {
            try {
                File file = new File(resourceUrl.toURI());
                PrintStream output = new PrintStream(file);
                output.print(
                        "Username:" + user + "\n" +
                                "License:" + license + "\n" +
                                "Licensed by Aleksandrovich K., Minsk. 2017-2020. All rights reserved.\nCopying, illegal distribution of program fragments, source code, resources, and encryption algorithm is prosecuted in accordance with the legislation of the Russian Federation under the Federal Law \"On Trade Secrets\" of July 29, 2004 N 98-FZ.");
                output.close();
            } catch (URISyntaxException | FileNotFoundException e) {
                return Status.FILE_IS_NOT_AVAILABLE;
            }
            return Status.SUCCESS;
        }
        return Status.FILE_IS_NOT_AVAILABLE;
    }

    public String getCorrectPassword() {
        return correctPassword;
    }

    public void setCorrectPassword(String correctPassword) {
        this.correctPassword = correctPassword;
    }
}
