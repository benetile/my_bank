package com.user.controller;

import com.user.beans.BeneficiaryBean;
import com.user.feign.AccountFeign;
import com.user.feign.BeneficiaryFeign;
import com.user.model.User;
import com.user.repository.UserRepository;
import com.user.service.AuthenticateUser;
import com.user.service.GeneratePassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    @Autowired
    private AuthenticateUser authenticateUser;
    @Autowired
    private AccountFeign accountFeign;

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
   // @PreAuthorize("hasRole('ROLE_ADMIN') or @authenticateUser.hasId(#id)")
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
        if (exist !=null){
            return userRepository.findByEmail(email);
        }
        else
            return null;
    }

    @GetMapping("/users/phone/{phone}")
    public User getPhoneForUser(@PathVariable("phone") String phone){
        return userRepository.findByPhone(phone);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users/find/{firstname}/{lastname}")
    public User getUserWithFirstAndLastname(@PathVariable("firstname") String firstname,@PathVariable("lastname") String lastname){
        User user = userRepository.findByFirstnameAndLastname(firstname, lastname);
        if (user!=null){
            return user;
        }
        else
            throw new IllegalArgumentException("Not found ");
    }

    @PostMapping("/users/register")
    public User addUser(@RequestBody User user,BindingResult result){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        //User userPrincipal = userRepository.findByEmail(userDetails.getUsername());
        if (!result.hasErrors() && userRepository.findByEmail(user.getEmail())== null){
            user.setRegistrationDate(new Date());
            /** if the user is an administrator, we will generate a password and assign the role of USER to our new user */
            //if(userPrincipal == null ){
                //user.setPassword(encoder.encode(generatePassword.generateStrongPassword()));
            user.setPassword(encoder.encode(user.getPassword()));
                user.setRole("USER");
                user.setActive(true);
                user.setNotLocked(true);
            //}
            userRepository.save(user);
            User exist = userRepository.findByEmail(user.getEmail());
            beneficiaryFeign.addBeneficiary(new BeneficiaryBean(exist.getFirstname(),exist.getLastname(),
                    exist.getEmail(),null,exist.getPhone()));
            return user;
        }
        else
            throw new IllegalArgumentException("Error Cannot");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or @authenticateUser.hasId(#id)")
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

    @PreAuthorize("hasRole('ROLE_ADMIN') or @authenticateUser.hasId(#id)")
    @DeleteMapping("/users/delete/{id}")
    public void deleteUser(@PathVariable("id") Integer id){
        if (userRepository.existsById(id)){
            User exist = userRepository.findById(id).orElseThrow(()-> new IllegalArgumentException());
            BeneficiaryBean beneficiaryBean = beneficiaryFeign.getBeneficiaryWithEmail(exist.getEmail());
            beneficiaryFeign.deleteBeneficiary(beneficiaryBean.getIdBeneficiary());
            userRepository.deleteById(id);
            accountFeign.deleteAccount(exist.getAccount().getIdAccount());
        }
        else
            throw new IllegalArgumentException("Invalid id : "+id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or @authenticateUser.hasId(#id)")
    @GetMapping("/users/unsubscribe/{id}")
    public User unsubscribeUser(@PathVariable("id") Integer id){
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Id : "+id));
        user.setActive(false);
        user.setNotLocked(false);
        return userRepository.save(user);
    }
}
