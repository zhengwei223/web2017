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
  public static void main(String[] args) {
    String md5 = md5("abc123","rbac") ;
    System.out.println(md5);
  }
}
