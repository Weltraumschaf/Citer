<h1>${layout.title}</h1>

<table>
    <thead>
        <tr>
            <td>ID</td>
            <td>Name</td>
            <td colspan="2"/>
        </tr>
    </thead>
    <tbody>
    <#list vars.originators as originator>
        <tr>
            <td>${originator.id}</td>
            <td>${originator.name}</td>
            <td><a id="edit_originator" href="${layout.baseUri}originator/edit/${originator.id}">edit</a></td>
            <td><a class="delete" href="${layout.baseUri}originator/${originator.id}">delete</a></td>
        </tr>
    </#list>
    </tbody>
</table>