package co.com.tasks.model.gateway;

import co.com.tasks.model.Employee;
import co.com.tasks.model.Task;
import reactor.core.publisher.Mono;

import java.util.List;

public interface EmployeeGateway {
    Mono<List<Employee>> getAll();
}
