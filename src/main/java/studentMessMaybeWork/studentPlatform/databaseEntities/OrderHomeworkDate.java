package studentMessMaybeWork.studentPlatform.databaseEntities;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class OrderHomeworkDate {
    public static List<Date> sortByDate(List<Date> dateList){
        Collections.sort(dateList);
        return dateList;
    }
}
