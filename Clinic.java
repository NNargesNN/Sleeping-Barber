import java.util.concurrent.Semaphore;

public class Clinic extends Thread {
    public int[] DoctorChairStates;
    public long time;
    public int NumberOfDoctors;
    public int NumberOfPatients;
    public int NumberOfFreeChairs;
    public int NumberOfChairs;
    public Semaphore PATIENTsem;
    private Patient[] patientList;
    private Doctor[] doctorList;
    public Semaphore waitingRoomMutex;
    public Semaphore DoctorRoomMutex;
    public Semaphore Doctors;
    public Semaphore[] DoctorChairs;
    public Semaphore Chairs;


    public Clinic(int numberOfDoctors, Patient[] Patients, int NumberOfChairs) {

        DoctorChairStates = new int[numberOfDoctors];
        DoctorRoomMutex = new Semaphore(1);
        DoctorChairs = new Semaphore[numberOfDoctors];
        for (int i = 0; i < numberOfDoctors; i++) {
            DoctorChairs[i] = new Semaphore(0);
            DoctorChairStates[i] = 0;
        }
        waitingRoomMutex = new Semaphore(1);
        PATIENTsem = new Semaphore(0);
        NumberOfDoctors = numberOfDoctors;
        patientList = Patients;
        doctorList = new Doctor[numberOfDoctors];
        NumberOfPatients = Patients.length;
        NumberOfFreeChairs = NumberOfChairs;
        this.NumberOfChairs = NumberOfChairs;
        Chairs = new Semaphore(NumberOfChairs);
        Doctors = new Semaphore(numberOfDoctors);

    }


    public void RunClinic() {

        for (int i = 0; i < NumberOfDoctors; i++) {
            Doctor doctor = new Doctor(i);
            doctorList[i] = doctor;
            doctorList[i].start();
        }
        for (int i = 0; i < NumberOfPatients; i++) {
            Patient patient = new Patient(patientList[i].PatienName, patientList[i].time);
            patient.start();

        }
        this.time = System.currentTimeMillis();

    }

}
