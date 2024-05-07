@import exercise.dto.users.UserPage;
@param UserPage user
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User</title>
</head>
<body>
<table>
    <tr>
        <td>${user.getUser().getId()}</td>
        <td>${user.getUser().getFirstName()}</td>
        <td>${user.getUser().getLastName()}</td>
        <td>${user.getUser().getEmail()}</td>
    </tr>
</table>
</body>
</html>