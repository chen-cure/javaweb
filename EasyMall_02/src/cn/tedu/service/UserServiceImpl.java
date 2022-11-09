package cn.tedu.service;

import cn.tedu.dao.MySqlDao;
import cn.tedu.domain.User;
import cn.tedu.exception.MsgException;

import java.nio.file.attribute.UserDefinedFileAttributeView;

public class UserServiceImpl {
    private MySqlDao userDao = new MySqlDao();
    public boolean hasUername(String username){
        User user = userDao.queryUserByUsername(username);
        return user!=null;
    }

    /**
     * 注册用户的
     * @param user 封装了所有的用户信息
     */
    public void regist(User user) throws MsgException {
        if (userDao.queryUserByUsername(user.getUsername())!=null){
            throw new MsgException("用户名已存在");
        }
        userDao.addUser(user);
    }
    public User login(String username ,String password) throws MsgException {
        User user = userDao.queryUserByUsernameAndPassword(username,password);
        if (user==null){
            throw new MsgException("用户名或者密码错误");
        }
        return user;
    }
}
