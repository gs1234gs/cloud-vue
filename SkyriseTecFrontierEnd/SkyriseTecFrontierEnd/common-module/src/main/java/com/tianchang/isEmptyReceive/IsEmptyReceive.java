package com.tianchang.isEmptyReceive;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

//判断是否为的工具类
@Slf4j
public class IsEmptyReceive {
    public static boolean isEmpty(Object obj) {
       if(obj==null){
           return true;
       }
        Class<?> clazz = obj.getClass();
       //遍历所有的属性(包括私有的)
       for(Field field : clazz.getDeclaredFields()){
           try {

               //允许访问私有属性
               field.setAccessible(true);
               Object value = field.get(obj);
               if(value!=null){
                   return false;
               }
           }

           catch (IllegalAccessException  e) {
               e.printStackTrace();
               log.info("获取属性失败,{}",field.getName());
           }
       }
        return true;
   }
}
