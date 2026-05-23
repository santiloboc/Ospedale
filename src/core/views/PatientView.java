package core.views;

import core.controllers.AppointmentController;
import core.controllers.HospitalizationController;
import core.controllers.PatientController;
import core.controllers.UserController;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PatientView extends javax.swing.JFrame {

    private int x, y;
    private HashMap<String, Object> userData;
    private HashMap<String, Object> adminData;
    private boolean isAdmin;

    public PatientView(HashMap<String, Object> userData, boolean isAdmin, HashMap<String, Object> adminData) {
        initComponents();
        this.userData = userData;
        this.isAdmin = isAdmin;
        this.adminData = adminData;
        backButton.setEnabled(isAdmin);
        this.setBackground(new Color(0, 0, 0, 0));
        this.setLocationRelativeTo(null);
        loadUserData();
        loadDoctorComboBoxes();
        loadRoomTypeComboBox();
    }

    private void loadUserData() {
        firstnameTextField.setText((String) userData.get("firstname"));
        lastnameTextField.setText((String) userData.get("lastname"));
        emailTextField.setText((String) userData.get("email"));
        phoneTextField.setText(String.valueOf(userData.get("phone")));
        addressTextField.setText((String) userData.get("address"));
        birthdateTextField.setText((String) userData.get("birthdate"));
        usernameTextField.setText((String) userData.get("username"));
        String gender = (String) userData.get("gender");
        if (gender != null && gender.equals("Male")) {
            genderComboBox.setSelectedItem("Male");
        } else {
            genderComboBox.setSelectedItem("Female");
        }
    }

    private void loadDoctorComboBoxes() {
        hospDoctorComboBox.removeAllItems();
        hospDoctorComboBox.addItem("Select one");
        Response resp = UserController.getDoctors();
        if (resp.getStatus() == Status.OK) {
            ArrayList<HashMap<String, Object>> doctors = (ArrayList<HashMap<String, Object>>) resp.getData().get("doctors");
            for (HashMap<String, Object> d : doctors) {
                hospDoctorComboBox.addItem(d.get("id") + " - " + d.get("firstname") + " " + d.get("lastname"));
            }
        }
    }

    private void loadRoomTypeComboBox() {
        hospRoomTypeComboBox.removeAllItems();
        hospRoomTypeComboBox.addItem("Select one");
        Response resp = UserController.getRoomTypeNames();
        if (resp.getStatus() == Status.OK) {
            ArrayList<String> roomTypes = (ArrayList<String>) resp.getData().get("roomTypes");
            for (String rt : roomTypes) {
                hospRoomTypeComboBox.addItem(rt);
            }
        }
    }

    private void loadCancelComboBox() {
        cancelAppointmentComboBox.removeAllItems();
        cancelAppointmentComboBox.addItem("Select one");
        String patientId = String.valueOf(userData.get("id"));
        Response response = AppointmentController.getPatientAppointments(patientId);
        if (response.getStatus() == Status.OK) {
            ArrayList<HashMap<String, Object>> appointments =
                    (ArrayList<HashMap<String, Object>>) response.getData().get("appointments");
            for (HashMap<String, Object> a : appointments) {
                cancelAppointmentComboBox.addItem((String) a.get("id"));
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
        jPanel3 = new javax.swing.JPanel();
        appointmentsScrollPane = new javax.swing.JScrollPane();
        appointmentsTable = new javax.swing.JTable();
        refreshButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        firstnameTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lastnameTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        birthdateTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        emailTextField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        phoneTextField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        addressTextField = new javax.swing.JTextField();
        passwordTextField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        confirmPasswordTextField = new javax.swing.JTextField();
        updateButton = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        usernameTextField = new javax.swing.JTextField();
        genderComboBox = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        specialtyRadioButton = new javax.swing.JRadioButton();
        doctorRadioButton = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        appointmentDateTextField = new javax.swing.JTextField();
        appointmentTimeTextField = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        appointmentTypeComboBox = new javax.swing.JComboBox<>();
        createAppointmentButton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        hospDoctorComboBox = new javax.swing.JComboBox<>();
        hospDateTextField = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        hospRoomTypeComboBox = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        hospObservationsScrollPane = new javax.swing.JScrollPane();
        hospObservationsTextArea = new javax.swing.JTextArea();
        createHospitalizationButton = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        cancelObservationsScrollPane = new javax.swing.JScrollPane();
        cancelObservationsTextArea = new javax.swing.JTextArea();
        cancelAppointmentButton = new javax.swing.JButton();
        hospReasonScrollPane = new javax.swing.JScrollPane();
        hospReasonTextArea = new javax.swing.JTextArea();
        appointmentReasonScrollPane = new javax.swing.JScrollPane();
        appointmentReasonTextArea = new javax.swing.JTextArea();
        cancelAppointmentComboBox = new javax.swing.JComboBox<>();
        specialtyOrDoctorComboBox = new javax.swing.JComboBox<>();

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
        titleLabel.setText("PATIENT VIEW");

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
                .addGap(15, 15, 15)
                .addComponent(titleLabel)
                .addGap(29, 29, 29)
                .addComponent(backButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(closeButton)
                .addGap(19, 19, 19))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(closeButton))
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(titleLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        appointmentsTable.setAutoCreateRowSorter(true);
        appointmentsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Date", "Doctor", "Specialty", "Type", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        appointmentsScrollPane.setViewportView(appointmentsTable);

        refreshButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        refreshButton.setText("Refresh");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        logoutButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        logoutButton.setText("Logout");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(appointmentsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(602, 602, 602)
                .addComponent(refreshButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logoutButton)
                .addGap(78, 78, 78))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(appointmentsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(refreshButton)
                    .addComponent(logoutButton))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Appointment history", jPanel3);

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel2.setText("Firstname");

        firstnameTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel3.setText("Lastname");

        lastnameTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel5.setText("Birthdate");

        birthdateTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel6.setText("Gender");

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel7.setText("Email");

        emailTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel8.setText("Phone");

        phoneTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel9.setText("Address");

        addressTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        passwordTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel10.setText("Password");

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel11.setText("Password confirmation");

        confirmPasswordTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        updateButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        updateButton.setText("Save");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel12.setText("User");

        usernameTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        genderComboBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        genderComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select one", "Female", "Male" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(firstnameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(phoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(addressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lastnameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(birthdateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(genderComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(141, 141, 141))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(516, 516, 516)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(updateButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(confirmPasswordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel11)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel10))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(39, 39, 39)
                                    .addComponent(jLabel12)))
                            .addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(firstnameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(lastnameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(birthdateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(genderComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(phoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(addressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(confirmPasswordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(updateButton)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Modify info", jPanel1);

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel13.setText("Request medical appointment");

        specialtyRadioButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        specialtyRadioButton.setText("Specialty");
        specialtyRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                specialtyRadioButtonActionPerformed(evt);
            }
        });

        doctorRadioButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        doctorRadioButton.setText("Doctor");
        doctorRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doctorRadioButtonActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel14.setText("Appointment date");

        appointmentDateTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        appointmentTimeTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel15.setText("Appointment time");

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel16.setText("Appointment type");

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel17.setText("Appointment reason");

        appointmentTypeComboBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        appointmentTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select one", "Remote", "In-person" }));

        createAppointmentButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        createAppointmentButton.setText("Create");
        createAppointmentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createAppointmentButtonActionPerformed(evt);
            }
        });

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel18.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Request hospitalization");

        jLabel19.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Hospitalization reason");

        jLabel20.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Attending doctor");

        hospDoctorComboBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        hospDoctorComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select one" }));

        hospDateTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel21.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Estimated date of admission");
        jLabel21.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel22.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Desired room type");

        hospRoomTypeComboBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        hospRoomTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select one" }));

        jLabel23.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Observations");

        hospObservationsTextArea.setColumns(20);
        hospObservationsTextArea.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        hospObservationsTextArea.setRows(5);
        hospObservationsScrollPane.setViewportView(hospObservationsTextArea);

        createHospitalizationButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        createHospitalizationButton.setText("Create");
        createHospitalizationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createHospitalizationButtonActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel24.setText("Cancel appointment");

        jLabel25.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel25.setText("ID appointment");

        jLabel26.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel26.setText("Observations");

        cancelObservationsTextArea.setColumns(20);
        cancelObservationsTextArea.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        cancelObservationsTextArea.setRows(5);
        cancelObservationsScrollPane.setViewportView(cancelObservationsTextArea);

        cancelAppointmentButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        cancelAppointmentButton.setText("Cancel");
        cancelAppointmentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelAppointmentButtonActionPerformed(evt);
            }
        });

        hospReasonTextArea.setColumns(20);
        hospReasonTextArea.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        hospReasonTextArea.setRows(5);
        hospReasonScrollPane.setViewportView(hospReasonTextArea);

        appointmentReasonTextArea.setColumns(20);
        appointmentReasonTextArea.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        appointmentReasonTextArea.setRows(5);
        appointmentReasonScrollPane.setViewportView(appointmentReasonTextArea);

        cancelAppointmentComboBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        cancelAppointmentComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select one" }));

        specialtyOrDoctorComboBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        specialtyOrDoctorComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select one" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(44, 44, 44)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(specialtyRadioButton)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(doctorRadioButton))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(63, 63, 63)
                                    .addComponent(appointmentDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(47, 47, 47)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel15)
                                        .addComponent(jLabel14)
                                        .addComponent(specialtyOrDoctorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(63, 63, 63)
                                    .addComponent(appointmentTimeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(38, 38, 38)
                                    .addComponent(jLabel17))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(46, 46, 46)
                                    .addComponent(jLabel16))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(55, 55, 55)
                                    .addComponent(appointmentTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(42, 42, 42)
                            .addComponent(jLabel13)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(appointmentReasonScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(createAppointmentButton)))
                .addGap(69, 69, 69)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(211, 211, 211)
                            .addComponent(createHospitalizationButton))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(127, 127, 127)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(hospReasonScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addGap(127, 127, 127)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(hospObservationsScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(hospDoctorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(hospDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(191, 191, 191)
                        .addComponent(hospRoomTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cancelObservationsScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel24))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(77, 77, 77)
                                .addComponent(cancelAppointmentButton))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cancelAppointmentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel25)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(jLabel26)))
                        .addGap(49, 49, 49)))
                .addGap(81, 81, 81))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator2)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hospReasonScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel20)
                        .addGap(18, 18, 18)
                        .addComponent(hospDoctorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(hospDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel22)
                        .addGap(18, 18, 18)
                        .addComponent(hospRoomTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(hospObservationsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(createHospitalizationButton)
                        .addGap(15, 15, 15))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(specialtyRadioButton)
                                    .addComponent(doctorRadioButton))
                                .addGap(18, 18, 18)
                                .addComponent(specialtyOrDoctorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(appointmentDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)
                                .addComponent(jLabel15)
                                .addGap(18, 18, 18)
                                .addComponent(appointmentTimeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel17)
                                .addGap(24, 24, 24)
                                .addComponent(appointmentReasonScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addGap(39, 39, 39)
                                .addComponent(jLabel25)
                                .addGap(18, 18, 18)
                                .addComponent(cancelAppointmentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel26)
                                .addGap(18, 18, 18)
                                .addComponent(cancelObservationsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)
                                .addComponent(cancelAppointmentButton)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(appointmentTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(createAppointmentButton)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("Request/Cancel", jPanel2);

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1)
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

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        String patientId = String.valueOf(userData.get("id"));
        Response response = AppointmentController.getPatientAppointments(patientId);
        JOptionPane.showMessageDialog(this, response.getMessage());
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
                    a.get("doctorUsername"),
                    a.get("specialty"),
                    typeStr,
                    a.get("status")
                });
            }
            loadCancelComboBox();
        }
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        String patientId = String.valueOf(userData.get("id"));
        Response response = PatientController.updatePatient(
                patientId,
                usernameTextField.getText(),
                firstnameTextField.getText(),
                lastnameTextField.getText(),
                passwordTextField.getText(),
                confirmPasswordTextField.getText(),
                emailTextField.getText(),
                birthdateTextField.getText(),
                phoneTextField.getText(),
                addressTextField.getText(),
                (String) genderComboBox.getSelectedItem()
        );
        JOptionPane.showMessageDialog(this, response.getMessage());
        if (response.getStatus() == Status.OK) {
            passwordTextField.setText("");
            confirmPasswordTextField.setText("");
        }
    }//GEN-LAST:event_updateButtonActionPerformed

    private void specialtyRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_specialtyRadioButtonActionPerformed
        if (doctorRadioButton.isSelected()) {
            doctorRadioButton.setSelected(false);
        }
        specialtyOrDoctorComboBox.removeAllItems();
        specialtyOrDoctorComboBox.addItem("Select one");
        Response resp = UserController.getSpecialtyNames();
        if (resp.getStatus() == Status.OK) {
            ArrayList<String> specialties = (ArrayList<String>) resp.getData().get("specialties");
            for (String s : specialties) {
                specialtyOrDoctorComboBox.addItem(s);
            }
        }
    }//GEN-LAST:event_specialtyRadioButtonActionPerformed

    private void doctorRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doctorRadioButtonActionPerformed
        if (specialtyRadioButton.isSelected()) {
            specialtyRadioButton.setSelected(false);
        }
        specialtyOrDoctorComboBox.removeAllItems();
        specialtyOrDoctorComboBox.addItem("Select one");
        Response resp = UserController.getDoctors();
        if (resp.getStatus() == Status.OK) {
            ArrayList<HashMap<String, Object>> doctors = (ArrayList<HashMap<String, Object>>) resp.getData().get("doctors");
            for (HashMap<String, Object> d : doctors) {
                specialtyOrDoctorComboBox.addItem(d.get("id") + " - " + d.get("firstname") + " " + d.get("lastname"));
            }
        }
    }//GEN-LAST:event_doctorRadioButtonActionPerformed

    private void createAppointmentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createAppointmentButtonActionPerformed
        String patientId = String.valueOf(userData.get("id"));
        String selected = (String) specialtyOrDoctorComboBox.getSelectedItem();
        String doctorId = "";
        String specialty = "";

        if (doctorRadioButton.isSelected()) {
            String[] parts = selected.split(" - ");
            doctorId = parts[0];
            Response userResp = UserController.getUserById(doctorId);
            if (userResp.getStatus() == Status.OK) {
                HashMap<String, Object> doctorData = userResp.getData();
                if ("doctor".equals(doctorData.get("type"))) {
                    specialty = (String) doctorData.get("specialty");
                }
            }
        } else if (specialtyRadioButton.isSelected()) {
            specialty = selected;
        }

        String typeStr;
        String selectedType = (String) appointmentTypeComboBox.getSelectedItem();
        if (selectedType.equals("In-person")) {
            typeStr = "true";
        } else {
            typeStr = "false";
        }

        Response response = AppointmentController.requestAppointment(
                patientId,
                doctorId,
                specialty,
                appointmentDateTextField.getText(),
                appointmentTimeTextField.getText(),
                appointmentReasonTextArea.getText(),
                typeStr
        );
        JOptionPane.showMessageDialog(this, response.getMessage());
        if (response.getStatus() == Status.CREATED) {
            appointmentDateTextField.setText("");
            appointmentTimeTextField.setText("");
            appointmentReasonTextArea.setText("");
            appointmentTypeComboBox.setSelectedIndex(0);
            specialtyOrDoctorComboBox.setSelectedIndex(0);
        }
    }//GEN-LAST:event_createAppointmentButtonActionPerformed

    private void createHospitalizationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createHospitalizationButtonActionPerformed
        String patientId = String.valueOf(userData.get("id"));
        String selectedDoctor = (String) hospDoctorComboBox.getSelectedItem();
        String doctorId = selectedDoctor.split(" - ")[0];

        Response response = HospitalizationController.requestHospitalization(
                patientId,
                doctorId,
                hospDateTextField.getText(),
                hospReasonTextArea.getText(),
                (String) hospRoomTypeComboBox.getSelectedItem(),
                hospObservationsTextArea.getText()
        );
        JOptionPane.showMessageDialog(this, response.getMessage());
        if (response.getStatus() == Status.CREATED) {
            hospDateTextField.setText("");
            hospReasonTextArea.setText("");
            hospObservationsTextArea.setText("");
            hospDoctorComboBox.setSelectedIndex(0);
            hospRoomTypeComboBox.setSelectedIndex(0);
        }
    }//GEN-LAST:event_createHospitalizationButtonActionPerformed

    private void cancelAppointmentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelAppointmentButtonActionPerformed
        String appointmentId = (String) cancelAppointmentComboBox.getSelectedItem();
        String patientId = String.valueOf(userData.get("id"));
        Response response = AppointmentController.cancelAppointment(appointmentId, patientId);
        JOptionPane.showMessageDialog(this, response.getMessage());
        if (response.getStatus() == Status.OK) {
            cancelObservationsTextArea.setText("");
            loadCancelComboBox();
        }
    }//GEN-LAST:event_cancelAppointmentButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addressTextField;
    private javax.swing.JScrollPane appointmentReasonScrollPane;
    private javax.swing.JTextArea appointmentReasonTextArea;
    private javax.swing.JTextField appointmentDateTextField;
    private javax.swing.JTextField appointmentTimeTextField;
    private javax.swing.JComboBox<String> appointmentTypeComboBox;
    private javax.swing.JScrollPane appointmentsScrollPane;
    private javax.swing.JTable appointmentsTable;
    private javax.swing.JButton backButton;
    private javax.swing.JTextField birthdateTextField;
    private javax.swing.JComboBox<String> cancelAppointmentComboBox;
    private javax.swing.JButton cancelAppointmentButton;
    private javax.swing.JScrollPane cancelObservationsScrollPane;
    private javax.swing.JTextArea cancelObservationsTextArea;
    private javax.swing.JButton closeButton;
    private javax.swing.JTextField confirmPasswordTextField;
    private javax.swing.JButton createAppointmentButton;
    private javax.swing.JButton createHospitalizationButton;
    private javax.swing.JRadioButton doctorRadioButton;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JTextField firstnameTextField;
    private javax.swing.JComboBox<String> genderComboBox;
    private javax.swing.JTextField hospDateTextField;
    private javax.swing.JComboBox<String> hospDoctorComboBox;
    private javax.swing.JScrollPane hospObservationsScrollPane;
    private javax.swing.JTextArea hospObservationsTextArea;
    private javax.swing.JScrollPane hospReasonScrollPane;
    private javax.swing.JTextArea hospReasonTextArea;
    private javax.swing.JComboBox<String> hospRoomTypeComboBox;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField lastnameTextField;
    private javax.swing.JButton logoutButton;
    private javax.swing.JTextField passwordTextField;
    private javax.swing.JTextField phoneTextField;
    private javax.swing.JButton refreshButton;
    private javax.swing.JRadioButton specialtyRadioButton;
    private javax.swing.JComboBox<String> specialtyOrDoctorComboBox;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JButton updateButton;
    private javax.swing.JTextField usernameTextField;
    private packagee.PanelRound panelRound1;
    private packagee.PanelRound panelRound2;
    // End of variables declaration//GEN-END:variables
}
