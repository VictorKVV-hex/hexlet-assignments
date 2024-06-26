package exercise.service;

import exercise.model.User;
import exercise.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    // BEGIN
    public Mono<User> create(User user) {
        return userRepository.save(user);
    }

    public Mono<User> findById(Long Id) {
        return userRepository.findById(Id);
    }

    public Mono<User> show(Long id) {
        return userRepository.findById(id);
    }

    public Mono<Void> delete(Long id) {
        return userRepository.deleteById(id);
    }

    public Mono<User> update(User userData, Long id) {
/*        User res = new User(userData.getFirstName(), userData.getLastName(), userData.getEmail());
        res.setId(id);
        return userRepository.save(res);*/

/*        var task =  userRepository.findById(id);
//                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found")); // этого метода тут нет
        task.setTitle(taskData.getTitle());
        task.setDescription(taskData.getDescription());
        return userRepository.save(task);*/
        return userRepository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty())
                .flatMap(optionalTutorial -> {
                    if (optionalTutorial.isPresent()) {
                        userData.setId(id);
                        return userRepository.save(userData);
                    }
                    return Mono.empty();
                });
    }
    // END
}
