package co.com.tasks.services;

import co.com.tasks.dao.TaskReactiveRepository;
import co.com.tasks.entity.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TaskService {

    @Autowired
    private TaskReactiveRepository taskReactiveRepository;

    /**
     * Method Get All
     * @return Flux<TaskEntity>
     */
    public Flux<TaskEntity> getAll() {
        return taskReactiveRepository
                .findAll();
    }

    /**
     * Method Get by Id
     * @param id Unique Identifier of Task
     * @return Mono<TaskEntity>
     */
    public Mono<TaskEntity> getById(Long id) {
        return taskReactiveRepository.findById(id);
    }

    /**
     * Method Save of Update
     * @param task Model of Task
     * @return Mono<TaskEntity>
     */
    public Mono<TaskEntity> saveOrUpdate(TaskEntity task) {
        return taskReactiveRepository.save(task);
    }

    /**
     * Method Update Status
     * @param id Unique Identifier of Task
     * @param status Status
     * @return Mono<Boolean>
     */
    public Mono<Boolean> updateStatus(Long id, Integer status) {
        return taskReactiveRepository
                .updateStatus(id, status)
                .thenReturn(Boolean.TRUE);
    }

    /**
     * Method Delete
     * @param id Unique Identifier of Task
     * @return Mono<Boolean>
     */
    public Mono<Boolean> delete(Long id) {
        return taskReactiveRepository
                .deleteById(id)
                .thenReturn(Boolean.TRUE);
    }
}
