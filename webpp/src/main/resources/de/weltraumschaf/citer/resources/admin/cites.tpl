<h1>${layout.title}</h1>

<ul>
    <li><a href="${layout.baseUri}cite/generate/1">generate random cite</a></li>
</ul>

<h2>${vars.cites.size()} Cites</h2>

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
            <td><a href="${layout.baseUri}cite/edit/${cite.id}">edit</a></td>
            <td><a class="delete" href="${layout.baseUri}cite/${cite.id}">delete</a></td>
        </tr>
    </#list>
    </tbody>
</table>