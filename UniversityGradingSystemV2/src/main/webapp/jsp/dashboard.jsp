<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%
    if (session.getAttribute("student") == null) {
        response.sendRedirect(request.getContextPath() + "/login");
        return;
    }
    com.example.universitygradingsystemV2.model.Student student = (com.example.universitygradingsystemV2.model.Student) session.getAttribute("student");
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Dashboard</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f8f9fa;
        }
        .dashboard-container {
            display: flex;
            height: 100vh;
        }
        .sidebar {
            width: 250px;
            background: #007bff;
            color: white;
            padding: 20px;
            display: flex;
            flex-direction: column;
        }
        .sidebar a {
            text-decoration: none;
            color: white;
            padding: 10px;
            display: block;
            border-radius: 5px;
            margin-bottom: 10px;
            transition: background 0.3s;
        }
        .sidebar a:hover {
            background: rgba(255, 255, 255, 0.2);
        }
        .content {
            flex-grow: 1;
            padding: 30px;
        }
        .logout-btn {
            margin-top: auto;
        }
    </style>
</head>
<body>

    <!-- Dashboard Layout -->
    <div class="dashboard-container">
        <!-- Sidebar -->
        <div class="sidebar">
            <h4>Dashboard</h4>
            <p>Welcome, <strong><%= student.getName() %></strong></p>
            <a href="<%= request.getContextPath() %>/marks">📖 View My Grades</a>
            <a href="<%= request.getContextPath() %>/stats">📊 View Class Statistics</a>
            <form action="<%= request.getContextPath() %>/logout" method="post" class="d-inline logout-btn logout-btn btn btn-danger">
                <button type="submit" class="logout-btn btn btn-danger">🚪 Logout</button>
            </form>
        </div>

        <!-- Main Content -->
        <div class="content">
            <h2 class="text-primary">Hello, <%= student.getName() %>! 👋</h2>
            <p class="lead">Welcome to your student dashboard. Here you can manage your grades and view class statistics.</p>

            <div class="row">
                <div class="col-md-6">
                    <div class="card p-4 shadow-sm">
                        <h5>📖 My Grades</h5>
                        <p>View and track your grades for enrolled courses.</p>
                        <a href="<%= request.getContextPath() %>/marks" class="btn btn-primary">View Grades</a>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="card p-4 shadow-sm">
                        <h5>📊 Class Statistics</h5>
                        <p>See how your performance compares to the class.</p>
                        <a href="<%= request.getContextPath() %>/stats" class="btn btn-success">View Statistics</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
