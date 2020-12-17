<#import "detail/overall.ftlh" as o>
<#import "detail/login.ftlh" as l>
<@o.page>
    <div>
        <@l.logout />
        <span><a href="/user">User list</a></span>
    </div>
    <h1>Share your impressions of your travels!</h1>
    <div>
        <form method="post">
            <label><input type="text" name="text" placeholder="Ваш комментарий..."></label>
            <label><input type="text" name="country" placeholder="Страна путешествия"></label>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <input type="submit" value="Post">
        </form>
    </div>
    <div><h2>List of comments</h2></div>
    <form method="get">
        <input type="text" name="filter" value="${filter?ifExists}" placeholder="Введите страну для поиска">
        <button type="submit">Find</button>
    </form>

    <#list comments as comment>
        <div>
            <b>*</b>
            <b><i>${comment.country}</i></b>
            <b> : </b>
            <i>${comment.text}</i>
            <b> : </b>
            <strong>${comment.authorName}</strong>
            <hr>
        </div>
        <#else>
        No comments
    </#list>
</@o.page>