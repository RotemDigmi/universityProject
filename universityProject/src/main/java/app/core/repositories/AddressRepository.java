package app.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import app.core.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{

}
