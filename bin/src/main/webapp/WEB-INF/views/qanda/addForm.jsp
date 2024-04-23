<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add New Q&A</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
            color: #333;
        }
        h1 {
            color: #333;
        }
        form {
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            max-width: 500px;
            margin: 20px auto;
        }
        label {
            margin-bottom: 10px;
            display: block;
            font-weight: bold;
        }
        input[type="text"],
        textarea {
            width: calc(100% - 22px);
            padding: 10px;
            margin-top: 5px;
            margin-bottom: 16px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type="submit"] {
            background-color: #5c67f2;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        input[type="submit"]:hover {
            background-color: #5058e5;
        }
        a {
            display: inline-block;
            margin-top: 20px;
            color: #6c63ff;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<h1>새 글 작성</h1>
<form action="${pageContext.request.contextPath}/qanda/add" method="post">
    <label>제목: <input type="text" name="title"/></label><br>
    <label>글쓴이: <input type="text" name="author"/></label><br>
    <label>내용: <textarea name="content" rows="5" cols="30"></textarea></label><br>
    <input type="submit" value="저장하기"/>
</form>
<a href="${pageContext.request.contextPath}/qanda">취소</a>
</body>
</html>
