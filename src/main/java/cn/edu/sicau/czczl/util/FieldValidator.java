package cn.edu.sicau.czczl.util;



//import com.sun.tools.javac.util.Assert;
import lombok.SneakyThrows;


import java.lang.reflect.Field;

/**
 * 这是一个表单校验工具,传入表单对象,如果
 * 表单对象中有empty("") 或者 null 则返
 * 回相应的值
 * @author qkmc
 */
public class FieldValidator<T> {

    /**
     *  判断一个对象中字段是否有空字段
     *  有空字段则返回true, 否则返回false
     * @param obj 范型对象
     * @return {@code true}  or {@code false}
     */
    @SneakyThrows
    public boolean hasNullOrEmptyField(T obj){
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f :
                fields) {
            f.setAccessible(true);
            Object value = f.get(obj);
            if(null == value || value.equals("")){
                return true;
            }
        }
        return false;
    }

    /**
     * 将b中的同名变量更新到a中去
     * @param a 需要被更新的对象
     * @param b 需要获取值的对象
     * @param <R> 范型
     * @return 更新之后的a
     */
    @SneakyThrows
    public <R> R updateAWithB(R a, T b){

//        Assert.checkNonNull(a, "a cannot be null");
//        Assert.checkNonNull(b, "b cannot be null");

        Class aType = a.getClass();
        Class bType = b.getClass();

        Field[] aTypeFields = aType.getDeclaredFields();
        Field[] bTypeFields = bType.getDeclaredFields();

        for (Field field : bTypeFields) {
            String bFieldName = field.getName();
            field.setAccessible(true);
            field.get(b);
            for (Field aField :
                    aTypeFields) {
                if (aField.getName().equals(bFieldName)){
                    aField.setAccessible(true);
                    aField.set(a, field.get(b));
                    break;
                }
            }
        }
        return a;
    }
}
