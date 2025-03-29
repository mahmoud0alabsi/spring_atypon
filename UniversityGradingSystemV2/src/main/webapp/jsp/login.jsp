<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (session.getAttribute("student") != null) {
        response.sendRedirect(request.getContextPath() + "/dashboard");
        return;
    }
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Login</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f8f9fa;
        }
        .login-container {
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .login-card {
            width: 100%;
            max-width: 400px;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
        }
    </style>
</head>
<body>

    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container">
            <a class="navbar-brand" href="<%= request.getContextPath() %>/home">University Grading System</a>
        </div>
    </nav>

    <!-- Login Form Section -->
    <div class="container login-container">
        <div class="card login-card">
            <h3 class="text-center text-primary">Student Login</h3>

            <!-- Display Error Message -->
            <% if (request.getAttribute("errorMessage") != null) { %>
                <div class="alert alert-danger text-center" role="alert">
                    <%= request.getAttribute("errorMessage") %>
                </div>
            <% } %>

            <form action="login" method="post">
                <div class="mb-3">
                    <label class="form-label">Student ID:</label>
                    <input type="number" name="student_id" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Password:</label>
                    <input type="password" name="password" class="form-control" required>
                </div>
                <button type="submit" class="btn btn-primary w-100">Login</button>
            </form>

            <div class="text-center mt-3">
                <a href="<%= request.getContextPath() %>/" class="text-secondary">Back to Home</a>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
