<h1>${layout.title}</h1>

<table>
    <thead>
        <tr>
            <td>ID</td>
            <td>Name</td>
        </tr>
    </thead>
    <tbody>
    <#list vars.originators as originator>
        <tr>
            <td>${originator.id}</td>
            <td>${originator.name}</td>
        </tr>
    </#list>
    </tbody>
</table>