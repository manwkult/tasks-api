package co.com.tasks.services;

import co.com.tasks.dao.EmployeeReactiveRepository;
import co.com.tasks.entity.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeReactiveRepository employeeReactiveRepository;

    /**
     * Method Get All
     * @return Flux<TaskEntity>
     */
    public Flux<EmployeeEntity> getAll() {
        return employeeReactiveRepository
                .findAll();
    }
}
