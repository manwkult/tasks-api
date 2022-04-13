package co.com.tasks.config;

import co.com.tasks.model.gateway.EmployeeGateway;
import co.com.tasks.model.gateway.TaskGateway;
import co.com.tasks.usecase.EmployeeUseCase;
import co.com.tasks.usecase.TaskUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfig {

        @Bean
        public TaskUseCase getTaskUseCase(TaskGateway taskGateway) {
                return new TaskUseCase(taskGateway);
        }

        @Bean
        public EmployeeUseCase getEmployeeUseCase(EmployeeGateway employeeGateway) {
                return new EmployeeUseCase(employeeGateway);
        }
}
