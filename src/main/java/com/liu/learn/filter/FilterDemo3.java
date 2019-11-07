package com.liu.learn.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

@WebFilter(value = "/*", dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class FilterDemo3 implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        String res=null;
        ServletRequest proxy_req = (ServletRequest) Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().equals("getParameter")) {
                    String sensitive = (String) method.invoke(req, args);
                    if (sensitive!=null){
                        for (String temp:sensi){
                            if (sensitive.contains(temp)){
                                sensitive=sensitive.replaceAll(temp,"***");
                            }
                        }
                    }
                    return sensitive;
                }
                return method.invoke(req,args);
            }
        });
        chain.doFilter(proxy_req,resp);
    }

    private List<String> sensi = null;

    public void init(FilterConfig config) throws ServletException {
        sensi=new ArrayList<>();
        BufferedReader br=null;
        try {
            InputStream sensiFile = FilterDemo3.class.getResourceAsStream("/sensitiveWords");
            br = new BufferedReader(new InputStreamReader(sensiFile));
            String len = null;
            while ((len = br.readLine()) != null) {
                sensi.add(len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
