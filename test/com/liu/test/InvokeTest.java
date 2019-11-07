package com.liu.test;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class InvokeTest {
    public static void main(String[] args) {
        Mon1 m=new Mon1();
        Monkey proxy_m = (Monkey) Proxy.newProxyInstance(m.getClass().getClassLoader(), m.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().equals("colorShow")) {
                    String arg = (String) args[0];
                    arg="黑";
                    String m_invoke = (String) method.invoke(m,arg);
                    return m_invoke;
                }else {
                    Object m_invoke = method.invoke(m,args);
                    return m_invoke;
                }
            }
        });
        String colorShow =proxy_m.colorShow("白");
        System.out.println(colorShow);//黑猴儿
        proxy_m.show();//介猴儿卖吗?
    }
    @Test
    public void test() throws Exception {
        InputStream sensitiveWords = InvokeTest.class.getResourceAsStream("/sensitiveWords");
        BufferedReader br=new BufferedReader(new InputStreamReader(sensitiveWords));
        String len=null;
        while ((len=br.readLine())!=null){
            System.out.println(len);
        }
    }
}
