package lab4;

import java.util.Scanner;

public class Q2SJFPreemptive {
	
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
//		System.out.println ("enter no of process:");
//		int n = sc.nextInt();
		int n = 5;
		char process[] = {'A','B','C','D','E'};
		int completeTime[] = new int[n]; 
		int arrivalTime[] = {0,2,5,6,9};
		int burstTime[] = {6,4,1,3,5};
		int turnAroundTime[] = new int[n];
		int waitingTime[] = new int[n];  //wt means waiting time
		int flag[] = new int[n];  // checks process is completed or not
		int systemTime=0, total_processes=0;
		float totalWaitingTime=0, totalTimeArrival=0;
 
		for(int i=0;i<n;i++)
		{
//			System.out.println ("enter process " + (i+1) + " arrival time:");
//			arrival_time[i] = sc.nextInt();
//			System.out.println ("enter process " + (i+1) + " brust time:");
//			burst_time[i] = sc.nextInt();
//			process[i] = i+1;
			flag[i] = 0;
		}
		
		//boolean a = true;
		while(true)
		{
			int current =n, min=999; //set min value 
			if (total_processes == n) // 0                              1 2 3 4 5
				break;
			
			//                        st = 6 7 10 14 -
			for (int i=0; i<n; i++) // 1 2 3 4
			{
				/*
				 * If i'th process arrival time is less than or equal to system time and its flag=0 and burst<min 
				 * this process will be first 
				 */ 
				if ((arrivalTime[i] <= systemTime) && (flag[i] == 0) && (burstTime[i]<min))
				{
					min=burstTime[i]; // min = 6 4 1 4 3 4 5
					current = i; // c = 0 1 2 1 3 1 4
				}
			}
			
			if (current != n)
			{
				// 	Masuk sini untuk completekan process
				completeTime[current] = systemTime + burstTime[current]; // complete [0] = 6 7 10 14 19
				systemTime += burstTime[current]; // st = 6 7 10 14 19
				turnAroundTime[current] = completeTime[current] - arrivalTime[current]; // turn_around_time[0] = 6 2 4 12 10
				waitingTime[current] = turnAroundTime[current] - burstTime[current]; // 0 1 1 8 5
				flag[current] = 1;
				total_processes++; // 1 2 3 4 5
			}
		}
		
		System.out.println("|Process   |Time Turn Around   |Waiting Time|");
		for(int i=0;i<n;i++)
		{
			totalWaitingTime+= waitingTime[i];
			totalTimeArrival+= turnAroundTime[i];
			System.out.println(String.format("|%-10c|%-19d|%-12d|",process[i], turnAroundTime[i], waitingTime[i] ));
			//System.out.println(process[i]+"\t"+arrival_time[i]+"\t"+burst_time[i]+"\t"+complete_time[i]+"\t"+turn_around_time[i]+"\t"+waiting_time[i]);
		}
		System.out.println ("\nAverage Turn Around Time: "+ (float)(totalTimeArrival/n));
		System.out.println ("Average Waiting Time: "+ (float)(totalWaitingTime/n));
		sc.close();
	}
}