# 🚀 Axon Backend

Advanced Software Engineering Project - Backend Service

![CI/CD Pipeline](https://github.com/AXON-ASWE/FE/actions/workflows/backend-ci.yml/badge.svg)
![Java Version](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.5-brightgreen)
![Docker](https://img.shields.io/badge/Docker-Multi--Platform-blue)

## 📋 Table of Contents

- [Overview](#overview)
- [Tech Stack](#tech-stack)
- [Getting Started](#getting-started)
- [Development](#development)
- [Testing](#testing)
- [Deployment](#deployment)
- [CI/CD Pipeline](#cicd-pipeline)
- [API Documentation](#api-documentation)
- [Contributing](#contributing)

## 🎯 Overview

This is the backend service for the Axon project, built with Spring Boot and designed for cloud deployment with comprehensive CI/CD automation.

### Key Features

- ✅ RESTful API with Spring Boot
- ✅ PostgreSQL database integration
- ✅ Spring Security authentication
- ✅ OpenAPI/Swagger documentation
- ✅ Docker containerization
- ✅ Multi-platform support (Linux AMD64/ARM64)
- ✅ Automated CI/CD pipeline
- ✅ Comprehensive testing (Unit + Integration)

## 🛠️ Tech Stack

### Core Technologies
- **Java**: 21
- **Spring Boot**: 3.3.5
- **Spring Data JPA**: Database access
- **Spring Security**: Authentication & Authorization
- **PostgreSQL**: 16 (Alpine)
- **Maven**: 3.9+ (Build tool)

### Development Tools
- **Lombok**: Reduce boilerplate code
- **Spring DevTools**: Hot reload
- **SpringDoc OpenAPI**: API documentation

### DevOps
- **Docker**: Containerization
- **Docker Compose**: Local orchestration
- **GitHub Actions**: CI/CD automation
- **Trivy**: Security vulnerability scanning

## 🚀 Getting Started

### Prerequisites

- Java 21 (JDK)
- Maven 3.9+ or use included Maven wrapper
- Docker & Docker Compose (for containerized setup)
- PostgreSQL 16 (if running without Docker)

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/AXON-ASWE/FE.git
   cd AXON_ACTIVE_PROJECT/Backend
   ```

2. **Configure database** (Skip if using Docker)
   
   Create a PostgreSQL database:
   ```sql
   CREATE DATABASE axonproject;
   ```
   
   Update `src/main/resources/application.yml` if needed.

3. **Build the project**
   ```bash
   # Using Maven wrapper (recommended)
   ./mvnw clean install
   
   # Or with installed Maven
   mvn clean install
   ```

### Running the Application

#### Option 1: Run with Maven (Development)

```bash
./mvnw spring-boot:run
```

#### Option 2: Run with Docker Compose (Recommended)

```bash
# Start all services (Backend + PostgreSQL)
docker-compose up -d

# View logs
docker-compose logs -f

# Stop services
docker-compose down
```

#### Option 3: Run JAR directly

```bash
# Build JAR
./mvnw clean package -DskipTests

# Run JAR
java -jar target/project-0.0.1-SNAPSHOT.jar
```

### Verify Installation

Once running, access:
- **Application**: http://localhost:8080
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **API Docs**: http://localhost:8080/v3/api-docs
- **Health Check**: http://localhost:8080/actuator/health

## 💻 Development

### Project Structure

```
Backend/
├── src/
│   ├── main/
│   │   ├── java/com/swe/project/
│   │   │   ├── controllers/      # REST controllers
│   │   │   ├── services/         # Business logic
│   │   │   ├── repositories/     # Data access
│   │   │   ├── models/           # Entity classes
│   │   │   ├── dto/              # Data transfer objects
│   │   │   ├── config/           # Configuration classes
│   │   │   └── ProjectApplication.java
│   │   └── resources/
│   │       ├── application.yml   # Main config
│   │       ├── secret.yml        # Secrets (not in git)
│   │       └── data.sql          # Initial data
│   └── test/
│       ├── java/                 # Test classes
│       └── resources/
│           └── application-test.yml
├── docs/                         # Documentation
├── scripts/                      # Deployment scripts
├── docker-compose.yml           # Local development
├── docker-compose.staging.yml   # Staging environment
├── Dockerfile                   # Container image
└── pom.xml                      # Maven configuration
```

### Development Workflow

1. **Create a feature branch**
   ```bash
   git checkout -b feature/your-feature-name
   ```

2. **Make changes and test**
   ```bash
   # Run tests
   ./mvnw test
   
   # Run with hot reload
   ./mvnw spring-boot:run
   ```

3. **Commit and push**
   ```bash
   git add .
   git commit -m "feat: Add your feature"
   git push origin feature/your-feature-name
   ```

4. **Create Pull Request**
   - CI/CD pipeline will automatically run tests
   - Wait for approval and merge

## 🧪 Testing

### Run All Tests

```bash
# Unit tests only
./mvnw test

# Integration tests
./mvnw verify

# With coverage
./mvnw test jacoco:report
```

### Test Structure

- **Unit Tests**: `src/test/java/.../unit/`
- **Integration Tests**: `src/test/java/.../integration/`
- **Test Configuration**: `src/test/resources/application-test.yml`

### Writing Tests

```java
@SpringBootTest
class YourServiceTest {
    
    @Autowired
    private YourService service;
    
    @Test
    void testYourMethod() {
        // Arrange
        // Act
        // Assert
    }
}
```

## 🚢 Deployment

### Docker Deployment

#### Local/Development

```bash
docker-compose up -d
```

#### Staging Environment

```bash
# Copy and configure environment
cp .env.staging.example .env.staging.local
# Edit .env.staging.local with your values

# Deploy with script (Linux/Mac)
./scripts/deploy-staging.sh

# Or with PowerShell (Windows)
.\scripts\deploy-staging.ps1
```

#### Pull from Docker Hub

```bash
# Pull multi-platform image
docker pull <your-username>/axon-backend:staging

# Run
docker run -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/axonproject \
  -e SPRING_DATASOURCE_USERNAME=postgres \
  -e SPRING_DATASOURCE_PASSWORD=password \
  <your-username>/axon-backend:staging
```

### Manual Deployment

1. Build JAR: `./mvnw clean package -DskipTests`
2. Copy JAR to server: `scp target/*.jar user@server:/app/`
3. Run on server: `java -jar /app/project-0.0.1-SNAPSHOT.jar`

## 🔄 CI/CD Pipeline

The project includes a comprehensive CI/CD pipeline with GitHub Actions.

### Pipeline Overview

```
Code Quality → Unit Tests → Integration Tests → Build → Docker Build → Deploy to Staging
```

### Pipeline Features

- ✅ **Automated Testing**: Unit and integration tests on every push
- ✅ **Multi-Platform Builds**: Linux (AMD64/ARM64)
- ✅ **Docker Hub Integration**: Automatic image publishing
- ✅ **Security Scanning**: Vulnerability detection with Trivy
- ✅ **Staging Deployment**: Automatic deployment to staging environment
- ✅ **Notifications**: Failure alerts and status updates

### Setup CI/CD

1. **Configure GitHub Secrets**
   
   See [GitHub Secrets Setup Guide](docs/GITHUB-SECRETS-SETUP.md) for detailed instructions.
   
   Required secrets:
   - `DOCKER_USERNAME`: Your Docker Hub username
   - `DOCKER_PASSWORD`: Your Docker Hub token

2. **Push to trigger pipeline**
   ```bash
   git push origin main
   ```

3. **Monitor pipeline**
   - Go to GitHub Actions tab
   - Watch the workflow execution

For detailed CI/CD documentation, see [CI/CD Guide](docs/CI-CD.md).

## 📚 API Documentation

### Swagger UI

Interactive API documentation is available at:
- **Development**: http://localhost:8080/swagger-ui.html
- **Staging**: https://staging-api.axon-backend.com/swagger-ui.html

### OpenAPI Specification

Raw OpenAPI JSON/YAML:
- http://localhost:8080/v3/api-docs
- http://localhost:8080/v3/api-docs.yaml

### Example API Endpoints

```bash
# Health check
curl http://localhost:8080/actuator/health

# Your API endpoints here
# GET /api/users
# POST /api/appointments
# etc.
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Write/update tests
5. Ensure all tests pass
6. Create a Pull Request

### Code Style

- Follow Java naming conventions
- Use Lombok annotations appropriately
- Write meaningful commit messages
- Add JavaDoc for public methods
- Keep methods small and focused

## 📝 Documentation

- [CI/CD Pipeline Guide](docs/CI-CD.md)
- [GitHub Secrets Setup](docs/GITHUB-SECRETS-SETUP.md)

## 🔒 Security

- Never commit sensitive data (passwords, tokens, keys)
- Use environment variables for configuration
- Keep dependencies up to date
- Review security scan results regularly

## 📄 License

This project is part of the Advanced Software Engineering course.

## 👥 Team

Axon Development Team - Advanced Software Engineering Project

---

**Last Updated**: November 2, 2025