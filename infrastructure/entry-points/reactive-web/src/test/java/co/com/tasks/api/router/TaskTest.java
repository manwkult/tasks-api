package co.com.tasks.api.router;

import co.com.tasks.api.handler.TaskHandler;
import co.com.tasks.model.Task;
import co.com.tasks.usecase.TaskUseCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TaskRouter.class, TaskHandler.class})

@TestPropertySource(properties = {
        "routes.tasks=/tasks"
})
@WebFluxTest
public class TaskTest {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private TaskUseCase taskUseCase;


    @Value("${routes.tasks}")
    private String routeTasks;

    Task task;
    List<Task> tasks;
    Map<String, Object> response;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToApplicationContext(context).build();
    }

    @Before
    public void init() {
        tasks = new ArrayList<>();

        task = Task.builder()
                .id(1L)
                .build();

        tasks.add(task);

        Mockito.when(taskUseCase.getAll()).thenReturn(Mono.just(tasks));
        Mockito.when(taskUseCase.getById(Mockito.anyLong())).thenReturn(Mono.just(task));
        Mockito.when(taskUseCase.saveOrUpdate(Mockito.any())).thenReturn(Mono.just(task));
        Mockito.when(taskUseCase.delete(Mockito.anyLong())).thenReturn(Mono.just(true));
    }

    @Test
    public void getAllTest() {
        response = new HashMap<>();

        response.put("success", true);
        response.put("data", tasks);

        webTestClient
                .get()
                .uri(routeTasks)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Map.class)
                .consumeWith(response -> {
                    Assert.assertEquals(response.getResponseBody(), response.getResponseBody());
                });
    }

    @Test
    public void getAllByNameContainsTest() {
        response = new HashMap<>();

        response.put("success", true);
        response.put("data", tasks);

        webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(routeTasks)
                        .queryParam("name", "Super")
                        .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Map.class)
                .consumeWith(response -> {
                    Assert.assertEquals(response.getResponseBody(), response.getResponseBody());
                });
    }

    @Test
    public void getByIdTest() {
        response = new HashMap<>();

        response.put("success", true);
        response.put("data", tasks);

        webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(routeTasks.concat("/{id}"))
                        .build(1)
                )
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Map.class)
                .consumeWith(response -> {
                    Assert.assertEquals(response.getResponseBody(), response.getResponseBody());
                });
    }

    @Test
    public void saveTest() {
        response = new HashMap<>();

        response.put("success", true);
        response.put("data", task);

        webTestClient
                .post()
                .uri(routeTasks)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(Mono.just(task), Task.class))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Map.class)
                .consumeWith(response -> {
                    Assert.assertEquals(response.getResponseBody(), response.getResponseBody());
                });
    }

    @Test
    public void updateTest() {
        response = new HashMap<>();

        response.put("success", true);
        response.put("data", task);

        webTestClient
                .put()
                .uri(routeTasks)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(Mono.just(task), Task.class))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Map.class)
                .consumeWith(response -> {
                    Assert.assertEquals(response.getResponseBody(), response.getResponseBody());
                });
    }

    @Test
    public void deleteTest() {
        response = new HashMap<>();

        response.put("success", true);
        response.put("data", true);

        webTestClient
                .delete()
                .uri(uriBuilder -> uriBuilder
                        .path(routeTasks.concat("/{id}"))
                        .build(1)
                )
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Map.class)
                .consumeWith(response -> {
                    Assert.assertEquals(response.getResponseBody(), response.getResponseBody());
                });
    }
}
