/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.User;
import core.models.storage.Storage;

/**
 *
 * @author edangulo
 */
public class AuthController {

    public static Response login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return new Response("Username and password are required", Status.BAD_REQUEST);
        }

        User user = Storage.getInstance().getUserByUsername(username);

        if (user == null) {
            return new Response("User not found", Status.NOT_FOUND);
        }

        if (!user.getPassword().equals(password)) {
            return new Response("Invalid password", Status.BAD_REQUEST);
        }

        return new Response("Login successful", Status.OK, user.serialize());
    }

}
