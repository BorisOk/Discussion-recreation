<#import "detail/overall.ftlh" as o>
<@o.page>
    <h5>${username}</h5>
    ${message?ifExists}
    <form method="post">
        <div class="form-group">
            <label for="exampleInputEmail1"> Password: </label>
            <input type="password" name="password" class="form-control" placeholder="Password" />
        </div>

            <div class="form-group">
                <label for="exampleInputEmail1"> Email: </label>
                <input type="email" name="email" class="form-control" placeholder="mail@mail.ru" value="${email!''}" />
            </div>

        <br/>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit" class="btn btn-primary">Save</button>
    </form>
</@o.page>