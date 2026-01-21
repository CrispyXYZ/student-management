package io.github.crispyxyz.studentmanagement.util;

public class ValidatingUtil {

    /**
     * 判断给定的字符串是否是空白字符串，与 JDK11+ 的 String.isBlank 功能相同
     * @param str 给定字符串
     * @return 若是空白字符串则为 true；反之则为 false
     */
    public static boolean isBlank(String str) {
        for(char c : str.toCharArray()) {
            if(!Character.isWhitespace(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 验证字符串是否合法，若不合法，则抛出异常
     * @param str 给定字符串
     * @param object 验证对象的描述名称，如 "课程名"、"姓名"
     * @throws NullPointerException 若字符串为 null
     * @throws IllegalArgumentException 若字符串为空白字符串
     */
    public static void validateString(String str, String object) {
        if(str == null) {
            throw new NullPointerException(object + "不能为 null");
        }
        if(isBlank(str)) {
            throw new IllegalArgumentException(object + "不能为空");
        }
    }

    /**
     * 验证浮点数是否在给定闭区间内，若超出范围，则抛出异常
     * @param num 给定浮点数
     * @param lower 区间左端点
     * @param upper 区间右端点
     * @param object 验证对象的描述名称，如 "年龄"、"成绩"
     * @throws IllegalArgumentException 若区间为空集，或浮点数不在区间内
     */
    public static void validateDouble(double num, double lower, double upper, String object) {
        if(lower > upper) {
            throw new IllegalArgumentException(lower + " > " + upper);
        }
        if(lower <= num && num <= upper) return;
        throw new IllegalArgumentException(String.format("%s %f 超出范围 [%f, %f]", object, num, lower, upper));
    }
}
