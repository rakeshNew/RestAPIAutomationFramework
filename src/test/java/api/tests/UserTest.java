package api.tests;

import api.endpoints.UserRequests;
import api.payloads.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTest {

    Faker faker ;
    User userPayload ;
    public static Logger logger;


    @BeforeClass
    public void generateTestData(){
        faker = new Faker();
        userPayload = new User();
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5, 10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        logger = LogManager.getLogger("RestAssuredAPIAutomation");

    }


    @Test(priority = 1)
    public void testCreateUser(){
       Response response = UserRequests.createUser(userPayload);

        System.out.println("-----------------*********************---------------------------------------");
        System.out.println("Create User");

        //log response
        response.then().log().all();

        //validate response status code

        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("Create User executed");

    }


    @Test(priority = 2)
    public void testGetUserData(){

        Response response = UserRequests.getUser(userPayload.getUsername());

        System.out.println("-----------------*********************---------------------------------------");

        System.out.println("Read User Data");
        //log response
        response.then().log().all();

        //validate response status code
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("Get User Data executed");
    }


    @Test(priority = 3)
    public void testUpdateUser(){

        userPayload.setFirstName(faker.name().firstName());

        System.out.println("First Name after update "  + userPayload.getFirstName());

        System.out.println("UserPayload after update "  + userPayload);

        Response response = UserRequests.putUser(userPayload.getUsername(), userPayload);
        response.then().log().all();
        System.out.println("-----------------*********************---------------------------------------");
        System.out.println("Update User Data");
        //log response

        //validate response status code
        Assert.assertEquals(response.getStatusCode(), 200);

        //Read User data to check if the first name is updated or not

        System.out.println("Response after Update User Data");
        Response response1 = UserRequests.getUser(userPayload.getUsername());
        response1.then().log().all();
        logger.info("Update User Data executed");
    }

    @Test(priority = 4)
    public void testDeleteUser(){
        Response response = UserRequests.deleteUser(userPayload.getUsername());

        System.out.println("-----------------*********************---------------------------------------");
        System.out.println("Delete User Data");
        //log response
        response.then().log().all();

        //validate response status code
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("Delete User Data executed");
    }

}
