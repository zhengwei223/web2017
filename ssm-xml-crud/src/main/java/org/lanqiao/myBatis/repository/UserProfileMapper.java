package org.lanqiao.myBatis.repository;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.lanqiao.myBatis.dto.UserWithRole;
import org.lanqiao.myBatis.entity.UserProfile;

public interface UserProfileMapper extends BaseMapper<Integer,UserProfile>{
    List<UserWithRole> selectAllWithRoleName();
    List<UserProfile> selectByExample(UserProfile userProfile);

  UserWithRole selectUserWithRoleByUserId(Integer id);
}