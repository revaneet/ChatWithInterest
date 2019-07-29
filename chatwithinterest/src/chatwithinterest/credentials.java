package chatwithinterest;

import java.sql.Timestamp;
import java.util.Date;

public class credentials {

    static String dbusername = "root";
    static String dbpassword = "system";
    static String dburlconnectivity = "jdbc:mysql://127.0.0.1:3306/chatwithinterest";

    public static void main(String[] args) {
        long time = 1000 * 60 * 60 * 24;    

        System.out.println(time);

        Date d = new Date();
        Timestamp t = new Timestamp(d.getTime() - time);
        System.out.println(t);
//        System.out.println(new Date(d.getTime() - time));
    }

}
