package co.com.tasks.dao;

import co.com.tasks.entity.TaskEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

@Repository
public interface TaskReactiveRepository extends ReactiveCrudRepository<TaskEntity, Long> {

    @Query("UPDATE task SET status = :status WHERE id = :id")
    Mono<Void> updateStatus(@Param("id") Long id, @Param("status") Integer status);
}
