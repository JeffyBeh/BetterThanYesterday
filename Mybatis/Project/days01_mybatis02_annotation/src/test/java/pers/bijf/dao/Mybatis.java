package pers.bijf.dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.SqlBuilder;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
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
        // 3. 创建SqlSession  -- 创建生产机器
        SqlSession session = factory.openSession();
        // 4. 创建Dao接口代理对象  -- 创建操作人员
        IUserDao userDao =session.getMapper(IUserDao.class);
        // 5. 执行Dao中的方法  -- 进行生产操作
        List<User> users =userDao.findAll();
        for(User user:users){
            System.out.println(user);
        }
        // 6. 释放资源  -- 干完收工
        session.close();
        in.close();
    }
}
