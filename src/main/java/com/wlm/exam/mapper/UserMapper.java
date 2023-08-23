package com.wlm.exam.mapper;

import com.wlm.exam.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wlm
 * @date 2023/8/15 - 16:31
 */
@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectByUsernameAndPasswordAndUserType(@Param("username") String username, @Param("password") String password, @Param("userType") Integer userType);

    List<User> selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    List<User> selectByUsername(@Param("username")String username);


}