<#import "detail/overall.ftlh" as o>
<@o.page>
    <h1>Share your impressions of your travels!</h1>
    <div>
        <img src="/static/sea-2.jpg" alt="..." class="rounded">
        <img src="/static/wave.jpg" alt="..." class="rounded-circle">
        <img src="/static/sea.jpg" alt="..." class="rounded">
    </div>

    <form method="get" action="/main" class="form-check-inline">
        <label>
            <input class="form-control" type="text" name="filter" value="${filter?ifExists}" placeholder="Страна для поиска">
        </label>
        <button class="btn btn-outline-dark me-3" type="submit">Find</button>
    </form>

    <div><a class="btn btn-dark mb-1 mt-2" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
            Add a comment
        </a>
        <div class="collapse" id="collapseExample">
            <form method="post" action="/main">
                <div class="form-group mb-2 mt-2">
                    <textarea type="text" name="text" class="form-control" rows="5" placeholder="Ваш комментарий..."></textarea>
                </div>
                <div class="form-check-inline">
                    <label><input type="text" name="country" class="form-control" placeholder="Страна путешествия"></label>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <button type="submit" class="btn btn-outline-dark me-3">Post</button>
                </div>
            </form>
        </div>
    </div>

    <div><h2>List of comments</h2></div>
    <#list comments as comment>
        <div class="card my-3">
            <div class="card-header">
                <b><i>${comment.country}</i></b>
            </div>
            <div class="card-body">
                <blockquote class="blockquote mb-0">
                    <p><i>${comment.text}</i></p>
                    <footer class="blockquote-footer">Author: <cite title="Source Title"><strong>${comment.authorName}</strong></cite></footer>
                </blockquote>
            </div>
        </div>
        <#else>
        No comments
    </#list>
</@o.page>