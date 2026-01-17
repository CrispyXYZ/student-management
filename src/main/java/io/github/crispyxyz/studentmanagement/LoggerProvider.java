package io.github.crispyxyz.studentmanagement;

import io.github.crispyxyz.studentmanagement.model.Person;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggerProvider {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void log(String action, Object data) {
        String msg;
        if(data instanceof Person) {
            Person p = (Person)data;
            msg = p.getId();
        } else {
            msg = data.toString();
        }
        System.out.printf("[%s] [%s] 影响对象ID: %s%n", DATE_FORMAT.format(new Date()), action, msg);
    }
}
