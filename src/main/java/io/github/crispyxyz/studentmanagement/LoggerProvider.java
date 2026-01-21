package io.github.crispyxyz.studentmanagement;

import io.github.crispyxyz.studentmanagement.model.Person;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class LoggerProvider {
    /**
     * 日期格式化器，用于日志时间戳
     * 格式：年-月-日 时:分:秒
     */
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 记录日志到控制台
     * @param action 操作类型
     * @param data 涉及的操作对象
     */
    public static void log(String action, Object data) {
        String msg;
        if(data instanceof Person) {
            Person p = (Person)data;
            msg = p.getId();
        } else {
            msg = Objects.toString(data);
        }
        System.out.printf("[%s] [%s] 影响对象ID: %s%n", DATE_FORMAT.format(new Date()), action, msg);
    }
}
