package com.user.controller;

import com.user.feign.BeneficiaryFeign;
import com.user.model.User;
import com.user.repository.UserRepository;
import com.user.service.GeneratePassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BeneficiaryFeign beneficiaryFeign;

    @Autowired
    private GeneratePassword generatePassword;

    @GetMapping("/users")
    public List<User> showAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/users/name/{name}")
    public List<User> getUsersLikeName(@PathVariable("name") String name){
        return userRepository.findByFirstnameLikeAndLastnameLike(name,name);
    }

    @GetMapping("/users/role/{role}")
    public List<User> getUserWithRole(@PathVariable("role") String role){
        return userRepository.findByRole(role);
    }

    @GetMapping("/users/id/{id}")
    public Optional<User> getUser(@PathVariable("id") Integer id){
        if (userRepository.existsById(id)){
            return userRepository.findById(id);
        }
        else
            throw new IllegalArgumentException("Invalid id : "+id);
    }

    @Transactional
    @GetMapping("/users/email/{email}")
    public User getUserWithEmail(@PathVariable("email") String email){
        User exist = userRepository.findByEmail(email);
        if (exist!=null){
            return userRepository.findByEmail(email);
        }
        else
            return null;
    }

    @GetMapping("/users/find/{firstname}/{lastname}")
    public User getUserWithFirstAndLastname(@PathVariable("firstname") String firstname,@PathVariable("lastname") String lastname){
        User user = userRepository.findByFirstnameAndLastname(firstname, lastname);
        if (user!=null){
            return user;
        }
        else
            throw new IllegalArgumentException("Not found ");
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user, BindingResult result){
        if (!result.hasErrors() && userRepository.findByEmail(user.getEmail())== null){
            user.setRegistrationDate(new Date());
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(generatePassword.generateStrongPassword());
            System.out.println(user.getPassword());
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);
            return userRepository.save(user);
        }
        else
            throw new IllegalArgumentException("Error Cannot");
    }

    @PutMapping("/users/update/{id}")
    public User updateUser(@PathVariable("id") Integer id,@RequestBody User update){
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id : "+id));
        if (userRepository.existsById(id)){
            update.setIdUser(id);
            update.setRegistrationDate(user.getRegistrationDate());
            return userRepository.save(update);
        }
        else
            throw new IllegalArgumentException("Error update");
    }

    @DeleteMapping("/users/delete/{id}")
    public void deleteUser(@PathVariable("id") Integer id){
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
        }
        else
            throw new IllegalArgumentException("Invalid id : "+id);
    }
}
