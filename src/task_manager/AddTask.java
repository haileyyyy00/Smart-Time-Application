package Task_Manager;

import static Task_Manager.TaskInfo.filename;
import User.UserLogin;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import sta.STA_Design;
import sta.STA_Interface;

public class AddTask extends javax.swing.JFrame {

    UserLogin login = new UserLogin();
    int uID = login.uID;

    public AddTask() {
        initComponents();
      
        //set Frame
        setLocationRelativeTo(null); //set frame to center
        STA_Design design = new STA_Design();
        int x = design.frameWidth;
        int y = design.frameHeight;
        setMaximumSize(new Dimension(x, y));
        setMinimumSize(new Dimension(x, y));
        setPreferredSize(new Dimension(x, y));
        jPanel1.setBackground(new Color(153, 153, 153)); //Grey Color Background
        
        //Format button icons
        design.scaleIcon(btnAdd, "Icon\\Add Icon.png", 35, 30);
        design.scaleIcon(btnReset, "Icon\\Reset Icon.png", 35, 30);
        design.scaleIcon(btnTaskList, "Icon\\TaskManager Pic.png", 35, 30);
        design.scaleIcon(btnHome, "Icon\\home.png", 35, 30);
      
      
         // Format tools
        Font newFont = new Font("Arial Rounded MT Bold", Font.PLAIN, 18);
        txtTaskName.setFont(newFont);
        txtareaDetails.setFont(newFont);
        cmbCategory.setFont(newFont);
        cmbImportance.setFont(newFont);
        startDate_chooser.setFont(newFont);
        endDate_chooser.setFont(newFont);
        spinnerStartTime.setFont(newFont);
        spinnerEndTime.setFont(newFont);
        

    }    
    


    public class TaskAttribute {

        String taskName = txtTaskName.getText().trim();
        String taskDetails = txtareaDetails.getText().trim();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String startDate = dateFormat.format(startDate_chooser.getDate()).toString().trim();
        String endDate = dateFormat.format(endDate_chooser.getDate()).toString().trim();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String startTime = timeFormat.format(spinnerStartTime.getValue()).toString().trim();
        String endTime = timeFormat.format(spinnerEndTime.getValue()).toString().trim();
        String importance = cmbImportance.getSelectedItem().toString().trim();
        String category = cmbCategory.getSelectedItem().toString().trim();
        String status = "Incomplete";
        String userID = String.valueOf(uID); //declared as String, easier to write into file

    }

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnTaskList = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        Date date = new Date();
        SpinnerDateModel smStart = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
        spinnerStartTime = new javax.swing.JSpinner(smStart);
        SpinnerDateModel smEnd = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
        spinnerEndTime = new javax.swing.JSpinner(smEnd);
        lblNewTask = new javax.swing.JLabel();
        cmbImportance = new javax.swing.JComboBox<>();
        lblTaskName = new javax.swing.JLabel();
        cmbCategory = new javax.swing.JComboBox<>();
        lblTaskDetails = new javax.swing.JLabel();
        lblStartDate = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtareaDetails = new javax.swing.JTextArea();
        lblEndTime = new javax.swing.JLabel();
        lblImportance = new javax.swing.JLabel();
        lblCategory = new javax.swing.JLabel();
        txtTaskName = new javax.swing.JTextField();
        try{
            endDate_chooser = new com.toedter.calendar.JDateChooser();
            try{
                startDate_chooser = new com.toedter.calendar.JDateChooser();
                lblEndDate = new javax.swing.JLabel();
                lblStartTime = new javax.swing.JLabel();
                btnHome = new javax.swing.JButton();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                btnTaskList.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 15)); // NOI18N
                btnTaskList.setText("Task List");
                btnTaskList.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnTaskListActionPerformed(evt);
                    }
                });

                btnReset.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 15)); // NOI18N
                btnReset.setText("Reset");
                btnReset.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnResetActionPerformed(evt);
                    }
                });

                btnAdd.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 15)); // NOI18N
                btnAdd.setText("Add");
                btnAdd.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnAddActionPerformed(evt);
                    }
                });

                JSpinner.DateEditor stimeEditor = new JSpinner.DateEditor(spinnerStartTime, "HH:mm"); //spinnername, applied format
                spinnerStartTime.setEditor(stimeEditor);

                JSpinner.DateEditor etimeEditor = new JSpinner.DateEditor(spinnerEndTime, "HH:mm"); //spinnername, applied format
                spinnerEndTime.setEditor(etimeEditor);

                lblNewTask.setFont(new java.awt.Font("Cooper Black", 1, 40)); // NOI18N
                lblNewTask.setText("Add Task");

                cmbImportance.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Low", "Medium", "Important!" }));

                lblTaskName.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 20)); // NOI18N
                lblTaskName.setText("Task Name");

                cmbCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Leisure","Works","Studies","Health and Fitness","Social","Family"}));

                lblTaskDetails.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 20)); // NOI18N
                lblTaskDetails.setText("Details");

                lblStartDate.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 20)); // NOI18N
                lblStartDate.setText("Start Date");

                txtareaDetails.setColumns(20);
                txtareaDetails.setRows(5);
                jScrollPane1.setViewportView(txtareaDetails);

                lblEndTime.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 20)); // NOI18N
                lblEndTime.setText("End Time");

                lblImportance.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 20)); // NOI18N
                lblImportance.setText("Importance");

                lblCategory.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 20)); // NOI18N
                lblCategory.setText("Category");

                endDate_chooser.setDateFormatString("yyyy-MM-dd");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date curDate = new Date();
                String strCurDate = dateFormat.format(curDate);
                try{
                    endDate_chooser.setDate(dateFormat.parse(strCurDate));
                }catch(ParseException pe){
                    JOptionPane.showMessageDialog(null, pe);
                }
            }catch(NumberFormatException ne){
                JOptionPane.showMessageDialog(null, ne);
            }

            startDate_chooser.setDateFormatString("yyyy-MM-dd");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date curDate = new Date();
            String strCurDate = dateFormat.format(curDate);
            try{
                startDate_chooser.setDate(dateFormat.parse(strCurDate));
            }catch(ParseException pe){
                JOptionPane.showMessageDialog(null, pe);
            }
        }catch(NumberFormatException ne){
            JOptionPane.showMessageDialog(null, ne);
        }

        lblEndDate.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 20)); // NOI18N
        lblEndDate.setText("End Date");

        lblStartTime.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 20)); // NOI18N
        lblStartTime.setText("Start Time");

        btnHome.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 15)); // NOI18N
        btnHome.setText("Home");
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(379, 379, 379)
                .addComponent(lblNewTask)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(129, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblCategory)
                        .addGap(72, 72, 72)
                        .addComponent(cmbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(lblImportance)
                        .addGap(51, 51, 51)
                        .addComponent(cmbImportance, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTaskDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblEndTime)
                                    .addComponent(lblStartTime)
                                    .addComponent(lblTaskName)
                                    .addComponent(lblEndDate)
                                    .addComponent(lblStartDate))
                                .addGap(54, 54, 54)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(endDate_chooser, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(startDate_chooser, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtTaskName)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(spinnerStartTime, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(spinnerEndTime, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addGap(1, 1, 1)
                                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(106, 106, 106)
                                        .addComponent(btnTaskList, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(18, 18, 18)
                        .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(117, 117, 117))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(lblNewTask, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTaskName)
                    .addComponent(lblTaskName, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(lblTaskDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(startDate_chooser, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(endDate_chooser, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(spinnerStartTime, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblStartDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(22, 22, 22)
                        .addComponent(lblEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(lblStartTime)))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(spinnerEndTime, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEndTime))
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(lblCategory))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cmbImportance, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGap(8, 8, 8)
                            .addComponent(lblImportance)))
                    .addComponent(cmbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(88, 88, 88)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTaskList, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(292, 292, 292)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(296, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        Date startDate = startDate_chooser.getDate();
        Date endDate = endDate_chooser.getDate();

        Data_Layer taskData = new Data_Layer();
        boolean check = taskData.checkDateTime(startDate, endDate);
        if (check == false) {
            JOptionPane.showMessageDialog(null, "The startDate is after the endDate!");
        } else {
            try {

                TaskAttribute ta = new TaskAttribute();

                if (ta.taskName.isEmpty() || ta.taskDetails.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Failed, please fill up blanks!");
                } else {
                    //Data_Layer taskData = new Data_Layer();
                    ArrayList<TaskInfo> taskList = taskData.getTaskList();
                    ArrayList<String> tempArray = new ArrayList<String>();

                    for (int i = 0; i < taskList.size(); i++) {
                        TaskInfo task = taskList.get(i);
                        tempArray.add(task.toString());
                    }
                      
                    int nextRowNumber = taskList.get(taskList.size()-1).getTaskID() + 1;//get last row ID + 1
                    tempArray.add(String.valueOf(nextRowNumber) + " // "
                            + ta.taskName + " // "
                            + ta.taskDetails + " // "
                            + ta.startDate + " // "
                            + ta.endDate + " // "
                            + ta.startTime + " // "
                            + ta.endTime + " // "
                            + ta.importance + " // "
                            + ta.category + " // "
                            + ta.status + " // "
                            + ta.userID);

                    if (tempArray.size() > 0) {
                        taskData.Update(tempArray, filename);
                        JOptionPane.showMessageDialog(null, "Successfully Inserted!");

                    }

                }
            } catch (NumberFormatException ne) { 
                JOptionPane.showMessageDialog(null, ne);
            }
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        try {
            txtTaskName.setText("");
            txtareaDetails.setText("");

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date curDate = new Date();
            String strCurDate = dateFormat.format(curDate);
            startDate_chooser.setDate(dateFormat.parse(strCurDate));
            endDate_chooser.setDate(dateFormat.parse(strCurDate));

            cmbImportance.setSelectedIndex(0);
            cmbCategory.setSelectedIndex(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnTaskListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaskListActionPerformed
        setVisible(false);
        TaskList obj = new TaskList();
        obj.setVisible(true);
    }//GEN-LAST:event_btnTaskListActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        setVisible(false);
        STA_Interface obj = new STA_Interface();
        obj.setVisible(true);
    }//GEN-LAST:event_btnHomeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddTask.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddTask.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddTask.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddTask.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddTask().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnTaskList;
    private javax.swing.JComboBox<String> cmbCategory;
    private javax.swing.JComboBox<String> cmbImportance;
    private com.toedter.calendar.JDateChooser endDate_chooser;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCategory;
    private javax.swing.JLabel lblEndDate;
    private javax.swing.JLabel lblEndTime;
    private javax.swing.JLabel lblImportance;
    private javax.swing.JLabel lblNewTask;
    private javax.swing.JLabel lblStartDate;
    private javax.swing.JLabel lblStartTime;
    private javax.swing.JLabel lblTaskDetails;
    private javax.swing.JLabel lblTaskName;
    private javax.swing.JSpinner spinnerEndTime;
    private javax.swing.JSpinner spinnerStartTime;
    private com.toedter.calendar.JDateChooser startDate_chooser;
    private javax.swing.JTextField txtTaskName;
    private javax.swing.JTextArea txtareaDetails;
    // End of variables declaration//GEN-END:variables
}
