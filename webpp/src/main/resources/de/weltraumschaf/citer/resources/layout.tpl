<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" type="image/x-icon" href="${faviconUri}"/>
        <#list cssUris as uri>
        <link rel="stylesheet" type="text/css" href="${uri}"/>
        </#list>

        <title>${title}</title>
    </head>
    <body>${content}</body>
</html>