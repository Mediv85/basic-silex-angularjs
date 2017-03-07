package it.mediv.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import it.mediv.persistence.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {

	public Users findByUsername(String username);

}
