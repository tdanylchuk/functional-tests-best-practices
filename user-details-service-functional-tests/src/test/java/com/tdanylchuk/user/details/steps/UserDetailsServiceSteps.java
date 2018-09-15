package com.tdanylchuk.user.details.steps;

import io.restassured.http.ContentType;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import static io.restassured.RestAssured.given;

@Component
public class UserDetailsServiceSteps implements ApplicationListener<WebServerInitializedEvent> {

    private int servicePort;

    public String getUser(long userId) {
        return given().port(servicePort)
                .when().get("user/" + userId)
                .then().statusCode(200).contentType(ContentType.JSON).extract().asString();
    }

    public void saveUserDetails(long userId, String body) {
        given().port(servicePort).body(body).contentType(ContentType.JSON)
                .when().post("user/" + userId + "/details")
                .then().statusCode(200);
    }

    public String getUserDetails(long userId) {
        return given().port(servicePort)
                .when().get("user/" + userId + "/details")
                .then().statusCode(200).contentType(ContentType.JSON).extract().asString();
    }

    @Override
    public void onApplicationEvent(@NotNull WebServerInitializedEvent webServerInitializedEvent) {
        this.servicePort = webServerInitializedEvent.getWebServer().getPort();
    }
}
