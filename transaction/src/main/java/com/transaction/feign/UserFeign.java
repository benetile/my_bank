package com.transaction.feign;

import com.transaction.bean.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "user")
public interface UserFeign {

    @GetMapping("/users")
    List<UserDTO> showAllUsers();

    @GetMapping("/users/name/{name}")
    List<UserDTO> getUsersLikeName(@PathVariable("name") String name);

    @GetMapping("/users/role/{role}")
    List<UserDTO> getUserWithRole(@PathVariable("role") String role);

    @GetMapping("/users/id/{id}")
    UserDTO getUser(@PathVariable("id") Integer id);

    @GetMapping("/users/email/{email}")
    UserDTO getUserWithEmail(@PathVariable("email") String email);

    @GetMapping("/users/find/{firstname}/{lastname}")
    UserDTO getUserWithFirstAndLastname(@PathVariable("firstname") String firstname,@PathVariable("lastname") String lastname);

    @PostMapping("/users")
    UserDTO addUser(@RequestBody UserDTO user);

    @PutMapping("/users/update/{id}")
    UserDTO updateUser(@PathVariable("id") Integer id,@RequestBody UserDTO update);

    @GetMapping("/users/delete/{id}")
    void deleteUser(@PathVariable("id") Integer id);
}
