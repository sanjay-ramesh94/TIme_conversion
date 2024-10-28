package com.mycompany.time.time;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Reg extends javax.swing.JFrame {
    private String inputCountry;
    private String outputCountry;
    private String userInputTime;

    private javax.swing.JButton btnConvert;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtInputCountry;
    private javax.swing.JTextField txtInputTime;
    private javax.swing.JTextField txtOutputCountry;
    private javax.swing.JTextField txtConvertedTime;

    public Reg() {
        initComponents();
    }

    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtInputCountry = new javax.swing.JTextField();
        txtInputTime = new javax.swing.JTextField();
        txtOutputCountry = new javax.swing.JTextField();
        txtConvertedTime = new javax.swing.JTextField();
        btnConvert = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 24));
        jLabel1.setText("TIME CONVERSION");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Convert Time"));

        jLabel2.setText("Enter the Zone (e.g., India, UK, USA):");
        jLabel3.setText("Enter the Time (HH:mm):");
        jLabel4.setText("Enter the Zone to Convert To:");
        jLabel5.setText("Converted Time:");

        txtConvertedTime.setEditable(false);

        btnConvert.setText("CONVERT");
        btnConvert.addActionListener(evt -> convertTime(evt));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtInputCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtInputTime, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtOutputCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtConvertedTime, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnConvert))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtInputCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtInputTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtOutputCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txtConvertedTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(btnConvert)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1)
                    .addGap(18, 18, 18)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
        );

        pack();
    }

    private void convertTime(java.awt.event.ActionEvent evt) {
        inputCountry = txtInputCountry.getText().trim();
        userInputTime = txtInputTime.getText().trim();
        outputCountry = txtOutputCountry.getText().trim();

        Map<String, String> countryToTimeZoneMap = new HashMap<>();
        countryToTimeZoneMap.put("USA", "America/New_York");
        countryToTimeZoneMap.put("UK", "Europe/London");
        countryToTimeZoneMap.put("Japan", "Asia/Tokyo");
        countryToTimeZoneMap.put("Australia", "Australia/Sydney");
        countryToTimeZoneMap.put("India", "Asia/Kolkata");
        countryToTimeZoneMap.put("China", "Asia/Shanghai");
        countryToTimeZoneMap.put("Brazil", "America/Sao_Paulo");
        countryToTimeZoneMap.put("South Africa", "Africa/Johannesburg");
        countryToTimeZoneMap.put("France", "Europe/Paris");
        countryToTimeZoneMap.put("Germany", "Europe/Berlin");

        String inputTimeZone = countryToTimeZoneMap.get(inputCountry);
        String outputTimeZone = countryToTimeZoneMap.get(outputCountry);

        if (inputTimeZone == null || outputTimeZone == null) {
            txtConvertedTime.setText("Invalid country input. Please try again.");
            return;
        }

        try {
            LocalTime localTime = LocalTime.parse(userInputTime);
            ZoneId inputZoneId = ZoneId.of(inputTimeZone);
            ZonedDateTime inputZonedDateTime = ZonedDateTime.now(inputZoneId).with(localTime);

            ZoneId outputZoneId = ZoneId.of(outputTimeZone);
            ZonedDateTime outputZonedDateTime = inputZonedDateTime.withZoneSameInstant(outputZoneId);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm z");
            String convertedTime = outputZonedDateTime.format(formatter);
            txtConvertedTime.setText("Time in " + outputCountry + ": " + convertedTime);
            storeConversionLog(inputCountry, outputCountry, userInputTime, convertedTime);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Conversion Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void storeConversionLog(String inputCountry, String outputCountry, String inputTime, String convertedTime) {
        String insertSQL = "INSERT INTO time_conversions (input_country, output_country, input_time, converted_time, conversion_date) VALUES (?, ?, ?, ?, NOW())";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

            preparedStatement.setString(1, inputCountry);
            preparedStatement.setString(2, outputCountry);
            preparedStatement.setString(3, inputTime);
            preparedStatement.setString(4, convertedTime);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Initialize and display the form
        java.awt.EventQueue.invokeLater(() -> {
            new Reg().setVisible(true);
        });
    }
}
