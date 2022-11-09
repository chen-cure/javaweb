package cn.tedu.service;

import cn.tedu.dao.MySqlDao;
import cn.tedu.domain.User;

public class UserServiceImpl {
    private MySqlDao mySqlDao = new MySqlDao();
    public boolean hasUsername(String username){
        User user = mySqlDao.selectByUsername(username);
//        System.out.println(user);
        return user!=null;
    }

    public void regist(User user){
        if (hasUsername(user.getUsername())){
            return;
        }
        mySqlDao.add(user);
    }

    public User login(String username,String pasword){
        User user = mySqlDao.selectByUsernameAndPassword(username,pasword);
        return user;
    }
}
