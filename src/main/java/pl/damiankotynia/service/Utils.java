package pl.damiankotynia.service;


public class Utils {
    public static boolean isEmpty(Object object){
        if(object==null){
            return true;
        }
        return false;
    }

 /*   public boolean serviceNotEmpty(Service service){
        if(isBlank(service.getCustomerName())&&service.getStartTime()!=null)
            return false;

    }*/

    public boolean isBlank(String str){
        if(str==null)
            return false;
        str.replace("\\s", "");
        if ("".equals(str))
           return false;
        return true;
    }

    public static final String DATABASE_LOGGER = "DATABASE \t\t >>>> ";
    public static final String CONNECTION_LOGGER = "CONNECTION \t\t >>>> ";
    public static final String REQUEST_EXECUTOR_LOGGER = "REQUEST_EXE \t >>>> ";
}
