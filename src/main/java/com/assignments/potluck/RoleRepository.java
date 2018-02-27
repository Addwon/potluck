package com.assignments.potluck;

import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role,Long> {
    Role findByRole(String role);
}
