package com.hcr.demo.test.fanshe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class fanshe {
    public static void main(String[] args) throws Exception {
        //1、对象调用 getClass() 方法来获取,通常应用在：比如你传过来一个 Object
        //  类型的对象，而我不知道你具体是什么类，用这种方法
        Person person = new Person();
        Class c1 = person.getClass();

        //2、类名.class 的方式得到,该方法最为安全可靠，程序性能更高
        //  这说明任何一个类都有一个隐含的静态成员变量 class
        Class c2 = Person.class;

        //3、通过 Class 对象的 forName() 静态方法来获取，用的最多，
        //   但可能抛出 ClassNotFoundException 异常
        Class c3 = Class.forName("com.hcr.demo.test.fanshe.Person");

        if (c1.equals(c2)) {
            System.out.println("c1 equals c2");
        }
        if (c1.equals(c3)) {
            System.out.println("c1 equals c3");
        }

        //获取类的完整名字
        String name = c1.getName();
        System.out.println(name);

        //获取public修改的属性
        Field[] fields = c1.getFields();
        for (Field field : fields) {
            String name1 = field.getName();
            System.out.println(name1);
        }

        //获取所有属性
        Field[] declaredFields = c1.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            String name1 = declaredField.getName();
            System.out.println(name1);
        }

        //获的类的public修饰的方法
        Method[] methods = c1.getMethods();
        for (Method method : methods) {
            String name1 = method.getName();
            System.out.println(name1);
        }

        //获得类的所有方法
        Method[] declaredMethods = c1.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod.getName());
        }

        //根据方法名获取方法
        Method work = c1.getDeclaredMethod("work");
        //执行方法
        work.invoke(person);

        Constructor[] constructors = c1.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor.getName());
        }

        //获取类特定的构造方法
        Constructor constructor = c1.getConstructor();
        //通过无参构造创建对象
        Object o = constructor.newInstance();
        if (o instanceof Person) {
            ((Person) o).work();
        }

        //
        Object o1 = c1.newInstance();
        if (o1 instanceof Person) {
            ((Person) o1).work();
        }
    }

    //Class能实现的功能


}
