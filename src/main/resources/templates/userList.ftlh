<#import "detail/overall.ftlh" as o>
<@o.page>
    List of users
    <table>
        <thead>
        <tr>
            <th>User name</th>
            <th>Role</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list userList as user>
            <tr>
                <td>${user.username}</td>
                <td><#list user.roles as role>${role}<#sep>, </#list></td>
                <td><a href="/user/${user.id}">Edit</a> </td>
            </tr>
        </#list>
        </tbody>
    </table>
</@o.page>