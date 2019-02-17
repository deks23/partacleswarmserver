package pl.damiankotynia.partacleswarm.database;

import pl.damiankotynia.partacleswarm.exceptions.PermissionDeniedException;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;



public class Database {

/*
    private static Database instance;
    private List<Service> data;

    private Database(){
        data = new LinkedList<>();
    }

    public static Database getInstance(){
        if (instance==null) {
            instance = new Database();
        }
        return instance;
    }

    public synchronized boolean save(Service service){
        if("TESTER".equalsIgnoreCase(service.getCustomerName())){
            double a = 1;
            for(long i = 0; i<1000000000; i++){
                a = a *0.00005;
            }
        }
        if(checkIfAvalible(service.getStartTime())){
            service.setId(getUUID());
            data.add(service);
            System.out.println(DATABASE_LOGGER + "dodano do bazy" + service.toString());
            return true;
        }else{
            System.out.println(DATABASE_LOGGER + " termin niedostepny " + service.toString());
            return false;
        }

    }

    public synchronized List<Service> findByCustomerName(String nick){
        List<Service> returnList = new LinkedList<>();
        for (Service service : data){
            if(service.getCustomerName().equals(nick))
                returnList.add(service);
        }
        return returnList;
    }

    public synchronized List<Service> getAll(){
        return data;
    }

    public synchronized boolean remove(Service service){
        if("TESTER".equalsIgnoreCase(service.getCustomerName())){
            double a = 1;
            for(long i = 0; i<1000000000; i++){
                a = a *0.00005;
            }
        }
        return data.remove(service);
    }

    private boolean checkIfAvalible(LocalDateTime startTime){
        LocalDateTime endTime = startTime.plusHours(1L);
        if(START_TIME.isAfter(startTime.toLocalTime()))
            return false;
        if(FINISH_TIME.isBefore(endTime.toLocalTime()))
            return false;

        for (Service service : data){
            if(service.getStartTime().equals(startTime))
                return false;
        }
        return true;
    }




    private String getUUID(){
        return UUID.randomUUID().toString();
    }
*/



    private static final LocalTime START_TIME = LocalTime.of(10, 0);
    private static final LocalTime FINISH_TIME = LocalTime.of(18, 0);
}

