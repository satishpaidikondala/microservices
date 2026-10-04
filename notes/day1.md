# 🚀 Session 1 Notes: Order Service Domain & Data Isolation

## 🎯 What We Accomplished
- **Domain Modeling**: Created `Order`, `OrderItem`, and `OrderStatus` (enum) entities.
- **Repository Pattern**: Implemented `OrderRepository` with a custom `findByOrderNumber(UUID)` method.
- **Database Migrations**: Set up `V1__init.sql` using Flyway for PostgreSQL.

## 🧠 Key Interview & Architecture Takeaways

### 1. The Golden Rule of Microservices (Data Isolation)
* **Rule**: Services never share databases and never have foreign keys pointing to tables owned by other services.
* **Implementation**: Inside `OrderItem.java`, we strictly used `String productId` instead of a `@ManyToOne` relationship to a `Product` entity. If we need product details later, we will fetch them over the network (REST/Kafka), not via a database SQL JOIN.

### 2. Internal (ID) vs External (OrderNumber) Keys
* **Internal (`Long id`)**: Auto-incrementing (`BIGSERIAL`), very fast for database joins. We keep this completely hidden for security.
* **External (`UUID orderNumber`)**: Exposed to the frontend. Unpredictable and globally unique. Prevents IDOR security vulnerabilities and hides our daily sales volume from competitors.

### 3. Flyway vs Hibernate DDL
* **Hibernate (`ddl-auto=update`)**: Good for prototyping, dangerous for production. It guesses schema changes and can easily drop critical data.
* **Flyway (`V1__init.sql`)**: Treats database schema like Git. Safe, strictly version-controlled, and guarantees all developers and servers have the exact same database structure.

### 4. PostgreSQL vs MySQL Syntax
* **Primary Keys**: Postgres uses `BIGSERIAL`; MySQL uses `AUTO_INCREMENT`.
* **Decimals**: We use `DECIMAL(10,2)` or `NUMERIC(10,2)` in SQL to perfectly map to Java's `BigDecimal`.
* **UUIDs**: Postgres has a highly-optimized native `UUID` type. MySQL requires using a `VARCHAR(36)`.
