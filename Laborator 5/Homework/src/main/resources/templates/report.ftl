<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Catalog Report</title>
</head>
<body>
<h1>Catalog Report</h1>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>PathOrUrl</th>
        <th>Tags</th>
    </tr>
    </thead>
    <tbody>
    <#list documents as document>
        <tr>
            <td>${document.getId()}</td>
            <td>${document.getName()}</td>
            <td><a href="${document.getPathOrUrl()}">${document.getPathOrUrl()}</a></td>
            <td>
                <ul>
                    <#list document.getTags() as tag>
                        <li>${tag.getName()}: ${tag.getValue()}</li>
                    </#list>
                </ul>
            </td>
        </tr>
    </#list>
    </tbody>
</table>
</body>
</html>