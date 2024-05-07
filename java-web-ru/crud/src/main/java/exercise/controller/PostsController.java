package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class PostsController {

    // BEGIN
    public static void show(Context ctx) {
        try {
            var id = ctx.pathParamAsClass("id", Long.class).get();
            var post = PostRepository.find(id)
                    .orElseThrow(() -> new NotFoundResponse("Page not found"));
            var page = new PostPage(post);
            ctx.render("posts/show.jte", Collections.singletonMap("page", page));
        } catch (NotFoundResponse e) {
            ctx.status(404);
            ctx.result(String.valueOf(e));
        }

    }

    public static void showAll(Context ctx) {
        var page1 = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
        var per = ctx.queryParamAsClass("per", Integer.class).getOrDefault(5);
        var offset = (page1 - 1) * per;

        var posts = PostRepository.getEntities().subList(offset, offset + per);
        var page = new PostsPage(page1, posts);
        ctx.render("posts/index.jte", model("page", page));
    }
    // END
}
