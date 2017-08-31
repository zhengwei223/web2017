package org.lanqiao.myBatis.service;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.lanqiao.myBatis.MyBatisHelper;
import org.lanqiao.myBatis.dto.UserWithRole;
import org.lanqiao.myBatis.entity.UserProfile;
import org.lanqiao.myBatis.repository.UserProfileMapper;

import java.util.List;

public class UserProfileService {
  private static final String NAMESPACE ="org.lanqiao.myBatis.repository.UserProfileMapper" ;

  public List<UserProfile> findAll() {
    SqlSession session = MyBatisHelper.getSession();
    UserProfileMapper userProfileMapper = session.getMapper( UserProfileMapper.class );
    return userProfileMapper.selectAll();
  }
  public List<UserProfile> findAllPaging(int offset, int limit) {
    SqlSession session = MyBatisHelper.getSession();
    UserProfileMapper userProfileMapper = session.getMapper( UserProfileMapper.class );
    return userProfileMapper.selectAll(new RowBounds(offset,limit ));
  }
  public List<UserWithRole> findAllWithRoleName() {
    SqlSession session = MyBatisHelper.getSession();
    UserProfileMapper userProfileMapper = session.getMapper( UserProfileMapper.class );
    return userProfileMapper.selectAllWithRoleName();
  }
  public List<UserProfile> findByExample(UserProfile userProfile) {
    SqlSession session = MyBatisHelper.getSession();
    UserProfileMapper userProfileMapper = session.getMapper( UserProfileMapper.class );
    return userProfileMapper.selectByExample(userProfile);
  }
  public void update(UserProfile record){
    SqlSession session = MyBatisHelper.getSession();
    UserProfileMapper userProfileMapper = session.getMapper( UserProfileMapper.class );
    userProfileMapper.updateByPrimaryKey( record );
    session.commit();
  }

  public UserProfile findById(int i) {
    return MyBatisHelper.getSession().getMapper( UserProfileMapper.class ).selectByPrimaryKey( i );
  }
}
