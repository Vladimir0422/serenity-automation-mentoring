package serenity.bdd.config;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by : Volodymyr_Silitskyi
 * Created at : 12/2/2018
 */


public class DateTime {
    public String updateTimeStamp() {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(now);
    }
}
