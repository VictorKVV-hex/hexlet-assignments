@import exercise.dto.posts.PostPage
@param PostPage page

@template.layout.page(
    content = @`
    <p><a href="/posts">Back</a></p>
    <h1 style="text-align: center">${page.getPost().getName()}</h1>
    <p style="text-align: center">${page.getPost().getBody()}</p>
`)