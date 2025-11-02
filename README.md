# Backend

## Configuration

### Database Configuration

The application uses environment variables for database credentials. You can override the default values by setting the following environment variables:

- `SPRING_DATASOURCE_URL`: Database connection URL (default: `jdbc:postgresql://localhost:5433/axonproject`)
- `SPRING_DATASOURCE_USERNAME`: Database username (default: `postgres`)
- `SPRING_DATASOURCE_PASSWORD`: Database password (no public default - set this for production)

#### Example Usage

**Using environment variables:**
```bash
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5433/axonproject
export SPRING_DATASOURCE_USERNAME=myuser
export SPRING_DATASOURCE_PASSWORD=mypassword
./mvnw spring-boot:run
```

**Using Docker Compose:**
The `docker-compose.yml` file automatically sets these environment variables for the backend service.