package Task_Manager;

import static Task_Manager.TaskInfo.filename; //filename from TaskInfo class
import User.UserLogin;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;
import java.text.ParseException;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import sta.STA_Design;
import sta.STA_Interface;


public class TaskList extends javax.swing.JFrame {

    Data_Layer taskData = new Data_Layer();
    UserLogin login = new UserLogin();
    int uID = login.uID;
    Date curDate, setStart, setEnd;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public TaskList() { //Constructor
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
        
       //format button icons
        design.scaleIcon(btnFilter, "Icon\\filter.png", 35, 30);
        design.scaleIcon(btnAdd, "Icon\\Add Icon.png", 35, 30);
        design.scaleIcon(btnUpdate, "Icon\\Update Icon.png", 35, 30);
        design.scaleIcon(btnDelete, "Icon\\Delete Icon.png", 35, 30);
        design.scaleIcon(btnHome, "Icon\\home.png", 35, 30);
        
        //Format Label
        setLabel(lblTaskID);
        setLabel(lblTaskName);
        setLabel(lblDetails);
        setLabel(lblCategory);
        setLabel(lblImportance);
        setLabel(lblStartDate);
        setLabel(lblEndDate);
        setLabel(lblStartTime);
        setLabel(lblEndTime);
        setLabel(lblStatus);
        setLabel(lblSetStart);
        setLabel(lblSetEnd);

        // Format tools
        Font newFont = new Font("Arial Rounded MT Bold", Font.PLAIN, 18);
        txtTaskName.setFont(newFont);
        txtareaDetails.setFont(newFont);
        cmbCategory.setFont(newFont);
        cmbImportance.setFont(newFont);
        cmbStatus.setFont(newFont);
        startDate_chooser.setFont(newFont);
        endDate_chooser.setFont(newFont);
        spinnerStartTime.setFont(newFont);
        spinnerEndTime.setFont(newFont);
        jTable1.setRowHeight(30);
        jTable1.setFont(new Font(jTable1.getFont().getName(), jTable1.getFont().getStyle(), 18));

        Date getCurDate = new Date();
        String strCurDate = dateFormat.format(getCurDate);
        try {
            curDate = dateFormat.parse(strCurDate);
            show_tasks(taskData.taskList(uID), curDate, curDate); //show today tasks
        } catch (ParseException pe) {
            JOptionPane.showMessageDialog(null, pe);
        }
    }

    //set Label format
    static void setLabel(JLabel jlabel) {
        Font newFont = new Font("Franklin Gothic Heavy", Font.PLAIN, 20);
        jlabel.setFont(newFont);
        jlabel.setForeground(new Color(255, 255, 255)); //white

    }

    
    public void show_tasks(ArrayList<TaskInfo> taskList, Date setStartDate, Date setEndDate) { //retrieve from TaskInfo 

        boolean check = taskData.checkDateTime(setStartDate,setEndDate);

        if (check == false) {
            JOptionPane.showMessageDialog(null, "The startDate is after the endDate!");
        } else {
            ArrayList<TaskInfo> list = taskList;
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0); //clear the table

            ArrayList<TaskInfo> TaskList = new ArrayList<TaskInfo>();
            
            String getSetStart = dateFormat.format(setStartDate);
            String getSetEnd = dateFormat.format(setEndDate);
            try {
                Date setStart = dateFormat.parse(getSetStart);
                Date setEnd = dateFormat.parse(getSetEnd);

                try {
                    for (int i = 0; i < list.size(); i++) {
                        Date taskStart = dateFormat.parse(list.get(i).getStartDate());
                        Date taskEnd = dateFormat.parse(list.get(i).getEndDate());
                        if ((taskStart.equals(setStart) || taskStart.after(setStart) || taskStart.equals(setEnd) || taskEnd.equals(setEnd)) && (taskEnd.before(setEnd) || taskEnd.equals(setEnd) || taskStart.equals(setEnd) || taskStart.before(setEnd)) ||
                                (taskStart.before(setStart) && taskEnd.after(setEnd))) {
                            String startDate = list.get(i).getStartDate();
                            String endDate = list.get(i).getEndDate();
                            TaskList.add(list.get(i));
                        }
                    }
                } catch (ParseException pe) {
                    JOptionPane.showMessageDialog(null, pe);
                }
            } catch (ParseException pe) {
                JOptionPane.showMessageDialog(null, pe);
            }
            if (TaskList.size() > 0) {
                for (TaskInfo task : TaskList) {
                    model.addRow(new Object[]{ //column
                        task.getTaskID(),
                        task.getTaskName(),
                        task.getTaskDetails(),
                        task.getStartDate(),
                        task.getEndDate(),
                        task.getStartTime(),
                        task.getEndTime(),
                        task.getImportance(),
                        task.getCategory(),
                        task.getStatus()});

                    changeTableColor(jTable1);
                    
                }
            } else {
                JOptionPane.showMessageDialog(null, "No task here!");
            }
        }
    }

    //change the table row based on the importance of the task
    static JTable changeTableColor(JTable table) {
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() { //set jTable row

            @Override //override the getTableCellRendererComponent method from the Component class
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

                //get the value from the Component class
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                //set color
                String importance = table.getModel().getValueAt(row, 7).toString(); //get the importance column value
                if (importance.equals("Important!")) {
                    cell.setBackground(new Color(252, 132, 3));
                    cell.setForeground(Color.black);
                } else if (importance.equals("Medium")) {
                    cell.setBackground(new Color(98, 252, 3)); 
                    cell.setForeground(Color.black);
                } else {
                    cell.setBackground(Color.white);
                    cell.setForeground(Color.black);
                }
                return cell;
            }
        });
        return table;
    }



    public class TaskAttribute {

        int taskID = (lblTaskID.getText().equals("Task ID")) ? 0 : Integer.parseInt(lblTaskID.getText().trim());
        String taskName = txtTaskName.getText().trim();
        String taskDetails = txtareaDetails.getText().trim();
        String startDate = dateFormat.format(startDate_chooser.getDate()).toString().trim();
        String endDate = dateFormat.format(endDate_chooser.getDate()).toString().trim();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String startTime = timeFormat.format(spinnerStartTime.getValue()).toString().trim();
        String endTime = timeFormat.format(spinnerEndTime.getValue()).toString().trim();
        String importance = cmbImportance.getSelectedItem().toString().trim();
        String category = cmbCategory.getSelectedItem().toString().trim();
        String status = cmbStatus.getSelectedItem().toString().trim();
        int userID = uID;

    }

    //reset the text field, text area, data and time 
    public void reset() {
        try {
            lblTaskID.setText("Task ID");
            txtTaskName.setText("");
            txtareaDetails.setText("");
           
            Date curDate = new Date();
            String strCurDate = dateFormat.format(curDate);
            startDate_chooser.setDate(dateFormat.parse(strCurDate));
            endDate_chooser.setDate(dateFormat.parse(strCurDate));

            cmbImportance.setSelectedIndex(0);
            cmbCategory.setSelectedIndex(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //if user didnt filter, use currrent date to show task
    public void setDateConditions() {
        if (setStart == null || setEnd == null) {
            
            show_tasks(taskData.taskList(uID), curDate, curDate);
            reset();
        } else {
            show_tasks(taskData.taskList(uID), setStart, setEnd);
            reset();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooserPanelBeanInfo1 = new com.toedter.calendar.demo.DateChooserPanelBeanInfo();
        dateChooserPanelBeanInfo2 = new com.toedter.calendar.demo.DateChooserPanelBeanInfo();
        jPanel1 = new javax.swing.JPanel();
        lblTaskName = new javax.swing.JLabel();
        txtTaskName = new javax.swing.JTextField();
        lblStatus = new javax.swing.JLabel();
        cmbImportance = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        lblTaskID = new javax.swing.JLabel();
        lblDetails = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtareaDetails = new javax.swing.JTextArea();
        lblStartTime = new javax.swing.JLabel();
        lblEndTime = new javax.swing.JLabel();
        endDate_chooser = new com.toedter.calendar.JDateChooser();
        lblStartDate = new javax.swing.JLabel();
        cmbStatus = new javax.swing.JComboBox<>();
        lblImportance = new javax.swing.JLabel();
        cmbCategory = new javax.swing.JComboBox<>();
        lblCategory = new javax.swing.JLabel();
        Date date = new Date();
        SpinnerDateModel smStart = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
        spinnerStartTime = new javax.swing.JSpinner(smStart);
        SpinnerDateModel smEnd = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
        spinnerEndTime = new javax.swing.JSpinner(smEnd);
        btnHome = new javax.swing.JButton();
        lblEndDate = new javax.swing.JLabel();
        startDate_chooser = new com.toedter.calendar.JDateChooser();
        startDate_chooser1 = new com.toedter.calendar.JDateChooser();
        btnFilter = new javax.swing.JButton();
        lblSetEnd = new javax.swing.JLabel();
        endDate_chooser1 = new com.toedter.calendar.JDateChooser();
        lblSetStart = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        lblTitle_TaskList = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setMaximumSize(new java.awt.Dimension(1561, 876));
        jPanel1.setMinimumSize(new java.awt.Dimension(1561, 876));
        jPanel1.setPreferredSize(new java.awt.Dimension(1561, 876));

        lblTaskName.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 20)); // NOI18N
        lblTaskName.setForeground(new java.awt.Color(255, 255, 255));
        lblTaskName.setText("Task Name");

        txtTaskName.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 20)); // NOI18N

        lblStatus.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 20)); // NOI18N
        lblStatus.setForeground(new java.awt.Color(255, 255, 255));
        lblStatus.setText("Status");

        cmbImportance.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Low", "Medium", "Important!" }));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Task Name", "Details", "Start Date", "End Date", "Start Time", "End Time", "Importance", "Category", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        btnUpdate.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 15)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.setPreferredSize(new java.awt.Dimension(80, 30));
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 15)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        lblTaskID.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 20)); // NOI18N
        lblTaskID.setForeground(new java.awt.Color(255, 255, 255));
        lblTaskID.setText("Task ID");

        lblDetails.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 20)); // NOI18N
        lblDetails.setForeground(new java.awt.Color(255, 255, 255));
        lblDetails.setText("Details");

        txtareaDetails.setColumns(20);
        txtareaDetails.setRows(5);
        jScrollPane2.setViewportView(txtareaDetails);

        lblStartTime.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 20)); // NOI18N
        lblStartTime.setForeground(new java.awt.Color(255, 255, 255));
        lblStartTime.setText("Start Time");

        lblEndTime.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 20)); // NOI18N
        lblEndTime.setForeground(new java.awt.Color(255, 255, 255));
        lblEndTime.setText("End Time");

        endDate_chooser.setDateFormatString("yyyy-MM-dd");
        try{
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

        lblStartDate.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 20)); // NOI18N
        lblStartDate.setForeground(new java.awt.Color(255, 255, 255));
        lblStartDate.setText("Start Date");

        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Incomplete", "Completed" }));

        lblImportance.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 20)); // NOI18N
        lblImportance.setForeground(new java.awt.Color(255, 255, 255));
        lblImportance.setText("Importance");

        cmbCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Leisure","Works","Studies","Health and Fitness","Social","Family" }));

        lblCategory.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 20)); // NOI18N
        lblCategory.setForeground(new java.awt.Color(255, 255, 255));
        lblCategory.setText("Category");

        JSpinner.DateEditor stimeEditor = new JSpinner.DateEditor(spinnerStartTime, "HH:mm"); //spinnername, applied format
        spinnerStartTime.setEditor(stimeEditor);

        JSpinner.DateEditor etimeEditor = new JSpinner.DateEditor(spinnerEndTime, "HH:mm"); //spinnername, applied format
        spinnerEndTime.setEditor(etimeEditor);

        btnHome.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 15)); // NOI18N
        btnHome.setText("Home");
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        lblEndDate.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 20)); // NOI18N
        lblEndDate.setForeground(new java.awt.Color(255, 255, 255));
        lblEndDate.setText("End Date");

        startDate_chooser.setDateFormatString("yyyy-MM-dd");
        try{
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

        startDate_chooser1.setDateFormatString("yyyy-MM-dd");
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date curDate = new Date();
            String strCurDate = dateFormat.format(curDate);
            try{
                startDate_chooser1.setDate(dateFormat.parse(strCurDate));
            }catch(ParseException pe){
                JOptionPane.showMessageDialog(null, pe);
            }
        }catch(NumberFormatException ne){
            JOptionPane.showMessageDialog(null, ne);
        }

        btnFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterActionPerformed(evt);
            }
        });

        lblSetEnd.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 20)); // NOI18N
        lblSetEnd.setForeground(new java.awt.Color(255, 255, 255));
        lblSetEnd.setText("Filter End Date");

        endDate_chooser1.setDateFormatString("yyyy-MM-dd");
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date curDate = new Date();
            String strCurDate = dateFormat.format(curDate);
            try{
                endDate_chooser1.setDate(dateFormat.parse(strCurDate));
            }catch(ParseException pe){
                JOptionPane.showMessageDialog(null, pe);
            }
        }catch(NumberFormatException ne){
            JOptionPane.showMessageDialog(null, ne);
        }

        lblSetStart.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 20)); // NOI18N
        lblSetStart.setForeground(new java.awt.Color(255, 255, 255));
        lblSetStart.setText("Filter Start Date");

        btnAdd.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 15)); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        lblTitle_TaskList.setFont(new java.awt.Font("Cooper Black", 1, 40)); // NOI18N
        lblTitle_TaskList.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle_TaskList.setText("Task List");
        lblTitle_TaskList.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(165, 165, 165)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblTaskID, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(106, 106, 106))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lblTaskName)
                                                    .addComponent(lblDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(23, 23, 23)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtTaskName)
                                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(lblImportance)
                                                .addGap(18, 18, 18)
                                                .addComponent(cmbImportance, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(lblCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(cmbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(lblStartDate)
                                                            .addComponent(lblEndDate))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(startDate_chooser, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                                                            .addComponent(endDate_chooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(lblStartTime, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(spinnerStartTime, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(77, 77, 77))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lblEndTime, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lblStatus))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(spinnerEndTime, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                    .addComponent(btnHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(199, 199, 199)))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblTitle_TaskList, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 335, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSetStart)
                            .addComponent(startDate_chooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(56, 56, 56)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSetEnd)
                            .addComponent(endDate_chooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(56, 56, 56)
                        .addComponent(btnFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(151, 151, 151))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 22, Short.MAX_VALUE)
                        .addComponent(lblTitle_TaskList)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblSetStart, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                                    .addComponent(lblSetEnd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(endDate_chooser1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                                    .addComponent(startDate_chooser1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblTaskID, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblStartDate, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                                        .addGap(18, 18, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(startDate_chooser, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblEndDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(endDate_chooser, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                                .addGap(44, 44, 44)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblStartTime, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(spinnerStartTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblEndTime, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(spinnerEndTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(150, 150, 150))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblTaskName, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTaskName, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jScrollPane2))
                                .addGap(32, 32, 32)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cmbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblImportance, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbImportance, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31))))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        setVisible(false);
        STA_Interface obj = new STA_Interface();
        obj.setVisible(true);
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        TaskAttribute ta = new TaskAttribute();

        if (ta.taskID == 0) {
            JOptionPane.showMessageDialog(null, "Failed! Please select a row!");
        } else {

            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to detele " + ta.taskName + "?", "Delete Confirmation", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (response == 0) {
                Data_Layer taskData = new Data_Layer();
                ArrayList<TaskInfo> list = taskData.getTaskList();
                ArrayList<String> tempArray = new ArrayList<>();

                for (int i = 0; i < list.size(); i++) {
                    TaskInfo task = list.get(i);

                    if (!(task.getTaskID() == ta.taskID)) {
                        tempArray.add(task.toString());
                    }

                }

                JOptionPane.showMessageDialog(null, "Successlly Deleted!"); 
                taskData.Update(tempArray, filename);
                 
                setDateConditions();
                              
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        Date startDate = startDate_chooser.getDate();
        Date endDate = endDate_chooser.getDate();
        boolean check = taskData.checkDateTime(startDate, endDate);
        if (check == false) {
            JOptionPane.showMessageDialog(null, "The startDate is after the endDate!");
        } else {

            try {

                TaskAttribute ta = new TaskAttribute();
                if (ta.taskID == 0) {
                    JOptionPane.showMessageDialog(null, "Failed! Please select a row!");
                } else if (ta.taskName.isEmpty() || ta.taskDetails.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Update Failed, please fill up blanks!");
                } else {
                  
                    ArrayList<TaskInfo> list = taskData.getTaskList();
                    ArrayList<String> tempArray = new ArrayList<String>();
                    for (int i = 0; i < list.size(); i++) {
                        TaskInfo task = list.get(i);

                        int tID = task.getTaskID();
                        String data_taskname = task.getTaskName();
                        String data_taskdetails = task.getTaskDetails();
                        String data_startdate = task.getStartDate();
                        String data_enddate = task.getEndDate();
                        String data_starttime = task.getStartTime();
                        String data_endtime = task.getEndTime();
                        String data_importance = task.getImportance();
                        String data_category = task.getCategory();
                        String data_status = task.getStatus();

                        if (tID == ta.taskID && data_taskname.equals(ta.taskName) && data_taskdetails.equals(ta.taskDetails) && data_startdate.equals(ta.startDate)
                                && data_enddate.equals(ta.endDate) && data_starttime.equals(ta.startTime) && data_endtime.equals(ta.endTime) && data_importance.equals(ta.importance)
                                && data_category.equals(ta.category) && data_status.equals(ta.status)) {
                            JOptionPane.showMessageDialog(null, "The information remain unchanged!");
                            tempArray.add(task.toString());
                        } else {
                            if (!(tID == ta.taskID)) {
                                tempArray.add(task.toString());// add back others line
                            } else {
                                tempArray.add(String.valueOf(task.getTaskID()) + " // "
                                        + ta.taskName + " // "
                                        + ta.taskDetails + " // "
                                        + ta.startDate + " // "
                                        + ta.endDate + " // "
                                        + ta.startTime + " // "
                                        + ta.endTime + " // "
                                        + ta.importance + " // "
                                        + ta.category + " // "
                                        + ta.status + " // "
                                        + String.valueOf(ta.userID));
                                JOptionPane.showMessageDialog(null, "Successlly Updated!");
                            }
                        }
                    }
                    if (tempArray.size() > 0) {
                        taskData.Update(tempArray, filename);

                        setDateConditions();

                    }
                }

            } catch (NumberFormatException ne) {
                JOptionPane.showMessageDialog(null, ne);
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int i = jTable1.getSelectedRow(); //selectedRow Index
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        lblTaskID.setText(model.getValueAt(i, 0).toString().trim());
        txtTaskName.setText(model.getValueAt(i, 1).toString().trim());
        txtareaDetails.setText(model.getValueAt(i, 2).toString().trim());

        try {// if didnt match the format, could be handled by exception
            
            Date startDateFormat = dateFormat.parse(model.getValueAt(i, 3).toString().trim()); //Set the date format
            startDate_chooser.setDate(startDateFormat);
            Date endDateFormat = dateFormat.parse(model.getValueAt(i, 4).toString().trim());
            endDate_chooser.setDate(endDateFormat);

            try {
                Date st = new SimpleDateFormat("HH:mm").parse(model.getValueAt(i, 5).toString().trim());// convert to date
                Date et = new SimpleDateFormat("HH:mm").parse(model.getValueAt(i, 6).toString().trim());
                spinnerStartTime.setValue(st);
                spinnerEndTime.setValue(et);

            } catch (NumberFormatException ne) {
                JOptionPane.showMessageDialog(null, "Time format Error " + ne);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        String importance = model.getValueAt(i, 7).toString().trim();
        switch (importance) {
            case "Low":
                cmbImportance.setSelectedItem("Low");
                break;
            case "Medium":
                cmbImportance.setSelectedItem("Medium");
                break;
            case "Important!":
                cmbImportance.setSelectedItem("Important!");
                break;
        }

        String category = model.getValueAt(i, 8).toString().trim();
        switch (category) {
            case "Studies":
                cmbCategory.setSelectedItem("Studies");
                break;
            case "Works":
                cmbCategory.setSelectedItem("Works");
                break;    
            case "Health and Fitness":
                cmbCategory.setSelectedItem("Health and Fitness");
                break;
            case "Family":
                cmbCategory.setSelectedItem("Family");
                break;
            case "Social":
                cmbCategory.setSelectedItem("Social");
            case "Leisure":
                cmbCategory.setSelectedItem("Leisure");
        }

        String status = model.getValueAt(i, 9).toString().trim();
        switch (status) {
            case "Incomplete":
                cmbStatus.setSelectedItem("Incomplete");
                break;
            case "Completed":
                cmbStatus.setSelectedItem("Completed");
                break;

        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterActionPerformed

        setStart = startDate_chooser1.getDate();
        setEnd = endDate_chooser1.getDate();

        show_tasks(taskData.taskList(uID), setStart, setEnd);

    }//GEN-LAST:event_btnFilterActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        setVisible(false);
        AddTask addTask = new AddTask();
        addTask.setVisible(true);
    }//GEN-LAST:event_btnAddActionPerformed

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
            java.util.logging.Logger.getLogger(TaskList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TaskList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TaskList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TaskList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TaskList().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFilter;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmbCategory;
    private javax.swing.JComboBox<String> cmbImportance;
    private javax.swing.JComboBox<String> cmbStatus;
    private com.toedter.calendar.demo.DateChooserPanelBeanInfo dateChooserPanelBeanInfo1;
    private com.toedter.calendar.demo.DateChooserPanelBeanInfo dateChooserPanelBeanInfo2;
    private com.toedter.calendar.JDateChooser endDate_chooser;
    private com.toedter.calendar.JDateChooser endDate_chooser1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblCategory;
    private javax.swing.JLabel lblDetails;
    private javax.swing.JLabel lblEndDate;
    private javax.swing.JLabel lblEndTime;
    private javax.swing.JLabel lblImportance;
    private javax.swing.JLabel lblSetEnd;
    private javax.swing.JLabel lblSetStart;
    private javax.swing.JLabel lblStartDate;
    private javax.swing.JLabel lblStartTime;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTaskID;
    private javax.swing.JLabel lblTaskName;
    private javax.swing.JLabel lblTitle_TaskList;
    private javax.swing.JSpinner spinnerEndTime;
    private javax.swing.JSpinner spinnerStartTime;
    private com.toedter.calendar.JDateChooser startDate_chooser;
    private com.toedter.calendar.JDateChooser startDate_chooser1;
    private javax.swing.JTextField txtTaskName;
    private javax.swing.JTextArea txtareaDetails;
    // End of variables declaration//GEN-END:variables
}
