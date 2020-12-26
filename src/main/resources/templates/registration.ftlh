<#import "detail/overall.ftlh" as o>
<#import "detail/login.ftlh" as l>
<@o.page>
    <h2>Make registration</h2>

    <#if message??>
        <div class="alert alert-danger" role="alert">
            ${message}
        </div>
    </#if>

    <@l.login "/registration" true/>
</@o.page>