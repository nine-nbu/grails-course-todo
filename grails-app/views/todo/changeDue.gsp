<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>
<div class="content">
    <g:hasErrors bean="${command}">
        <ul class="errors" role="alert">
            <g:eachError bean="${command}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li></g:eachError>
        </ul>
    </g:hasErrors>
    <g:form>
        <g:select name="todo.id" from="${todoList}" optionKey="id" optionValue="title" value="${command.todo?.id}"/>
        <g:field type="datetime-local" name="newDueDate" value="${command.todo?.newDueDate}"/>
        <g:actionSubmit value="Submit" action="doChangeDue"/>
    </g:form>
</div>
</body>
</html>
