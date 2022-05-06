package lab5;

import java.util.Scanner;

public class Main {
	
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
//		System.out.println ("enter no of process:");
//		int n = sc.nextInt();
		int n = 5;
		int process_id[] = {'A','B','C','D','E'};
		int complete_time[] = new int[n]; 
		int arrival_time[] = {0,2,5,6,9};
		int burst_time[] = {6,4,1,3,5};
		int turn_around_time[] = new int[n];
		int waiting_time[] = new int[n];  //wt means waiting time
		int f[] = new int[n];  // f means it is flag it checks process is completed or not
		int st=0, total_processes=0;
		float avgwt=0, avgta=0;
 
		for(int i=0;i<n;i++)
		{
			System.out.println ("enter process " + (i+1) + " arrival time:");
			arrival_time[i] = sc.nextInt();
			System.out.println ("enter process " + (i+1) + " brust time:");
			burst_time[i] = sc.nextInt();
			process_id[i] = i+1;
			f[i] = 0;
		}
		
		boolean a = true;
		while(true)
		{
			int c=n, min=999;
			if (total_processes == n) // 0                              1 2 3 4 5
				break;
			
			//                        st = 6 7 10 14 -
			for (int i=0; i<n; i++) // 1 2 3 4
			{
				/*
				 * If i'th process arrival time is less than or equal to system time and its flag=0 and burst<min 
				 * this process will be first 
				 */ 
				if ((arrival_time[i] <= st) && (f[i] == 0) && (burst_time[i]<min))
				{
					min=burst_time[i]; // min = 6 4 1 4 3 4 5
					c=i; // c = 0 1 2 1 3 1 4
				}
			}
			
			/* If c==n means c value can not updated because no process arrival time< system time so we increase the system time */
			// if (c==n) 
				// System.out.println("dasdad");
			if (c!=n)
			{
				// 	Masuk sini untuk completekan process
				complete_time[c]=st+burst_time[c]; // complete [0] = 6 7 10 14 19
				st+=burst_time[c]; // st = 6 7 10 14 19
				turn_around_time[c]=complete_time[c]-arrival_time[c]; // turn_around_time[0] = 6 2 4 12 10
				waiting_time[c]=turn_around_time[c]-burst_time[c]; // 0 1 1 8 5
				f[c]=1;
				total_processes++; // 1 2 3 4 5
			}
		}
		
		System.out.println("\npid  arrival brust  complete turn waiting");
		for(int i=0;i<n;i++)
		{
			avgwt+= waiting_time[i];
			avgta+= turn_around_time[i];
			System.out.println(process_id[i]+"\t"+arrival_time[i]+"\t"+burst_time[i]+"\t"+complete_time[i]+"\t"+turn_around_time[i]+"\t"+waiting_time[i]);
		}
		System.out.println ("\naverage tat is "+ (float)(avgta/n));
		System.out.println ("average wt is "+ (float)(avgwt/n));
		sc.close();
	}
}