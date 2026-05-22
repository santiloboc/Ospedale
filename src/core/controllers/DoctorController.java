/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Doctor;
import core.models.Specialty;
import core.models.User;
import core.models.storage.Storage;

/**
 *
 * @author edangulo
 */
public class DoctorController {

    public static Response registerDoctor(String id, String username, String firstname,
            String lastname, String password, String confirmPassword,
            String specialty, String licenceNumber, String assignedOffice) {

        long parsedId;
        try {
            parsedId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return new Response("ID must be a number", Status.BAD_REQUEST);
        }
        if (id.length() != 12 || parsedId <= 0) {
            return new Response("ID must be exactly 12 digits and greater than 0", Status.BAD_REQUEST);
        }
        if (Storage.getInstance().getUserById(parsedId) != null) {
            return new Response("ID already exists", Status.BAD_REQUEST);
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

        Specialty parsedSpecialty;
        try {
            parsedSpecialty = Specialty.valueOf(specialty);
        } catch (IllegalArgumentException e) {
            return new Response("Invalid specialty value", Status.BAD_REQUEST);
        }

        if (!licenceNumber.matches("L-\\d{10} MTL")) {
            return new Response("Licence number must match format L-XXXXXXXXXX MTL", Status.BAD_REQUEST);
        }

        if (!assignedOffice.matches("O-\\d{3}")) {
            return new Response("Assigned office must match format O-XXX", Status.BAD_REQUEST);
        }

        Doctor doctor = new Doctor(parsedId, username, firstname, lastname, password,
                parsedSpecialty, licenceNumber, assignedOffice);
        Storage.getInstance().addUser(doctor);
        return new Response("Doctor registered successfully", Status.CREATED);
    }

    public static Response updateDoctor(String currentId, String username, String firstname,
            String lastname, String password, String confirmPassword,
            String specialty, String licenceNumber, String assignedOffice) {

        long parsedId;
        try {
            parsedId = Long.parseLong(currentId);
        } catch (NumberFormatException e) {
            return new Response("ID must be a number", Status.BAD_REQUEST);
        }

        User user = Storage.getInstance().getUserById(parsedId);
        if (user == null) {
            return new Response("Doctor not found", Status.NOT_FOUND);
        }
        if (!(user instanceof Doctor)) {
            return new Response("User is not a doctor", Status.BAD_REQUEST);
        }
        Doctor doctor = (Doctor) user;

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

        Specialty parsedSpecialty;
        try {
            parsedSpecialty = Specialty.valueOf(specialty);
        } catch (IllegalArgumentException e) {
            return new Response("Invalid specialty value", Status.BAD_REQUEST);
        }

        if (!licenceNumber.matches("L-\\d{10} MTL")) {
            return new Response("Licence number must match format L-XXXXXXXXXX MTL", Status.BAD_REQUEST);
        }

        if (!assignedOffice.matches("O-\\d{3}")) {
            return new Response("Assigned office must match format O-XXX", Status.BAD_REQUEST);
        }

        doctor.setUsername(username);
        doctor.setFirstname(firstname);
        doctor.setLastname(lastname);
        doctor.setPassword(password);
        doctor.setSpecialty(parsedSpecialty);
        doctor.setLicenceNumber(licenceNumber);
        doctor.setAssignedOffice(assignedOffice);

        return new Response("Doctor updated successfully", Status.OK);
    }

}
