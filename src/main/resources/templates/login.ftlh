<#import "detail/overall.ftlh" as o>
<#import "detail/login.ftlh" as l>
<@o.page>
    <h3>Entrance</h3>
    <#if message??>
        <div class="alert alert-success" role="alert">
            ${message}
        </div>
    </#if>
    <@l.login "/login" false/>
</@o.page>