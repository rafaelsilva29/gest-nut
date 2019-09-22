package smartThings.gestNuT.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import smartThings.gestNuT.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByFirstName(String firstName);
    User findByLastName(String lastName);
    User findByPhoneNumber(int phoneNumber);
    User findByEmail(String email);
    User findByConfirmationToken(String confirmationToken);
}