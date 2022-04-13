package co.com.tasks.api.handler;

import co.com.tasks.model.Task;
import co.com.tasks.usecase.EmployeeUseCase;
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
public class EmployeeHandler {
    private final EmployeeUseCase employeeUseCase;

    public Mono<ServerResponse> getAll(ServerRequest serverRequest) {
        return employeeUseCase.getAll()
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
