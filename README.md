# 🧩 Microservices Architecture Hub

Welcome to the **Microservices Architecture** hub. This module teaches you how to design, decouple, build, and orchestrate production-grade distributed microservices using **Java, Spring Boot, and Apache Kafka**.

---

## 🗺️ Curriculum & Roadmap

```text
microservices/
├── 01_architecture_patterns/   <- Database-per-service, Strangler Fig, API Gateway
├── 02_inter_service_comm/      <- REST vs gRPC vs WebSockets
├── 03_kafka_event_driven/      <- Topics, Partitions, Consumer Groups, Exactly-Once
├── 04_resilience4j/            <- Circuit Breaker, Retries, Rate Limiting, Bulkhead
├── 05_distributed_transactions/<- Saga Pattern (Choreography vs Orchestration), Outbox Pattern
└── 06_observability/           <- Distributed Tracing (OpenTelemetry), Centralized Logging
```

---

## 📌 Core Architecture Pillars

### 1. Decomposition & Design Patterns
* **Database-per-Service**: Why shared databases kill microservices. Managing polyglot persistence.
* **API Gateway Pattern**: Spring Cloud Gateway as the single entry point (Authentication, SSL termination, routing).
* **Service Discovery**: Netflix Eureka / Consul for dynamic IP and port resolution.
* **Strangler Fig Pattern**: Incrementally migrating a monolithic legacy codebase to microservices without downtime.

### 2. Communication Protocols
* **Synchronous (REST & gRPC)**:
  * REST: Easy to debug, JSON over HTTP/1.1.
  * gRPC: High-throughput, binary serialization with Protocol Buffers over HTTP/2 (streaming, low latency).
* **Asynchronous (Event-Driven Messaging)**:
  * Decoupled producers and consumers via message brokers.

### 3. Apache Kafka Mastery
* **Core Internals**: Topics, Partitions, Brokers, Producer ACK levels (`acks=0, 1, all`).
* **Consumer Mechanics**: Consumer Groups, Rebalancing, Offset management (`enable.auto.commit` vs manual commit).
* **Delivery Semantics**: At-least-once, At-most-once, and **Exactly-Once Processing (EOP)** with idempotent producers and transactional APIs.

### 4. Resilience & Fault Tolerance (Resilience4j)
* **Circuit Breaker Pattern**: States (`CLOSED`, `OPEN`, `HALF_OPEN`), failure rate thresholds, preventing cascading failures.
* **Retry Pattern**: Exponential backoff and jitter to survive transient network blips.
* **Bulkhead Pattern**: Isolating thread pools so one slow downstream service doesn't starve the entire server.

### 5. Distributed Transactions: The Saga Pattern
* **Why 2-Phase Commit (2PC) Fails**: High latency, blocking locks, single point of failure.
* **The Saga Pattern**:
  * **Choreography-based Saga**: Services publish events; next service listens and acts (good for simple workflows).
  * **Orchestration-based Saga**: A central coordinator tells participants what local transactions to run (good for complex workflows).
  * **Compensating Transactions**: Undoing state changes when a downstream step fails.
* **The Transactional Outbox Pattern**: Guaranteeing database save + event publishing without two-phase commit.
