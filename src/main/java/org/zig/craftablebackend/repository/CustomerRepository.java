package org.zig.craftablebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zig.craftablebackend.database.Creator;
import org.zig.craftablebackend.database.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findCustomerByEmail(String email);
}
