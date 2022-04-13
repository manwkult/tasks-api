package co.com.tasks.config;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.r2dbc.core.DatabaseClient;

@Configuration
public class H2DatabaseConfig {

    @Bean
    ApplicationRunner init(DatabaseClient client) {
        return args -> {
            client.sql("create table IF NOT EXISTS employee" +
                            "(id SERIAL PRIMARY KEY, name varchar(120) not null);")
                    .fetch()
                    .first()
                    .subscribe();

            client.sql("create table IF NOT EXISTS task" +
                            "(id SERIAL PRIMARY KEY, " +
                            "name varchar(120) not null, " +
                            "description varchar(250) not null, " +
                            "execution_date timestamp not null, " +
                            "status int not null, " +
                            "employee_id bigint not null, " +
                            "foreign key (employee_id) references employee(id));")
                    .fetch()
                    .first()
                    .subscribe();

            client.sql("DELETE FROM task; " +
                            "DELETE FROM employee;")
                    .fetch()
                    .first()
                    .subscribe();

            client.sql("INSERT INTO employee(name) VALUES ('Jose Manuel Rios');")
                    .fetch()
                    .first()
                    .subscribe();
        };
    }
}
