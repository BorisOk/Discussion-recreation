<#import "detail/overall.ftlh" as o>
<#import "detail/login.ftlh" as l>
<@o.page>
    <h3>Entry</h3>
    <@l.login "/login" />
    <h3>If you are not registered yet</h3>
    <a href="/registration">Register</a>
</@o.page>