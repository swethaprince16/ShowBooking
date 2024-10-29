package com.showbooking.keyGenerator;

import com.showbooking.dto.UserDto;
import com.showbooking.service.UserService;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component("customKeyGenerator")
public class CustomKeyGenerator implements KeyGenerator {
    @Override
    public Object generate(Object target, Method method, Object... params) {
        if(params.length==1 && params[0] instanceof UserDto){
            UserDto dto = (UserDto) params[0];
            Integer userId = dto.getId();
            return userId;

        }
        throw new IllegalArgumentException("Invalid method parameters for key generation");
    }

}