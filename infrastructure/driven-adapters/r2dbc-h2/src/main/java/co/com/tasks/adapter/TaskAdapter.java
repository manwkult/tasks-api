package co.com.tasks.adapter;

import co.com.tasks.entity.TaskEntity;
import co.com.tasks.model.Task;
import co.com.tasks.model.gateway.TaskGateway;
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
public class TaskAdapter implements TaskGateway {

    @Autowired
    private TaskService taskService;

    /**
     * Method Get All
     * @return Mono<List<Task>>
     */
    @Override
    public Mono<List<Task>> getAll() {
        return taskService
                .getAll()
                .switchIfEmpty(Mono.empty())
                .transform(this::buildResponse)
                .collectList();
    }

    /**
     * Method Get by Id
     * @param id Unique Identifier of Task
     * @return Mono<Task>
     */
    @Override
    public Mono<Task> getById(Long id) {
        return taskService
                .getById(id)
                .switchIfEmpty(Mono.empty())
                .flatMap(response -> {
                    Task data = new Task();
                    BeanUtils.copyProperties(response, data);

                    data.setExecutionDate(convertToDateByLocalDateTime(response.getExecutionDate()));

                    return Mono.just(data);
                });
    }

    /**
     * Method Save of Update
     * @param task Model of Task
     * @return Mono<Task>
     */
    @Override
    public Mono<Task> saveOrUpdate(Task task) {
        TaskEntity taskEntity = new TaskEntity();
        BeanUtils.copyProperties(task, taskEntity);

        taskEntity.setExecutionDate(convertToLocalDateTimeViaInstant(task.getExecutionDate()));

        return taskService
                .saveOrUpdate(taskEntity)
                .switchIfEmpty(Mono.empty())
                .flatMap(response -> {
                    Task data = new Task();
                    BeanUtils.copyProperties(response, data);

                    data.setExecutionDate(convertToDateByLocalDateTime(response.getExecutionDate()));

                    return Mono.just(data);
                });
    }

    /**
     * Method Update Status
     * @param id Unique Identifier of Task
     * @param status Status
     * @return Mono<Boolean>
     */
    @Override
    public Mono<Boolean> updateStatus(Long id, Integer status) {
        return taskService.updateStatus(id, status);
    }

    /**
     * Method Delete
     * @param id Unique Identifier of Task
     * @return Mono<Boolean>
     */
    @Override
    public Mono<Boolean> delete(Long id) {
        return taskService.delete(id);
    }


    @SneakyThrows
    private Flux<Task> buildResponse(Flux<TaskEntity> data) {
        List<Task> tasks = new ArrayList<>();

        data.collectList().toFuture().get().forEach(item -> {
            Task task = new Task();
            BeanUtils.copyProperties(item, task);

            task.setExecutionDate(convertToDateByLocalDateTime(item.getExecutionDate()));

            tasks.add(task);
        });

        return Mono.just(tasks).flatMapIterable(list -> list);
    }

    private LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    private Date convertToDateByLocalDateTime(LocalDateTime localDateTimeToConvert) {
        return Date.from(localDateTimeToConvert.atZone(ZoneId.systemDefault()).toInstant());
    }
}
