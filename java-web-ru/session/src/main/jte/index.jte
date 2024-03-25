@import exercise.dto.MainPage
@import exercise.util.NamedRoutes
@param MainPage page

@template.layout.page(content = @`
    @if(page.getName() != null)
        <p>Welcome, ${page.getName().toString()}!</p>
        <form method="post" action="${NamedRoutes.logoutPath()}">
            <button type="submit">Выйти</button>
        </form>
    @else
        <p><a href="${NamedRoutes.buildSessionPath()}">Войти</a></p>
    @endif
`)