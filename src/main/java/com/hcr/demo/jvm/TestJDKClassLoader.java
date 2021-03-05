package com.hcr.demo.jvm;

import sun.misc.Launcher;

import java.net.URL;

/**
 * 功能描述：查看类加载
 *
 * @Author:hr
 * @param:
 * @date: 16:04 2021/1/30 0030
 */
public class TestJDKClassLoader {
    public static void main(String[] args) {
        //这里String是引导类加载器加载的 是c++ （null值 是因为这是c++加载的 这里看不到）
        System.out.println(String.class.getClassLoader());
        System.out.println(com.sun.crypto.provider.DESKeyFactory.class.getClassLoader());
        System.out.println(TestJDKClassLoader.class.getClassLoader());

        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        ClassLoader extClassloader = appClassLoader.getParent();
        ClassLoader bootstrapLoader = extClassloader.getParent();
        System.out.println("the bootstrapLoader : " + bootstrapLoader);
        System.out.println("the extClassloader : " + extClassloader);
        System.out.println("the appClassLoader : " + appClassLoader);

        System.out.println();
        System.out.println("bootstrapLoader加载以下文件：");
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (int i = 0; i < urLs.length; i++) {
            System.out.println(urLs[i]);
        }

        System.out.println();
        System.out.println("extClassLoader加载以下文件：");
        System.out.println(System.getProperty("java.ext.dirs"));

        System.out.println();
        System.out.println("APPClassLoader加载以下文件：");
        System.out.println(System.getProperty("java.class.path"));

    }

}
