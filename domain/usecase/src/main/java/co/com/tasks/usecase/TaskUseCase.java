package co.com.tasks.usecase;

import co.com.tasks.model.Task;
import co.com.tasks.model.gateway.TaskGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public class TaskUseCase {
    private final TaskGateway taskGateway;

    /**
     * Method Get All
     * @return Mono<List<Task>
     */
    public Mono<List<Task>> getAll() {
        return taskGateway.getAll();
    }

    /**
     * Method Get by Id
     * @param id Unique Identifier of Task
     * @return Mono<Task>
     */
    public Mono<Task> getById(Long id) {
        return taskGateway.getById(id);
    }

    /**
     * Method Save of Update
     * @param tasks Model of Task
     * @return Mono<Task>
     */
    public Mono<Task> saveOrUpdate(Task tasks) {
        return taskGateway
                .saveOrUpdate(tasks)
                .doOnError(error -> {
                    System.out.println(error.getMessage());
                });
    }

    /**
     * Method Update Status
     * @param id Unique Identifier of Task
     * @param status Status
     * @return Mono<Boolean>
     */
    public Mono<Boolean> updateStatus(Long id, Integer status) {
        return taskGateway.updateStatus(id, status);
    }

    /**
     * Method Delete
     * @param id Unique Identifier of Task
     * @return Mono<Boolean>
     */
    public Mono<Boolean> delete(Long id) {
        return taskGateway.delete(id);
    }
}
