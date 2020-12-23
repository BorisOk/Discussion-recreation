<#macro login path isRegistreForm>
    <form action="${path}" method="post">
        <div class="form-group">
            <label for="exampleInputEmail1"> Name </label>
            <input type="text" name="username" class="form-control" placeholder="User name"/>
            <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
        </div>

        <div class="form-group">
            <label for="exampleInputEmail1"> Password: </label>
            <input type="password" name="password" class="form-control" placeholder="Password"/>
        </div>

        <#if isRegistreForm>
        <div class="form-group">
            <label for="exampleInputEmail1"> Email: </label>
            <input type="email" name="email" class="form-control" placeholder="mail@mail.ru"/>
        </div>
        </#if>

        <br>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <#if !isRegistreForm><a href="/registration">Registration</a></#if>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit" class="btn btn-outline-danger">Exit</button>
    </form>
</#macro>