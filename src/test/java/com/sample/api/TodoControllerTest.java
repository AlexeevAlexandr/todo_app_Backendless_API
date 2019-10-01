package com.sample.api;

import com.sample.api.controller.TodoController;
import com.sample.api.entity.TodoEntity;
import com.sample.api.service.TodoService;
import com.sample.api.testHelper.TestHelper;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.when;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static javax.servlet.http.HttpServletResponse.SC_OK;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Main.class)
@SpringBootTest
public class TodoControllerTest {
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
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    public void create() throws Exception {
        JSONObject jsonObject = testHelper.getJsonObjectFromFile("json/todoEntity.json");

        String id =
                given().contentType(MediaType.APPLICATION_JSON_VALUE).
                        body(jsonObject.toString()).
                        when().
                        post("/todo").
                        then().
                        statusCode(SC_OK).
                        extract().
                        path("objectId");

        // delete
        testHelper.delete(id);
    }

    @Test
    public void createFalse() throws Exception {
        JSONObject jsonObject = testHelper.getJsonObjectFromFile("json/brokenTodoEntity.json");

        given().contentType(MediaType.APPLICATION_JSON_VALUE).
                body(jsonObject.toString()).
                when().
                post("/todo").
                then().
                statusCode(SC_NOT_FOUND);
    }

    @Test
    public void getAll() {
        when().get("/todo").
        then().statusCode(SC_OK);
    }

    @Test
    public void getById() throws Exception {
        JSONObject jsonObject = testHelper.getJsonObjectFromFile("json/todoEntity.json");

        // create data
        String id =
                given().contentType(MediaType.APPLICATION_JSON_VALUE).
                        body(jsonObject.toString()).
                        when().
                        post("/todo").
                        then().
                        statusCode(SC_OK).
                        extract().
                        path("objectId");

        // check created data
                when().get("/todo/" + id).
                then().statusCode(SC_OK).body("objectId", equalTo(id));

        // delete
        testHelper.delete(id);
    }

    @Test
    public void getByIdFalse() {
        when().get("/todo/0123456789").
                then().statusCode(SC_NOT_FOUND);
    }

    @Test
    public void update() throws Exception {
        JSONObject jsonObject = testHelper.getJsonObjectFromFile("json/todoEntity.json");

        String id =
                given().contentType(MediaType.APPLICATION_JSON_VALUE).
                        body(jsonObject.toString()).
                        when().
                        post("/todo").
                        then().
                        statusCode(SC_OK).
                        extract().
                        path("objectId");

        TodoEntity todoEntity_1 = testHelper.getById(id);
        todoEntity_1.setDescription("new description");

                given().contentType(MediaType.APPLICATION_JSON_VALUE).
                        body(todoEntity_1.toString()).
                        when().
                        put("/todo").
                        then().statusCode(SC_OK);

        // check created data
        TodoEntity todoEntity_2 = testHelper.getById(id);
        Assert.assertEquals(todoEntity_1.getDescription(), todoEntity_2.getDescription());

        // delete
        testHelper.delete(id);
    }

    @Test
    public void updateFalse() throws Exception {
        JSONObject jsonObject = testHelper.getJsonObjectFromFile("json/wrongIdTodoEntity.json");

        given().contentType(MediaType.APPLICATION_JSON_VALUE).
                body(jsonObject.toString()).
                when().
                put("/todo").
                then().
                statusCode(SC_NOT_FOUND);
    }

    @Test
    public void markAsCompleted() throws Exception {
        JSONObject jsonObject = testHelper.getJsonObjectFromFile("json/todoEntity.json");

        String id =
                given().contentType(MediaType.APPLICATION_JSON_VALUE).
                        body(jsonObject.toString()).
                        when().
                        post("/todo").
                        then().
                        statusCode(SC_OK).
                        extract().
                        path("objectId");

        given().contentType(MediaType.APPLICATION_JSON_VALUE).
                when().
                delete("/todo/{id}",id);

        // check created data
        TodoEntity todoEntity = testHelper.getById(id);
        Assert.assertTrue(todoEntity.isCompleted());

        // delete
        testHelper.delete(id);
    }

    @Test
    public void markAsCompleted_False() {
        given().contentType(MediaType.APPLICATION_JSON_VALUE).
                when().
                delete("/todo/0123456789").
                then().
                statusCode(SC_NOT_FOUND);
    }
}
