package com.aleksandrovich.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.prefs.Preferences;

/**
 * Class with all licenses/passwords IO functions.
 *
 * @author AleksandrovichK
 */
public class Datastore {
    public static Preferences store = Preferences.userRoot().node("com.aleksandrovich.informationprotector");

    private User activeUser;
    private String correctPassword;
    private List<User> usersData = new LinkedList<>();

    public Datastore() {
    }

    public User getActiveUser() {
        return activeUser;
    }

    public Status toReadPassword() {
        String password = store.get("password", "");

        if (password != null) { // NO FILE
            if (!password.isEmpty()) {
                this.correctPassword = Utils.toSubstitute(password);
                return Status.SUCCESS;
            } else {
                return Status.REGISTRY_SEGMENT_IS_EMPTY;
            }
        } else {
            correctPassword = null;
            return Status.REGISTRY_SEGMENT_IS_EMPTY;
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
        String license = store.get("license", "");

        if (license != null) {
            if (!license.isEmpty()) {
                String[] lines = license.split("\n");

                if (lines[0].contains(":") && lines[1].contains(":")) {
                    String[] storedUsername = lines[0].split(":");
                    String[] storedLicense = lines[1].split(":");

                    if (storedUsername.length > 1 && storedLicense.length > 1) {
                        for (User user : this.usersData) {
                            if (user.getName().equals(storedUsername[1]) && user.getLicense().equals(storedLicense[1])) { // user is valid
                                this.activeUser = user;
                                return Status.SUCCESS;
                            }
                        }
                        return Status.REGISTRY_CONTAINS_WRONG_INFO;
                    }
                }
                return Status.REGISTRY_CONTAINS_WRONG_INFO;
            } else {
                return Status.REGISTRY_SEGMENT_IS_EMPTY;
            }
        } else {
            return Status.REGISTRY_SEGMENT_IS_EMPTY;
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

    public Status printLicenseToStore(String user, String license) {
        try {
            String text = "Username:" + user + "\n" +
                    "License:" + license + "\n" +
                    "Licensed by Aleksandrovich K., Minsk. 2017-2020. All rights reserved.\nCopying, illegal distribution of program fragments, source code, resources, and encryption algorithm is prosecuted in accordance with the legislation of the Russian Federation under the Federal Law \"On Trade Secrets\" of July 29, 2004 N 98-FZ.";
            store.put("license", text);
        } catch (Exception e) {
            return Status.REGISTRY_SEGMENT_IS_NOT_AVAILABLE;
        }
        return Status.SUCCESS;
    }

    public Status initStore() {
        try {
            this.correctPassword = Utils.toSubstitute("\"+;%");
            store.put("password", "\"+;%");
            store.put("data", "Ыєхюяхят-њяыя-ёнцш-сшќ-ънєншн-эноыяј-ю-ютчэтяъјщ-ђэнъхшхітщ-снъъјђ;\u001A\u0017");
        } catch (Exception e) {
            return Status.REGISTRY_SEGMENT_IS_NOT_AVAILABLE;
        }
        return Status.SUCCESS;
    }

    public String getCorrectPassword() {
        return correctPassword;
    }

    public void setCorrectPassword(String correctPassword) {
        this.correctPassword = correctPassword;
    }

}
