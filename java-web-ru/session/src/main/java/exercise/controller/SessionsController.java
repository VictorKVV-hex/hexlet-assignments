package exercise.controller;

import java.util.Collections;
import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.repository.UsersRepository;
import exercise.util.NamedRoutes;
import io.javalin.http.Context;

import static exercise.util.Security.encrypt;


public class SessionsController {

    // BEGIN
    public static void loginPage(Context ctx) {
        var page = new LoginPage("", null);
        ctx.render("build.jte", Collections.singletonMap("page", page));
    }

    public static void login(Context ctx) {
        var username = ctx.formParam("name");
        var password = ctx.formParam("password");
        var user = UsersRepository.findByName(username);

        if (user != null && user.getPassword().equals(encrypt(password))) {
            ctx.sessionAttribute("currentUser", username);
            ctx.redirect(NamedRoutes.rootPath());
        } else {
            var errorMessage = "Wrong username or password";
            var page = new LoginPage(username, errorMessage);
            ctx.render("build.jte", Collections.singletonMap("page", page));
        }
    }

    public static void destroy(Context ctx) {
        ctx.sessionAttribute("currentUser", null);
        ctx.redirect(NamedRoutes.rootPath());
    }

    public static void mainPage(Context ctx) {
        var page = new MainPage(ctx.sessionAttribute("currentUser"));
        ctx.render("index.jte", Collections.singletonMap("page", page));
    }
    // END
}