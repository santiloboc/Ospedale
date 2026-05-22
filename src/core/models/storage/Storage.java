/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.Administrator;
import core.models.Appointment;
import core.models.Doctor;
import core.models.Hospitalization;
import core.models.Patient;
import core.models.Specialty;
import core.models.User;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 *
 * @author edangulo
 */
public class Storage {

    private static Storage instance;

    private ArrayList<User> users;
    private ArrayList<Appointment> appointments;
    private ArrayList<Hospitalization> hospitalizations;
    private HashMap<Long, Integer> appointmentCounters;
    private HashMap<Long, Integer> hospitalizationCounters;

    private Storage() {
        this.users = new ArrayList<>();
        this.appointments = new ArrayList<>();
        this.hospitalizations = new ArrayList<>();
        this.appointmentCounters = new HashMap<>();
        this.hospitalizationCounters = new HashMap<>();
        loadUsers();
    }

    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    private void loadUsers() {
        try {
            FileReader reader = new FileReader("json/users.json");
            JSONObject root = new JSONObject(new JSONTokener(reader));
            JSONArray usersArray = root.getJSONArray("users");

            for (int i = 0; i < usersArray.length(); i++) {
                JSONObject obj = usersArray.getJSONObject(i);
                String type = obj.getString("type");
                long id = obj.getLong("id");
                String username = obj.getString("username");
                String firstname = obj.getString("firstname");
                String lastname = obj.getString("lastname");
                String password = obj.getString("password");

                if (type.equals("admin")) {
                    users.add(new Administrator(id, username, firstname, lastname, password));
                } else if (type.equals("patient")) {
                    String email = obj.getString("email");
                    LocalDate birthdate = LocalDate.parse(obj.getString("birthdate"));
                    boolean gender = obj.getBoolean("gender");
                    long phone = obj.getLong("phone");
                    String address = obj.getString("address");
                    users.add(new Patient(id, username, firstname, lastname, password,
                            email, birthdate, gender, phone, address));
                } else if (type.equals("doctor")) {
                    String specialtyStr = obj.getString("specialty");
                    Specialty specialty;
                    try {
                        specialty = Specialty.valueOf(specialtyStr);
                    } catch (IllegalArgumentException e) {
                        specialty = Specialty.GENERAL_MEDICINE;
                    }
                    String licenceNumber = obj.getString("licenceNumber");
                    String assignedOffice = obj.getString("assignedOffice");
                    users.add(new Doctor(id, username, firstname, lastname, password,
                            specialty, licenceNumber, assignedOffice));
                }
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Error cargando users.json: " + e.getMessage());
        }
    }

    // ── Users ──────────────────────────────────────────────────────────────

    public boolean addUser(User u) {
        for (User existing : this.users) {
            if (existing.getId() == u.getId()) {
                return false;
            }
            if (existing.getUsername().equals(u.getUsername())) {
                return false;
            }
        }
        this.users.add(u);
        return true;
    }

    public User getUserById(long id) {
        for (User u : this.users) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }

    public User getUserByUsername(String username) {
        for (User u : this.users) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    // ── Appointments ───────────────────────────────────────────────────────

    public boolean addAppointment(Appointment a) {
        for (Appointment existing : this.appointments) {
            if (existing.getId().equals(a.getId())) {
                return false;
            }
        }
        this.appointments.add(a);
        return true;
    }

    public Appointment getAppointmentById(String id) {
        for (Appointment a : this.appointments) {
            if (a.getId().equals(id)) {
                return a;
            }
        }
        return null;
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    // ── Hospitalizations ───────────────────────────────────────────────────

    public boolean addHospitalization(Hospitalization h) {
        for (Hospitalization existing : this.hospitalizations) {
            if (existing.getId().equals(h.getId())) {
                return false;
            }
        }
        this.hospitalizations.add(h);
        return true;
    }

    public Hospitalization getHospitalizationById(String id) {
        for (Hospitalization h : this.hospitalizations) {
            if (h.getId().equals(id)) {
                return h;
            }
        }
        return null;
    }

    public ArrayList<Hospitalization> getHospitalizations() {
        return hospitalizations;
    }

    // ── ID generation ──────────────────────────────────────────────────────

    public String generateAppointmentId(long patientId) {
        int count = 0;
        if (appointmentCounters.containsKey(patientId)) {
            count = appointmentCounters.get(patientId);
        }
        count++;
        appointmentCounters.put(patientId, count);
        return String.format("A-%d-%04d", patientId, count);
    }

    public String generateHospitalizationId(long patientId) {
        int count = 0;
        if (hospitalizationCounters.containsKey(patientId)) {
            count = hospitalizationCounters.get(patientId);
        }
        count++;
        hospitalizationCounters.put(patientId, count);
        return String.format("H-%d-%04d", patientId, count);
    }

}
