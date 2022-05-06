//Written by MUHAMMAD AIMAN IRFAN BIN SHAHREL 205774
//This program is to execute FCFS Scheduling

package lab4;

public class Q1FCFS {
	
	public static void main (String[] args) {
		
		// Declare variables
		int n = 5;
		int i = 0;
		char[] process = {'A','B','C','D','E'};
		int[] arrivalTime = {0,2,5,6,9};
		int[] burstTime = {6,4,1,3,5};
		int[] waitingTime = new int[n];
		int[] turnAroundTime = new int[n];
		int[] totalTime = new int[n];
		
		System.out.println("|Process   |Waiting Time   |Time Turn Around   |");
		
		// Calculate the FCFS
		for (i = 0; i < process.length; i++) {
			
			// For initial process
			if (i == 0) {
				totalTime[i] = arrivalTime[i] + burstTime[i];
				turnAroundTime[i] = burstTime[i];
				waitingTime[i] = arrivalTime[i];
			}
			
			else{
				totalTime[i] = totalTime[i -1] + burstTime[i]; 
				waitingTime[i] = totalTime[i-1] - arrivalTime[i];
				turnAroundTime[i] = burstTime[i] + waitingTime[i];
				
			}                           
			
			// Print the result
			System.out.println(String.format("|%-10c|%-15d|%-19d|",process[i], waitingTime[i], turnAroundTime[i] ));
		}
		
		System.out.println("\nAvg Waiting Time = " + (float)totalTime[i-1]/n);
		
	}
}
