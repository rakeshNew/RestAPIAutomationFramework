package api.tests;

import api.endpoints.UserRequests;
import api.payloads.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserDDTest {
    User userPayload ;


    @Test(priority = 1, dataProvider="allData", dataProviderClass = DataProviders.class)
    public void testCreateUser(String userId, String userName, String firstName, String lastName, String emailId, String password, String phoneNbr){

        userPayload = new User();
        userPayload.setId(Integer.parseInt(userId));
        userPayload.setUsername(userName);
        userPayload.setFirstName(firstName);
        userPayload.setLastName(lastName);
        userPayload.setEmail(emailId);
        userPayload.setPassword(password);
        userPayload.setPhone(phoneNbr);

        Response response = UserRequests.createUser(userPayload);

        System.out.println("-----------------*********************---------------------------------------");
        System.out.println("Create User");

        //log response
        response.then().log().all();

        //validate response status code
        Assert.assertEquals(response.getStatusCode(), 200);

    }



    @Test(priority = 2, dataProvider = "userNamesData", dataProviderClass = DataProviders.class)
    public void testDeleteUser(String userName) {

        Response response = UserRequests.deleteUser(userName);

        System.out.println("-----------------*********************---------------------------------------");
        System.out.println("Delete User Data");
        //log response
        response.then().log().all();

        //validate response status code
        Assert.assertEquals(response.getStatusCode(), 200);

    }
}
