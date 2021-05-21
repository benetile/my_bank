package com.sender.repository;

import com.sender.model.Sender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SenderRepository extends JpaRepository<Sender, Integer> {

    Sender findByEmail(String email);

    List<Sender> findByIdUser(Integer id);

}
