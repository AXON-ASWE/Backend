# Deploy to Staging Script (PowerShell)
# This script deploys the backend application to the staging environment on Windows

param(
    [string]$DockerUsername = $env:DOCKER_USERNAME,
    [string]$ImageTag = "staging"
)

$ErrorActionPreference = "Stop"

Write-Host "🚀 Starting Staging Deployment..." -ForegroundColor Cyan
Write-Host "=================================" -ForegroundColor Cyan

# Configuration
$ComposeFile = "docker-compose.staging.yml"
$EnvFile = ".env.staging.local"

# Check if .env.staging.local exists
if (-not (Test-Path $EnvFile)) {
    Write-Host "❌ Error: $EnvFile not found!" -ForegroundColor Red
    Write-Host "Please copy .env.staging.example to $EnvFile and configure it." -ForegroundColor Yellow
    exit 1
}

# Load environment variables from .env file
Get-Content $EnvFile | ForEach-Object {
    if ($_ -match '^([^#][^=]+)=(.*)$') {
        [Environment]::SetEnvironmentVariable($matches[1], $matches[2], "Process")
    }
}

if (-not $DockerUsername) {
    $DockerUsername = $env:DOCKER_USERNAME
}

Write-Host ""
Write-Host "📋 Configuration:" -ForegroundColor Yellow
Write-Host "  - Docker Image: $DockerUsername/axon-backend:$ImageTag"
Write-Host "  - Compose File: $ComposeFile"
Write-Host "  - Environment: staging"
Write-Host ""

# Pull the latest image
Write-Host "📥 Pulling latest Docker image..." -ForegroundColor Yellow
docker pull "$DockerUsername/axon-backend:$ImageTag"

if ($LASTEXITCODE -ne 0) {
    Write-Host "❌ Failed to pull Docker image" -ForegroundColor Red
    exit 1
}

# Stop existing containers
Write-Host "🛑 Stopping existing containers..." -ForegroundColor Yellow
docker-compose -f $ComposeFile --env-file $EnvFile down

# Start new containers
Write-Host "🚀 Starting new containers..." -ForegroundColor Yellow
docker-compose -f $ComposeFile --env-file $EnvFile up -d

if ($LASTEXITCODE -ne 0) {
    Write-Host "❌ Failed to start containers" -ForegroundColor Red
    exit 1
}

# Wait for services to be healthy
Write-Host "⏳ Waiting for services to be healthy..." -ForegroundColor Yellow
Start-Sleep -Seconds 10

# Check backend health
$MaxRetries = 30
$RetryCount = 0
$BackendPort = if ($env:BACKEND_PORT) { $env:BACKEND_PORT } else { "8080" }
$BackendUrl = "http://localhost:$BackendPort/actuator/health"

Write-Host "🏥 Checking backend health at $BackendUrl..." -ForegroundColor Yellow

while ($RetryCount -lt $MaxRetries) {
    try {
        $response = Invoke-WebRequest -Uri $BackendUrl -UseBasicParsing -TimeoutSec 5
        if ($response.StatusCode -eq 200) {
            Write-Host "✅ Backend is healthy!" -ForegroundColor Green
            break
        }
    } catch {
        # Ignore error and retry
    }
    
    $RetryCount++
    Write-Host "   Attempt $RetryCount/$MaxRetries - waiting..." -ForegroundColor Gray
    Start-Sleep -Seconds 2
}

if ($RetryCount -eq $MaxRetries) {
    Write-Host "❌ Backend health check failed after $MaxRetries attempts" -ForegroundColor Red
    Write-Host "📋 Container logs:" -ForegroundColor Yellow
    docker-compose -f $ComposeFile --env-file $EnvFile logs backend
    exit 1
}

# Run smoke tests
Write-Host ""
Write-Host "🧪 Running smoke tests..." -ForegroundColor Yellow

# Test 1: Health endpoint
Write-Host "  → Testing health endpoint..." -ForegroundColor Gray
try {
    $healthResponse = Invoke-RestMethod -Uri $BackendUrl -UseBasicParsing
    if ($healthResponse.status -eq "UP") {
        Write-Host "    ✅ Health check passed" -ForegroundColor Green
    } else {
        Write-Host "    ❌ Health check failed" -ForegroundColor Red
        exit 1
    }
} catch {
    Write-Host "    ❌ Health check failed: $_" -ForegroundColor Red
    exit 1
}

# Test 2: Swagger UI
$SwaggerUrl = "http://localhost:$BackendPort/swagger-ui.html"
Write-Host "  → Testing Swagger UI at $SwaggerUrl..." -ForegroundColor Gray
try {
    $swaggerResponse = Invoke-WebRequest -Uri $SwaggerUrl -UseBasicParsing
    if ($swaggerResponse.StatusCode -eq 200) {
        Write-Host "    ✅ Swagger UI accessible" -ForegroundColor Green
    }
} catch {
    Write-Host "    ⚠️  Swagger UI not accessible (non-critical)" -ForegroundColor Yellow
}

# Test 3: API docs
$ApiDocsUrl = "http://localhost:$BackendPort/v3/api-docs"
Write-Host "  → Testing API docs at $ApiDocsUrl..." -ForegroundColor Gray
try {
    $apiDocsResponse = Invoke-WebRequest -Uri $ApiDocsUrl -UseBasicParsing
    if ($apiDocsResponse.StatusCode -eq 200) {
        Write-Host "    ✅ API docs accessible" -ForegroundColor Green
    }
} catch {
    Write-Host "    ⚠️  API docs not accessible (non-critical)" -ForegroundColor Yellow
}

# Show running containers
Write-Host ""
Write-Host "📦 Running containers:" -ForegroundColor Yellow
docker-compose -f $ComposeFile --env-file $EnvFile ps

Write-Host ""
Write-Host "=================================" -ForegroundColor Cyan
Write-Host "✅ Staging Deployment Successful!" -ForegroundColor Green
Write-Host "=================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "📝 Summary:" -ForegroundColor Yellow
Write-Host "  - Backend URL: http://localhost:$BackendPort"
Write-Host "  - Swagger UI: http://localhost:$BackendPort/swagger-ui.html"
Write-Host "  - API Docs: http://localhost:$BackendPort/v3/api-docs"
$PostgresPort = if ($env:POSTGRES_PORT) { $env:POSTGRES_PORT } else { "5432" }
Write-Host "  - Database: localhost:$PostgresPort"
Write-Host ""
Write-Host "📋 Useful commands:" -ForegroundColor Yellow
Write-Host "  - View logs:        docker-compose -f $ComposeFile logs -f"
Write-Host "  - Stop services:    docker-compose -f $ComposeFile down"
Write-Host "  - Restart services: docker-compose -f $ComposeFile restart"
Write-Host ""
