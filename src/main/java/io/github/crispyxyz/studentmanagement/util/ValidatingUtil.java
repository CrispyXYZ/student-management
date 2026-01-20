package io.github.crispyxyz.studentmanagement.util;

public class ValidatingUtil {

    public static boolean isBlank(String str) {
        for(char c : str.toCharArray()) {
            if(!Character.isWhitespace(c)) {
                return false;
            }
        }
        return true;
    }

    public static void validateString(String str, String object) {
        if(str == null) {
            throw new NullPointerException(object + "不能为 null");
        }
        if(isBlank(str)) {
            throw new IllegalArgumentException(object + "不能为空");
        }
    }

    public static void validateDouble(double num, double lower, double upper, String object) {
        if(lower > upper) {
            throw new IllegalArgumentException(lower + " > " + upper);
        }
        if(lower <= num && num <= upper) return;
        throw new IllegalArgumentException(String.format("%s %f 超出范围 [%f, %f]", object, num, lower, upper));
    }
}
