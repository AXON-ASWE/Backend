#!/bin/bash

# Deploy to Staging Script
# This script deploys the backend application to the staging environment

set -e  # Exit on error

echo "🚀 Starting Staging Deployment..."
echo "================================="

# Configuration
DOCKER_USERNAME="${DOCKER_USERNAME:-your_dockerhub_username}"
IMAGE_TAG="${IMAGE_TAG:-staging}"
COMPOSE_FILE="docker-compose.staging.yml"
ENV_FILE=".env.staging.local"

# Check if .env.staging.local exists
if [ ! -f "$ENV_FILE" ]; then
    echo "❌ Error: $ENV_FILE not found!"
    echo "Please copy .env.staging.example to $ENV_FILE and configure it."
    exit 1
fi

# Load environment variables
source "$ENV_FILE"

echo "📋 Configuration:"
echo "  - Docker Image: $DOCKER_USERNAME/axon-backend:$IMAGE_TAG"
echo "  - Compose File: $COMPOSE_FILE"
echo "  - Environment: staging"
echo ""

# Pull the latest image
echo "📥 Pulling latest Docker image..."
docker pull "$DOCKER_USERNAME/axon-backend:$IMAGE_TAG"

# Stop existing containers
echo "🛑 Stopping existing containers..."
docker-compose -f "$COMPOSE_FILE" --env-file "$ENV_FILE" down

# Start new containers
echo "🚀 Starting new containers..."
docker-compose -f "$COMPOSE_FILE" --env-file "$ENV_FILE" up -d

# Wait for services to be healthy
echo "⏳ Waiting for services to be healthy..."
sleep 10

# Check backend health
MAX_RETRIES=30
RETRY_COUNT=0
BACKEND_URL="http://localhost:${BACKEND_PORT:-8080}/actuator/health"

echo "🏥 Checking backend health at $BACKEND_URL..."
while [ $RETRY_COUNT -lt $MAX_RETRIES ]; do
    if curl -f -s "$BACKEND_URL" > /dev/null 2>&1; then
        echo "✅ Backend is healthy!"
        break
    fi
    
    RETRY_COUNT=$((RETRY_COUNT + 1))
    echo "   Attempt $RETRY_COUNT/$MAX_RETRIES - waiting..."
    sleep 2
done

if [ $RETRY_COUNT -eq $MAX_RETRIES ]; then
    echo "❌ Backend health check failed after $MAX_RETRIES attempts"
    echo "📋 Container logs:"
    docker-compose -f "$COMPOSE_FILE" --env-file "$ENV_FILE" logs backend
    exit 1
fi

# Run smoke tests
echo ""
echo "🧪 Running smoke tests..."

# Test 1: Health endpoint
echo "  → Testing health endpoint..."
if curl -f -s "$BACKEND_URL" | grep -q "UP"; then
    echo "    ✅ Health check passed"
else
    echo "    ❌ Health check failed"
    exit 1
fi

# Test 2: Swagger UI
SWAGGER_URL="http://localhost:${BACKEND_PORT:-8080}/swagger-ui.html"
echo "  → Testing Swagger UI at $SWAGGER_URL..."
if curl -f -s "$SWAGGER_URL" > /dev/null 2>&1; then
    echo "    ✅ Swagger UI accessible"
else
    echo "    ⚠️  Swagger UI not accessible (non-critical)"
fi

# Test 3: API docs
API_DOCS_URL="http://localhost:${BACKEND_PORT:-8080}/v3/api-docs"
echo "  → Testing API docs at $API_DOCS_URL..."
if curl -f -s "$API_DOCS_URL" > /dev/null 2>&1; then
    echo "    ✅ API docs accessible"
else
    echo "    ⚠️  API docs not accessible (non-critical)"
fi

# Show running containers
echo ""
echo "📦 Running containers:"
docker-compose -f "$COMPOSE_FILE" --env-file "$ENV_FILE" ps

echo ""
echo "================================="
echo "✅ Staging Deployment Successful!"
echo "================================="
echo ""
echo "📝 Summary:"
echo "  - Backend URL: http://localhost:${BACKEND_PORT:-8080}"
echo "  - Swagger UI: http://localhost:${BACKEND_PORT:-8080}/swagger-ui.html"
echo "  - API Docs: http://localhost:${BACKEND_PORT:-8080}/v3/api-docs"
echo "  - Database: localhost:${POSTGRES_PORT:-5432}"
echo ""
echo "📋 Useful commands:"
echo "  - View logs:        docker-compose -f $COMPOSE_FILE logs -f"
echo "  - Stop services:    docker-compose -f $COMPOSE_FILE down"
echo "  - Restart services: docker-compose -f $COMPOSE_FILE restart"
echo "  - Scale backend:    docker-compose -f $COMPOSE_FILE up -d --scale backend=3"
echo ""
