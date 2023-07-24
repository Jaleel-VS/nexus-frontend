package com.nexus.vouchers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nexus.vouchers.models.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
