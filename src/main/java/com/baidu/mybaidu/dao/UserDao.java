package com.baidu.mybaidu.dao;

import com.baidu.mybaidu.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserDao {

    public User login(User user);

    public List<Map<String, Object>> queryAllUser();

    public List<Map<String, Object>> queryAllRole();

    public void createRole(@Param("createId")String createId, @Param("roleName")String roleName, @Param("remark")String remark);

    public void deleteRole(@Param("id")String id);

    public void createUser(User user);

    public Map<String, Object> queryUserById(@Param("id")int id);

    public void updateUser(User user);

    public void deleteUser(@Param("id")String id);

    Map<String,Object> queryUserByName(@Param("userName") String userName);

    public int countUserNum();

    User signUp(User user);
}
