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
import core.models.Hospitalization;
import core.models.HospitalizationStatus;
import core.models.Patient;
import core.models.RoomType;
import core.models.User;
import core.models.storage.Storage;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author edangulo
 */
public class HospitalizationController {

    public static Response requestHospitalization(String patientId, String doctorId,
            String date, String reason, String roomType, String observations) {

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
        Doctor doctor = (Doctor) doctorUser;

        LocalDate parsedDate;
        try {
            parsedDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            return new Response("Date must be in format YYYY-MM-DD", Status.BAD_REQUEST);
        }

        if (reason == null || reason.isEmpty()) {
            return new Response("Reason is required", Status.BAD_REQUEST);
        }

        RoomType parsedRoomType;
        try {
            parsedRoomType = RoomType.valueOf(roomType);
        } catch (IllegalArgumentException e) {
            return new Response("Invalid room type value", Status.BAD_REQUEST);
        }

        String hospId = Storage.getInstance().generateHospitalizationId(parsedPatientId);

        Hospitalization hospitalization = new Hospitalization(hospId, patient, doctor,
                parsedDate, reason, parsedRoomType, observations);
        Storage.getInstance().addHospitalization(hospitalization);

        return new Response("Hospitalization requested successfully", Status.CREATED);
    }

    public static Response approveHospitalization(String hospId, String doctorId) {
        Hospitalization hospitalization = Storage.getInstance().getHospitalizationById(hospId);
        if (hospitalization == null) {
            return new Response("Hospitalization not found", Status.NOT_FOUND);
        }

        long parsedDoctorId;
        try {
            parsedDoctorId = Long.parseLong(doctorId);
        } catch (NumberFormatException e) {
            return new Response("Doctor ID must be a number", Status.BAD_REQUEST);
        }
        if (hospitalization.getDoctor().getId() != parsedDoctorId) {
            return new Response("Doctor does not belong to this hospitalization", Status.BAD_REQUEST);
        }

        if (hospitalization.getStatus() != HospitalizationStatus.REQUESTED) {
            return new Response("Hospitalization must be in REQUESTED status", Status.BAD_REQUEST);
        }

        hospitalization.setStatus(HospitalizationStatus.ONGOING);
        return new Response("Hospitalization approved", Status.OK);
    }

    public static Response cancelHospitalization(String hospId, String doctorId) {
        Hospitalization hospitalization = Storage.getInstance().getHospitalizationById(hospId);
        if (hospitalization == null) {
            return new Response("Hospitalization not found", Status.NOT_FOUND);
        }

        long parsedDoctorId;
        try {
            parsedDoctorId = Long.parseLong(doctorId);
        } catch (NumberFormatException e) {
            return new Response("Doctor ID must be a number", Status.BAD_REQUEST);
        }
        if (hospitalization.getDoctor().getId() != parsedDoctorId) {
            return new Response("Doctor does not belong to this hospitalization", Status.BAD_REQUEST);
        }

        if (hospitalization.getStatus() != HospitalizationStatus.REQUESTED
                && hospitalization.getStatus() != HospitalizationStatus.ONGOING) {
            return new Response("Hospitalization must be in REQUESTED or ONGOING status", Status.BAD_REQUEST);
        }

        hospitalization.setStatus(HospitalizationStatus.CANCELED);
        return new Response("Hospitalization canceled", Status.OK);
    }

    public static Response getDoctorHospitalizationRequests(String doctorId) {
        try {
            long parsedDoctorId;
            try {
                parsedDoctorId = Long.parseLong(doctorId);
            } catch (NumberFormatException e) {
                return new Response("Doctor ID must be a number", Status.BAD_REQUEST);
            }
            ArrayList<Hospitalization> all = Storage.getInstance().getHospitalizations();
            ArrayList<String> ids = new ArrayList<>();
            for (int i = 0; i < all.size(); i++) {
                Hospitalization h = all.get(i);
                if (h.getDoctor().getId() == parsedDoctorId && h.getStatus() == HospitalizationStatus.REQUESTED) {
                    ids.add(h.getId());
                }
            }
            HashMap<String, Object> data = new HashMap<>();
            data.put("hospitalizations", ids);
            return new Response("Hospitalization requests retrieved", Status.OK, data);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response hospitalizeFromAppointment(String appointmentId, String doctorId,
            String roomType, String observations) {

        Appointment appointment = Storage.getInstance().getAppointmentById(appointmentId);
        if (appointment == null) {
            return new Response("Appointment not found", Status.NOT_FOUND);
        }

        long parsedDoctorId;
        try {
            parsedDoctorId = Long.parseLong(doctorId);
        } catch (NumberFormatException e) {
            return new Response("Doctor ID must be a number", Status.BAD_REQUEST);
        }
        if (appointment.getDoctor().getId() != parsedDoctorId) {
            return new Response("Doctor does not belong to this appointment", Status.BAD_REQUEST);
        }

        if (appointment.getStatus() != AppointmentStatus.PENDING) {
            return new Response("Appointment must be in PENDING status", Status.BAD_REQUEST);
        }

        RoomType parsedRoomType;
        try {
            parsedRoomType = RoomType.valueOf(roomType);
        } catch (IllegalArgumentException e) {
            return new Response("Invalid room type value", Status.BAD_REQUEST);
        }

        Patient patient = appointment.getPatient();
        Doctor doctor = appointment.getDoctor();
        LocalDate date = appointment.getDatetime().toLocalDate();
        String reason = appointment.getReason();

        String hospId = Storage.getInstance().generateHospitalizationId(patient.getId());

        Hospitalization hospitalization = new Hospitalization(hospId, patient, doctor,
                date, reason, parsedRoomType, observations, HospitalizationStatus.ONGOING);

        appointment.setStatus(AppointmentStatus.COMPLETED);
        Storage.getInstance().addHospitalization(hospitalization);

        return new Response("Patient hospitalized successfully", Status.CREATED);
    }


}
