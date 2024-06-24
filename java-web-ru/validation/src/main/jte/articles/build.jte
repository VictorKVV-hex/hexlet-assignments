@import exercise.dto.articles.BuildArticlePage
@param BuildArticlePage page

@if(page.getErrors() != null)
    <ul>
        @for(var validator : page.getErrors().values())
            @for(var error : validator)
                <li>${error.getMessage()}</li>
            @endfor
        @endfor
    </ul>
@endif

<form action="/articles" method="post">
    <meta charset="utf-8"/>
    <div>
        <label>
            title
            <input type="text" name="title" value="${page.getTitle()}" />
        </label>
    </div>
    <div>
        <label>
            content
            <textarea name="content" id="content" rows="10" cols="50" required>${page.getContent()}</textarea>
        </label>
    </div>
    <input type="submit" value="Submit" />
</form>