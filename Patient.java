import java.util.concurrent.Semaphore;

public class Patient extends Thread {
    public String PatienName;
    int time;
    int DoctorID = -1;


    public Patient(String patienName, int time) {

        PatienName = patienName;
        this.time = time;

    }


    public void EnterWaitingRoom() throws InterruptedException {

        Thread.sleep(time * 1000);
        System.out.println(PatienName + "  Enters at time : " + time);
        System.out.flush();
        Main.clinic.waitingRoomMutex.acquire();
        if (Main.clinic.NumberOfFreeChairs > 0) {
            Main.clinic.NumberOfFreeChairs--;
            Main.clinic.PATIENTsem.release();
            Main.clinic.waitingRoomMutex.release();
            Main.clinic.Doctors.acquire();
            Main.clinic.DoctorRoomMutex.acquire();
//            System.out.println("STATUS:");
//            for (int i = 0; i < Main.clinic.NumberOfDoctors; i++) {
//                System.out.println(PatienName + "  DoctorChairStates[" + i + "]=" + Main.clinic.DoctorChairStates[i]);
//            }
            for (int i = 0; i < Main.clinic.NumberOfDoctors; i++) {
                if (Main.clinic.DoctorChairStates[i] == 0) {
                    Main.clinic.DoctorChairStates[i] = 1;
                    DoctorID = i;
                    break;
                }
            }
            Main.clinic.DoctorRoomMutex.release();
            System.out.println(PatienName + "  goes to  Doctor  " + DoctorID);
            Main.clinic.DoctorChairs[DoctorID].acquire();
            System.out.println(PatienName + "  ENDS at time :  " + (int) ((System.currentTimeMillis() - Main.clinic.time) / 1000) + "  from doctor " + DoctorID);
            System.out.flush();
        } else  // there are NO free seats
        {
            Main.clinic.waitingRoomMutex.release();
            System.out.println(PatienName + "  is leaving clinic  , no free seats available");
            System.out.flush();
        }

    }


    @Override
    public void run() {

        try {
            EnterWaitingRoom();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
