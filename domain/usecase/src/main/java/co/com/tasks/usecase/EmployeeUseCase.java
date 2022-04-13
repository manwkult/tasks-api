package co.com.tasks.usecase;

import co.com.tasks.model.Employee;
import co.com.tasks.model.Task;
import co.com.tasks.model.gateway.EmployeeGateway;
import co.com.tasks.model.gateway.TaskGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public class EmployeeUseCase {
    private final EmployeeGateway employeeGateway;

    /**
     * Method Get All
     * @return Mono<List<Task>
     */
    public Mono<List<Employee>> getAll() {
        return employeeGateway.getAll();
    }
}
