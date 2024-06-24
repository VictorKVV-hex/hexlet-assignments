@import exercise.dto.users.UsersPage;
@param UsersPage page

<form action="/users" method="get">
    <input type="search" required name="term" value="${page.getTerm()}">
    <input type="submit" value="Искать">
</form>

@template.layout.page(
content = @`
    @for(var user : page.getUsers())
        <div><a href="/users/${user.getId()}">${user.getId()}</a> --> ${user.getFirstName()} ${user.getLastName()}</div>
    @endfor
`
)