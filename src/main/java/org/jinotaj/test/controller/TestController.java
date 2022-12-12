package org.jinotaj.test.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * @author Filip Jirsák
 */
@Controller
public class TestController {
    private final Logger logger = LoggerFactory.getLogger(TestController.class);
    private final Random random = new Random();

    @Get("/anonymous")
    @Secured(SecurityRule.IS_ANONYMOUS)
    public TestForm anonymous() {
        return random();
    }

    @Get("/authenticated")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    public TestForm authenticated(Authentication authentication) {
        logger.debug("Authenticated user: {}", authentication.getName());
        return random();
    }

    @Get("/unsecured")
    public TestForm unsecured() {
        return random();
    }

    private TestForm random() {
        return new TestForm(random.nextInt());
    }
}
