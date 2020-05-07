<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>
<div class="content">
    <g:form>
        <g:select name="todo.id" from="${todoList}" optionKey="id" optionValue="title" value="${command.todo?.id}"/>
        <g:field type="datetime-local" name="newDueDate" value="${command.todo?.newDueDate}"/>
        <g:actionSubmit value="Submit" action="doChangeDue"/>
    </g:form>
</div>
</body>
</html>
