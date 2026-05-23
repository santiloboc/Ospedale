package core.views;

import core.controllers.DoctorController;
import core.controllers.PatientController;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Doctor;
import core.models.Patient;
import core.models.Specialty;
import core.models.User;
import core.models.storage.Storage;
import java.awt.Color;
import java.util.HashMap;
import javax.swing.JOptionPane;

public class AdminView extends javax.swing.JFrame {

    private int x, y;
    private HashMap<String, Object> userData;

    public AdminView(HashMap<String, Object> userData) {
        initComponents();
        this.userData = userData;
        this.setBackground(new Color(0, 0, 0, 0));
        this.setLocationRelativeTo(null);
        loadSpecialtyComboBox();
        loadUserSelectors();
    }

    private void loadSpecialtyComboBox() {
        doctorSpecialtyComboBox.removeAllItems();
        for (Specialty s : Specialty.values()) {
            doctorSpecialtyComboBox.addItem(s.name());
        }
    }

    private void loadUserSelectors() {
        doctorSelectorComboBox.removeAllItems();
        patientSelectorComboBox.removeAllItems();
        for (User user : Storage.getInstance().getUsers()) {
            if (user instanceof Doctor) {
                doctorSelectorComboBox.addItem(user.getId() + " - " + user.getFirstname() + " " + user.getLastname());
            } else if (user instanceof Patient) {
                patientSelectorComboBox.addItem(user.getId() + " - " + user.getFirstname() + " " + user.getLastname());
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound1 = new packagee.PanelRound();
        panelRound2 = new packagee.PanelRound();
        adminTitleLabel = new javax.swing.JLabel();
        logoutButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();

        doctorRegisterPanel = new packagee.PanelRound();
        doctorFirstnameLabel = new javax.swing.JLabel();
        doctorFirstnameTextField = new javax.swing.JTextField();
        doctorLastnameLabel = new javax.swing.JLabel();
        doctorLastnameTextField = new javax.swing.JTextField();
        doctorIdLabel = new javax.swing.JLabel();
        doctorIdTextField = new javax.swing.JTextField();
        doctorSpecialtyLabel = new javax.swing.JLabel();
        doctorSpecialtyComboBox = new javax.swing.JComboBox<>();
        doctorLicenseLabel = new javax.swing.JLabel();
        doctorLicenseTextField = new javax.swing.JTextField();
        doctorOfficeLabel = new javax.swing.JLabel();
        doctorOfficeTextField = new javax.swing.JTextField();
        doctorUsernameLabel = new javax.swing.JLabel();
        doctorUsernameTextField = new javax.swing.JTextField();
        doctorPasswordLabel = new javax.swing.JLabel();
        doctorPasswordTextField = new javax.swing.JTextField();
        doctorConfirmPasswordLabel = new javax.swing.JLabel();
        doctorConfirmPasswordTextField = new javax.swing.JTextField();
        saveDoctorButton = new javax.swing.JButton();

        patientRegisterPanel = new packagee.PanelRound();
        patientFirstnameLabel = new javax.swing.JLabel();
        patientFirstnameTextField = new javax.swing.JTextField();
        patientLastnameLabel = new javax.swing.JLabel();
        patientLastnameTextField = new javax.swing.JTextField();
        patientIdLabel = new javax.swing.JLabel();
        patientIdTextField = new javax.swing.JTextField();
        patientUsernameLabel = new javax.swing.JLabel();
        patientUsernameTextField = new javax.swing.JTextField();
        patientEmailLabel = new javax.swing.JLabel();
        patientEmailTextField = new javax.swing.JTextField();
        patientBirthdateLabel = new javax.swing.JLabel();
        patientBirthdateTextField = new javax.swing.JTextField();
        patientPhoneLabel = new javax.swing.JLabel();
        patientPhoneTextField = new javax.swing.JTextField();
        patientAddressLabel = new javax.swing.JLabel();
        patientAddressTextField = new javax.swing.JTextField();
        patientPasswordLabel = new javax.swing.JLabel();
        patientPasswordTextField = new javax.swing.JTextField();
        patientConfirmPasswordLabel = new javax.swing.JLabel();
        patientConfirmPasswordTextField = new javax.swing.JTextField();
        patientGenderLabel = new javax.swing.JLabel();
        patientGenderComboBox = new javax.swing.JComboBox<>();
        savePatientButton = new javax.swing.JButton();

        viewUsersPanel = new packagee.PanelRound();
        doctorSelectorLabel = new javax.swing.JLabel();
        doctorSelectorComboBox = new javax.swing.JComboBox<>();
        viewDoctorButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        patientSelectorLabel = new javax.swing.JLabel();
        patientSelectorComboBox = new javax.swing.JComboBox<>();
        viewPatientButton = new javax.swing.JButton();

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

        adminTitleLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        adminTitleLabel.setText("ADMIN VIEW");

        logoutButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        logoutButton.setText("Logout");
        logoutButton.setBorderPainted(false);
        logoutButton.setContentAreaFilled(false);
        logoutButton.setFocusable(false);
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
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

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(adminTitleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logoutButton)
                .addGap(18, 18, 18)
                .addComponent(closeButton)
                .addGap(19, 19, 19))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(closeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(adminTitleLabel)
                .addComponent(logoutButton))
        );

        // ===== Register Doctor tab =====

        doctorFirstnameLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        doctorFirstnameLabel.setText("Firstname");

        doctorFirstnameTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        doctorLastnameLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        doctorLastnameLabel.setText("Lastname");

        doctorLastnameTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        doctorIdLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        doctorIdLabel.setText("ID");

        doctorIdTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        doctorSpecialtyLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        doctorSpecialtyLabel.setText("Specialty");

        doctorSpecialtyComboBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        doctorLicenseLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        doctorLicenseLabel.setText("License Number");

        doctorLicenseTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        doctorOfficeLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        doctorOfficeLabel.setText("Assigned Office");

        doctorOfficeTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        doctorUsernameLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        doctorUsernameLabel.setText("Username");

        doctorUsernameTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        doctorPasswordLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        doctorPasswordLabel.setText("Password");

        doctorPasswordTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        doctorConfirmPasswordLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        doctorConfirmPasswordLabel.setText("Confirm Password");

        doctorConfirmPasswordTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        saveDoctorButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        saveDoctorButton.setText("Register Doctor");
        saveDoctorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveDoctorButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout doctorRegisterPanelLayout = new javax.swing.GroupLayout(doctorRegisterPanel);
        doctorRegisterPanel.setLayout(doctorRegisterPanelLayout);
        doctorRegisterPanelLayout.setHorizontalGroup(
            doctorRegisterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(doctorRegisterPanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(doctorRegisterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(doctorRegisterPanelLayout.createSequentialGroup()
                        .addComponent(doctorFirstnameLabel)
                        .addGap(18, 18, 18)
                        .addComponent(doctorFirstnameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(doctorLastnameLabel)
                        .addGap(18, 18, 18)
                        .addComponent(doctorLastnameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(doctorIdLabel)
                        .addGap(18, 18, 18)
                        .addComponent(doctorIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(doctorRegisterPanelLayout.createSequentialGroup()
                        .addComponent(doctorSpecialtyLabel)
                        .addGap(18, 18, 18)
                        .addComponent(doctorSpecialtyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(doctorLicenseLabel)
                        .addGap(18, 18, 18)
                        .addComponent(doctorLicenseTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(doctorOfficeLabel)
                        .addGap(18, 18, 18)
                        .addComponent(doctorOfficeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(doctorRegisterPanelLayout.createSequentialGroup()
                        .addComponent(doctorUsernameLabel)
                        .addGap(18, 18, 18)
                        .addComponent(doctorUsernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(doctorRegisterPanelLayout.createSequentialGroup()
                        .addComponent(doctorPasswordLabel)
                        .addGap(18, 18, 18)
                        .addComponent(doctorPasswordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(doctorRegisterPanelLayout.createSequentialGroup()
                        .addComponent(doctorConfirmPasswordLabel)
                        .addGap(18, 18, 18)
                        .addComponent(doctorConfirmPasswordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(saveDoctorButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        doctorRegisterPanelLayout.setVerticalGroup(
            doctorRegisterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(doctorRegisterPanelLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(doctorRegisterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(doctorFirstnameLabel)
                    .addComponent(doctorFirstnameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(doctorLastnameLabel)
                    .addComponent(doctorLastnameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(doctorIdLabel)
                    .addComponent(doctorIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(doctorRegisterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(doctorSpecialtyLabel)
                    .addComponent(doctorSpecialtyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(doctorLicenseLabel)
                    .addComponent(doctorLicenseTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(doctorOfficeLabel)
                    .addComponent(doctorOfficeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(doctorRegisterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(doctorUsernameLabel)
                    .addComponent(doctorUsernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(doctorRegisterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(doctorPasswordLabel)
                    .addComponent(doctorPasswordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(doctorRegisterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(doctorConfirmPasswordLabel)
                    .addComponent(doctorConfirmPasswordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(saveDoctorButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Register Doctor", doctorRegisterPanel);

        // ===== Register Patient tab =====

        patientFirstnameLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        patientFirstnameLabel.setText("Firstname");

        patientFirstnameTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        patientLastnameLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        patientLastnameLabel.setText("Lastname");

        patientLastnameTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        patientIdLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        patientIdLabel.setText("ID");

        patientIdTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        patientUsernameLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        patientUsernameLabel.setText("Username");

        patientUsernameTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        patientEmailLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        patientEmailLabel.setText("Email");

        patientEmailTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        patientBirthdateLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        patientBirthdateLabel.setText("Birthdate (YYYY-MM-DD)");

        patientBirthdateTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        patientPhoneLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        patientPhoneLabel.setText("Phone");

        patientPhoneTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        patientAddressLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        patientAddressLabel.setText("Address");

        patientAddressTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        patientPasswordLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        patientPasswordLabel.setText("Password");

        patientPasswordTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        patientConfirmPasswordLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        patientConfirmPasswordLabel.setText("Confirm Password");

        patientConfirmPasswordTextField.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        patientGenderLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        patientGenderLabel.setText("Gender");

        patientGenderComboBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        patientGenderComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Male", "Female"}));

        savePatientButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        savePatientButton.setText("Register Patient");
        savePatientButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savePatientButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout patientRegisterPanelLayout = new javax.swing.GroupLayout(patientRegisterPanel);
        patientRegisterPanel.setLayout(patientRegisterPanelLayout);
        patientRegisterPanelLayout.setHorizontalGroup(
            patientRegisterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patientRegisterPanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(patientRegisterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(patientRegisterPanelLayout.createSequentialGroup()
                        .addComponent(patientFirstnameLabel)
                        .addGap(18, 18, 18)
                        .addComponent(patientFirstnameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(patientLastnameLabel)
                        .addGap(18, 18, 18)
                        .addComponent(patientLastnameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(patientIdLabel)
                        .addGap(18, 18, 18)
                        .addComponent(patientIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(patientRegisterPanelLayout.createSequentialGroup()
                        .addComponent(patientUsernameLabel)
                        .addGap(18, 18, 18)
                        .addComponent(patientUsernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(patientEmailLabel)
                        .addGap(18, 18, 18)
                        .addComponent(patientEmailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(patientRegisterPanelLayout.createSequentialGroup()
                        .addComponent(patientBirthdateLabel)
                        .addGap(18, 18, 18)
                        .addComponent(patientBirthdateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(patientPhoneLabel)
                        .addGap(18, 18, 18)
                        .addComponent(patientPhoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(patientRegisterPanelLayout.createSequentialGroup()
                        .addComponent(patientAddressLabel)
                        .addGap(18, 18, 18)
                        .addComponent(patientAddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(patientRegisterPanelLayout.createSequentialGroup()
                        .addComponent(patientPasswordLabel)
                        .addGap(18, 18, 18)
                        .addComponent(patientPasswordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(patientConfirmPasswordLabel)
                        .addGap(18, 18, 18)
                        .addComponent(patientConfirmPasswordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(patientRegisterPanelLayout.createSequentialGroup()
                        .addComponent(patientGenderLabel)
                        .addGap(18, 18, 18)
                        .addComponent(patientGenderComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(savePatientButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        patientRegisterPanelLayout.setVerticalGroup(
            patientRegisterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patientRegisterPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(patientRegisterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(patientFirstnameLabel)
                    .addComponent(patientFirstnameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(patientLastnameLabel)
                    .addComponent(patientLastnameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(patientIdLabel)
                    .addComponent(patientIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(patientRegisterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(patientUsernameLabel)
                    .addComponent(patientUsernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(patientEmailLabel)
                    .addComponent(patientEmailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(patientRegisterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(patientBirthdateLabel)
                    .addComponent(patientBirthdateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(patientPhoneLabel)
                    .addComponent(patientPhoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(patientRegisterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(patientAddressLabel)
                    .addComponent(patientAddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(patientRegisterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(patientPasswordLabel)
                    .addComponent(patientPasswordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(patientConfirmPasswordLabel)
                    .addComponent(patientConfirmPasswordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(patientRegisterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(patientGenderLabel)
                    .addComponent(patientGenderComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(savePatientButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Register Patient", patientRegisterPanel);

        // ===== View Users tab =====

        doctorSelectorLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        doctorSelectorLabel.setText("Doctor");

        doctorSelectorComboBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        viewDoctorButton.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        viewDoctorButton.setText("DOCTOR VIEW");
        viewDoctorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewDoctorButtonActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        patientSelectorLabel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        patientSelectorLabel.setText("Patient");

        patientSelectorComboBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        viewPatientButton.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        viewPatientButton.setText("PATIENT VIEW");
        viewPatientButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewPatientButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout viewUsersPanelLayout = new javax.swing.GroupLayout(viewUsersPanel);
        viewUsersPanel.setLayout(viewUsersPanelLayout);
        viewUsersPanelLayout.setHorizontalGroup(
            viewUsersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewUsersPanelLayout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addGroup(viewUsersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(doctorSelectorLabel)
                    .addComponent(doctorSelectorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewDoctorButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(viewUsersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(patientSelectorLabel)
                    .addComponent(patientSelectorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewPatientButton))
                .addGap(150, 150, 150))
        );
        viewUsersPanelLayout.setVerticalGroup(
            viewUsersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewUsersPanelLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(viewUsersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(doctorSelectorLabel)
                    .addComponent(patientSelectorLabel))
                .addGap(18, 18, 18)
                .addGroup(viewUsersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(doctorSelectorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(patientSelectorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(viewUsersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewDoctorButton)
                    .addComponent(viewPatientButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(viewUsersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
        );

        jTabbedPane1.addTab("View Users", viewUsersPanel);

        // ===== Outer layout =====

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1028, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
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

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        LoginView loginView = new LoginView();
        this.setVisible(false);
        loginView.setVisible(true);
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void saveDoctorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveDoctorButtonActionPerformed
        String id = doctorIdTextField.getText();
        String username = doctorUsernameTextField.getText();
        String firstname = doctorFirstnameTextField.getText();
        String lastname = doctorLastnameTextField.getText();
        String password = doctorPasswordTextField.getText();
        String confirmPassword = doctorConfirmPasswordTextField.getText();
        String specialty = (String) doctorSpecialtyComboBox.getSelectedItem();
        String licenceNumber = doctorLicenseTextField.getText();
        String assignedOffice = doctorOfficeTextField.getText();

        Response response = DoctorController.registerDoctor(id, username, firstname, lastname,
                password, confirmPassword, specialty, licenceNumber, assignedOffice);
        JOptionPane.showMessageDialog(this, response.getMessage());
        if (response.getStatus() == Status.CREATED) {
            doctorIdTextField.setText("");
            doctorFirstnameTextField.setText("");
            doctorLastnameTextField.setText("");
            doctorUsernameTextField.setText("");
            doctorPasswordTextField.setText("");
            doctorConfirmPasswordTextField.setText("");
            doctorLicenseTextField.setText("");
            doctorOfficeTextField.setText("");
            loadUserSelectors();
        }
    }//GEN-LAST:event_saveDoctorButtonActionPerformed

    private void savePatientButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savePatientButtonActionPerformed
        String id = patientIdTextField.getText();
        String username = patientUsernameTextField.getText();
        String firstname = patientFirstnameTextField.getText();
        String lastname = patientLastnameTextField.getText();
        String password = patientPasswordTextField.getText();
        String confirmPassword = patientConfirmPasswordTextField.getText();
        String email = patientEmailTextField.getText();
        String birthdate = patientBirthdateTextField.getText();
        String phone = patientPhoneTextField.getText();
        String address = patientAddressTextField.getText();
        String gender = (String) patientGenderComboBox.getSelectedItem();

        Response response = PatientController.registerPatient(id, username, firstname, lastname,
                password, confirmPassword, email, birthdate, phone, address, gender);
        JOptionPane.showMessageDialog(this, response.getMessage());
        if (response.getStatus() == Status.CREATED) {
            patientIdTextField.setText("");
            patientFirstnameTextField.setText("");
            patientLastnameTextField.setText("");
            patientUsernameTextField.setText("");
            patientPasswordTextField.setText("");
            patientConfirmPasswordTextField.setText("");
            patientEmailTextField.setText("");
            patientBirthdateTextField.setText("");
            patientPhoneTextField.setText("");
            patientAddressTextField.setText("");
            loadUserSelectors();
        }
    }//GEN-LAST:event_savePatientButtonActionPerformed

    private void viewDoctorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewDoctorButtonActionPerformed
        String selected = (String) doctorSelectorComboBox.getSelectedItem();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "No doctor selected");
            return;
        }
        String idStr = selected.split(" - ")[0];
        long doctorId;
        try {
            doctorId = Long.parseLong(idStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid doctor ID");
            return;
        }
        User doctorUser = Storage.getInstance().getUserById(doctorId);
        if (doctorUser == null) {
            JOptionPane.showMessageDialog(this, "Doctor not found");
            return;
        }
        if (!(doctorUser instanceof Doctor)) {
            JOptionPane.showMessageDialog(this, "Selected user is not a doctor");
            return;
        }
        HashMap<String, Object> doctorData = doctorUser.serialize();
        DoctorView doctorView = new DoctorView(doctorData, true, userData);
        this.setVisible(false);
        doctorView.setVisible(true);
    }//GEN-LAST:event_viewDoctorButtonActionPerformed

    private void viewPatientButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewPatientButtonActionPerformed
        String selected = (String) patientSelectorComboBox.getSelectedItem();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "No patient selected");
            return;
        }
        String idStr = selected.split(" - ")[0];
        long patientId;
        try {
            patientId = Long.parseLong(idStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid patient ID");
            return;
        }
        User patientUser = Storage.getInstance().getUserById(patientId);
        if (patientUser == null) {
            JOptionPane.showMessageDialog(this, "Patient not found");
            return;
        }
        if (!(patientUser instanceof Patient)) {
            JOptionPane.showMessageDialog(this, "Selected user is not a patient");
            return;
        }
        HashMap<String, Object> patientData = patientUser.serialize();
        PatientView patientView = new PatientView(patientData, true, userData);
        this.setVisible(false);
        patientView.setVisible(true);
    }//GEN-LAST:event_viewPatientButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel adminTitleLabel;
    private javax.swing.JButton closeButton;
    private javax.swing.JLabel doctorConfirmPasswordLabel;
    private javax.swing.JTextField doctorConfirmPasswordTextField;
    private javax.swing.JLabel doctorFirstnameLabel;
    private javax.swing.JTextField doctorFirstnameTextField;
    private javax.swing.JLabel doctorIdLabel;
    private javax.swing.JTextField doctorIdTextField;
    private javax.swing.JLabel doctorLastnameLabel;
    private javax.swing.JTextField doctorLastnameTextField;
    private javax.swing.JLabel doctorLicenseLabel;
    private javax.swing.JTextField doctorLicenseTextField;
    private javax.swing.JLabel doctorOfficeLabel;
    private javax.swing.JTextField doctorOfficeTextField;
    private javax.swing.JLabel doctorPasswordLabel;
    private javax.swing.JTextField doctorPasswordTextField;
    private packagee.PanelRound doctorRegisterPanel;
    private javax.swing.JComboBox<String> doctorSelectorComboBox;
    private javax.swing.JLabel doctorSelectorLabel;
    private javax.swing.JComboBox<String> doctorSpecialtyComboBox;
    private javax.swing.JLabel doctorSpecialtyLabel;
    private javax.swing.JLabel doctorUsernameLabel;
    private javax.swing.JTextField doctorUsernameTextField;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton logoutButton;
    private javax.swing.JLabel patientAddressLabel;
    private javax.swing.JTextField patientAddressTextField;
    private javax.swing.JLabel patientBirthdateLabel;
    private javax.swing.JTextField patientBirthdateTextField;
    private javax.swing.JLabel patientConfirmPasswordLabel;
    private javax.swing.JTextField patientConfirmPasswordTextField;
    private javax.swing.JLabel patientEmailLabel;
    private javax.swing.JTextField patientEmailTextField;
    private javax.swing.JLabel patientFirstnameLabel;
    private javax.swing.JTextField patientFirstnameTextField;
    private javax.swing.JComboBox<String> patientGenderComboBox;
    private javax.swing.JLabel patientGenderLabel;
    private javax.swing.JLabel patientIdLabel;
    private javax.swing.JTextField patientIdTextField;
    private javax.swing.JLabel patientLastnameLabel;
    private javax.swing.JTextField patientLastnameTextField;
    private javax.swing.JLabel patientPasswordLabel;
    private javax.swing.JTextField patientPasswordTextField;
    private javax.swing.JLabel patientPhoneLabel;
    private javax.swing.JTextField patientPhoneTextField;
    private packagee.PanelRound patientRegisterPanel;
    private javax.swing.JComboBox<String> patientSelectorComboBox;
    private javax.swing.JLabel patientSelectorLabel;
    private javax.swing.JLabel patientUsernameLabel;
    private javax.swing.JTextField patientUsernameTextField;
    private packagee.PanelRound panelRound1;
    private packagee.PanelRound panelRound2;
    private javax.swing.JButton saveDoctorButton;
    private javax.swing.JButton savePatientButton;
    private javax.swing.JButton viewDoctorButton;
    private javax.swing.JButton viewPatientButton;
    private packagee.PanelRound viewUsersPanel;
    // End of variables declaration//GEN-END:variables
}
