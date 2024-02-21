// Checked By Sample, CONFIRMED CORRECT
//Hind 1202416 Original Code
class Process {
    int pid; // process ID
    int arrivalTime; // time arrival of process
    int burstTime; // time burst of process
    int[] startTime; // array that stores time starts for each time slot
    int completionTime; // completion time of process
    int turnaroundTime; // turnaround time of process
    int waitingTime; // waiting time of process
    int finalTime; // final time of process


    public Process(int pid, int arrivalTime, int burstTime) { // constructor
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.startTime = new int[20];

        // each element in startTime array  was initialized to -1
        for (int i = 0; i < this.startTime.length; i++) {
            this.startTime[i] = -1;
        }
    }

    // Second Constructor of process that initializes startTime array with a given size
    Process(int size) {
        startTime = new int[size];
        for (int i = 0; i < size; i++) {
            startTime[i] = -1; // each element in startTime array was initialized to -1
        }
    }
}