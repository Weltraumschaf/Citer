<h1>${layout.title}</h1>

<table>
    <thead>
        <tr>
            <td>ID</td>
            <td>Text</td>
            <td>Originator</td>
            <td colspan="2"/>
        </tr>
    </thead>
    <tbody>
    <#list vars.cites as cite>
        <tr>
            <td>${cite.id}</td>
            <td>${cite.text}</td>
            <td>n/a</td>
            <td><a id="edit_cite" href="${layout.baseUri}cite/edit/${cite.id}">edit</a></td>
            <td><a id="delete_cite" href="javascript:;">delete</a></td>
        </tr>
    </#list>
    </tbody>
</table>