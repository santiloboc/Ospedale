package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Doctor;
import core.models.Patient;
import core.models.RoomType;
import core.models.Specialty;
import core.models.User;
import core.models.storage.Storage;
import java.util.ArrayList;
import java.util.HashMap;

public class UserController {

    public static Response getDoctors() {
        try {
            ArrayList<User> users = Storage.getInstance().getUsers();
            ArrayList<HashMap<String, Object>> doctors = new ArrayList<>();
            for (int i = 0; i < users.size(); i++) {
                User u = users.get(i);
                if (u instanceof Doctor) {
                    doctors.add(u.serialize());
                }
            }
            HashMap<String, Object> data = new HashMap<>();
            data.put("doctors", doctors);
            return new Response("Doctors retrieved successfully", Status.OK, data);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response getPatients() {
        try {
            ArrayList<User> users = Storage.getInstance().getUsers();
            ArrayList<HashMap<String, Object>> patients = new ArrayList<>();
            for (int i = 0; i < users.size(); i++) {
                User u = users.get(i);
                if (u instanceof Patient) {
                    patients.add(u.serialize());
                }
            }
            HashMap<String, Object> data = new HashMap<>();
            data.put("patients", patients);
            return new Response("Patients retrieved successfully", Status.OK, data);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response getUserById(String id) {
        try {
            long parsedId;
            try {
                parsedId = Long.parseLong(id);
            } catch (NumberFormatException e) {
                return new Response("ID must be a number", Status.BAD_REQUEST);
            }
            User user = Storage.getInstance().getUserById(parsedId);
            if (user == null) {
                return new Response("User not found", Status.NOT_FOUND);
            }
            return new Response("User found", Status.OK, user.serialize());
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response getSpecialtyNames() {
        try {
            ArrayList<String> names = new ArrayList<>();
            for (Specialty s : Specialty.values()) {
                names.add(s.name());
            }
            HashMap<String, Object> data = new HashMap<>();
            data.put("specialties", names);
            return new Response("Specialties retrieved successfully", Status.OK, data);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response getRoomTypeNames() {
        try {
            ArrayList<String> names = new ArrayList<>();
            for (RoomType rt : RoomType.values()) {
                names.add(rt.name());
            }
            HashMap<String, Object> data = new HashMap<>();
            data.put("roomTypes", names);
            return new Response("Room types retrieved successfully", Status.OK, data);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
