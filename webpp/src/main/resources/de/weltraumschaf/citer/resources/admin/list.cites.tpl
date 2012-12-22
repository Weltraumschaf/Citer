<h1>${layout.title}</h1>

<table>
    <thead>
        <tr>
            <td>ID</td>
            <td>Text</td>
            <td>Originator</td>
        </tr>
    </thead>
    <tbody>
    <#list vars.cites as cite>
        <tr>
            <td>${cite.id}</td>
            <td>${cite.text}</td>
            <td>n/a</td>
        </tr>
    </#list>
    </tbody>
</table>