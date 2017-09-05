package org.lanqiao.myBatis.service;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.lanqiao.myBatis.MyBatisHelper;
import org.lanqiao.myBatis.dto.UserWithRole;
import org.lanqiao.myBatis.entity.UserProfile;
import org.lanqiao.myBatis.repository.UserProfileMapper;

import java.util.List;

/**
 * 未集成spring之前，需要在代码中用SqlSession来获得Mapper实例
 */
public class UserProfileService {
  private static final String NAMESPACE ="org.lanqiao.myBatis.repository.UserProfileMapper" ;

  public List<UserProfile> findAll() {
    SqlSession session = MyBatisHelper.getSession();
    UserProfileMapper userProfileMapper = session.getMapper( UserProfileMapper.class );
    return userProfileMapper.selectAll();
  }
  public List<UserProfile> findAllPaging(int page, int size) {
    SqlSession session = MyBatisHelper.getSession();
    UserProfileMapper userProfileMapper = session.getMapper( UserProfileMapper.class );
    return PageHelper.startPage( page,size ).doSelectPage( ()->{
      userProfileMapper.selectAll();
    } );
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
