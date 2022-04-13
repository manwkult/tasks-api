package co.com.tasks.api.handler;

import co.com.tasks.model.Task;
import co.com.tasks.usecase.TaskUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TaskHandler {
    private final TaskUseCase taskUseCase;

    public Mono<ServerResponse> getAll(ServerRequest serverRequest) {
        return taskUseCase.getAll()
                .flatMap(this::buildResponse);
    }

    public Mono<ServerResponse> getById(ServerRequest serverRequest) {
        Long id = Long.valueOf(serverRequest.pathVariable("id"));

        return taskUseCase
                .getById(id)
                .flatMap(this::buildResponse);
    }

    public Mono<ServerResponse> saveOrUpdate(ServerRequest serverRequest) {
        return serverRequest
                .bodyToMono(Task.class)
                .single()
                .flatMap(taskUseCase::saveOrUpdate)
                .flatMap(this::buildResponse);
    }

    public Mono<ServerResponse> updateStatus(ServerRequest serverRequest) {
        Long id = Long.valueOf(serverRequest.pathVariable("id"));
        Integer status = Integer.valueOf(serverRequest.pathVariable("status"));

        return taskUseCase
                .updateStatus(id, status)
                .flatMap(this::buildResponse);
    }

    public Mono<ServerResponse> delete(ServerRequest serverRequest) {
        Long id = Long.valueOf(serverRequest.pathVariable("id"));

        return taskUseCase
                .delete(id)
                .flatMap(this::buildResponse);
    }

    private Mono<ServerResponse> buildResponse(Object data) {
        Map<String, Object> response = new HashMap<>();

        response.put("success", data != null);
        response.put("data", data);

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(response);
    }
}
