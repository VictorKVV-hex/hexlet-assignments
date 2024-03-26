@import exercise.dto.LoginPage
@import exercise.util.NamedRoutes
@param LoginPage page

@template.layout.page(content = @`
    <h2>Login</h2>
    <form action="${NamedRoutes.loginPath()}" method="post">
        <input type="text" placeholder="Nickname" name="name" />
        <input type="password" placeholder="Password" name="password" />
        <input type="submit" />
    </form>

    @if(page.getError() != null)
        <p>${page.getError()}</p>
    @endif
`)