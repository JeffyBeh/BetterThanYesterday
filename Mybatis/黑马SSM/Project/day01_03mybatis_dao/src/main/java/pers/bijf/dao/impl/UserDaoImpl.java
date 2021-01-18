package pers.bijf.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import pers.bijf.dao.IUserDao;
import pers.bijf.domain.User;

import java.util.List;

/**
 * @Author: BIJF
 * @Date: Create in 1:16 PM 3/26/2020/026
 * @ModifiedBy:
 * @Description:
 **/


public class UserDaoImpl implements IUserDao {

    private SqlSessionFactory factory;

    public UserDaoImpl(SqlSessionFactory factory){
        this.factory = factory;
    }
    public List<User> findAll(){
        // 1. 使用工厂创建查询对象
        SqlSession session = factory.openSession();
        // 2.使用session执行查询所有方法
        List<User> users= session.selectList("pers.bijf.dao.IUserDao.findAll");
        session.close();
        // 3. 返回查询结果
        return users;
    }

}
