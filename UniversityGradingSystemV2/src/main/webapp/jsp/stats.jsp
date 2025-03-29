<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.example.universitygradingsystemV2.model.ClassStats" %>
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
    <title>Class Statistics</title>

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
            <h2 class="text-primary">Class Statistics</h2>

            <div class="table-responsive">
                <table class="table table-striped table-bordered">
                    <thead class="table-primary">
                        <tr>
                            <th>Class Name</th>
                            <th>Number of Students</th>
                            <th>Average Grade</th>
                            <th>Median Grade</th>
                            <th>Highest Grade</th>
                            <th>Lowest Grade</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<ClassStats> classStats = (List<ClassStats>) request.getAttribute("classStats");
                            if (classStats != null) {
                                for (ClassStats stats : classStats) {
                        %>
                        <tr>
                            <td><%= stats.getCourseName() %></td>
                            <td><%= stats.getStudentCount() %></td>
                            <td><%= String.format("%.2f", stats.getAvgGrade()) %></td>
                            <td><%= String.format("%.2f", stats.getMedianGrade()) %></td>
                            <td><%= String.format("%.2f", stats.getMaxGrade()) %></td>
                            <td><%= String.format("%.2f", stats.getMinGrade()) %></td>
                        </tr>
                        <%
                                }
                            }
                        %>
                    </tbody>
                </table>
            </div>

            <h2 class="text-primary">Class Performance Visualization</h2>
            <div class="row">
                <div class="col-md-6">
                    <canvas id="barChart"></canvas>
                </div>
                <div class="col-md-6">
                    <canvas id="lineChart"></canvas>
                </div>
            </div>
        </div>
    </div>

    <script>
        // Prepare data for the charts
        var classNames = [];
        var studentCounts = [];
        var classAverages = [];
        var classMedians = [];
        var highestGrades = [];
        var lowestGrades = [];

        <% if (classStats != null) {
            for (ClassStats stats : classStats) { %>
                classNames.push("<%= stats.getCourseName() %>");
                studentCounts.push(<%= stats.getStudentCount() %>);
                classAverages.push(<%= stats.getAvgGrade() %>);
                classMedians.push(<%= stats.getMedianGrade() %>);
                highestGrades.push(<%= stats.getMaxGrade() %>);
                lowestGrades.push(<%= stats.getMinGrade() %>);
        <% } } %>

        // Bar Chart (Class Average & Median Grades)
        var ctx1 = document.getElementById('barChart').getContext('2d');
        var barChart = new Chart(ctx1, {
            type: 'bar',
            data: {
                labels: classNames,
                datasets: [
                    {
                        label: 'Class Average Grade',
                        data: classAverages,
                        backgroundColor: 'blue'
                    },
                    {
                        label: 'Class Median Grade',
                        data: classMedians,
                        backgroundColor: 'green'
                    }
                ]
            },
            options: { responsive: true }
        });

        // Line Chart (Highest & Lowest Grades per Class)
        var ctx2 = document.getElementById('lineChart').getContext('2d');
        var lineChart = new Chart(ctx2, {
            type: 'line',
            data: {
                labels: classNames,
                datasets: [
                    {
                        label: 'Highest Grade',
                        data: highestGrades,
                        borderColor: 'red',
                        borderWidth: 2,
                        fill: false
                    },
                    {
                        label: 'Lowest Grade',
                        data: lowestGrades,
                        borderColor: 'orange',
                        borderWidth: 2,
                        fill: false
                    }
                ]
            },
            options: { responsive: true }
        });
    </script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
