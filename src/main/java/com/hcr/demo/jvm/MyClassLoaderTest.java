//package com.hcr.demo.jvm;
//
//import java.io.FileInputStream;
//import java.lang.reflect.Method;
//
//
///**
// * 功能描述：自定义类加载器
// *
// * @Author:hr
// * @param:
// * @date: 16:56 2021/1/30 0030
// */
//public class MyClassLoaderTest {
//    static class MyClassLoader extends ClassLoader {
//        private String classPath;
//
//        public MyClassLoader(String classPath) {
//            this.classPath = classPath;
//        }
//
//        private byte[] loadByte(String name) throws Exception {
//            name = name.replaceAll("\\.", "/");
//            FileInputStream fis = new FileInputStream(classPath + "/" + name + ".class");
//            int len = fis.available();
//            byte[] date = new byte[len];
//            fis.read(date);
//            fis.close();
//            return date;
//
//        }
//
//        @Override
//        protected Class<?> findClass(String name) throws ClassNotFoundException {
//            try {
//                byte[] date = loadByte(name);
//                return defineClass(name, date, 0, date.length);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                throw new ClassNotFoundException();
//            }
//        }
//
//        /**
//         * 功能描述：重写类加载方法，实现自己的加载逻辑，不委派给双亲加载
//         *
//         * @Author:hr
//         * @param:
//         * @date: 11:13 2021/2/2 0002
//         */
//        @Override
//        protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
//            synchronized (getClassLoadingLock(name)) {
//                // First, check if the class has already been loaded
//                Class<?> c = findLoadedClass(name);
//                if (c == null) {
//                    // If still not found, then invoke findClass in order
//                    // to find the class.
//                    long t1 = System.nanoTime();
//
//                    if (!name.startsWith("com.hcr.demo")) {
//                        c = this.getParent().loadClass(name);
//                    } else {
//                        c = findClass(name);
//                    }
//
//
//                    // this is the defining class loader; record the stats
//                    sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
//                    sun.misc.PerfCounter.getFindClasses().increment();
//                }
//                if (resolve) {
//                    resolveClass(c);
//                }
//                return c;
//            }
//        }
//    }
//
//
//    //注：如果不在项目中删除User1类，则加载器是app加载器，因为项目中有User1这个类
//    public static void main(String[] args) throws Exception {
//        //初始化自定义类加载器，会先初始化父类ClassLoader，其中会把自定义类加载器的父加载 器设置为应用程序类加载器AppClassLoader
//        MyClassLoader classLoader = new MyClassLoader("D:/test");
//        //D盘创建 com/hcr/demo/jvm 几级目录，将User类的复制类User1.class丢入该目录
//        Class<?> clazz = classLoader.loadClass("com.hcr.demo.jvm.User1");
//        Object obj = clazz.newInstance();
//        Method sout = clazz.getDeclaredMethod("sout", null);
//        sout.invoke(obj, null);
//        System.out.println(clazz.getClassLoader());
//    }
//}
