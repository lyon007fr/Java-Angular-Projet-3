package com.openclassrooms.ApiRentals.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.openclassrooms.ApiRentals.model.User;


@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
	User findUserByName(final String name);
	User findUserByEmail(String email);

}

