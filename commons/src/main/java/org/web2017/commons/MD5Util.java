package org.web2017.commons;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * 基于Shiro的MD5加密
 * @author web2017
 */
public class MD5Util {
  public static String md5(String str,String salt){
    return new Md5Hash(str,salt).toString() ;
  }
  public static String md5(String s) {
    return new Md5Hash(s).toString();
  }

  public static void main(String[] args) {
    String md5 = md5("admin","rbac") ;
    System.out.println(md5);
  }
}
