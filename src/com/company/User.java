package com.company;

/**
 * @author AleksandrovichK
 */
public class User {
    private String name;
    private String license;

    User(String name, String license) {
        this.name = name;
        this.license = license;
    }

    public String getName() {
        return name;
    }

    public String getLicense() {
        return license;
    }
}
