package com.hashtagdk.repository;

import com.hashtagdk.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by dawid on 7/25/17.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);
}
