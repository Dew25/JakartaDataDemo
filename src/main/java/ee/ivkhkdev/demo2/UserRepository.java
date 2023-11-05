package ee.ivkhkdev.demo2;

import jakarta.data.repository.DataRepository;
import jakarta.data.repository.OrderBy;
import jakarta.data.repository.Repository;

import java.util.List;

@Repository
public interface UserRepository extends DataRepository<User, Long> {

    @OrderBy("lastname")
    List<User> findAll();
    void deleteByUserId(Long id);
    void save(User user);

    User findById(Long id);
}
