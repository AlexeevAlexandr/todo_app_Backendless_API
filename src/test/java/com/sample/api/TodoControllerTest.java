package com.sample.api;

import com.sample.api.controller.TodoController;
import com.sample.api.entity.TodoEntity;
import com.sample.api.service.TodoService;
import com.sample.api.service.TodoServiceImpl;
import com.sample.api.testHelper.TestHelper;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Main.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TodoControllerTest {
    private MockMvc mockMvc;
    @Autowired
    TodoController todoController;
    @Autowired
    TodoService todoService;
    @Autowired
    TestHelper testHelper;
    @Autowired
    private WebApplicationContext wac;

    @Before
    public final void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    public static void main(String[] args) {
        TodoServiceImpl todoService = new TodoServiceImpl();
        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setDeadlineDate("2020-12-31");
        todoEntity.setDescription("here will be a description");
        todoEntity.setOwner("Owner's Name");
        System.out.println(todoService.save(todoEntity));

        System.out.println(todoService.getAll().size());

        TodoEntity todoEntity2 = todoService.getById("59DBBBF6-057D-4D82-FFC2-3A359F35D000");
        todoEntity2.setDescription("rrrrrrrrr");
        System.out.println(todoService.save(todoEntity2));
    }

    @Test
    public void A_createTest() throws Exception {
        JSONObject jsonObject = testHelper.getJsonObjectFromFile("json/todoEntity.json");

        mockMvc.perform(MockMvcRequestBuilders.post("/todo")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonObject.toString()))
                .andExpect(jsonPath("$.description").value("here will be a description"))
                .andExpect(jsonPath("$.owner").value("Owner's Name"))
                .andExpect(jsonPath("$.deadlineDate").value("2020-01-01"))
                .andDo(print());
    }

    @Test
    public void B_getAllTest() throws Exception {
        JSONObject jsonObject = testHelper.getJsonObjectFromFile("json/todoEntity.json");

        mockMvc.perform(MockMvcRequestBuilders.post("/todo")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonObject.toString()))
                .andDo(print());

        mockMvc.perform(MockMvcRequestBuilders.get("/todo")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andDo(print());
    }

    @Test
    public void C_getByIdTest() throws Exception {
        List<TodoEntity> todoEntityList = todoService.getAll();
        TodoEntity todoEntity = todoEntityList.get(0);
        String id = todoEntity.getObjectId();

        mockMvc.perform(MockMvcRequestBuilders.get("/todo/" + id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.objectId").value(id))
                .andDo(print());
    }

    @Test
    public void D_updateTest() throws Exception {
        List<TodoEntity> todoEntityList = todoService.getAll();
        TodoEntity todoEntity = todoEntityList.get(0);
        todoEntity.setDescription("updated description");

        mockMvc.perform(MockMvcRequestBuilders.put("/todo")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(todoEntity.toString()))
                .andExpect(jsonPath("$.description").value("updated description"))
                .andDo(print());
    }

    @Test
    public void E_markAsCompletedTest() throws Exception {
        List<TodoEntity> todoEntityList = todoService.getAll();
        TodoEntity todoEntity = todoEntityList.get(0);
        String id = todoEntity.getObjectId();

        mockMvc.perform(MockMvcRequestBuilders.delete("/todo/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(jsonPath("$.completed").value(true))
                .andDo(print());
    }

    @Test
    public void F_getByIdFalseTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/todo/0123456789"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(print());
    }

    @Test
    public void G_updateFalseTest() throws Exception {
        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setObjectId("0123456789");

        mockMvc.perform(MockMvcRequestBuilders.put("/todo")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(todoEntity.toString()))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(print());
    }

    @Test
    public void G_markAsCompletedFalseTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/todo/0123456789"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(print());
    }
}
