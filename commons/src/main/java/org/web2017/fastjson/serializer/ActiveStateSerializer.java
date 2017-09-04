package org.web2017.fastjson.serializer;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.io.IOException;
import java.lang.reflect.Type;

public class ActiveStateSerializer implements ObjectSerializer {
  @Override
  public void write(JSONSerializer serializer, Object value, Object fieldName,
                    Type fieldType, int features) throws IOException {
    Boolean flag = (Boolean) value;
    if (flag) {
      serializer.write("是");
    }else {
      serializer.write("否");
    }
  }
}
