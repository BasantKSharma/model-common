FROM mysql:8.4.0

# Create the directory for SQL files
RUN mkdir -p /tmp/db /docker-entrypoint-initdb.d

# Argument for the directory containing the SQL files
ARG DB_DIR

# Copy the SQL files to /tmp/db
COPY $DB_DIR /tmp/db

# Display the content of the copied files
#RUN find /tmp/db -type f -name "*.sql" -exec cat {} \;

COPY dockerfile/addDelimiters.sh /usr/local/bin/
RUN chmod +x /usr/local/bin/addDelimiters.sh
RUN find /tmp/db -type f -name "*procedure*.sql" -exec /usr/local/bin/addDelimiters.sh {} \;

# Move and rename all SQL files from /tmp/db and its subdirectories to /docker-entrypoint-initdb.d
RUN find /tmp/db -type f -name "*.sql" | while read -r file; do \
        base=$(basename "$file"); \
        dir=$(dirname "$file" | sed 's|/|_|g'); \
        mv "$file" "/docker-entrypoint-initdb.d/${dir}_${base}"; \
    done

# Default command to run MySQL
CMD ["mysqld"]
