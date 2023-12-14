// Encapsulation program for task:
// 1. To group the data
// 2. To hide the data from other classes
// 3. Purpose: To ensure the data integrity
package Task_Manager;

public class TaskInfo {

    protected static final String filename = "Task.txt";

    protected int taskID, userID;
    protected String taskName, taskDetails, startDate, endDate, startTime, endTime, importance, category, status;

    public TaskInfo(int taskID, int userID, String taskName, String taskDetails, String startDate, String endDate, String startTime, String endTime, String importance, String category, String status) {
        this.taskID = taskID; //current varible = value (from parameter)
        this.userID = userID;
        this.taskName = taskName;
        this.taskDetails = taskDetails;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.importance = importance;
        this.category = category;
        this.status = status;
    }

    public int getTaskID() { //used to get the values from the variables after set the value
        return taskID;
    }

    public int getUserID() {
        return userID;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDetails() {
        return taskDetails;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getImportance() {
        return importance;
    }

    public String getCategory() {
        return category;
    }

    public String getStatus() {
        return status;
    }
    
    public String toString(){
        String joinLine = String.join(" // ", String.valueOf(taskID), taskName, taskDetails, startDate, endDate, startTime, endTime, importance, category, status, String.valueOf(userID));
        return joinLine;
    }
}
