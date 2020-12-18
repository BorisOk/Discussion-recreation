<#import "detail/overall.ftlh" as o>
<#import "detail/login.ftlh" as l>
<@o.page>
    <h3>Entry</h3>
    <@l.login "/login" false/>
</@o.page>