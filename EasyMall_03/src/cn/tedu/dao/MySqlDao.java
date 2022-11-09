package cn.tedu.dao;

import cn.tedu.domain.User;
import cn.tedu.utiles.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MySqlDao {
    Connection conn = null;
    PreparedStatement ps=null;
    ResultSet rs = null;

    /**
     * 查询用户名是否存在
     * @param username
     * @return
     */
    public User selectByUsername(String username){
        try{
            conn = JDBCUtils.getConn();
//            System.out.println(conn);
            ps = conn.prepareStatement("select * from user where username=?");
            ps.setString(1,username);
            rs = ps.executeQuery();
//            System.out.println(rs.getInt(1));
            if (rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setNickname(rs.getString("nickname"));
                user.setNickname(rs.getString("email"));
                return user;
            }else{
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            JDBCUtils.closeAll(conn,ps,rs);
        }
    }

    /**
     * 添加用户，用户注册
     * @param user
     */
    public void add(User user){
        try{
            conn=JDBCUtils.getConn();
            ps = conn.prepareStatement("insert into user values(null,?,?,?,?)");
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getNickname());
            ps.setString(4,user.getEmail());
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            JDBCUtils.closeAll(conn,ps,rs);
        }
    }

    /**
     * 用户查询，根据用户名和密码来查询登录
     * @param username
     * @param password
     * @return
     */
    public User selectByUsernameAndPassword(String username,String password){
        try {
            conn = JDBCUtils.getConn();
            ps = conn.prepareStatement("select * from user where username=? and password=?");
            ps.setString(1,username);
            ps.setString(2,password);
            rs = ps.executeQuery();
            if (rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setNickname(rs.getString("nickname"));
                user.setNickname(rs.getString("email"));
                return user;
            }else{
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        finally {
            JDBCUtils.closeAll(conn,ps,rs);
        }
    }


}
