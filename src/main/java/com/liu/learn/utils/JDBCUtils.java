package com.liu.learn.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * Druid连接池工具类
 */
public class JDBCUtils {
    private static DataSource dataSource=null;
    static {
        //加载文件
        Properties pro=new Properties();
        InputStream resourceAsStream = JDBCUtils.class.getResourceAsStream("/druid.properties");
        try {
            pro.load(resourceAsStream);
            dataSource=DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws Exception{
        return dataSource.getConnection();
    }

    //获取连接池
    public static DataSource getDataSource(){
        return dataSource;
    }

    public static void close(PreparedStatement ps,Connection connection){
        try {
            close(null,ps,connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void close(ResultSet rs,PreparedStatement ps, Connection connection){
        try {
            if (rs!=null){
                rs.close();
            }
            if (ps!=null){
                ps.close();
            }
            if (connection!=null){
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
