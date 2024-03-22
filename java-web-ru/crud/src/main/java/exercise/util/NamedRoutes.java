package exercise.util;

public class NamedRoutes {

    public static String rootPath() {
        return "/";
    }

    // BEGIN
    public static String postPathId() {
        return "/posts/{id}";
    }
    public static String postsPath() {
        return "/posts";
    }
    // END
}
