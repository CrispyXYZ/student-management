package io.github.crispyxyz.studentmanagement;

import io.github.crispyxyz.studentmanagement.exception.DataNotFoundException;
import io.github.crispyxyz.studentmanagement.model.Person;

import java.util.List;

public class GenericDataManager {

    public static <T extends Person> void add(List<T> list, T person) {
        LoggerProvider.log("增", person);
        list.add(person);
    }

    public static <T extends Person> void deleteById(List<T> list, String id) throws DataNotFoundException {
        LoggerProvider.log("删", id);
        for(T person : list) {
            if(person.getId().equals(id)) {
                list.remove(person);
                return;
            }
        }
        throw new DataNotFoundException("ID为"+id+"的对象在列表中不存在");
    }

    public static <T extends Person> void update(List<T> list, T person) throws DataNotFoundException {
        LoggerProvider.log("改", person);
        for(T p : list) {
            if(p.getId().equals(person.getId())) {
                list.remove(p);
                list.add(person);
                return;
            }
        }
        throw new DataNotFoundException("ID为"+person.getId()+"的对象在列表中不存在");
    }

    public static <T extends Person> T findById(List<T> list, String id) throws DataNotFoundException {
        for(T person : list) {
            if(person.getId().equals(id)) {
                return person;
            }
        }
        throw new DataNotFoundException("找不到ID为"+id+"的对象");
    }
}
