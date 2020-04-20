package pers.bijf.dao;

import org.apache.ibatis.annotations.Select;
import pers.bijf.domain.User;

import java.util.List;

/**
 * @Author: BIJF
 * @Date: Create in 8:31 PM 3/19/2020/019
 * @ModifiedBy:
 * @Description:
 **/


public interface IUserDao {

    // 加入注解
    @Select("select * from user;")
    List<User> findAll();
}
