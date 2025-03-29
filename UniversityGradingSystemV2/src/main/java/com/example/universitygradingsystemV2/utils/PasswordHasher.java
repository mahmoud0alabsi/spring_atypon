package com.example.universitygradingsystemV2.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHasher {

    private PasswordHasher(){}

    public static String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    public static boolean verifyPassword(String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }
}

