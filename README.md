# Local Sky Network - Weather Application
**Jordan Connor**  
**Email:** jordanaconnor@gmail.com  
**GitHub** - https://github.com/jordanaconnor/Weather-Application

This web application was developed as a portfolio project to demonstrate proficiency in Java web development.  
It provides live weather data through the Open-Meteo API and showcases a production-style Spring Boot setup.

---

## Highlights
- **Spring Boot + Thymeleaf** for MVC structure
- **Open-Meteo API** integration for hourly and daily weather data
- **UI/UX features** such as city search, Celsius/Fahrenheit toggle, responsive grid, and background video
- **Frontend interactivity** via JavaScript event listeners (scroll and toggle behaviors)
- **Code quality** through service separation (Controller, Service, Domain layers)

This project emphasizes API integration, backend-frontend coordination, and presenting information in a clean, interactive interface.  
It is designed to reflect real-world development practices.

---

## DevOps / CI/CD Pipeline

This project also demonstrates a full DevOps deployment workflow using modern tooling:

### GitHub Actions
- A CI/CD pipeline defined in `.github/workflows/docker-build.yml`
- On each push to `master`, the workflow:
  - Builds the Spring Boot app into a Docker image
  - Tags the image (`latest` and version tags)
  - Pushes the image to GitHub Container Registry (GHCR)

### GitHub Container Registry (GHCR)
- Stores versioned Docker images of the application
- Accessible at:  
  `ghcr.io/jordanaconnor/weather-application/spring-app:latest`

### ArgoCD
- Connected to a separate **manifests repo** containing Kubernetes YAML (`deployment.yaml`, `service.yaml`)
- ArgoCD continuously syncs the manifests to the cluster
- Ensures the running application always matches what is defined in GitHub

### K3s (Raspberry Pi Cluster)
- Lightweight Kubernetes distribution running across Raspberry Pi nodes
- Hosts the deployed application
- Exposed via **NodePort** for internal network access

---

## Architecture Summary
1. Developer pushes code → GitHub Actions builds image → GHCR stores image  
2. Manifests repo updated → ArgoCD detects changes → syncs to k3s  
3. Application runs on Raspberry Pi cluster, exposed via Kubernetes Service  

This showcases a complete **GitOps workflow**: code → container → registry → manifests → automated deployment.

