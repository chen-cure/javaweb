package cn.tedu.utiles;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtils {
    private static DataSource dataSource = new ComboPooledDataSource();

    public static Connection getConn(){
        Connection conn =null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭连接
     */
    public static void closeAll(Connection conn, PreparedStatement ps, ResultSet rs){
        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                conn=null;
            }
        }
        if (ps!=null){
            try {
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                ps=null;
            }
        }
        if (rs!=null){
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                rs=null;
            }
        }
    }
}
