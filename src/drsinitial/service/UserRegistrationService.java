/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package drsinitial.service;

import drsinitial.model.User;
import drsinitial.model.enums.UserRole;

/**
 *
 * @author Cardoso Pepe
 */


public class UserRegistrationService {

    public boolean isValidRegistration(String fullName,
            String email,
            String username,
            String password,
            String confirmPassword) {

        if (isBlank(fullName)
                || isBlank(email)
                || isBlank(username)
                || isBlank(password)
                || isBlank(confirmPassword)) {
            return false;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            return false;
        }

        if (username.trim().length() < 4) {
            return false;
        }

        if (password.length() < 6) {
            return false;
        }

        return password.equals(confirmPassword);
    }

    public User createPublicUser(String userId,
            String fullName,
            String email,
            String username,
            String password,
            String confirmPassword) {

        if (!isValidRegistration(fullName, email, username,
                password, confirmPassword)) {
            return null;
        }

        return new User(
                userId,
                fullName.trim(),
                email.trim(),
                username.trim(),
                UserRole.PUBLIC_USER.name(),
                "Active"
        );
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}