package com.account.feign;

import com.account.beans.UserBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user")
public interface UserFeign {

    @GetMapping("/users/id/{id}")
    UserBean getUser(@PathVariable("id") Integer id);
}
