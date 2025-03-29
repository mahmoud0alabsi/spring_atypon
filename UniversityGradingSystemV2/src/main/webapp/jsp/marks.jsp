<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.example.universitygradingsystemV2.model.Grade" %>
<%
    if (session.getAttribute("student") == null) {
        response.sendRedirect(request.getContextPath() + "/login");
        return;
    }
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Marks</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

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
        canvas {
            max-width: 100%;
            height: auto;
        }
    </style>
</head>
<body>

    <!-- Dashboard Layout -->
    <div class="dashboard-container">
        <!-- Sidebar -->
        <div class="sidebar">
            <h4>Dashboard</h4>
            <a href="<%= request.getContextPath() %>/dashboard">🏠 Home</a>
            <a href="<%= request.getContextPath() %>/marks">📖 View My Grades</a>
            <a href="<%= request.getContextPath() %>/stats">📊 View Class Statistics</a>
            <form action="<%= request.getContextPath() %>/logout" method="post" class="d-inline logout-btn logout-btn btn btn-danger">
                <button type="submit" class="logout-btn btn btn-danger">🚪 Logout</button>
            </form>
        </div>

        <!-- Main Content -->
        <div class="content">
            <h2 class="text-primary">Your Enrolled Classes & Grades</h2>

            <div class="table-responsive">
                <table class="table table-striped table-bordered">
                    <thead class="table-primary">
                        <tr>
                            <th>Class Name</th>
                            <th>Your Grade</th>
                            <th>Class Average</th>
                            <th>Class Median</th>
                            <th>Highest</th>
                            <th>Lowest</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                    <%
                        List<Grade> grades = (List<Grade>) request.getAttribute("grades");
                        if (grades != null) {
                            for (Grade stats : grades) {
                                double grade = stats.getGrade();
                                double avg = stats.getAvgGrade();
                                double median = stats.getMedianGrade();
                                double max = stats.getMaxGrade();
                                double min = stats.getMinGrade();

                                String status;
                                if (grade == max) {
                                    status = "Top of the Class 🏆";
                                } else if (grade >= avg) {
                                    status = "Above Average 👍";
                                } else if (grade >= median) {
                                    status = "Around Median 👌";
                                } else if (grade == min) {
                                    status = "Lowest in Class ⚠️";
                                } else {
                                    status = "Below Average ⚠️";
                                }
                    %>
                        <tr>
                            <td><%= stats.getCourseName() %></td>
                            <td><%= String.format("%.2f", grade) %></td>
                            <td><%= String.format("%.2f", avg) %></td>
                            <td><%= String.format("%.2f", median) %></td>
                            <td><%= String.format("%.2f", max) %></td>
                            <td><%= String.format("%.2f", min) %></td>
                            <td><%= status %></td>
                        </tr>
                    <%
                            }
                        }
                    %>
                    </tbody>
                </table>
            </div>

            <h2 class="text-primary mt-4">Marks Visualization</h2>
            <div class="row">
                <div class="col-md-8 mx-auto">
                    <canvas id="barChart"></canvas>
                </div>
            </div>
        </div>
    </div>

    <script>
        // Prepare data for the bar chart
        var classNames = [];
        var studentGrades = [];
        var classAverages = [];

        <% if (grades != null) {
            for (Grade stats : grades) { %>
                classNames.push("<%= stats.getCourseName() %>");
                studentGrades.push(<%= stats.getGrade() %>);
                classAverages.push(<%= stats.getAvgGrade() %>);
        <% } } %>

        // Bar Chart (Student Grades vs. Class Averages)
        var ctx = document.getElementById('barChart').getContext('2d');
        var barChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: classNames,
                datasets: [
                    {
                        label: 'Your Grade',
                        data: studentGrades,
                        backgroundColor: 'blue'
                    },
                    {
                        label: 'Class Average',
                        data: classAverages,
                        backgroundColor: 'green'
                    }
                ]
            },
            options: { responsive: true }
        });
    </script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
