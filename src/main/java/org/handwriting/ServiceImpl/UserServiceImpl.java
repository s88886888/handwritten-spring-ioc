package org.handwriting.ServiceImpl;

import org.handwriting.Mapper.MapperImpl.UserMapperImpl;
import org.handwriting.Mapper.UserMapper;
import org.handwriting.Service.IUserService;
import org.handwriting.anno.Bean;
import org.handwriting.anno.DI;

/**
 * @author Linson
 */


@Bean
public class UserServiceImpl implements IUserService {

    @DI
    private UserMapper userMapper;

    @Override
    public void getList() {

        userMapper.getList();
        System.err.println("调用---------->UserServiceImpl");

    }
}
