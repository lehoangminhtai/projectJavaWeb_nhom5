<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
</head>
<body>
    <script>
        alert("${errorMessage}");
        // Redirect to the desired page or perform additional actions if needed
        window.location.href = "/login/signup"; // Example: Redirect to the home page
    </script>
</body>
</html>
