package co.com.tasks.model.gateway;

import co.com.tasks.model.Task;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TaskGateway {
    Mono<List<Task>> getAll();
    Mono<Task> getById(Long id);
    Mono<Task> saveOrUpdate(Task tasks);
    Mono<Boolean> updateStatus(Long id, Integer status);
    Mono<Boolean>delete(Long id);
}
