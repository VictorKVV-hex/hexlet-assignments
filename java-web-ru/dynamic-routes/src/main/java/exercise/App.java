package exercise;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// BEGIN

// END

public final class App {

    private static final List<Map<String, String>> COMPANIES = Data.getCompanies();

    public static Javalin getApp() {
        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });
        // BEGIN
        app.get("/companies/{id}", ctx -> App.getCompanyById(ctx));
        // END
        app.get("/companies", ctx -> {
            ctx.json(COMPANIES);
        });
        app.get("/", ctx -> {
            ctx.result("open something like (you can change id): /companies/5");
        });
        return app;
    }
    public static void getCompanyById(Context ctx) {
        var id = ctx.pathParam("id");
        Map<String, String> company = new HashMap<>();
        for (Map<String, String> c : COMPANIES) {
            System.out.println(c);
            if (c.get("id").equals(id)) {
                company = c;
            }
        }
/*        Optional<Map<String, String>> company = COMPANIES.stream()
                .filter(c -> c.get("id").equals(id))
                .findFirst();*/
        if (company.isEmpty()) {
            throw new NotFoundResponse("Company not found");
        } else {
            ctx.json(company);
        }
    }
    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
