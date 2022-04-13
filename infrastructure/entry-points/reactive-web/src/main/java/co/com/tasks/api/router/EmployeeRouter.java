package co.com.tasks.api.router;

import co.com.tasks.api.handler.EmployeeHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class EmployeeRouter {

    @Value("${routes.employees}")
    private String routeEmployees;

    @Bean
    public RouterFunction<ServerResponse> routerFunctionEmployees(EmployeeHandler handler) {
        return route(GET(routeEmployees), handler::getAll);
    }
}
