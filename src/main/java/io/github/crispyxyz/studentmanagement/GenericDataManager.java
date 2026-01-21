package io.github.crispyxyz.studentmanagement;

import io.github.crispyxyz.studentmanagement.exception.DataNotFoundException;
import io.github.crispyxyz.studentmanagement.model.Person;

import java.util.List;

public class GenericDataManager {

    /**
     * 添加对象到列表，并触发日志记录
     * @param list 目标列表
     * @param person 要新增的对象
     * @param <T> Person及其子类
     */
    public static <T extends Person> void add(List<T> list, T person) {
        LoggerProvider.log("增", person);
        list.add(person);
    }

    /**
     * 根据 ID 删除列表中的对象，并触发日志记录
     * @param list 目标列表
     * @param id 要删除的 ID
     * @param <T> Person及其子类
     * @throws DataNotFoundException 若列表中不存在指定 ID 的对象
     */
    public static <T extends Person> void deleteById(List<T> list, String id) throws DataNotFoundException {
        LoggerProvider.log("删", id);

        // 遍历比较 ID，找到后立即删除并 return
        for(T person : list) {
            if(person.getId().equals(id)) {
                list.remove(person);
                return;
            }
        }

        // 没找到，抛出异常
        throw new DataNotFoundException("ID为"+id+"的对象在列表中不存在");
    }

    /**
     * 按照新对象的 ID，更新列表中的对象，并触发日志记录
     * @param list 目标列表
     * @param person 新对象
     * @param <T> Person及其子类
     * @throws DataNotFoundException 若列表中不存在指定 ID 的对象
     */
    public static <T extends Person> void update(List<T> list, T person) throws DataNotFoundException {
        LoggerProvider.log("改", person);

        // 遍历比较 ID，找到后立即删除，再新增，并return
        for(T p : list) {
            if(p.getId().equals(person.getId())) {
                list.remove(p);
                list.add(person);
                return;
            }
        }

        // 没找到，抛出异常
        throw new DataNotFoundException("ID为"+person.getId()+"的对象在列表中不存在");
    }

    /**
     * 根据 ID 查找对象
     * @param list 目标列表
     * @param id 要查找对象的 ID
     * @return 找到的对象
     * @param <T> Person及其子类
     * @throws DataNotFoundException 若列表中不存在指定 ID 的对象
     */
    public static <T extends Person> T findById(List<T> list, String id) throws DataNotFoundException {
        // 遍历比较 ID 查找，找到后立即返回
        for(T person : list) {
            if(person.getId().equals(id)) {
                return person;
            }
        }

        // 没找到，抛出异常
        throw new DataNotFoundException("找不到ID为"+id+"的对象");
    }
}
