package me.kyrene.springboot4integration.utils.serialize;

import me.kyrene.springboot4integration.pojo.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by wanglin on 2018/1/11.
 */
public class SerializeUtil {
    private static final Logger logger = LoggerFactory.getLogger(SerializeUtil.class);

    /**
     * 将对象序列化
     *
     * @param obj
     * @return
     */
    public static byte[] serialize(Object obj) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        byte[] byteArray = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            byteArray = baos.toByteArray();
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return byteArray;
    }

    /**
     * 将对象反序列化
     * @param byteArray
     * @return
     */
    public static Object unSerialize(byte[] byteArray) {
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        Object result = null ;
        try {
            //反序列化为对象
            bais = new ByteArrayInputStream(byteArray);
            ois = new ObjectInputStream(bais);
            result = ois.readObject();
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return result;
    }

//    @Test
//    public void test01(){
//        User user = new User();
//        user.setName("aaa");
//        user.setId(5L);
//        user.setAge(33);
//        byte[] serialize = serialize(user);
//        User o = (User) unSerialize(serialize);
//        System.out.println("aaaaa");
//    }
}
