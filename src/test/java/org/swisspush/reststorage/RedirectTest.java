package org.swisspush.reststorage;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.with;

@RunWith(VertxUnitRunner.class)
public class RedirectTest extends AbstractTestCase {

    @Test
    public void testGetHTMLResourceWithoutTrailingSlash(TestContext testContext) throws InterruptedException {
        Async async = testContext.async();
        RestAssured.basePath = "/pages";
        with().body("<h1>nemo.html</h1>").put("nemo.html");
        get("nemo.html").then().assertThat().
                statusCode(200).
                assertThat().
                contentType(ContentType.HTML);
        async.complete();
    }

    @Test
    public void testGetHTMLResourceWithTrailingSlash(TestContext testContext) throws InterruptedException {
        Async async = testContext.async();
        RestAssured.basePath = "/pages";
        with().body("<h1>nemo.html</h1>").put("nemo.html");
        get("nemo.html/").then().assertThat().
                statusCode(200).
                assertThat().
                contentType(ContentType.HTML);
        async.complete();
    }
}
