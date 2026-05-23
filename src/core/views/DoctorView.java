package core.views;

import core.controllers.AppointmentController;
import core.controllers.DoctorController;
import core.controllers.HospitalizationController;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Appointment;
import core.models.AppointmentStatus;
import core.models.Doctor;
import core.models.Hospitalization;
import core.models.HospitalizationStatus;
import core.models.Patient;
import core.models.Specialty;
import core.models.User;
import core.models.storage.Storage;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class DoctorView extends javax.swing.JFrame {

    private int x, y;
    private HashMap<String, Object> userData;
    private HashMap<String, Object> adminData;
    private boolean isAdmin;

    public DoctorView(HashMap<String, Object> userData, boolean isAdmin, HashMap<String, Object> adminData) {
        initComponents();
        this.userData = userData;
        this.isAdmin = isAdmin;
        this.adminData = adminData;
        backButton.setEnabled(isAdmin);
        this.setBackground(new Color(0, 0, 0, 0));
        this.setLocationRelativeTo(null);
        loadUserData();
        loadAllComboBoxes();
    }

    private void loadUserData() {
        firstnameTextField.setText((String) userData.get("firstname"));
        lastnameTextField.setText((String) userData.get("lastname"));
        usernameTextField.setText((String) userData.get("username"));
        licenseTextField.setText((String) userData.get("licenceNumber"));
        assignedOfficeTextField.setText((String) userData.get("assignedOffice"));
        String specialty = (String) userData.get("specialty");
        specialtyComboBox.setSelectedItem(specialty);
    }

    private void loadAllComboBoxes() {
        String doctorId = String.valueOf(userData.get("id"));

        patientComboBox.removeAllItems();
        patientComboBox.addItem("Select one");
        hospPatientIdComboBox.removeAllItems();
        hospPatientIdComboBox.addItem("Select one");
        ArrayList<User> users = Storage.getInstance().getUsers();
        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            if (u instanceof Patient) {
                String item = u.getId() + " - " + u.getFirstname() + " " + u.getLastname();
                patientComboBox.addItem(item);
                hospPatientIdComboBox.addItem(item);
            }
        }

        acceptAppointmentComboBox.removeAllItems();
        acceptAppointmentComboBox.addItem("Select one");
        rescheduleAppointmentComboBox.removeAllItems();
        rescheduleAppointmentComboBox.addItem("Select one");
        completeAppointmentComboBox.removeAllItems();
        completeAppointmentComboBox.addItem("Select one");
        prescribeAppointmentComboBox.removeAllItems();
        prescribeAppointmentComboBox.addItem("Select one");

        Response resp = AppointmentController.getDoctorAppointments(doctorId, false);
        if (resp.getStatus() == Status.OK) {
            ArrayList<HashMap<String, Object>> appointments =
                    (ArrayList<HashMap<String, Object>>) resp.getData().get("appointments");
            for (HashMap<String, Object> a : appointments) {
                String id = (String) a.get("id");
                String status = (String) a.get("status");
                if (status.equals(AppointmentStatus.REQUESTED.name())) {
                    acceptAppointmentComboBox.addItem(id);
                }
                if (status.equals(AppointmentStatus.PENDING.name())) {
                    rescheduleAppointmentComboBox.addItem(id);
                    completeAppointmentComboBox.addItem(id);
                    prescribeAppointmentComboBox.addItem(id);
                }
            }
        }

        hospRequestComboBox.removeAllItems();
        hospRequestComboBox.addItem("Select one");
        long parsedDoctorId = Long.parseLong(doctorId);
        ArrayList<Hospitalization> hospitalizations = Storage.getInstance().getHospitalizations();
        for (int i = 0; i < hospitalizations.size(); i++) {
            Hospitalization h = hospitalizations.get(i);
            if (h.getDoctor().getId() == parsedDoctorId) {
                if (h.getStatus() == HospitalizationStatus.REQUESTED) {
                    hospRequestComboBox.addItem(h.getId());
                }
            }
        }
    }

    private void fillAppointmentsTable(boolean pendingOnly) {
        String doctorId = String.valueOf(userData.get("id"));
        Response response = AppointmentController.getDoctorAppointments(doctorId, pendingOnly);
        if (response.getStatus() == Status.OK) {
            DefaultTableModel model = (DefaultTableModel) appointmentsTable.getModel();
            model.setRowCount(0);
            ArrayList<HashMap<String, Object>> appointments =
                    (ArrayList<HashMap<String, Object>>) response.getData().get("appointments");
            for (HashMap<String, Object> a : appointments) {
                String typeStr;
                Object typeVal = a.get("type");
                if (Boolean.TRUE.equals(typeVal)) {
                    typeStr = "In-person";
                } else {
                    typeStr = "Remote";
                }
                model.addRow(new Object[]{
                    a.get("id"),
                    a.get("datetime"),
                    a.get("patientUsername"),
                    a.get("specialty"),
                    typeStr,
                    a.get("status")
                });
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound1 = new packagee.PanelRound();
        panelRound2 = new packagee.PanelRound();
        closeButton = new javax.swing.JButton();
        titleLabel = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        allAppointmentsRadioButton = new javax.swing.JRadioButton();
        appointmentsScrollPane = new javax.swing.JScrollPane();
        appointmentsTable = new javax.swing.JTable();
        pendingAppointmentsRadioButton = new javax.swing.JRadioButton();
        logoutButton = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        patientComboBox = new javax.swing.JComboBox<>();
        jLabel38 = new javax.swing.JLabel();
        patientHistoryScrollPane = new javax.swing.JScrollPane();
        patientHistoryTable = new javax.swing.JTable();
        searchPatientButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        firstnameTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lastnameTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        licenseTextField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        assignedOfficeTextField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        usernameTextField = new javax.swing.JTextField();
        passwordTextField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        confirmPasswordTextField = new javax.swing.JTextField();
        specialtyComboBox = new javax.swing.JComboBox<>();
        updateButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        acceptAppointmentComboBox = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        acceptButton = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        rescheduleAppointmentComboBox = new javax.swing.JComboBox<>();
        rescheduleButton = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        rescheduleNewTimeTextField = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        rescheduleReasonTextField = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        completeAppointmentComboBox = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        completeButton = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        hospEntryDateTextField = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        hospDurationTextField = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        hospObservationsScrollPane = new javax.swing.JScrollPane();
        hospObservationsTextArea = new javax.swing.JTextArea();
        generateHospButton = new javax.swing.JButton();
        hospRequestComboBox = new javax.swing.JComboBox<>();
        hospRequestsRadioButton = new javax.swing.JRadioButton();
        hospPatientIdRadioButton = new javax.swing.JRadioButton();
        diagnosisScrollPane = new javax.swing.JScrollPane();
        diagnosisTextArea = new javax.swing.JTextArea();
        observationsScrollPane = new javax.swing.JScrollPane();
        observationsTextArea = new javax.swing.JTextArea();
        treatmentScrollPane = new javax.swing.JScrollPane();
        treatmentTextArea = new javax.swing.JTextArea();
        followUpScrollPane = new javax.swing.JScrollPane();
        followUpTextArea = new javax.swing.JTextArea();
        jSeparator4 = new javax.swing.JSeparator();
        cancelHospButton = new javax.swing.JButton();
        hospPatientIdComboBox = new javax.swing.JComboBox<>();
        hospReasonScrollPane = new javax.swing.JScrollPane();
        hospReasonTextArea = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        medicationNameTextField = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        doseTextField = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        administrationRouteTextField = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        frequencyTextField = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        treatmentDurationTextField = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        additionalInstructionsTextField = new javax.swing.JTextField();
        prescriptionsScrollPane = new javax.swing.JScrollPane();
        prescriptionsTable = new javax.swing.JTable();
        addPrescriptionRowButton = new javax.swing.JButton();
        prescribeButton = new javax.swing.JButton();
        prescribeAppointmentComboBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelRound1.setRadius(50);

        panelRound2.setRadius(50);
        panelRound2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panelRound2MouseDragged(evt);
            }
        });
        panelRound2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelRound2MousePressed(evt);
            }
        });

        closeButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        closeButton.setText("X");
        closeButton.setBorderPainted(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        closeButton.setFocusable(false);
        closeButton.setRequestFocusEnabled(false);
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        titleLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        titleLabel.setText("DOCTOR VIEW");

        backButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel)
                .addGap(32, 32, 32)
                .addComponent(backButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(closeButton)
                .addGap(19, 19, 19))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(closeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(backButton))
        );

        // ── Tab: Appointments visualization ──────────────────────────────────

        allAppointmentsRadioButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        allAppointmentsRadioButton.setText("Total appointments");
        allAppointmentsRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allAppointmentsRadioButtonActionPerformed(evt);
            }
        });

        appointmentsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] { "ID", "Date", "Patient", "Specialty", "Type", "Status" }
        ) {
            boolean[] canEdit = new boolean [] { false, false, false, false, false, false };
            public boolean isCellEditable(int r, int c) { return canEdit[c]; }
        });
        appointmentsScrollPane.setViewportView(appointmentsTable);

        pendingAppointmentsRadioButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        pendingAppointmentsRadioButton.setText("Pending appointments");
        pendingAppointmentsRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pendingAppointmentsRadioButtonActionPerformed(evt);
            }
        });

        logoutButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        logoutButton.setText("Logout");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(logoutButton)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(16, 16, 16)
                            .addComponent(allAppointmentsRadioButton)
                            .addGap(18, 18, 18)
                            .addComponent(pendingAppointmentsRadioButton))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(108, 108, 108)
                            .addComponent(appointmentsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1035, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(152, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(allAppointmentsRadioButton)
                    .addComponent(pendingAppointmentsRadioButton))
                .addGap(18, 18, 18)
                .addComponent(appointmentsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(logoutButton)
                .addGap(23, 23, 23))
        );

        jTabbedPane1.addTab("Appointments visualization", jPanel4);

        // ── Tab: History Appointments of a patient ───────────────────────────

        patientComboBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        patientComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select one" }));

        jLabel38.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel38.setText("Patient");

        patientHistoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] { "ID", "Date", "Doctor", "Specialty", "Type", "Status" }
        ) {
            boolean[] canEdit = new boolean [] { false, false, false, false, false, false };
            public boolean isCellEditable(int r, int c) { return canEdit[c]; }
        });
        patientHistoryScrollPane.setViewportView(patientHistoryTable);

        searchPatientButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        searchPatientButton.setText("Search");
        searchPatientButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchPatientButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel38)
                        .addGap(18, 18, 18)
                        .addComponent(patientComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(patientHistoryScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(99, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(searchPatientButton)
                .addGap(601, 601, 601))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(patientComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(patientHistoryScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(searchPatientButton)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("History Appointments of a patient", jPanel5);

        // ── Tab: Modify info ─────────────────────────────────────────────────

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel2.setText("Firstname");
        firstnameTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel3.setText("Lastname");
        lastnameTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel5.setText("Specialty");

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel7.setText("License Number");
        licenseTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel8.setText("Assigned office");
        assignedOfficeTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("User");
        usernameTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Password");
        passwordTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel11.setText("Password confirmation");
        confirmPasswordTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        specialtyComboBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        javax.swing.DefaultComboBoxModel<String> specModel = new javax.swing.DefaultComboBoxModel<>();
        specModel.addElement("Select one");
        for (Specialty s : Specialty.values()) {
            specModel.addElement(s.name());
        }
        specialtyComboBox.setModel(specModel);

        updateButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        updateButton.setText("Save");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(211, 211, 211)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(firstnameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(lastnameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(specialtyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(351, 351, 351)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(licenseTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(assignedOfficeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(558, 558, 558)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(usernameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(521, 521, 521)
                        .addComponent(jLabel11))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(576, 576, 576)
                        .addComponent(updateButton))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(561, 561, 561)
                        .addComponent(confirmPasswordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(269, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(firstnameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(lastnameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(specialtyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(licenseTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(assignedOfficeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(30, 30, 30)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addGap(27, 27, 27)
                .addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(confirmPasswordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(updateButton)
                .addContainerGap(161, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Modify info", jPanel3);

        // ── Tab: Request/Appointments ────────────────────────────────────────

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Appointment ID");

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel13.setText("Accept medical appointment");

        acceptAppointmentComboBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        acceptAppointmentComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select one" }));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        acceptButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        acceptButton.setText("Accept");
        acceptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptButtonActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Reschedule medical appointment");

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Appointment");

        rescheduleAppointmentComboBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        rescheduleAppointmentComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select one" }));

        rescheduleButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        rescheduleButton.setText("Accept");
        rescheduleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rescheduleButtonActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("New time appointment");
        rescheduleNewTimeTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Reason for appointment");
        rescheduleReasonTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel19.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Complete medical appointment");

        jLabel20.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Appointment");

        completeAppointmentComboBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        completeAppointmentComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select one" }));

        jLabel21.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Diagnosis");

        jLabel22.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Observations");

        jLabel23.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Recommended treatment");

        jLabel24.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Follow-up indication");

        completeButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        completeButton.setText("Complete");
        completeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                completeButtonActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Hospitalization");

        jLabel27.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Reason for hospitalization");

        jLabel28.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Date of entry");
        hospEntryDateTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel29.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Estimated duration");
        hospDurationTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel30.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Observations");

        hospObservationsTextArea.setColumns(20);
        hospObservationsTextArea.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        hospObservationsTextArea.setRows(5);
        hospObservationsScrollPane.setViewportView(hospObservationsTextArea);

        generateHospButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        generateHospButton.setText("Generate");
        generateHospButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateHospButtonActionPerformed(evt);
            }
        });

        hospRequestComboBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        hospRequestComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select one" }));

        hospRequestsRadioButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        hospRequestsRadioButton.setText("Requests");

        hospPatientIdRadioButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        hospPatientIdRadioButton.setText("Patient ID");

        diagnosisTextArea.setColumns(20);
        diagnosisTextArea.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        diagnosisTextArea.setRows(5);
        diagnosisScrollPane.setViewportView(diagnosisTextArea);

        observationsTextArea.setColumns(20);
        observationsTextArea.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        observationsTextArea.setRows(5);
        observationsScrollPane.setViewportView(observationsTextArea);

        treatmentTextArea.setColumns(20);
        treatmentTextArea.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        treatmentTextArea.setRows(5);
        treatmentScrollPane.setViewportView(treatmentTextArea);

        followUpTextArea.setColumns(20);
        followUpTextArea.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        followUpTextArea.setRows(5);
        followUpScrollPane.setViewportView(followUpTextArea);

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);

        cancelHospButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        cancelHospButton.setText("Cancel");
        cancelHospButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelHospButtonActionPerformed(evt);
            }
        });

        hospPatientIdComboBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        hospPatientIdComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select one" }));

        hospReasonTextArea.setColumns(20);
        hospReasonTextArea.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        hospReasonTextArea.setRows(5);
        hospReasonScrollPane.setViewportView(hospReasonTextArea);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(acceptButton)
                                        .addGap(87, 87, 87))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(acceptAppointmentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(67, 67, 67))))
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel13)
                        .addGap(22, 22, 22)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(90, 90, 90)
                                    .addComponent(rescheduleAppointmentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(99, 99, 99)
                                    .addComponent(rescheduleNewTimeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(98, 98, 98)
                                    .addComponent(rescheduleReasonTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(112, 112, 112)
                                    .addComponent(rescheduleButton)))
                            .addGap(91, 91, 91))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(completeButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(99, 99, 99)
                                        .addComponent(completeAppointmentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 25, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(42, 42, 42)
                                        .addComponent(diagnosisScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(41, 41, 41)
                                        .addComponent(observationsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(42, 42, 42)
                                        .addComponent(treatmentScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(43, 43, 43)
                                        .addComponent(followUpScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(121, 121, 121)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(hospEntryDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(hospDurationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cancelHospButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(generateHospButton))
                            .addComponent(hospObservationsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(56, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(hospRequestComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(hospRequestsRadioButton)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(hospPatientIdRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(hospPatientIdComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(hospReasonScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel19)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel20)
                        .addGap(18, 18, 18)
                        .addComponent(completeAppointmentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel21)
                        .addGap(18, 18, 18)
                        .addComponent(diagnosisScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel22)
                        .addGap(18, 18, 18)
                        .addComponent(observationsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel23)
                        .addGap(18, 18, 18)
                        .addComponent(treatmentScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(followUpScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(completeButton)
                        .addGap(12, 12, 12))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(acceptAppointmentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(acceptButton))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel15)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel16)
                                .addGap(18, 18, 18)
                                .addComponent(rescheduleAppointmentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel17)
                                .addGap(18, 18, 18)
                                .addComponent(rescheduleNewTimeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel18)
                                .addGap(18, 18, 18)
                                .addComponent(rescheduleReasonTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(rescheduleButton)))
                        .addGap(18, 18, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel25)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hospRequestsRadioButton)
                    .addComponent(hospPatientIdRadioButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hospRequestComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hospPatientIdComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel27)
                .addGap(16, 16, 16)
                .addComponent(hospReasonScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel28)
                .addGap(18, 18, 18)
                .addComponent(hospEntryDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel29)
                .addGap(18, 18, 18)
                .addComponent(hospDurationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel30)
                .addGap(18, 18, 18)
                .addComponent(hospObservationsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(generateHospButton)
                    .addComponent(cancelHospButton))
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jTabbedPane1.addTab("Request/Appointments", jPanel1);

        // ── Tab: Prescribe medications ───────────────────────────────────────

        jLabel31.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel31.setText("Appointment ID");

        jLabel32.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel32.setText("Medication name");
        medicationNameTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel33.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel33.setText("Dose");
        doseTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel34.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel34.setText("Administration route");
        administrationRouteTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel35.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel35.setText("Frecuency");
        frequencyTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel36.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel36.setText("Treatment duration");
        treatmentDurationTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel37.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel37.setText("Additional instructions");
        additionalInstructionsTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        prescriptionsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Appointment ID", "Medication name", "Dose",
                "Administration route", "Treatment duration",
                "Additional instructions", "Frecuency"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] { false, false, false, false, false, false, false };
            public Class getColumnClass(int c) { return types[c]; }
            public boolean isCellEditable(int r, int c) { return canEdit[c]; }
        });
        prescriptionsScrollPane.setViewportView(prescriptionsTable);

        addPrescriptionRowButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        addPrescriptionRowButton.setText("Add");
        addPrescriptionRowButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPrescriptionRowButtonActionPerformed(evt);
            }
        });

        prescribeButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        prescribeButton.setText("Prescribe");
        prescribeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prescribeButtonActionPerformed(evt);
            }
        });

        prescribeAppointmentComboBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        prescribeAppointmentComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select one" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(prescriptionsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel31)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(prescribeAppointmentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(9, 9, 9)
                                        .addComponent(jLabel32))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel36)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(treatmentDurationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel37)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(additionalInstructionsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel35)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(frequencyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(medicationNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel33)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(doseTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel34)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(administrationRouteTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addPrescriptionRowButton))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(583, 583, 583)
                        .addComponent(prescribeButton)))
                .addContainerGap(108, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(jLabel32)
                    .addComponent(medicationNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33)
                    .addComponent(doseTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34)
                    .addComponent(administrationRouteTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addPrescriptionRowButton)
                    .addComponent(prescribeAppointmentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(treatmentDurationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37)
                    .addComponent(additionalInstructionsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35)
                    .addComponent(frequencyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(prescriptionsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(prescribeButton)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Prescribe medications", jPanel2);

        // ── panelRound1 layout ───────────────────────────────────────────────

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void panelRound2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelRound2MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_panelRound2MousePressed

    private void panelRound2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelRound2MouseDragged
        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);
    }//GEN-LAST:event_panelRound2MouseDragged

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_closeButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        if (isAdmin) {
            AdminView adminView = new AdminView(adminData);
            this.setVisible(false);
            adminView.setVisible(true);
        }
    }//GEN-LAST:event_backButtonActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        LoginView loginView = new LoginView();
        this.setVisible(false);
        loginView.setVisible(true);
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void allAppointmentsRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allAppointmentsRadioButtonActionPerformed
        pendingAppointmentsRadioButton.setSelected(false);
        fillAppointmentsTable(false);
    }//GEN-LAST:event_allAppointmentsRadioButtonActionPerformed

    private void pendingAppointmentsRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pendingAppointmentsRadioButtonActionPerformed
        allAppointmentsRadioButton.setSelected(false);
        fillAppointmentsTable(true);
    }//GEN-LAST:event_pendingAppointmentsRadioButtonActionPerformed

    private void searchPatientButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchPatientButtonActionPerformed
        String selected = (String) patientComboBox.getSelectedItem();
        String patientId = selected.split(" - ")[0];
        Response response = AppointmentController.getPatientAppointments(patientId);
        if (response.getStatus() == Status.OK) {
            DefaultTableModel model = (DefaultTableModel) patientHistoryTable.getModel();
            model.setRowCount(0);
            ArrayList<HashMap<String, Object>> appointments =
                    (ArrayList<HashMap<String, Object>>) response.getData().get("appointments");
            for (HashMap<String, Object> a : appointments) {
                String typeStr;
                Object typeVal = a.get("type");
                if (Boolean.TRUE.equals(typeVal)) {
                    typeStr = "In-person";
                } else {
                    typeStr = "Remote";
                }
                model.addRow(new Object[]{
                    a.get("id"),
                    a.get("datetime"),
                    a.get("doctorUsername"),
                    a.get("specialty"),
                    typeStr,
                    a.get("status")
                });
            }
        } else {
            JOptionPane.showMessageDialog(this, response.getMessage());
        }
    }//GEN-LAST:event_searchPatientButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        String doctorId = String.valueOf(userData.get("id"));
        Response response = DoctorController.updateDoctor(
                doctorId,
                usernameTextField.getText(),
                firstnameTextField.getText(),
                lastnameTextField.getText(),
                passwordTextField.getText(),
                confirmPasswordTextField.getText(),
                (String) specialtyComboBox.getSelectedItem(),
                licenseTextField.getText(),
                assignedOfficeTextField.getText()
        );
        JOptionPane.showMessageDialog(this, response.getMessage());
        if (response.getStatus() == Status.OK) {
            passwordTextField.setText("");
            confirmPasswordTextField.setText("");
        }
    }//GEN-LAST:event_updateButtonActionPerformed

    private void acceptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptButtonActionPerformed
        String appointmentId = (String) acceptAppointmentComboBox.getSelectedItem();
        String doctorId = String.valueOf(userData.get("id"));
        Response response = AppointmentController.acceptAppointment(appointmentId, doctorId);
        JOptionPane.showMessageDialog(this, response.getMessage());
        if (response.getStatus() == Status.OK) {
            loadAllComboBoxes();
        }
    }//GEN-LAST:event_acceptButtonActionPerformed

    private void rescheduleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rescheduleButtonActionPerformed
        String appointmentId = (String) rescheduleAppointmentComboBox.getSelectedItem();
        String doctorId = String.valueOf(userData.get("id"));
        Response response = AppointmentController.rescheduleAppointment(
                appointmentId,
                doctorId,
                rescheduleNewTimeTextField.getText(),
                rescheduleReasonTextField.getText()
        );
        JOptionPane.showMessageDialog(this, response.getMessage());
        if (response.getStatus() == Status.OK) {
            rescheduleNewTimeTextField.setText("");
            rescheduleReasonTextField.setText("");
        }
    }//GEN-LAST:event_rescheduleButtonActionPerformed

    private void completeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_completeButtonActionPerformed
        String appointmentId = (String) completeAppointmentComboBox.getSelectedItem();
        String doctorId = String.valueOf(userData.get("id"));
        Response response = AppointmentController.completeAppointment(appointmentId, doctorId);
        JOptionPane.showMessageDialog(this, response.getMessage());
        if (response.getStatus() == Status.OK) {
            Appointment appointment = Storage.getInstance().getAppointmentById(appointmentId);
            if (appointment != null) {
                appointment.setDiagnosis(diagnosisTextArea.getText());
                appointment.setObservations(observationsTextArea.getText());
                appointment.setRecommendedTreatment(treatmentTextArea.getText());
                appointment.setFollowUp(followUpTextArea.getText());
            }
            diagnosisTextArea.setText("");
            observationsTextArea.setText("");
            treatmentTextArea.setText("");
            followUpTextArea.setText("");
            loadAllComboBoxes();
        }
    }//GEN-LAST:event_completeButtonActionPerformed

    private void generateHospButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateHospButtonActionPerformed
        String doctorId = String.valueOf(userData.get("id"));
        Response response;
        if (hospRequestsRadioButton.isSelected()) {
            String hospId = (String) hospRequestComboBox.getSelectedItem();
            response = HospitalizationController.approveHospitalization(hospId, doctorId);
        } else {
            String selected = (String) hospPatientIdComboBox.getSelectedItem();
            String patientId = selected.split(" - ")[0];
            response = HospitalizationController.requestHospitalization(
                    patientId,
                    doctorId,
                    hospEntryDateTextField.getText(),
                    hospReasonTextArea.getText(),
                    "STANDARD",
                    hospObservationsTextArea.getText()
            );
        }
        JOptionPane.showMessageDialog(this, response.getMessage());
        if (response.getStatus() == Status.OK || response.getStatus() == Status.CREATED) {
            hospEntryDateTextField.setText("");
            hospReasonTextArea.setText("");
            hospObservationsTextArea.setText("");
            loadAllComboBoxes();
        }
    }//GEN-LAST:event_generateHospButtonActionPerformed

    private void cancelHospButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelHospButtonActionPerformed
        String doctorId = String.valueOf(userData.get("id"));
        String hospId = (String) hospRequestComboBox.getSelectedItem();
        Response response = HospitalizationController.cancelHospitalization(hospId, doctorId);
        JOptionPane.showMessageDialog(this, response.getMessage());
        if (response.getStatus() == Status.OK) {
            loadAllComboBoxes();
        }
    }//GEN-LAST:event_cancelHospButtonActionPerformed

    private void addPrescriptionRowButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPrescriptionRowButtonActionPerformed
        String appointmentId = (String) prescribeAppointmentComboBox.getSelectedItem();
        String doctorId = String.valueOf(userData.get("id"));
        Response response = AppointmentController.prescribeMedication(
                appointmentId,
                doctorId,
                medicationNameTextField.getText(),
                doseTextField.getText(),
                frequencyTextField.getText()
        );
        JOptionPane.showMessageDialog(this, response.getMessage());
        if (response.getStatus() == Status.CREATED) {
            DefaultTableModel model = (DefaultTableModel) prescriptionsTable.getModel();
            model.addRow(new Object[]{
                appointmentId,
                medicationNameTextField.getText(),
                doseTextField.getText(),
                administrationRouteTextField.getText(),
                treatmentDurationTextField.getText(),
                additionalInstructionsTextField.getText(),
                frequencyTextField.getText()
            });
            medicationNameTextField.setText("");
            doseTextField.setText("");
            administrationRouteTextField.setText("");
            frequencyTextField.setText("");
            treatmentDurationTextField.setText("");
            additionalInstructionsTextField.setText("");
        }
    }//GEN-LAST:event_addPrescriptionRowButtonActionPerformed

    private void prescribeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prescribeButtonActionPerformed
        DefaultTableModel model = (DefaultTableModel) prescriptionsTable.getModel();
        model.setRowCount(0);
    }//GEN-LAST:event_prescribeButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton acceptButton;
    private javax.swing.JComboBox<String> acceptAppointmentComboBox;
    private javax.swing.JTextField additionalInstructionsTextField;
    private javax.swing.JTextField administrationRouteTextField;
    private javax.swing.JRadioButton allAppointmentsRadioButton;
    private javax.swing.JScrollPane appointmentsScrollPane;
    private javax.swing.JTable appointmentsTable;
    private javax.swing.JButton addPrescriptionRowButton;
    private javax.swing.JButton backButton;
    private javax.swing.JButton cancelHospButton;
    private javax.swing.JButton closeButton;
    private javax.swing.JComboBox<String> completeAppointmentComboBox;
    private javax.swing.JButton completeButton;
    private javax.swing.JTextField confirmPasswordTextField;
    private javax.swing.JScrollPane diagnosisScrollPane;
    private javax.swing.JTextArea diagnosisTextArea;
    private javax.swing.JTextField doseTextField;
    private javax.swing.JTextField firstnameTextField;
    private javax.swing.JScrollPane followUpScrollPane;
    private javax.swing.JTextArea followUpTextArea;
    private javax.swing.JButton generateHospButton;
    private javax.swing.JTextField hospDurationTextField;
    private javax.swing.JTextField hospEntryDateTextField;
    private javax.swing.JScrollPane hospObservationsScrollPane;
    private javax.swing.JTextArea hospObservationsTextArea;
    private javax.swing.JComboBox<String> hospPatientIdComboBox;
    private javax.swing.JRadioButton hospPatientIdRadioButton;
    private javax.swing.JScrollPane hospReasonScrollPane;
    private javax.swing.JTextArea hospReasonTextArea;
    private javax.swing.JComboBox<String> hospRequestComboBox;
    private javax.swing.JRadioButton hospRequestsRadioButton;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField lastnameTextField;
    private javax.swing.JTextField licenseTextField;
    private javax.swing.JButton logoutButton;
    private javax.swing.JTextField medicationNameTextField;
    private javax.swing.JScrollPane observationsScrollPane;
    private javax.swing.JTextArea observationsTextArea;
    private javax.swing.JComboBox<String> patientComboBox;
    private javax.swing.JScrollPane patientHistoryScrollPane;
    private javax.swing.JTable patientHistoryTable;
    private javax.swing.JTextField passwordTextField;
    private javax.swing.JRadioButton pendingAppointmentsRadioButton;
    private javax.swing.JButton prescribeButton;
    private javax.swing.JComboBox<String> prescribeAppointmentComboBox;
    private javax.swing.JScrollPane prescriptionsScrollPane;
    private javax.swing.JTable prescriptionsTable;
    private javax.swing.JTextField frequencyTextField;
    private javax.swing.JButton rescheduleButton;
    private javax.swing.JComboBox<String> rescheduleAppointmentComboBox;
    private javax.swing.JTextField rescheduleNewTimeTextField;
    private javax.swing.JTextField rescheduleReasonTextField;
    private javax.swing.JButton searchPatientButton;
    private javax.swing.JComboBox<String> specialtyComboBox;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JScrollPane treatmentScrollPane;
    private javax.swing.JTextArea treatmentTextArea;
    private javax.swing.JTextField treatmentDurationTextField;
    private javax.swing.JButton updateButton;
    private javax.swing.JTextField usernameTextField;
    private javax.swing.JTextField assignedOfficeTextField;
    private packagee.PanelRound panelRound1;
    private packagee.PanelRound panelRound2;
    // End of variables declaration//GEN-END:variables
}
