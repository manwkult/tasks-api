package co.com.tasks.adapter;

import co.com.tasks.entity.EmployeeEntity;
import co.com.tasks.entity.TaskEntity;
import co.com.tasks.model.Employee;
import co.com.tasks.model.Task;
import co.com.tasks.model.gateway.EmployeeGateway;
import co.com.tasks.model.gateway.TaskGateway;
import co.com.tasks.services.EmployeeService;
import co.com.tasks.services.TaskService;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class EmployeeAdapter implements EmployeeGateway {

    @Autowired
    private EmployeeService employeeService;

    /**
     * Method Get All
     * @return Mono<List<Task>>
     */
    @Override
    public Mono<List<Employee>> getAll() {
        return employeeService
                .getAll()
                .switchIfEmpty(Mono.empty())
                .transform(this::buildResponse)
                .collectList();
    }


    @SneakyThrows
    private Flux<Employee> buildResponse(Flux<EmployeeEntity> data) {
        List<Employee> employees = new ArrayList<>();

        data.collectList().toFuture().get().forEach(item -> {
            Employee employee = new Employee();
            BeanUtils.copyProperties(item, employee);

            employees.add(employee);
        });

        return Mono.just(employees).flatMapIterable(list -> list);
    }
}
