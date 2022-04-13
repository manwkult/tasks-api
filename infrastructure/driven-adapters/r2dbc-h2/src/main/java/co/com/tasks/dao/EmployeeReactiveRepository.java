package co.com.tasks.dao;

import co.com.tasks.entity.EmployeeEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeReactiveRepository extends ReactiveCrudRepository<EmployeeEntity, Long> {}
