# TraceIQ

**AI-Powered Root Cause Analysis for Spring Boot Applications**

TraceIQ is a modular Java library designed to help developers investigate application failures by collecting diagnostic evidence and generating AI-assisted root-cause explanations and suggested fixes.

## Planned Features

- Automatic exception and diagnostic evidence collection
- Incident history and persistence
- AI-assisted root-cause analysis
- Pluggable AI providers
- REST APIs for incident inspection
- React-based diagnostic dashboard
- Optional Docker-based deployment

## Project Structure

| Module | Purpose |
|---|---|
| `traceiq-core` | Core incident and evidence models |
| `traceiq-ai` | AI provider integration |
| `traceiq-storage` | Incident persistence |
| `traceiq-spring-boot-autoconfigure` | Spring Boot integration |
| `traceiq-spring-boot-starter` | Single-dependency integration |
| `traceiq-demo` | Sample application |
| `traceiq-dashboard` | React dashboard source |

## Technology Stack

- Java 17
- Maven
- Spring Boot
- React
- H2 database (planned default storage)
- Ollama or another supported AI provider
- Docker (planned)

## Development Status

The initial Maven multi-module project structure has been created and validated. Feature implementation and integration testing are the next steps.