import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class Scheduling {
	// ideally we want to input all of this in an arraylist 
	
	//gannt chart start ups
	static int maxpID;
	
	//find a better way to get the 45 modified 
	static ArrayList<String> processID =new ArrayList<String>();
	static ArrayList<Integer> arrivalTime = new ArrayList<Integer>();
	static ArrayList<Integer> burstTime = new ArrayList<Integer>(); //run time
	
	//forming the gannt chart
	static ArrayList<Integer> completionTime = new ArrayList<Integer>(); //aka when it ends
	static ArrayList<Integer> actualArrivalTime = new ArrayList<Integer>(); //time at which the process gets to the CPU first after going through the Gannt chart.
	
	//calculations
	static ArrayList<Integer> turnAroundTime = new ArrayList<Integer>();
	static ArrayList<Integer> waitTime = new ArrayList<Integer>();
	static ArrayList<Integer> responseTime = new ArrayList<Integer>();
	
	
	public static ArrayList<Integer> getWaitTime() {
		return waitTime;
	}


	public static void setWaitTime(ArrayList<Integer> waitTime) {
		Scheduling.waitTime = waitTime;
	}


	public static ArrayList<Integer> getResponseTime() {
		return responseTime;
	}


	public static void setResponseTime(ArrayList<Integer> responseTime) {
		Scheduling.responseTime = responseTime;
	}


	public static ArrayList<Integer> getCompletionTime() {
		return completionTime;
	}


	public static void setCompletionTime(ArrayList<Integer> completionTime) {
		Scheduling.completionTime = completionTime;
	}


	public static ArrayList<Integer> getActualArrivalTime() {
		return actualArrivalTime;
	}


	public static void setActualArrivalTime(ArrayList<Integer> actualArrivalTime) {
		Scheduling.actualArrivalTime = actualArrivalTime;
	}


	public static ArrayList<Integer> getTurnAroundTime() {
		return turnAroundTime;
	}


	public static void setTurnAroundTime(ArrayList<Integer> turnAroundTime) {
		Scheduling.turnAroundTime = turnAroundTime;
	}


	public static ArrayList<String> getProcessID() {
		return processID;
	}


	public static void setProcessID(ArrayList<String> processID) {
		Scheduling.processID = processID;
	}


	public static ArrayList<Integer> getArrivalTime() {
		return arrivalTime;
	}


	public static void setArrivalTime(ArrayList<Integer> arrivalTime) {
		Scheduling.arrivalTime = arrivalTime;
	}


	public static ArrayList<Integer> getBurstTime() {
		return burstTime;
	}


	public static void setBurstTime(ArrayList<Integer> burstTime) {
		Scheduling.burstTime = burstTime;
	}


	public static ArrayList<Integer> TAT(ArrayList<Integer> arrivalT, ArrayList<Integer> completionTime2) {
		int holder;
		for (int i =0; i< arrivalT.size();i++) {
			holder = completionTime2.get(i) - arrivalT.get(i);
			turnAroundTime.add(holder);
			setTurnAroundTime(turnAroundTime);
			System.out.println("P"+i + " = " + holder);
		}
		return turnAroundTime;
	}
	public static int averageTAT() {
		//average tat
				int sum = 0;
				for(int i=0; i < turnAroundTime.size();i++) {
					sum += i;
				}
				return sum/turnAroundTime.size();
	}
	
	
	public static ArrayList<Integer> waitingTime(ArrayList<Integer> turnAroundTime,ArrayList<Integer> burst) {
		int holder;
		for (int i =0; i< turnAroundTime.size();i++) {
			holder = turnAroundTime.get(i) - burst.get(i);
			waitTime.add(holder);
			setWaitTime(waitTime);
			System.out.println("P"+i + " = " + holder);
		}
		return waitTime;
	}
	public static int averageWaitTime() {
		int sum = 0;
		for(int i=0; i < waitTime.size();i++) {
			sum += i;
		}
		return sum/waitTime.size();
	}
	public static ArrayList<Integer> responseTime (ArrayList<Integer> actualArrivalTime, ArrayList<Integer> arrivalTime){
		int holder;
		for (int i =0; i< arrivalTime.size();i++) {
			holder = actualArrivalTime.get(i) - arrivalTime.get(i);
			responseTime.add(holder);
			setResponseTime(responseTime);
			System.out.println("P"+i + " = " + holder);
		}
		return responseTime;
	}
	
	public static int averageResponseTime() {
		int sum = 0;
		for(int i=0; i < responseTime.size();i++) {
			sum += i;
		}
		return sum/responseTime.size();

	}
	public static void FCFS(ArrayList<Integer> arrivalT, ArrayList<Integer> burst) {
		int holder;
		for(int i=0;i < arrivalT.size();i++) {
			for(int j=i; j <arrivalT.size();j++) {
				if(arrivalT.get(i) == 0) {
					holder = arrivalT.get(i) + burst.get(i);
					completionTime.add(holder);
					actualArrivalTime.add(holder);
				}
//				holder = actualArrivalTime.get(i) + burst.get(i);
//				completionTime.add(holder);
//				actualArrivalTime.add(holder);
				
			
			}
		
		}
		

		setArrivalTime(arrivalT);
		setCompletionTime(completionTime);
		setActualArrivalTime(actualArrivalTime);
		
	}
	
	
	
	
	public static void main(String[] args) throws FileNotFoundException{
		System.out.println("-------------------------------------------------\n" + 
				"            CPU Scheduling Simulation\n" + 
				"-------------------------------------------------\n" + 
				"");
		Scanner scnr = new Scanner(System.in);
		
		File file = new File ("Text_file/Process");
		
		
		Scanner scan = new Scanner(file);
		
		while(scan.hasNextLine()) {
		String line = scan.nextLine();
		String[] details = line.split(" ");
		String process = details[0];
		int arrival = Integer.parseInt(details[1]);
		int burst = Integer.parseInt(details[2]);
		processID.add(process);
		arrivalTime.add(arrival);
		burstTime.add(burst);
		setProcessID(processID);
		setArrivalTime(arrivalTime);
		setBurstTime(burstTime);
		}
		
		System.out.println("-------------------------------------------------\n" + 
				"       First Come First Served Scheduling\n" + 
				"-------------------------------------------------\n" + 
				"");
		
		FCFS(getArrivalTime(),getBurstTime());
		
		System.out.println("Turnaround times: ");
		TAT(getArrivalTime(), getCompletionTime());
		System.out.println("Wait times: ");
		waitingTime(getTurnAroundTime(),getBurstTime());
		System.out.println("Response times: ");
		responseTime(getActualArrivalTime(), getArrivalTime());
		System.out.println("Average turnaround time: " + averageTAT());
		System.out.println("Average wait time: " + averageWaitTime());
		System.out.println("Average response time: " + averageResponseTime());
		
		
		//the end 
		System.out.print("\n" + 
				"-------------------------------------------------\n" + 
				"Project done by Anabel\n" + 
				"-------------------------------------------------\n" + 
				"\n" + 
				"");
	}

}






