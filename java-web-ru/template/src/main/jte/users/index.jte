@import exercise.dto.users.UsersPage;
@param UsersPage page
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Users</title>

</head>
<body>
<table>
    @for(var user : page.getUsers())
        <tr>
            <td><a href="/users/${user.getId()}">${user.getId()}</a></td>
            <td>${user.getFirstName()}</td>
            <td>${user.getLastName()}</td>
            <td>${user.getEmail()}</td>
        </tr>
    @endfor
</table>
</body>
</html>