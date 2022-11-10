package ru.netology.test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import netscape.javascript.JSException;
import netscape.javascript.JSObject;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.json.Json;
import ru.netology.data.DataHelper;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BackTest {

    @Test
    void shouldRotate (){
        JSObject requestBody = new JSObject() {
            @Override
            public Object call(String methodName, Object... args) throws JSException {
                return null;
            }

            @Override
            public Object eval(String s) throws JSException {
                return null;
            }

            @Override
            public Object getMember(String name) throws JSException {
                return null;
            }

            @Override
            public void setMember(String name, Object value) throws JSException {

            }

            @Override
            public void removeMember(String name) throws JSException {

            }

            @Override
            public Object getSlot(int index) throws JSException {
                return null;
            }

            @Override
            public void setSlot(int index, Object value) throws JSException {

            }
        };

        requestBody.setMember("login", "vasya");
        requestBody.setMember("password", "qwerty123");
        RequestSpecification request = RestAssured.given();
        request.header("Content-type", "application/json")
                .baseUri("http://localhost:9999/api")
                .body(requestBody);
        Response response = request.post("/auth");
        int statusCode = response.getStatusCode();
        assertEquals(statusCode, 200);
    }
}
