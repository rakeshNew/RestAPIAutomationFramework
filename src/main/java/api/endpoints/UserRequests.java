package api.endpoints;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class UserRequests {

    //Create User
    public static Response createUser(User payload) {

        // Prerequisite
        Response response = given()
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .body(payload)

        //action
        .when()
        .post(Routes.post_url);
         return response;
    }

    //Get User
    public static Response getUser(String username) {

        // Prerequisite
        Response response = given()
                .accept(ContentType.JSON)
                .pathParam("username", username)

                //action
                .when()
                .get(Routes.get_url);
        return response;
    }


    //Put User
    public static Response putUser(String username, User payload) {

        // Prerequisite
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("username", username)
                .body(payload)

                //action
                .when()
                .put(Routes.put_url);
        return response;
    }

    //Delete User
    public static Response deleteUser(String username) {

        // Prerequisite
        Response response = given()
                .accept(ContentType.JSON)
                .pathParam("username", username)

                //action
                .when()
                .delete(Routes.delete_url);
        return response;
    }


}
