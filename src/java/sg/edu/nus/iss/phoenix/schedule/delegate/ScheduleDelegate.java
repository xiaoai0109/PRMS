package sg.edu.nus.iss.phoenix.schedule.delegate;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.service.ScheduleService;

public class ScheduleDelegate {

    public ArrayList<ProgramSlot> findAllPS() {
        ScheduleService service = new ScheduleService();
        return service.findAllPS();

    }

    public boolean checkScheduleOverlap(Date date, Time sttime, Time duration) {
        ScheduleService service = new ScheduleService();
        return service.checkScheduleOverlap(date, sttime, duration);
    }

    public void processCreate(ProgramSlot ps) {
        ScheduleService service = new ScheduleService();
        service.processCreate(ps);

    }
    public void processModify(ProgramSlot ps) {
        ScheduleService service = new ScheduleService();
        service.processModify(ps);

    }

//    public void processDelete(String rpname, Date date, Time sttime) {
//        ScheduleService service = new ScheduleService();
//        service.processDelete(rpname, date, sttime);
//    }
    public void processDelete(int id) {
        ScheduleService service = new ScheduleService();
        service.processDelete(id);
    }
}
