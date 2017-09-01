package org.zhengwei.commons;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * 基于Shiro的MD5加密
 * @author zhengwei
 */
public class MD5Util {
  public static String md5(String str,String salt){
    return new Md5Hash(str,salt).toString() ;
  }
  public static String md5(String s) {
    return new Md5Hash(s).toString();
  }

  public static void main(String[] args) {
    String md5 = md5("pageNumber=1&pageSize=108e90d411a162423289d764d14f0351fa") ;
    System.out.println(md5);
  }
}
