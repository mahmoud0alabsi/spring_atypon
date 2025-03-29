# Use official MySQL image
FROM mysql:8.0

# Set environment variables for MySQL
ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=student_grading_system
ENV MYSQL_USER=admin
ENV MYSQL_PASSWORD=admin

# Copy the schema.sql file into the container
COPY schema.sql /docker-entrypoint-initdb.d/

# Expose MySQL default port
EXPOSE 3306
