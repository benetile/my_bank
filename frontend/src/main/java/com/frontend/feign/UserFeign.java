package com.frontend.feign;

import com.frontend.Beans.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "user")
public interface UserFeign {

    @GetMapping("/users")
    List<User> showAllUsers();

    @GetMapping("/users/name/{name}")
    List<User> getUsersLikeName(@PathVariable("name") String name);

    @GetMapping("/users/role/{role}")
    List<User> getUserWithRole(@PathVariable("role") String role);

    @GetMapping("/users/id/{id}")
    User getUser(@PathVariable("id") Integer id);

    @GetMapping("/users/email/{email}")
    User getUserWithEmail(@PathVariable("email") String email);

    @GetMapping("/users/find/{firstname}/{lastname}")
    User getUserWithFirstAndLastname(@PathVariable("firstname") String firstname,@PathVariable("lastname") String lastname);

    @PostMapping("/users")
    User addUser(@RequestBody User user);

    @PutMapping("/users/update/{id}")
    User updateUser(@PathVariable("id") Integer id,@RequestBody User update);

    @GetMapping("/users/delete/{id}")
    void deleteUser(@PathVariable("id") Integer id);
}
