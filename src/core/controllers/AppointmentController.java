/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Appointment;
import core.models.AppointmentStatus;
import core.models.Doctor;
import core.models.Patient;
import core.models.Specialty;
import core.models.User;
import core.models.storage.Storage;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 *
 * @author edangulo
 */
public class AppointmentController {

    public static Response requestAppointment(String patientId, String doctorId,
            String specialty, String date, String time, String reason, String type) {

        // 1. Validate patientId
        long parsedPatientId;
        try {
            parsedPatientId = Long.parseLong(patientId);
        } catch (NumberFormatException e) {
            return new Response("Patient ID must be a number", Status.BAD_REQUEST);
        }
        User patientUser = Storage.getInstance().getUserById(parsedPatientId);
        if (patientUser == null) {
            return new Response("Patient not found", Status.NOT_FOUND);
        }
        if (!(patientUser instanceof Patient)) {
            return new Response("User is not a patient", Status.BAD_REQUEST);
        }
        Patient patient = (Patient) patientUser;

        // 2. Validate date
        LocalDate parsedDate;
        try {
            parsedDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            return new Response("Date must be in format YYYY-MM-DD", Status.BAD_REQUEST);
        }

        // 3. Validate time: minutes must be 00, 15, 30 or 45
        LocalTime parsedTime;
        try {
            parsedTime = LocalTime.parse(time);
        } catch (DateTimeParseException e) {
            return new Response("Time must be in format HH:mm", Status.BAD_REQUEST);
        }
        int minutes = parsedTime.getMinute();
        if (minutes != 0 && minutes != 15 && minutes != 30 && minutes != 45) {
            return new Response("Minutes must be 00, 15, 30 or 45", Status.BAD_REQUEST);
        }

        LocalDateTime datetime = LocalDateTime.of(parsedDate, parsedTime);

        // Validate specialty
        Specialty parsedSpecialty;
        try {
            parsedSpecialty = Specialty.valueOf(specialty);
        } catch (IllegalArgumentException e) {
            return new Response("Invalid specialty value", Status.BAD_REQUEST);
        }

        // Parse type
        boolean parsedType;
        if (type.equals("true")) {
            parsedType = true;
        } else if (type.equals("false")) {
            parsedType = false;
        } else {
            return new Response("Type must be 'true' or 'false'", Status.BAD_REQUEST);
        }

        // 4 & 5. Find doctor
        Doctor doctor = null;

        if (doctorId != null && !doctorId.isEmpty()) {
            long parsedDoctorId;
            try {
                parsedDoctorId = Long.parseLong(doctorId);
            } catch (NumberFormatException e) {
                return new Response("Doctor ID must be a number", Status.BAD_REQUEST);
            }
            User doctorUser = Storage.getInstance().getUserById(parsedDoctorId);
            if (doctorUser == null) {
                return new Response("Doctor not found", Status.NOT_FOUND);
            }
            if (!(doctorUser instanceof Doctor)) {
                return new Response("User is not a doctor", Status.BAD_REQUEST);
            }
            doctor = (Doctor) doctorUser;
            if (doctor.getSpecialty() != parsedSpecialty) {
                return new Response("Doctor specialty does not match requested specialty", Status.BAD_REQUEST);
            }
            for (Appointment a : doctor.getAppointments()) {
                if (a.getDatetime().equals(datetime) && a.getStatus() != AppointmentStatus.CANCELED) {
                    return new Response("Doctor is not available at the requested time", Status.BAD_REQUEST);
                }
            }
        } else {
            ArrayList<User> users = Storage.getInstance().getUsers();
            for (int i = 0; i < users.size(); i++) {
                User u = users.get(i);
                if (u instanceof Doctor && doctor == null) {
                    Doctor candidate = (Doctor) u;
                    if (candidate.getSpecialty() == parsedSpecialty) {
                        boolean busy = false;
                        for (Appointment a : candidate.getAppointments()) {
                            if (a.getDatetime().equals(datetime) && a.getStatus() != AppointmentStatus.CANCELED) {
                                busy = true;
                            }
                        }
                        if (!busy) {
                            doctor = candidate;
                        }
                    }
                }
            }
            if (doctor == null) {
                return new Response("No available doctor found for the requested specialty and time", Status.NOT_FOUND);
            }
        }

        // 6. Generate ID and create appointment
        String appointmentId = Storage.getInstance().generateAppointmentId(parsedPatientId);

        Appointment appointment = new Appointment(appointmentId, patient, doctor,
                parsedSpecialty, datetime, reason, parsedType);

        // 7. Register in patient, doctor and Storage
        patient.addAppointment(appointment);
        doctor.addAppointment(appointment);
        Storage.getInstance().addAppointment(appointment);

        return new Response("Appointment requested successfully", Status.CREATED);
    }

    public static Response getPatientAppointments(String patientId) {
        long parsedPatientId;
        try {
            parsedPatientId = Long.parseLong(patientId);
        } catch (NumberFormatException e) {
            return new Response("Patient ID must be a number", Status.BAD_REQUEST);
        }
        User user = Storage.getInstance().getUserById(parsedPatientId);
        if (user == null) {
            return new Response("Patient not found", Status.NOT_FOUND);
        }
        if (!(user instanceof Patient)) {
            return new Response("User is not a patient", Status.BAD_REQUEST);
        }
        Patient patient = (Patient) user;

        ArrayList<Appointment> appointments = new ArrayList<>(patient.getAppointments());
        Collections.sort(appointments, new Comparator<Appointment>() {
            @Override
            public int compare(Appointment a1, Appointment a2) {
                return a2.getDatetime().compareTo(a1.getDatetime());
            }
        });

        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        for (Appointment a : appointments) {
            list.add(a.serialize());
        }

        HashMap<String, Object> data = new HashMap<>();
        data.put("appointments", list);
        return new Response("Appointments retrieved successfully", Status.OK, data);
    }

    public static Response getDoctorAppointments(String doctorId, boolean pendingOnly) {
        long parsedDoctorId;
        try {
            parsedDoctorId = Long.parseLong(doctorId);
        } catch (NumberFormatException e) {
            return new Response("Doctor ID must be a number", Status.BAD_REQUEST);
        }
        User user = Storage.getInstance().getUserById(parsedDoctorId);
        if (user == null) {
            return new Response("Doctor not found", Status.NOT_FOUND);
        }
        if (!(user instanceof Doctor)) {
            return new Response("User is not a doctor", Status.BAD_REQUEST);
        }
        Doctor doctor = (Doctor) user;

        ArrayList<Appointment> appointments = new ArrayList<>();
        for (Appointment a : doctor.getAppointments()) {
            if (pendingOnly) {
                if (a.getStatus() == AppointmentStatus.PENDING) {
                    appointments.add(a);
                }
            } else {
                appointments.add(a);
            }
        }

        Collections.sort(appointments, new Comparator<Appointment>() {
            @Override
            public int compare(Appointment a1, Appointment a2) {
                return a2.getDatetime().compareTo(a1.getDatetime());
            }
        });

        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        for (Appointment a : appointments) {
            list.add(a.serialize());
        }

        HashMap<String, Object> data = new HashMap<>();
        data.put("appointments", list);
        return new Response("Appointments retrieved successfully", Status.OK, data);
    }

}
