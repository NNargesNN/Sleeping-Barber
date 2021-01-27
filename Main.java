import java.util.Scanner;

public class Main {
    public static Clinic clinic;


    public static void main(String[] args)  {

        Patient[] patients = new Patient[]{
                new Patient("Patient1", 1),
                new Patient("Patient2", 1),
                new Patient("Patient3", 1),
                new Patient("Patient4", 1),
                new Patient("Patient5", 1),
                new Patient("Patient6", 1),
                new Patient("Patient7", 1),
                new Patient("Patient8", 1),
                new Patient("Patient9", 1),
                new Patient("Patient10", 5),
                new Patient("Patient11", 6),
                new Patient("Patient12", 7),
                new Patient("Patient13", 7),
                new Patient("Patient14", 7),
                new Patient("Patient15", 8),
                new Patient("Patient16", 8),
                new Patient("Patient17", 8),
                new Patient("Patient18", 8),
                new Patient("Patient19", 9),
                new Patient("Patient20", 9),
                new Patient("Patient21", 9),
                new Patient("Patient22", 10),
                new Patient("Patient23", 11),
                new Patient("Patient24", 11),
        };
        Scanner scanner = new Scanner(System.in);
        int NumberOfDoctors = 3;
        int NumberOfChairs = 6;
        System.out.println("Clinic With " + NumberOfDoctors + " Doctors And " + NumberOfChairs + " Chairs " + patients.length + " Patients");
        System.out.println("____________________________________________");
        clinic = new Clinic(NumberOfDoctors, patients, NumberOfChairs);
        clinic.RunClinic();

    }
}
