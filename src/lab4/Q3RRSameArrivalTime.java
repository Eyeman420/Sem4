package lab4;

import java.util.Scanner;

public class Q3RRSameArrivalTime {

	public static void main(String args[]) {
		System.out.println("test lab4");
		int temp, totalBurst = 0; // totalBurst = sum semua burst yg complete
		int quantumTime = 3;
		int n = 5;
		float totalWaitingTime = 0, totalTurnAroundTime = 0;
		int[] burst = { 6, 4, 2, 5, 3 };
		int[] waitingTime = new int[10];
		int[] turnAroundTime = new int[10];
		int[] remaining = { 6, 4, 2, 5, 3 };
		int[] flag = { 0, 0, 0, 0, 0 }; // status klau lom siap 0, dh siap 1
		int count = 0;
		boolean complete = false;
		char processID = 'A';
		
		Scanner s = new Scanner(System.in);
		System.out.print("Enter the number of process (maximum 10) = ");
		n = s.nextInt();
		System.out.print("Enter the burst time of the process\n");
		for (int i = 0; i < n; i++) {
			System.out.print("P" + i + " = ");
			burst[i] = s.nextInt();
			remaining[i] = burst[i];
		}
		System.out.print("Enter the quantum time: ");
		quantumTime = s.nextInt();
		while (complete == false) {
			for (int i = 0; i < n; i++) {
				temp = quantumTime;

				System.out.println("Nilai remainder " + (i + 1) + " = " + remaining[i]);
				// klau current remainder == 0
				if (remaining[i] == 0) {
					System.out.println("remainder " + (i + 1) + "= 0");
					count++;
					continue;
				}

				// Kalau remainder > Quantum
				if (remaining[i] > quantumTime) {
					System.out.println("remainder " + (i + 1) + " lagi besar dari quantum");
					remaining[i] = remaining[i] - quantumTime; // remainder = remainder - quantum
					System.out.println("Nilai remainder lps tolak Quantum " + (i + 1) + " = " + remaining[i]);
				}

				// Kalau remainder < Quantum
				else if (remaining[i] >= 0) {
					System.out.println("remainder " + (i + 1) + " kurang dari quantum: " + remaining[i]);
					temp = remaining[i];
					System.out.println("nilai temp: " + temp);
					remaining[i] = 0;
					System.out.println("remainder " + (i + 1) + " menjadi 0");
					flag[i] = 1;
				}
				totalBurst = totalBurst + temp;
				System.out.println("nilai sq: " + totalBurst);
				turnAroundTime[i] = totalBurst;
				System.out.println("nilai turn Around Time: " + (i + 1) + ": " + turnAroundTime[i]);

				System.out.println();
			}
//			for (int x = 0; x < n; x++ ) {
//				if (flag[x] == 1){
//					break;
//				}
//				complete = true;
//			}
			complete = checkFlag(flag, n);

//			if (n == count)
////				System.out.println("loop stop");
//				break;
		}
		System.out.println("\n\n\n\n");
		System.out.print("--------------------------------------------------------------------------------");
		System.out.print("\nProcess\t      Burst Time\t       Turnaround Time\t          Waiting Time\n");
		System.out.print("--------------------------------------------------------------------------------");
		for (int i = 0; i < n; i++) {
			waitingTime[i] = turnAroundTime[i] - burst[i];
			totalWaitingTime = totalWaitingTime + waitingTime[i];
			totalTurnAroundTime = totalTurnAroundTime + turnAroundTime[i];
			System.out.print("\n " + processID + "\t " + burst[i] + "\t\t " + turnAroundTime[i] + "\t\t " + waitingTime[i] + "\n");
			processID++;
		}
		totalWaitingTime = totalWaitingTime / n;
		totalTurnAroundTime = totalTurnAroundTime / n;
		System.out.println("\nAverage waiting Time = " + totalWaitingTime + "\n");
		System.out.println("Average turnaround time = " + totalTurnAroundTime);
	}

	public static boolean checkFlag(int[] flag, int n) {
		for (int i = 0; i < n; i++) {
			if (flag[i] == 0) {
				return false;
			}
		}
		return true;
	}
}