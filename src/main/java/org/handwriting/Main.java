package org.handwriting;

import org.handwriting.Mapper.MapperImpl.UserMapperImpl;
import org.handwriting.Mapper.UserMapper;
import org.handwriting.Service.IUserService;
import org.handwriting.bean.ApplicationContextImpl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Linson
 */
public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {


        ApplicationContextImpl applicationContextImpl = new ApplicationContextImpl("org.handwriting");


        IUserService userService = (IUserService) applicationContextImpl.getBean(IUserService.class);


//
//
        userService.getList();

//        UserMapper userMapper = (UserMapper)  applicationContextImpl.getBean(UserMapper.class);
//        userMapper.getList();


    }
}
