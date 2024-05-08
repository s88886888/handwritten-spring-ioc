package org.handwriting.Mapper.MapperImpl;

import org.handwriting.Mapper.UserMapper;
import org.handwriting.anno.Bean;


/**
 * @author Linson
 */
@Bean
public class UserMapperImpl implements UserMapper {
    @Override
    public void getList() {
        System.err.println("调用---------->UserMapperImpl");
    }
}
