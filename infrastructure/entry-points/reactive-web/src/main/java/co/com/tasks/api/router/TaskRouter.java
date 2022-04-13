package co.com.tasks.api.router;

import co.com.tasks.api.handler.TaskHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class TaskRouter {

    @Value("${routes.tasks}")
    private String routeTasks;

    @Bean
    public RouterFunction<ServerResponse> routerFunctionTasks(TaskHandler handler) {
        return route(GET(routeTasks), handler::getAll)
                .andRoute(GET(routeTasks.concat("/{id}")), handler::getById)
                .andRoute(POST(routeTasks), handler::saveOrUpdate)
                .andRoute(PUT(routeTasks), handler::saveOrUpdate)
                .andRoute(PATCH(routeTasks.concat("/{id}/{status}")), handler::updateStatus)
                .andRoute(DELETE(routeTasks.concat("/{id}")), handler::delete);
    }
}
