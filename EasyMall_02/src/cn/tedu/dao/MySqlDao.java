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
    public User queryUserByUsername(String username){
        try {
            conn = JDBCUtils.getConn();
            ps = conn.prepareStatement("select * from user where username=?");
            ps.setString(1,username);
            rs = ps.executeQuery();
            if(rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setNickname(rs.getString("nickname"));
                user.setNickname(rs.getString("email"));
                return user;
            }else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            JDBCUtils.closeAll(conn,ps,rs);
        }
    }

    public void addUser(User user){
        try{
            conn=JDBCUtils.getConn();
            ps=conn.prepareStatement("insert into user values(null,?,?,?,?)");
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getNickname());
            ps.setString(4,user.getEmail());
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
    }

    public User queryUserByUsernameAndPassword(String username,String password) {
        try{
            conn = JDBCUtils.getConn();
            ps = conn.prepareStatement("select * from user where username=? and password = ?");
            ps.setString(1,username);
            ps.setString(2,password);
            rs = ps.executeQuery();
            if(rs.next()){
                User u = new User();
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setNickname(rs.getString("nickname"));
                u.setNickname(rs.getString("email"));
                return u;
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
}
