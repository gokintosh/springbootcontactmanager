package com.gokul.contactmanager.dao;

import com.gokul.contactmanager.enities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
