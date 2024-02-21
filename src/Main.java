// Checked By Sample, CONFIRMED CORRECT
//Hind 1202416 Original Code
import java.util.*;

public class Main {
    static int quantumTime =20; // identify quantumTime as 20 as professor asked for for RR algorithm

    public static void main(String[] args) {
        Process[] processes = generateRandomProcesses(); // call method to generate random processes
        System.out.println("This is Hind's (1202416) OS Project");
        System.out.println("⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻");
        float[][] avgFCFSArray = new float[][]{ // store the FCFS Algorithm's AWT and ATT in 2D array
                simulateAndPrintResults(processes, 100), // iterate the process 100 times
                simulateAndPrintResults(processes, 1000), // iterate the process 1000 times
                simulateAndPrintResults(processes, 10000), // iterate the process 10000 times
                simulateAndPrintResults(processes, 100000), // iterate the process 100000 times
        };

        displayFCFSResults(avgFCFSArray); // print the AWT and ATT results
        System.out.println("⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻");

        double[][] avgSRTFArray = new double[][]{ // store the SRTF Algorithm's ATT and AWT in 2D array
                runSRTF(processes, 100), // iterate the process 100 times
                runSRTF(processes, 1000), // iterate the process 1000 times
                runSRTF(processes, 10000), // iterate the process 10000 times
                runSRTF(processes, 100000), // iterate the process 100000 times
        };

        displaySRJResults(avgSRTFArray); // print the AWT and ATT results

        System.out.println("⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻");


        double[][] avgRRArray = new double[][]{ // store the RR Algorithm's ATT and AWT in 2D array
                runRoundRobin(processes, 100), // iterate the process 100 times
                runRoundRobin(processes, 1000), // iterate the process 1000 times
                runRoundRobin(processes, 10000), // iterate the process 10000 times
                runRoundRobin(processes, 100000), // iterate the process 100000 times
        };

        displayRRResults(avgRRArray); // print the AWT and ATT results


        System.out.println("⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻");

        double[][] avgMLFQArray = new double[][]{ // store the MLFQ Algorithm's ATT and AWT in 2D array
                runMLFQ(processes, 100), // iterate the process 100 times
                runMLFQ(processes, 1000), // iterate the process 1000 times
                runMLFQ(processes, 10000), // iterate the process 10000 times
                runMLFQ(processes, 100000), // iterate the process 100000 times
        };

        displayMLFQResults(avgMLFQArray); // print the AWT and ATT results
        System.out.println("⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻⸻");

    }

    //SRTF
    private static double[] runSRTF(Process[] processes, int numIterations) {
        int[] burstTime = new int[processes.length]; // identify burst time
        int[] temp = new int[processes.length]; //temporary array to store burst time
        int[] completionTime = new int[processes.length]; // array to store completion time
        int[] turnaroundTime = new int[processes.length]; //array to store turnaround time
        int[] waitingTime = new int[processes.length]; //array to store waiting time
        double waitTime = 0; // total waiting time
        double turnaroundTimeSum = 0; // sum of turnaround time

        for (int i = 0; i < processes.length; i++) { // loop to copy the burst time to the burst and temp arrays
            burstTime[i] = processes[i].burstTime;
            temp[i] = burstTime[i];
        }

        int count = 0; // count of completed processes
        int time = 0; // current time

        while (count != processes.length && time < numIterations) { // main loop to simulate the SRTF scheduling algorithm

            int smallest = findSmallestJob(time, processes, burstTime); // find the index of the process with the smallest remaining burst time

            if (smallest == -1) { // if no process is ready to execute, then the time will be incremented and continue
                time++;
                continue;
            }

            burstTime[smallest]--; // decrement the burst time of the selected process

            if (burstTime[smallest] == 0) { // if burst time is zero, the process is done
                count++;
                // store the completion time, turnaround time, and waiting time for the process that is completed
                completionTime[smallest] = time + 1;
                turnaroundTime[smallest] = completionTime[smallest] - processes[smallest].arrivalTime;
                waitingTime[smallest] = turnaroundTime[smallest] - temp[smallest];

                // update the total wait time and sum of turnaround time
                waitTime += waitingTime[smallest];
                turnaroundTimeSum += turnaroundTime[smallest];
            }

            time++; // increment time
        }

        // calculate AWT and ATT
        double averageWaitingTime = waitTime / processes.length;
        double averageTurnaroundTime = turnaroundTimeSum / processes.length;

        // return AWT and ATT
        return new double[]{averageTurnaroundTime, averageWaitingTime};
    }

    private static int findSmallestJob(int currentTime, Process[] processes, int[] burstTime) {
        int smallest = -1; // store the index of the process with the smallest burst time
        for (int i = 0; i < processes.length; i++) {
            if (processes[i].arrivalTime <= currentTime && burstTime[i] > 0) { // check if the process has arrived and has remaining burst time
                if (smallest == -1 || burstTime[i] < burstTime[smallest]) { // check if smallest is still -1 or the current process has a smaller burst time
                    smallest = i; // update smallest to the index of the current process
                }
            }
        }
        return smallest; // return the process index with the smallest burst time
    }
    // End SRTF

    // FCFS
    private static float[] simulateAndPrintResults(Process[] processes, int numIterations) {
        float totalAvgWaitingTime = 0, totalAvgTurnaroundTime = 0;

        for (int iteration = 0; iteration < numIterations; iteration++) { // loop through the number of iterations (100, 1000, 10000, 100000)
            Process[] clonedProcesses = cloneProcesses(processes); // Clone the original array of processes to avoid modifying the original
            float[] avgTimes = simulateFCFS(clonedProcesses); // simulate FCFS and get average turnaround and waiting time

            // collect the total average waiting time and total average turnaround time
            totalAvgWaitingTime += avgTimes[0];
            totalAvgTurnaroundTime += avgTimes[1];
        }
        // calculate the ATT and AWT
        float avgWaitingTime = totalAvgWaitingTime / numIterations / processes.length;
        float avgTurnaroundTime = totalAvgTurnaroundTime / numIterations / processes.length;

        return new float[]{avgTurnaroundTime, avgWaitingTime}; // return the results in 2D array
    }

    private static Process[] cloneProcesses(Process[] processes) {
        Process[] clonedProcesses = new Process[processes.length]; // create new array to store the clone processes
        for (int i = 0; i < processes.length; i++) { // iterate over each process in the original array
            clonedProcesses[i] = new Process(processes[i].pid, processes[i].arrivalTime, processes[i].burstTime); // create a new process object using the construct in the process class
        }
        return clonedProcesses; // return the array of cloned processes
    }

    // Generate the eight random processes
    private static Process[] generateRandomProcesses() {
        Process[] processes = new Process[8];
        Random random = new Random();
        for (int i = 0; i < 8; i++) { // create 8 processes with random arrival time and burst time
            int processId = i + 1;
            int arrivalTime = random.nextInt(96) + 5; // Random arrival time between 5 and 100 as professor asked to assign some kind of order for arrival time
            int cpuBurst = random.nextInt(96) + 5; // Random CPU-burst between 5 and 100 as professor asked in the ready queue with a random CPU-burst for each process between 5 & 100 time units

            processes[i] = new Process(processId, arrivalTime, cpuBurst); // input the pid, random time arrival and random burst time into process
        }

        Arrays.sort(processes, Comparator.comparingInt(p -> p.arrivalTime));
        return processes;
    }

    private static float[] simulateFCFS(Process[] processes) {
        float avgWaitingTime = 0, avgTurnaroundTime = 0;

        for (int i = 0; i < processes.length; i++) { // iterate over each process in the array
            if (i == 0) {
                // calculate completion time for the process using FCFS algo and logic
                processes[i].completionTime = processes[i].arrivalTime + processes[i].burstTime;
            } else {
                if (processes[i].arrivalTime > processes[i - 1].completionTime) {
                    processes[i].completionTime = processes[i].arrivalTime + processes[i].burstTime;
                } else {
                    processes[i].completionTime = processes[i - 1].completionTime + processes[i].burstTime;
                }
            }

            processes[i].turnaroundTime = processes[i].completionTime - processes[i].arrivalTime; // calculate the TT for the process
            processes[i].waitingTime = processes[i].turnaroundTime - processes[i].burstTime; // calculate the WT for the process

            // collect the waiting time and turnaround time (for the average)
            avgWaitingTime += processes[i].waitingTime;
            avgTurnaroundTime += processes[i].turnaroundTime;
        }

        return new float[]{avgWaitingTime, avgTurnaroundTime}; // return results in 2D array
    }
    // End FCFS


    // Start RR
    private static double[] runRoundRobin(Process[] processes, int numIterations) {
        double avgTurnaroundTime=0, avgWaitingTime = 0;
        for (int iteration = 0; iteration < numIterations; iteration++) { // iterate the processes dependent on the number (100, 1000, 10000, 100000)
            int c = processes.length;
            int[][] startTime = new int[processes.length][20];
            float time = 0;
            float[] remainingBurstTime = new float[processes.length];
            float[] arrivalTime = new float[processes.length];

            initializeArrays(processes, startTime, remainingBurstTime, arrivalTime);  // Initialize arrays and variables with the method

            while (c != 0) { // loop to simulate the RR algorithm
                float mini = Integer.MAX_VALUE;
                boolean flag = false;
                int index = -1;

                for (int i = 0; i < processes.length; i++) { // the loop is to find the process with the earliest arrival time and remaining burst time
                    float p1 = time + 0.1f;
                    if (arrivalTime[i] <= p1 && mini > arrivalTime[i] && remainingBurstTime[i] > 0) {
                        index = i;
                        mini = arrivalTime[i];
                        flag = true;
                    }
                }

                if (!flag) { // if none of the processes are ready to execute, then increment the time and continue
                    time++;
                    continue;
                }

                calculateStartTime(processes, startTime, time, index); // method to calculate start time

                if (remainingBurstTime[index] <= quantumTime) { // execute the process for quantum time or until its completed
                    time += remainingBurstTime[index];
                    remainingBurstTime[index] = 0;
                } else {
                    time += quantumTime;
                    remainingBurstTime[index] -= quantumTime;
                }

                if (remainingBurstTime[index] > 0) { // update arrival time if the process isn't completed yet
                    arrivalTime[index] = time + 0.1f;
                }

                if (remainingBurstTime[index] == 0) {
                    c--;
                    updateProcessDetails(processes, index, time); // method to update process details if the process has been completed
                }
            }

            // calculate the ATT and AWT for the current iteration
            avgWaitingTime = calculateAverageTurnaroundTime(processes);
            avgTurnaroundTime = calculateAverageWaitingTime(processes);
        }

        return new double[]{avgWaitingTime, avgTurnaroundTime}; // return the results in a 2D array
    }

    private static double calculateAverageTurnaroundTime(Process[] processes) { // method that calculates average of TT by adding all the TT and dividing it by the amount of processes
        double totalTurnaroundTime = 0;
        for (Process process : processes) {
            totalTurnaroundTime += process.turnaroundTime;
        }
        return totalTurnaroundTime / processes.length;
    }

    private static double calculateAverageWaitingTime(Process[] processes) { // method that calculates average of WT by adding all the WT and dividing it by the amount of processes
        double totalWaitingTime = 0;
        for (Process process : processes) {
            totalWaitingTime += process.waitingTime;
        }
        return totalWaitingTime / processes.length;
    }



    // Method to initialize arrays
    private static void initializeArrays(Process[] processes, int[][] startTime, float[] remainingBurstTime, float[] arrivalTime) {
        for (int i = 0; i < processes.length; i++) { // iterate each process in the array
            remainingBurstTime[i] = processes[i].burstTime; // the original burst time will be set for the remaining burst time for every process
            arrivalTime[i] = processes[i].arrivalTime; // the original arrival time will be set for the arrival time of every process
            Arrays.fill(startTime[i], -1); // startTime will be filled with -1s in its array for each process
        }
    }

    // Method to calculate start time
    private static void calculateStartTime(Process[] processes, int[][] startTime, float time, int index) {
        int j = 0;
        while (startTime[index][j] != -1) { // loop that searches for the first available time slot in the startTime array for the specified process
            j++;
        }

        if (startTime[index][j] == -1) { // if available slot is found, the start time is recorded
            startTime[index][j] = (int) time; // start time is recorded in 2D array
            processes[index].startTime[j] = (int) time; // start time in the process details is also recorded
        }
    }
    private static void updateProcessDetails(Process[] processes, int index, float time) {
        processes[index].finalTime = (int) time; // update the process's final time
        processes[index].waitingTime = processes[index].finalTime - processes[index].arrivalTime - processes[index].burstTime; // calculate the process's waiting time
        processes[index].turnaroundTime = processes[index].burstTime + processes[index].waitingTime; // calculate the process's turnaround time
    }

    // End RR Process

    // Start MLFQ
    private static double[] runMLFQ(Process[] processes, int numIterations) {
        double totalTurnaroundTime = 0, totalWaitingTime = 0;

        for (int iteration = 0; iteration < numIterations; iteration++) { // iterate the processes dependent on the number (100, 1000, 10000, 100000)
            int time = 0;
            int completedProcesses = 0;

            Queue<Process>[] queues = new LinkedList[3]; // create array of queues for each level in the MLFQ
            for (int i = 0; i < 3; i++) {
                queues[i] = new LinkedList<>();
            }

            for (Process process : processes) {
                queues[0].offer(process); // All processes are added to the first queue (initially)
            }

            while (completedProcesses < processes.length) { // loop to simulate MLFQ
                Process currentProcess = null;

                for (int i = 0; i < 3; i++) { // find the first non-empty queue
                    if (!queues[i].isEmpty()) {
                        currentProcess = queues[i].poll();
                        break;
                    }
                }

                if (currentProcess == null) { // if there is no process ready to execute, then increment the time and continue
                    time++;
                    continue;
                }

                int sliceTime;

                switch (currentProcess.startTime.length) { // determine the time slice based on the number of times the process has been in the system
                    case 0:
                        sliceTime = 10; // Q1 time slice, 10 as the professor wants
                        break;
                    case 1:
                        sliceTime = 50; // Q2 time slice, 50 as the professor wants
                        break;
                    default:
                        sliceTime = Integer.MAX_VALUE; // Q3 time slice, FCFSS as the professor wants
                        break;
                }

                int remainingBurstTime = currentProcess.burstTime - currentProcess.startTime.length; // execute the process for the calculated time slice

                // execute the process for the calculated time slice
                if (remainingBurstTime <= sliceTime) {
                    time += remainingBurstTime;

                    // update the process details for the completed process
                    currentProcess.finalTime = time;
                    currentProcess.turnaroundTime = currentProcess.finalTime - currentProcess.arrivalTime;
                    currentProcess.waitingTime = currentProcess.turnaroundTime - currentProcess.burstTime;

                    // collect and add the waiting times together and collect and add the turnaround times together (for average)
                    totalWaitingTime += currentProcess.waitingTime;
                    totalTurnaroundTime += currentProcess.turnaroundTime;

                    completedProcesses++;
                } else {
                    time += sliceTime;
                    // update the start time array for process
                    currentProcess.startTime = Arrays.copyOf(currentProcess.startTime, currentProcess.startTime.length + 1);
                    currentProcess.startTime[currentProcess.startTime.length - 1] = time;


                    queues[Math.min(currentProcess.startTime.length - 1, 2)].offer(currentProcess); // add the process to the appropriate queue based on level
                }
            }
        }

        // calculate the ATT and AWT for all iterations
        double avgWaitingTime = totalWaitingTime / (numIterations * processes.length);
        double avgTurnaroundTime = totalTurnaroundTime / (numIterations * processes.length);

        return new double[]{avgWaitingTime, avgTurnaroundTime}; // return results in 2D array
    }
    // end MLFQ


    private static void displayFCFSResults(float[][] avgFCFSArray) { // method that allows the FCFS to be printed the way the professor wants
        System.out.println("Times    100          1000         10000        100000");
        System.out.println("ATT      " + avgFCFSArray[0][0] + "          " + avgFCFSArray[1][0] + "          " + avgFCFSArray[2][0] + "          " + avgFCFSArray[3][0]);
        System.out.println("AWT      " + avgFCFSArray[0][1] + "          " + avgFCFSArray[1][1] + "          " + avgFCFSArray[2][1] + "          " + avgFCFSArray[3][1]);
        System.out.println();
        System.out.println("                            FCFS                                                 ");
    }

    private static void displaySRJResults(double[][] avgSRTFArray) { // method that allows the SRTF to be printed the way the professor wants
        System.out.println("Times    100          1000         10000        100000");
        System.out.println("ATT      " + avgSRTFArray[0][0] + "          " + avgSRTFArray[1][0] + "          " + avgSRTFArray[2][0] + "          " + avgSRTFArray[3][0]);
        System.out.println("AWT      " + avgSRTFArray[0][1] + "          " + avgSRTFArray[1][1] + "          " + avgSRTFArray[2][1] + "          " + avgSRTFArray[3][1]);
        System.out.println();
        System.out.println("                            SJR                                                 ");
    }

    private static void displayRRResults(double[][] avgRRArray) { // method that allows the RR to be printed the way the professor wants
        System.out.println("Times    100          1000          10000         100000");
        System.out.println("ATT      " + avgRRArray[0][0] + "          " + avgRRArray[1][0] + "          " + avgRRArray[2][0] + "          " + avgRRArray[3][0]);
        System.out.println("AWT      " + avgRRArray[0][1] + "          " + avgRRArray[1][1] + "          " + avgRRArray[2][1] + "          " + avgRRArray[3][1]);
        System.out.println();
        System.out.println("                            RR                                                 ");
    }

    private static void displayMLFQResults(double[][] avgMLFQArray) { // method that allows the MLFQ to be printed the way the professor wants
        System.out.println("Times    100          1000         10000        100000");
        System.out.println("ATT      " + avgMLFQArray[0][0] + "          " + avgMLFQArray[1][0] + "          " + avgMLFQArray[2][0] + "          " + avgMLFQArray[3][0]);
        System.out.println("AWT      " + avgMLFQArray[0][1] + "          " + avgMLFQArray[1][1] + "          " + avgMLFQArray[2][1] + "          " + avgMLFQArray[3][1]);
        System.out.println();
        System.out.println("                          MLFQ                                                 ");
        // potentially the MLFQ will not result in correct ATT and AWT
    }
}
