package com.duckonmoon.storypiper.storypiper.repository;

import com.duckonmoon.storypiper.storypiper.model.Role;
import com.duckonmoon.storypiper.storypiper.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
