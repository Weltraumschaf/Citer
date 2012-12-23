<h1>${layout.title}</h1>

<form action="${vars.actionUri}" method="POST">
    <label for="id">ID:</label> <input type="text" disabled="disables" value="${vars.cite.id}" size="64"/><br/>
    <label for="text">Text:</label> <textarea cols="50" rows="10">${vars.cite.text}</textarea><br/>
    <input type="submit" value="save"/>
</form>