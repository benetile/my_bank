package com.user.repository;

import com.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByEmail(String email);

    User findByFirstnameAndLastname(String first, String last);

    User findByEmailAndPassword(String email, String password);

    User findByPhone(String phone);

    List<User> findByFirstnameLikeAndLastnameLike(String first,String like);

    List<User> findByRole(String role);

}
