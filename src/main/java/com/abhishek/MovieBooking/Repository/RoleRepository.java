package com.abhishek.MovieBooking.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhishek.MovieBooking.Model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

	Optional<Role> findByName(String name);
}
