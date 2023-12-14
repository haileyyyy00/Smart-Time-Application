package sta;

import Task_Manager.*;
import User.UserInfo;
import User.UserLogin;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import User.UserUpdate;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class STA_Interface extends javax.swing.JFrame {

    UserLogin login = new UserLogin();
    int uID = login.uID;
    Data_Layer taskData = new Data_Layer();
    DecimalFormat df = new DecimalFormat("#.##"); //decimal format
    

    public STA_Interface() {
        initComponents();
        //JFrame
        setLocationRelativeTo(null); //set JFrame to center
        jPanel1.setBackground(new Color(153,153,153));

      //set the button icons
      STA_Design design = new STA_Design();
      design.scaleIcon(btnTaskList, "Icon\\TaskManager Pic.png",75,70);
      design.scaleIcon(btnAddTask,"Icon\\Add Icon1.png",75,70);
      design.scaleIcon(btnOverallCategory_Productivity, "Icon\\Overall PieChart Pic.png",75,70);
      design.scaleIcon(btnDailyCategory_Productivity, "Icon\\Daily PieChart Pic.png",75,70);
      design.scaleIcon(btnUserProfile, "Icon\\User Profile Icon.png",75,70);
      design.scaleIcon(btnLogout, "Icon\\Logout Icon.png",75,70);
      
      //format the buttons
      formatbtn(btnTaskList);
      formatbtn(btnAddTask);
      formatbtn(btnOverallCategory_Productivity);
      formatbtn(btnDailyCategory_Productivity);
      formatbtn(btnUserProfile);
      formatbtn(btnLogout);
      
    }
    
    //button format
    static void formatbtn(JButton jbutton) {
        //set the text below the icon
        jbutton.setVerticalTextPosition(SwingConstants.BOTTOM);
        jbutton.setHorizontalTextPosition(SwingConstants.CENTER);

        Font btnFont = new Font("Arial Rounded MT Bold", Font.PLAIN, 15);
        jbutton.setFont(btnFont);

    }


    public void category_productivity(String day, int studies, int works, int fitness, int family, int social, int leisure, int completedT, int totalTask){
        ArrayList<TaskInfo> userTask = taskData.taskList(uID);
        double completeP = 0.0, incompleteP = 0.0;
        double studiesP = 0.0, worksP = 0.0, fitnessP = 0.0, familyP = 0.0, socialP = 0.0, leisureP = 0.0;

        if (studies == 0 && works == 0 && fitness == 0 && family == 0 && social == 0 && leisure == 0) {
            switch (day){
                case "Overall":
                 JOptionPane.showMessageDialog(null, "You never create a task.");   
                 break;
                case "Today":
                JOptionPane.showMessageDialog(null, "There has no task for today");
        }
        } else {
            //calculate the percentage of each category
            studiesP = studies * 100.0 / totalTask;
            worksP = works * 100.0 / totalTask;
            fitnessP = fitness * 100.0 / totalTask;
            familyP = family * 100.0 / totalTask;
            socialP = social * 100.0 / totalTask;
            leisureP = 100.0 - studiesP - worksP - fitnessP - familyP - socialP;

            //Calculate the percentage of both completed and incompleted tasks
            completeP = completedT * 100.0 / totalTask;
            incompleteP = 100.0 - completeP;

//create pie charts for overall category and overall productivity
            DefaultPieDataset pieDataset = new DefaultPieDataset();
            pieDataset.setValue("Studies" + "\n" + df.format(studiesP) + "%", studiesP);
            pieDataset.setValue("Works" + "\n" + df.format(worksP) + "%", worksP);
            pieDataset.setValue("Fitness" + "\n" + df.format(fitnessP) + "%", fitnessP);
            pieDataset.setValue("Family" + "\n" + df.format(familyP) + "%", familyP);
            pieDataset.setValue("Social" + "\n" + df.format(socialP) + "%", socialP);
            pieDataset.setValue("Leisure" + "\n" + df.format(leisureP) + "%", leisureP);
            JFreeChart chart = ChartFactory.createPieChart(day + " Category", pieDataset, true, true, true);

            DefaultPieDataset pieDataset2 = new DefaultPieDataset();
            pieDataset2.setValue("Complete" + "\n" + df.format(completeP) + "%", completeP);
            pieDataset2.setValue("Incomplete" + "\n" + df.format(incompleteP) + "%", incompleteP);
            JFreeChart chart2 = ChartFactory.createPieChart(day + " Productivity ", pieDataset2, true, true, true);
          
            JFrame comframe = new JFrame(day + " Category & Productivity");
            comframe.setDefaultCloseOperation(comframe.DISPOSE_ON_CLOSE);
            comframe.setLayout(new FlowLayout());
            comframe.getContentPane().add(new ChartPanel(chart), BorderLayout.EAST);
            comframe.getContentPane().add(new ChartPanel(chart2), BorderLayout.WEST);
            comframe.pack();
            comframe.setLocationRelativeTo(null); //set frame to center
            comframe.setVisible(true);

            if (completeP >= 0.0 && completeP <= 39.9) {
                JOptionPane.showMessageDialog(null, "The distance between your dream and reality is action!");
            } else if (completeP >= 40.0 && completeP <= 79.9) {
                JOptionPane.showMessageDialog(null, "Keep going, don't give up halfway!");
            } else if (completeP >= 80.0 && completeP <= 99.9) {
                JOptionPane.showMessageDialog(null, "Keep going, you're almost there!");
            } else {
                JOptionPane.showMessageDialog(null, "You've accomplished all the tasks, Well Done!");
            }
        }
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        //ImageIcon icon = new ImageIcon("Icon\\TaskManager Pic.png");
        //        Image img = icon.getImage();
        //        Image imgScale = img.getScaledInstance(75, 70, Image.SCALE_SMOOTH);
        //        ImageIcon scaledIcon = new ImageIcon(imgScale);
        //JButton btnTaskList = new JButton();
        //        btnTaskList.setIcon(scaledIcon);
        btnTaskList = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnOverallCategory_Productivity = new javax.swing.JButton();
        btnDailyCategory_Productivity = new javax.swing.JButton();
        btnAddTask = new javax.swing.JButton();
        btnUserProfile = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1561, 876));

        btnTaskList.setText("Task List");
        btnTaskList.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTaskList.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        //btnTaskList.setVerticalTextPosition(SwingConstants.BOTTOM);
        //btnTaskList.setHorizontalTextPosition(SwingConstants.CENTER);
        btnTaskList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaskListActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Cooper Black", 1, 50)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Menu");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnOverallCategory_Productivity.setText("Overall Category & Productivity");
        btnOverallCategory_Productivity.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnOverallCategory_Productivity.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnOverallCategory_Productivity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOverallCategory_ProductivityActionPerformed(evt);
            }
        });

        btnDailyCategory_Productivity.setText("Daily Category & Productivity");
        btnDailyCategory_Productivity.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDailyCategory_Productivity.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDailyCategory_Productivity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDailyCategory_ProductivityActionPerformed(evt);
            }
        });

        btnAddTask.setText("Add Task");
        btnAddTask.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAddTask.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAddTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddTaskActionPerformed(evt);
            }
        });

        btnUserProfile.setText("User Profile");
        btnUserProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserProfileActionPerformed(evt);
            }
        });

        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(177, 177, 177)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnTaskList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnOverallCategory_Productivity, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnDailyCategory_Productivity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAddTask, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnUserProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(552, 552, 552)
                        .addComponent(jLabel1)))
                .addContainerGap(182, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnTaskList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAddTask, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnUserProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnOverallCategory_Productivity, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDailyCategory_Productivity, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(101, 101, 101))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUserProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserProfileActionPerformed
        setVisible(false);
        UserUpdate obj = new UserUpdate();
        obj.setVisible(true);
    }//GEN-LAST:event_btnUserProfileActionPerformed

    private void btnAddTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddTaskActionPerformed
        setVisible(false);
        AddTask obj = new AddTask();
        obj.setVisible(true);
    }//GEN-LAST:event_btnAddTaskActionPerformed

    private void btnTaskListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaskListActionPerformed
        setVisible(false);
        TaskList obj = new TaskList();
        obj.setVisible(true);
    }//GEN-LAST:event_btnTaskListActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        System.exit(0); //exit
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnOverallCategory_ProductivityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOverallCategory_ProductivityActionPerformed
        ArrayList<TaskInfo> userTask = taskData.taskList(uID);
        int completedT = 0, incompleteT = 0;
        int studies = 0, works = 0, fitness = 0, family = 0, social = 0, leisure = 0;

        //calculate the number of tasks in each category
        for (int i = 0; i < userTask.size(); i++) {
            if (userTask.get(i).getCategory().equals("Studies")) {
                studies += 1;
            } else if (userTask.get(i).getCategory().equals("Works")) {
                works += 1;
            }else if (userTask.get(i).getCategory().equals("Health and Fitness")) {
                fitness += 1;
            } else if (userTask.get(i).getCategory().equals("Family")) {
                family += 1;
            } else if (userTask.get(i).getCategory().equals("Social")) {
                social += 1;
            } else if (userTask.get(i).getCategory().equals("Leisure")) {
                leisure += 1;
            }

        }
        
            //calculate the numbers of completed and incompleted tasks
            for (int i = 0; i < userTask.size(); i++) {
                if (userTask.get(i).getStatus().equals("Completed")) {
                    completedT += 1;
                }
            }
             STA_Interface obj = new STA_Interface();
            obj.category_productivity("Overall", studies, works, fitness, family, social, leisure, completedT, userTask.size());
            
    }//GEN-LAST:event_btnOverallCategory_ProductivityActionPerformed

    private void btnDailyCategory_ProductivityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDailyCategory_ProductivityActionPerformed
        ArrayList<TaskInfo> userTask = taskData.taskList(uID);
        int completedT = 0, curTask = 0;
        int studies = 0, works = 0, fitness = 0, family = 0, social = 0, leisure = 0;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        String strCurDate = dateFormat.format(currentDate);
        try {
            Date curDate = dateFormat.parse(strCurDate);
       

            for (int i = 0; i < userTask.size(); i++) {
                Date startD = dateFormat.parse(userTask.get(i).getStartDate());
                Date endD = dateFormat.parse(userTask.get(i).getEndDate());
                if ((startD.equals(curDate) || startD.after(curDate) || startD.equals(curDate) || endD.equals(curDate)) && (endD.before(curDate) || endD.equals(curDate) || startD.equals(curDate) || startD.before(curDate))
                        || (startD.before(curDate) && endD.after(curDate))) {
                    if (userTask.get(i).getStatus().equals("Completed")) {
                        completedT += 1;
                        curTask += 1;
                    }
                    if (userTask.get(i).getStatus().equals("Incomplete")) {
                        curTask += 1;
                    }
                }
            }
                 
                for (int i = 0; i < userTask.size(); i++) {
                Date startD = dateFormat.parse(userTask.get(i).getStartDate());
                Date endD = dateFormat.parse(userTask.get(i).getEndDate());
                if ((startD.equals(curDate) || startD.after(curDate) || startD.equals(curDate) || endD.equals(curDate)) && (endD.before(curDate) || endD.equals(curDate) || startD.equals(curDate) || startD.before(curDate))
                        || (startD.before(curDate) && endD.after(curDate))) {
                    if (userTask.get(i).getCategory().equals("Studies")) {
                        studies += 1;
                    }else if (userTask.get(i).getCategory().equals("Works")) {
                        works += 1;
                    }else if (userTask.get(i).getCategory().equals("Health and Fitness")) {
                        fitness += 1;
                    }else if (userTask.get(i).getCategory().equals("Family")) {
                        family += 1;
                    }else if (userTask.get(i).getCategory().equals("Social")) {
                        social += 1;
                    }else if (userTask.get(i).getCategory().equals("Leisure")) {
                        leisure += 1;
                    }
                }
            }
         
            
            STA_Interface obj = new STA_Interface();
            obj.category_productivity("Today", studies, works, fitness, family, social, leisure, completedT, curTask);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_btnDailyCategory_ProductivityActionPerformed

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
            java.util.logging.Logger.getLogger(STA_Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(STA_Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(STA_Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(STA_Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new STA_Interface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddTask;
    private javax.swing.JButton btnDailyCategory_Productivity;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnOverallCategory_Productivity;
    private javax.swing.JButton btnTaskList;
    private javax.swing.JButton btnUserProfile;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
