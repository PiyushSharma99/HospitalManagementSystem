package com.astrotalk.hospital.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.astrotalk.hospital.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
