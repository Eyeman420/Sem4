package lab4;

import java.util.Scanner;

public class test {
	public static void main(String args[]) {
		System.out.println("test lab4");
		int  temp, sq = 0; // sq = sum semua burst yg complete
		int count = 0;
		int quantumTime = 3;
		int n = 4;
		float totalWaitingTime = 0, totalTurnAroundTime = 0;
		int[] burst = { 7, 4, 1, 4};
		int[] waitingTime = new int[4];
		int[] tat = new int[4];
		int[] rem_bt = { 7, 4, 1, 4};
		int[] flag = { 0, 0, 0, 0}; // status klau lom siap 0, dh siap 1
		boolean complete = false;
		int i = 0;
		Scanner s = new Scanner(System.in);
//		System.out.print("Enter the number of process (maximum 10) = ");
//		n = s.nextInt();
//		System.out.print("Enter the burst time of the process\n");
//		for (i = 0; i < n; i++) {
//			System.out.print("P" + i + " = ");
//			bt[i] = s.nextInt();
//			rem_bt[i] = bt[i];
//		}
//		System.out.print("Enter the quantum time: ");
//		qt = s.nextInt();
		while (complete == false) {
			for (i = 0, count = 0; i < n; i++) {
				temp = quantumTime;

				System.out.println("Nilai remainder " + (i + 1) + " = " + rem_bt[i]);
				System.out.println("COUNT = " + count);
				// klau current remainder == 0
				if (rem_bt[i] == 0) {
					System.out.println("remainder " + (i + 1) + "= 0");
					count++;
					System.out.println("COUNT++ = " + count);
					continue;
				}

				// Kalau remainder > Quantum
				if (rem_bt[i] > quantumTime) {
					System.out.println("remainder " + (i + 1) + " lagi besar dari quantum");
					rem_bt[i] = rem_bt[i] - quantumTime; // remainder = remainder - quantum
					System.out.println("Nilai remainder lps tolak Quantum " + (i + 1) + " = " + rem_bt[i]);
				}

				// Kalau remainder < Quantum
				else if (rem_bt[i] >= 0) {
					System.out.println("remainder " + (i + 1) + " kurang dari quantum: " + rem_bt[i]);
					temp = rem_bt[i];
					System.out.println("nilai temp: " + temp);
					rem_bt[i] = 0;
					System.out.println("remainder " + (i + 1) + " menjadi 0");
					flag[i] = 1;
				}
				sq = sq + temp;
				System.out.println("nilai sq: " + sq);
				tat[i] = sq;
				System.out.println("nilai turn Around Time: " + (i + 1) + ": " + tat[i]);

				System.out.println();
			}
			for (int x = 0; x < n; x++ ) {
				if (flag[x] == 1){
					break;
				}
				complete = true;
			}
			complete = checkFlag(flag, n);

//			if (n == count) {
////				System.out.println("loop stop");
//				break;
//				}
		}
		System.out.println("\n\n\n\n");
		System.out.print("--------------------------------------------------------------------------------");
		System.out.print("\nProcess\t      Burst Time\t       Turnaround Time\t          Waiting Time\n");
		System.out.print("--------------------------------------------------------------------------------");
		for (i = 0; i < n; i++) {
			waitingTime[i] = tat[i] - burst[i];
			totalWaitingTime = totalWaitingTime + waitingTime[i];
			totalTurnAroundTime = totalTurnAroundTime + tat[i];
			System.out.print("\n " + (i + 1) + "\t "+ "\t " + burst[i] + "\t\t "+ "\t " + tat[i] + "\t\t "+ "\t " + waitingTime[i] + "\n");
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