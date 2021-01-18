package pers.bijf.dao;

import org.apache.ibatis.builder.xml.dynamic.ForEachSqlNode;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import pers.bijf.dao.impl.UserDaoImpl;
import pers.bijf.domain.User;

import java.io.InputStream;
import java.util.List;

/**
 * @Author: BIJF
 * @Date: Create in 8:46 PM 3/19/2020/019
 * @ModifiedBy:
 * @Description:
 **/


public class Mybatis {

    public static void main(String[] args) throws Exception{

        // 1. 读取配置文件 -- 获取生产图纸
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2. 创建SqlSessionFactory工厂  -- 创建生产工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        // 3. 使用工厂创建dao对象
        IUserDao userDao = new UserDaoImpl(factory);
        // 4. 使用代理对象执行方法
        List<User> users = userDao.findAll();
        for(User user : users){
            System.out.println(user);
        }

        // 5.释放资源
        in.close();
    }
}
