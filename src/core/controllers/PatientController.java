/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Patient;
import core.models.User;
import core.models.storage.Storage;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 *
 * @author edangulo
 */
public class PatientController {

    public static Response registerPatient(String id, String username, String firstname,
            String lastname, String password, String confirmPassword, String email,
            String birthdate, String phone, String address, String gender) {

        long parsedId;
        try {
            parsedId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return new Response("ID must be a number", Status.BAD_REQUEST);
        }
        if (id.length() != 12 || parsedId <= 0) {
            return new Response("ID must be exactly 12 digits and greater than 0", Status.BAD_REQUEST);
        }

        if (username == null || username.isEmpty()) {
            return new Response("Username is required", Status.BAD_REQUEST);
        }
        if (Storage.getInstance().getUserByUsername(username) != null) {
            return new Response("Username already exists", Status.BAD_REQUEST);
        }

        if (firstname == null || firstname.isEmpty()) {
            return new Response("Firstname is required", Status.BAD_REQUEST);
        }
        if (lastname == null || lastname.isEmpty()) {
            return new Response("Lastname is required", Status.BAD_REQUEST);
        }

        if (password == null || password.isEmpty()) {
            return new Response("Password is required", Status.BAD_REQUEST);
        }
        if (!password.equals(confirmPassword)) {
            return new Response("Passwords do not match", Status.BAD_REQUEST);
        }

        if (!email.contains("@") || !email.endsWith(".com")) {
            return new Response("Invalid email address", Status.BAD_REQUEST);
        }

        LocalDate parsedBirthdate;
        try {
            parsedBirthdate = LocalDate.parse(birthdate);
        } catch (DateTimeParseException e) {
            return new Response("Birthdate must be in format YYYY-MM-DD", Status.BAD_REQUEST);
        }

        long parsedPhone;
        try {
            parsedPhone = Long.parseLong(phone);
        } catch (NumberFormatException e) {
            return new Response("Phone must be a number", Status.BAD_REQUEST);
        }
        if (phone.length() != 10) {
            return new Response("Phone must be exactly 10 digits", Status.BAD_REQUEST);
        }

        if (address == null || address.isEmpty()) {
            return new Response("Address is required", Status.BAD_REQUEST);
        }

        boolean parsedGender;
        if (gender.equals("Male")) {
            parsedGender = true;
        } else if (gender.equals("Female")) {
            parsedGender = false;
        } else {
            return new Response("Gender must be Male or Female", Status.BAD_REQUEST);
        }

        if (Storage.getInstance().getUserById(parsedId) != null) {
            return new Response("ID already exists", Status.BAD_REQUEST);
        }

        Patient patient = new Patient(parsedId, username, firstname, lastname, password,
                email, parsedBirthdate, parsedGender, parsedPhone, address);
        Storage.getInstance().addUser(patient);
        return new Response("Patient registered successfully", Status.CREATED);
    }

    public static Response updatePatient(String currentId, String username, String firstname,
            String lastname, String password, String confirmPassword, String email,
            String birthdate, String phone, String address, String gender) {

        long parsedId;
        try {
            parsedId = Long.parseLong(currentId);
        } catch (NumberFormatException e) {
            return new Response("ID must be a number", Status.BAD_REQUEST);
        }

        User user = Storage.getInstance().getUserById(parsedId);
        if (user == null) {
            return new Response("Patient not found", Status.NOT_FOUND);
        }
        if (!(user instanceof Patient)) {
            return new Response("User is not a patient", Status.BAD_REQUEST);
        }
        Patient patient = (Patient) user;

        if (username == null || username.isEmpty()) {
            return new Response("Username is required", Status.BAD_REQUEST);
        }
        User existingWithUsername = Storage.getInstance().getUserByUsername(username);
        if (existingWithUsername != null && existingWithUsername.getId() != parsedId) {
            return new Response("Username already exists", Status.BAD_REQUEST);
        }

        if (firstname == null || firstname.isEmpty()) {
            return new Response("Firstname is required", Status.BAD_REQUEST);
        }
        if (lastname == null || lastname.isEmpty()) {
            return new Response("Lastname is required", Status.BAD_REQUEST);
        }

        if (password == null || password.isEmpty()) {
            return new Response("Password is required", Status.BAD_REQUEST);
        }
        if (!password.equals(confirmPassword)) {
            return new Response("Passwords do not match", Status.BAD_REQUEST);
        }

        if (!email.contains("@") || !email.endsWith(".com")) {
            return new Response("Invalid email address", Status.BAD_REQUEST);
        }

        LocalDate parsedBirthdate;
        try {
            parsedBirthdate = LocalDate.parse(birthdate);
        } catch (DateTimeParseException e) {
            return new Response("Birthdate must be in format YYYY-MM-DD", Status.BAD_REQUEST);
        }

        long parsedPhone;
        try {
            parsedPhone = Long.parseLong(phone);
        } catch (NumberFormatException e) {
            return new Response("Phone must be a number", Status.BAD_REQUEST);
        }
        if (phone.length() != 10) {
            return new Response("Phone must be exactly 10 digits", Status.BAD_REQUEST);
        }

        if (address == null || address.isEmpty()) {
            return new Response("Address is required", Status.BAD_REQUEST);
        }

        boolean parsedGender;
        if (gender.equals("Male")) {
            parsedGender = true;
        } else if (gender.equals("Female")) {
            parsedGender = false;
        } else {
            return new Response("Gender must be Male or Female", Status.BAD_REQUEST);
        }

        patient.setUsername(username);
        patient.setFirstname(firstname);
        patient.setLastname(lastname);
        patient.setPassword(password);
        patient.setEmail(email);
        patient.setBirthdate(parsedBirthdate);
        patient.setPhone(parsedPhone);
        patient.setAddress(address);
        patient.setGender(parsedGender);

        return new Response("Patient updated successfully", Status.OK);
    }

}
