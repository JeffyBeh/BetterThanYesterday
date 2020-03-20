package pers.bijf.dao;

import pers.bijf.domain.User;

import java.util.List;

/**
 * @Author: BIJF
 * @Date: Create in 9:27 AM 3/19/2020/019
 * @ModifiedBy:
 * @Description: 用户的持久层接口
 **/


public interface IUserDao {
    /*
    * 查询所有操作
    */
    List<User> findAll();

}
