<#import "detail/overall.ftlh" as o>
<#import "detail/login.ftlh" as l>
<@o.page>
    <h2>Make registration</h2>
    ${message?ifExists}
    <@l.login "/registration" />
</@o.page>