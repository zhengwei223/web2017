package org.lanqiao.myBatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class MyBatisHelper {
  private static SqlSessionFactory sqlSessionFactory;
  private static ThreadLocal<SqlSession> sessionHolder = new ThreadLocal<>();
  static {
    Reader reader = null;
    try {
      reader = Resources.getResourceAsReader( "myBatis-config.xml" );
      sqlSessionFactory = new SqlSessionFactoryBuilder().build( reader );
    } catch (IOException e) {
      System.out.println( "#IOException happened in initialising the SessionFactory:" + e.getMessage() );
      throw new ExceptionInInitializerError( e );
    }
  }

  public static SqlSession getSession(){
    SqlSession session = sessionHolder.get();
    if (session==null){
      session = sqlSessionFactory.openSession();
      sessionHolder.set( session );
    }
    return session;
  }
}
