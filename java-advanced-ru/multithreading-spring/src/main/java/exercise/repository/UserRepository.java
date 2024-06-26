package exercise.repository;

import exercise.model.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;


// BEGIN
// Реализуем интерфейс ReactiveCrudRepository
@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {
    // Все необходимые методы уже содержатся в интерфейсе ReactiveCrudRepository
}
// END
