import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
// Task class, that i used to created task
class Task{
		private String taskName;
		private int taskID;
		private LocalDate dueDate;
		
	 public String status;
		
	// Constructor initializing Task class	
	Task(String taskName,int taskID,LocalDate dueDate){
		this.taskName = taskName;
		this.taskID = taskID;
		this.dueDate = dueDate;
		}
     // Getter method for taskName
	public String getTaskName() {
		return taskName;
	}
	// Setter method for taskName
	public void setTaskName(String tName) {
		taskName = tName;
	}
	
	// Getter method for taskID
	public int getID() {
		return taskID;
	}
	// Getter method for dueDate
	public LocalDate getDate() {
		return dueDate;
	}
	// Setter method for dueDate
	public void setDueDate(LocalDate newDate) {
		dueDate = newDate;
		
	}
	// getStatus method checks status of the task(in progress,overdue,completed)
	public String getStatus() {
		
		if("COMPLETED".equals(status)) {
			return "COMPLETED";
		}
		if(LocalDate.now().isAfter(dueDate)) {
			status = "OVERDUE";
			
		}else {
			status = "IN PROGRESS";
		}
		return status;
	}
	// markAsCompleted method to mark task as completed
	public void markAsCompleted() {
		status = "COMPLETED";
	}
	
		
	// Overriding toString method to display task attributes.
	@Override
	public String toString() {
		 return String.format("ID: %d | Task: %s | Due: %s | Status: %s",
		            taskID, taskName, dueDate, getStatus());
       }

}



	
// TaskManager class that handles all the operation
public class TaskManager {
     ArrayList<Task> task; // creating a list of Task object to hold tasks.
 
	//Constructor TaskManager class
     TaskManager(){
		task = new ArrayList<Task>();
		Scanner scanner = new Scanner(System.in);
		Scanner scn = new Scanner(System.in);
		int option = 0;
		do {
		System.out.println("Welcome to the Task Manager");
		System.out.println("----------------------------");
		System.out.println("1.ADD TASK");
		System.out.println("2.DELETE TASK");
		System.out.println("3.DISPLAY TASKS");
		System.out.println("4.SEARCH TASK");
		System.out.println("5.CLEAR");
		System.out.println("6.SET NEW DUE DATE");
		System.out.println("7.UPDATE TASK");
		System.out.println("8.MARK TASK AS COMPLETED");
		System.out.println("9.FILTER BY STATUS");
		System.out.println("10.PRESS 0 TO EXIT");
		System.out.println("Enter your choice: ");
		System.out.println("----------------------------");
	
		 option = scanner.nextInt();
		
		 // Switch for picking operation to perform.
		switch(option) {
		//1.ADD TASK
		case 1: 
			System.out.println("Enter task to add: ");
			String taskName = scn.nextLine();
			System.out.println("Enter task id: ");
			int id = scanner.nextInt();
			// Calling id method to check if entered id already exits.
			id =idExist(id, scanner); 
			
			
			// Calling getValidDate method to check to see whether entered due date is appropriate
			LocalDate dueDate = getValidDate(scn);
			
			
			// adding new Task to task list
			task.add(new Task(taskName,id,dueDate));
			System.out.println("----------------------------");
		    System.out.println("Task Added Succesfully.");
		    System.out.println("----------------------------");
		    break;
		    
		    //2.DELETE TASK
		case 2:
			// Calling finId method to see if such id exists.
			Task t2 = findId(scanner);
			if(t2!= null) {
				System.out.println("----------------------------");
				   task.remove(t2);
				   System.out.println("Task removed succesfully.");
				   System.out.println("----------------");
			   }
			break;
			//3.DISPLAY TASKS
		case 3:
			System.out.println("All listed tasks:");
			System.out.println("----------------------------");
			// sortbyDueDate method to sort tasks by their due date.
			System.out.println(sortbyDueDate(task)+","); 
			System.out.println("----------------------------");
			
			
			 break;
			 //4.SEARCH TASK
		case 4: 
			// Calling "findId" method to see if task exists.
			 Task t =findId(scanner); 
			 if(t!= null) {
				 System.out.println("----------------------------");
				   System.out.println(t);
				  System.out.println("----------------------------");
			  
			   }
			
			  
			 break;
			 //5.CLEAR 
		case 5:
			System.out.println("----------------------------");
			// Calling clear method to delete all tasks in the list.
			task.clear(); 
			System.out.println("All tasks have been cleared");
			System.out.println("----------------------------");
		break;
		//6.SET NEW DUE DATE
		case 6:
			System.out.println("----------------------------");
			Task t1 = findId(scanner); // Calling findId method to see if task exists.
			
			if(t1 !=null) {
				LocalDate newDue = getValidDate(scn); // checking if user enters valid values.
				t1.setDueDate(newDue); // Setting new due date
				System.out.println("Due date updated succesfully.");
			}
			System.out.println("----------------------------");
			break;
			// 7.UPDATE TASK
		case 7:
			System.out.println("----------------------------");
			 Task newTask = findId(scanner);
			 if(newTask != null) {
				 System.out.println("Enter new name for the task.");
				 String newName = scn.nextLine(); // Getting new name for task from user.
				 newTask.setTaskName(newName);// Setting new name for the task.
				 System.out.println("Name of the task updated succesfully");
			 }
			
			System.out.println("----------------------------");
			break;
			//8.MARK AS COMPLETED
		case 8:
			System.out.println("----------------------------");
			Task completedTask = findId(scanner);
			if(completedTask !=null) {
				completedTask.markAsCompleted();// Marking task as completed.
				System.out.println("Task marked as  completed.");
			}
			
		   
			break;
			//9.FILTER BY STATUS
		case 9:
			System.out.println("----------------------------");
			filterBystatus(task);// calling filterBystatus method to group  tasks by their status.
			System.out.println("----------------------------");
			break;
		}
		}while(option !=0);
		
		// Closing scanners to prevent any leaks.
		scanner.close();
		scn.close(); 
		System.out.println("Thank you for using Task Manager!!!");
	
 }
	
	// Method that searches through the task list to check if id already exits.
	private int idExist(int id,Scanner scanner) {
		boolean idExists = false;
		
		for(int i = 0; i < task.size(); i++) {
			if(task.get(i).getID()==id) {
				idExists = true;
				// if id exists this method will output message below and force user to enter different id.
				while(idExists) {
					System.out.println("ID already exists,please enter different id.");
					int notExistedID = scanner.nextInt();
					if(notExistedID != task.get(i).getID()) {
						id = notExistedID;
						idExists = false;
					}
				 }
				 }
         }
		return id;
		
	}

   // Method for grouping tasks by status.
	private void filterBystatus(ArrayList<Task> task) {
		ArrayList<Task> inprog = new ArrayList<>();
		ArrayList<Task> completed = new ArrayList<>();
		ArrayList<Task> overdue= new ArrayList<>();
		
		for(int i = 0; i < task.size(); i++) {
			
			if(task.get(i).getStatus().equals("IN PROGRESS")) {
				inprog.add(task.get(i));
			}
			if(task.get(i).getStatus().equals("COMPLETED")) {
				completed.add(task.get(i));
			}
			if(task.get(i).getStatus().equals("OVERDUE")) {
				overdue.add(task.get(i));
	
			}	
		}
		// printing tasks that grouped by their status.
		System.out.println("IN PROGRESS: " + inprog);
		System.out.println("COMPLETED: " + completed);
		System.out.println("OVERDUE: " + overdue);
		
		
	}

    // Method that checks if user entered valid date format.
	public LocalDate getValidDate(Scanner scn) {
		LocalDate date = null;
		// while date variable is null program will constantly ask for proper format.
		while(date == null) {
			System.out.println("Enter a due date(yyyy-MM-DD): ");
			String input = scn.nextLine();
			try {
				date = LocalDate.parse(input, DateTimeFormatter.ISO_DATE);
				
			}
			catch(Exception e){
				System.out.println("Invalid format.Please use yyyy-MM-DD");
			}
			}
		return date;
		}
	// Method that searches through the task list to check if id exists.
	public Task findId(Scanner scanner) {
		 System.out.println("----------------------------");
	      System.out.println("Enter id of the task: ");
	      int id = scanner.nextInt();
	      Task taskl = null;
	      boolean found = false;
	      for(int i = 0; i < task.size(); i++) {
	    	  if(task.get(i).getID()== id) {
	    		taskl = task.get(i);
	    		found = true;
	    		break;
	    	  }
	    	 }
	      
	      
	      if(!found ){
	    	  System.out.println("There is no such a task.");
	    	  System.out.println("----------------------------");
	    	  return null;
	      }
	      
	       return taskl;
	     
	    }
	
	// Method for sorting  tasks by their due date.
	public ArrayList<Task> sortbyDueDate(ArrayList<Task> tsk) {
		for(int i = 0; i < tsk.size(); i++) {
			for(int j = 0; j < tsk.size(); j++) {
			   if(tsk.get(i).getDate().isBefore(tsk.get(j).getDate())) {
					Task temp = tsk.get(i);
					tsk.set(i, tsk.get(j));
					tsk.set(j, temp);
				}
			}
			
		}
		return tsk;
	}
	
	

	
 }
