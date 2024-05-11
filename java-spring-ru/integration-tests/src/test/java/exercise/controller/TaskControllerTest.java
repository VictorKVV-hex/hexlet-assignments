package exercise.controller;

import org.junit.jupiter.api.Test;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

// BEGIN
@SpringBootTest
@AutoConfigureMockMvc
// END
class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;


    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }

    // BEGIN
    private Task generateTask() {
        return Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .supply(Select.field(Task::getTitle), () -> faker.lorem().word())
                .supply(Select.field(Task::getDescription), () -> faker.lorem().sentence())
                .create();
    }

    @Test
    public void testViewTask() throws Exception {
        Task task = generateTask();
        taskRepository.save(task);
       mockMvc.perform(get("/tasks/" + task.getId()))
//        mockMvc.perform(get("/tasks/{id}", task.getId()))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    var body = result.getResponse().getContentAsString();
                    assertThatJson(body).node("id").isEqualTo(task.getId());
                    assertThatJson(body).node("title").isEqualTo(task.getTitle());
                    assertThatJson(body).node("description").isEqualTo(task.getDescription());
                });
    }

    public void testCreateTask() throws Exception {
        Task task = generateTask();
        String jsonTask = om.writeValueAsString(task);

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonTask))
                .andExpect(status().isCreated());

        assertThat(task.getTitle()).isEqualTo(task.getTitle());
    }

    @Test
    public void testUpdateTask() throws Exception {
        Task task = generateTask();
        taskRepository.save(task);

        task.setTitle("Новый заголовок");
        task.setDescription("Новое описание");
        String updatedJsonTask = om.writeValueAsString(task);

        mockMvc.perform(put("/tasks/{id}", task.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedJsonTask))
                .andExpect(status().isOk());
        assertThat(task.getTitle()).isEqualTo(task.getTitle());
    }

    @Test
    public void testDeleteTask() throws Exception {
        Task task = generateTask();
        taskRepository.save(task);

        mockMvc.perform(delete("/tasks/{id}", task.getId()))
                .andExpect(status().isOk());
        Task nullTask = taskRepository.findById(task.getId()).orElse(null);
        assertThat(nullTask).isNull();
    }
    // END
}
