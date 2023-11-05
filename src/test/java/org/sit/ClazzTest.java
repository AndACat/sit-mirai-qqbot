package org.sit;

import org.sit.handler.FriendMessageEventHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author WangZhen
 * @Date 2023/11/5 13:03
 */
public class ClazzTest {
    public static void main(String[] args) {
        FriendMessageEventHandler eventHandler = new FriendMessageEventHandler();
        Type genericSuperclass = eventHandler.getClass().getGenericSuperclass();
        if(genericSuperclass instanceof ParameterizedType){
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            if(actualTypeArguments != null && actualTypeArguments.length == 1){
                Type clazz = actualTypeArguments[0];
                if(clazz instanceof Class<?>){
                    System.out.println((Class)clazz);
                }

            }
        }
    }
}
