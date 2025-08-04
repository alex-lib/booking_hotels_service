package com.service.bookinghotels.repositories;
import com.service.bookinghotels.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.name = :name")
    User getUserByName(String name);

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.name = :name")
    boolean isUserExistsByNameAndEmail(String name, String email);
}