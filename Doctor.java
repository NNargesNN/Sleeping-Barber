import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Doctor extends Thread {
    private int Doctor_ID;


    public Doctor(int doctor_ID) {

        Doctor_ID = doctor_ID;

    }


    public void Visit() throws InterruptedException {

        while (true) {
            Main.clinic.PATIENTsem.acquire();
            Main.clinic.waitingRoomMutex.acquire();
            Main.clinic.NumberOfFreeChairs++;
            Main.clinic.waitingRoomMutex.release();
            Thread.sleep(2000);
            Main.clinic.DoctorRoomMutex.acquire();
            Main.clinic.DoctorChairStates[Doctor_ID] = 0;
            Main.clinic.DoctorChairs[Doctor_ID].release();
            Main.clinic.DoctorRoomMutex.release();
            Main.clinic.Doctors.release();

        }

    }


    @Override
    public void run() {

        try {
            Visit();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
