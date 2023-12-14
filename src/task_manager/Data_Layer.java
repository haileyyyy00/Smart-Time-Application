package Task_Manager;

import User.UserInfo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Data_Layer {

    final String filename = "Task.txt";
    final String userFilename = "User.txt";

    //get the textfile data into arraylist (TaskList)
    public ArrayList<TaskInfo> getTaskList() { //taskList store TaskInfo
        ArrayList<TaskInfo> alltaskList = new ArrayList<TaskInfo>(); //create an ArrayList called alltaskList

        try {

            File file = new File(filename);
            BufferedReader br = new BufferedReader(new FileReader(file));

            Object[] tableLines = br.lines().toArray(); //store in tableLines array (return lines to array)

            for (int i = 0; i < tableLines.length; i++) {
                String line = tableLines[i].toString().trim();
                String[] dataColumn = line.split("//"); // each line data separated by "//"

                int taskID = Integer.parseInt(dataColumn[0].trim());
                String taskName = dataColumn[1].trim();
                String taskDetails = dataColumn[2].trim();
                String startDate = dataColumn[3].trim();
                String endDate = dataColumn[4].trim();
                String startTime = dataColumn[5].trim();
                String endTime = dataColumn[6].trim();
                String importance = dataColumn[7].trim();
                String category = dataColumn[8].trim();
                String status = dataColumn[9].trim();
                int userID = Integer.parseInt(dataColumn[10].trim());

                TaskInfo taskInfo = new TaskInfo(taskID, userID, taskName, taskDetails, startDate, endDate, startTime, endTime, importance, category, status);
                alltaskList.add(taskInfo); // tableLines are represented in the form of TaskInfo
            }
        } catch (Exception ex) { // return logger if the class parameter are missed or wrong in the caller
            Logger.getLogger(TaskList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alltaskList;

    }

    //filter the task list for specific user
    public ArrayList<TaskInfo> taskList(int uID) {
        Data_Layer taskData = new Data_Layer();
        ArrayList<TaskInfo> list = taskData.getTaskList();
        ArrayList<TaskInfo> TaskList = new ArrayList<TaskInfo>();
        for (TaskInfo task : list) {
            if (task.getUserID() == uID)//only retrieve the corresponding user task data
            {
                TaskList.add(task);
            }
        }
        return TaskList;
    }

    //get the textfile data into arraylist (UserList)
    public ArrayList<UserInfo> getUserList() { //userList store UserInfo
        ArrayList<UserInfo> allUserList = new ArrayList<UserInfo>(); //create an ArrayList called allUserList

        try {

            File file = new File(userFilename);
            BufferedReader br = new BufferedReader(new FileReader(file));

            Object[] tableLines = br.lines().toArray(); //store in tableLines array (return lines to array)

            for (int i = 0; i < tableLines.length; i++) {
                String line = tableLines[i].toString().trim();
                String[] dataColumn = line.split("//"); // each line data separated by "//"

                int userID = Integer.parseInt(dataColumn[0].trim());
                String username = dataColumn[1].trim();
                String password = dataColumn[2].trim();

                UserInfo userInfo = new UserInfo(userID, username, password);

                allUserList.add(userInfo); // tableLines are represented in the form of UserInfo
            }
        } catch (Exception ex) { // return logger if the class parameter are missed or wrong in the caller
            Logger.getLogger(UserInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allUserList;
    }

//for user registration & user update
    public boolean checkMultipleConditions(String username, String password, String confirm_Password) {
        Data_Layer data = new Data_Layer();
        boolean checkCon = true;

        //check Blanks      
        if (username.isEmpty() || password.isEmpty() || confirm_Password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Failed, please fill up blanks!");
            checkCon = false;
        } else if (!(password.equals(confirm_Password))) {//check Password
            JOptionPane.showMessageDialog(null, "Your Password and Confirm Password doesn't match!\nPlease try again.");
            checkCon = false;
        } else {
            //check Existing User
            ArrayList<UserInfo> userList = data.getUserList();
            ArrayList<String> tempList = new ArrayList<String>();

            for (int i = 0; i < userList.size(); i++) {
                UserInfo userData = userList.get(i);
                String data_username = userData.getUsername().trim();
                String data_password = userData.getPassword().trim();
                if (data_username.equals(username) && data_password.equals(password)) {
                    JOptionPane.showMessageDialog(null, "The username and password already existed, \nplease change your username and password!");
                    checkCon = false;
                }
            }
        }
        return checkCon;

    }
    
     //Make sure the start date is after the end date or not
        public boolean checkDateTime(Date startDate, Date endDate) {
        boolean check = true;
        try {
//            Date startDate = startDate_chooser.getDate();
//            Date endDate = endDate_chooser.getDate();
            if (startDate.after(endDate)) {
                check = false;
            }
        } catch (Exception pe) {
            JOptionPane.showMessageDialog(null, pe);
        }
        return check;
    }
    

    //printwriter: write all the elements from an arraylist into textfile
    public void Update(ArrayList<String> tempArray, String file) {
        try {
            PrintWriter pr = new PrintWriter(file);

            for (String str : tempArray) {
                pr.println(str);

            }
            pr.close();
        } catch (FileNotFoundException fe) {
            JOptionPane.showMessageDialog(null, "File Not Found");
        }
    }
}
