package sg.edu.nus.iss.phoenix.schedule.service;

import java.sql.Date;
import java.sql.Time;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Calendar;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

public class ScheduleService {
    DAOFactoryImpl factory;
    ScheduleDAO psdao;

    public ScheduleService() {
        super();
        // Sorry. This implementation is wrong. To be fixed. mia what????
        factory = new DAOFactoryImpl();
        psdao = factory.getScheduleDAO();
    }

//    public ArrayList<ProgramSlot> searchSchedules(ProgramSlot psso) {
//        ArrayList<ProgramSlot> list = new ArrayList<ProgramSlot>();
//        try {
//            list = (ArrayList<ProgramSlot>) psdao.searchMatching(psso);
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return list;
//    }

//    public ArrayList<ProgramSlot> findPSByCriteria(ProgramSlot ps) {
//        ArrayList<ProgramSlot> currentList = new ArrayList<ProgramSlot>();
//
//        try {
//            currentList = (ArrayList<ProgramSlot>) psdao.searchMatching(ps);
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        return currentList;
//    }

//    public ProgramSlot findPS(String rpname) {
//        ProgramSlot currentps = new ProgramSlot();
//        currentps.setRpname(rpname);
//        try {
//            currentps = ((ArrayList<ProgramSlot>) psdao
//                    .searchMatching(currentps)).get(0);
//            return currentps;
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return currentps;
//    }

    public ArrayList<ProgramSlot> findAllPS() {
        ArrayList<ProgramSlot> currentList = new ArrayList<ProgramSlot>();
        try {
            currentList = (ArrayList<ProgramSlot>) psdao.loadAll();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return currentList;

    }

    public void processCreate(ProgramSlot ps) {
        try {
            psdao.create(ps);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void processModify(ProgramSlot ps) {

        try {
            System.out.println(ps.toString());
            psdao.save(ps);

//            System.out.println(oldPs.toString());
//            psdao.save(ps, oldPs);

        } catch (NotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void processDelete(int id) {

        try {
//            ProgramSlot ps = new ProgramSlot(id);
            psdao.delete(id);
        } catch (NotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // Mia: not correct
    public boolean checkScheduleOverlap(Date date, Time sttime, Time duration) {
        try {
            Time etime = (Time) sttime.clone();
            etime.setTime(sttime.getTime() + duration.getTime() + 8 * 3600 * 1000);
            System.out.print(etime.toString());
            
//            ZoneOffset offset = ZoneOffset.ofHours(+8);
//            LocalTime utc =
//                    OffsetTime.of(etime.toLocalTime(), offset).withOffsetSameInstant(ZoneOffset.UTC).toLocalTime();
//            etime = Time.valueOf(utc);

            System.out.print("sttime: " + sttime.getTime());
            System.out.print("duration: " + duration.getTime());
            System.out.print("duration: " + duration.toString());

            System.out.print(etime.toString());
            return psdao.checkOverlap(date, sttime, duration);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return false;

    }

}
