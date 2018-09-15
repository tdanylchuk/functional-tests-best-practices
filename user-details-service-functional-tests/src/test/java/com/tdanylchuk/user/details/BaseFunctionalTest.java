package com.tdanylchuk.user.details;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.tdanylchuk.user.details.steps.ContactsServiceSteps;
import com.tdanylchuk.user.details.steps.UserDetailsServiceSteps;
import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.charset.Charset;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.springframework.util.ResourceUtils.getFile;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = UserDetailsServiceApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public abstract class BaseFunctionalTest {

    @Rule
    public WireMockRule contactsServiceMock = new WireMockRule(options().port(8777));

    @Autowired
    protected UserDetailsServiceSteps userDetailsServiceSteps;
    @Autowired
    protected ContactsServiceSteps contactsServiceSteps;

    @TestConfiguration
    @ComponentScan("com.tdanylchuk.user.details.steps")
    public static class StepsConfiguration {
    }

    public static String readFile(String path) {
        try {
            return FileUtils.readFileToString(getFile("classpath:" + path), Charset.defaultCharset());
        } catch (IOException e) {
            throw new RuntimeException("File cannot be read");
        }
    }

}
