# 📽️ Movie Booking Platform

A **Spring Boot** application that supports both **B2B (Theatre partners)** and **B2C (End customers)** for online movie ticket booking.  
It provides functionality for **browsing shows, booking tickets, bulk operations, payments**, and includes **logging, optimistic locking, idempotency, security, and metric monitoring**.

# Project is on a new branch created as master branch
---

## Table of Contents

- [Features](#features)
- [Architecture](#architecture)
- [Project Structure](#project-structure)
- [Technologies](#technologies)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Design Patterns](#design-patterns)
- [Logging, Metrics & Monitoring](#logging-metrics--monitoring)
- [Future Improvements](#future-improvements)

---

## Features

### B2C (End Customer)
- Browse theatres running a selected movie in a city by date.
- View show timings and seat availability.
- Book movie tickets (single or bulk).
- Cancel bookings (single or bulk).
- Pay via **Credit Card, UPI, or Wallet**.

### B2B (Theatre Partner)
- Create, update, and delete shows for the theatre.
- Manage seat inventory.
- Perform bulk booking and cancellations.

### General Features
- **Optimistic locking** to handle concurrent bookings.
- **Retry mechanism** for transient failures.
- **Idempotency checks** to prevent duplicate bookings or payments.
- **Role-based authentication & authorization**: Guest, Logged-in user, Partner.
- **Strategy pattern** for payment methods.
- **State pattern** for payment status.

---

## Architecture

The application follows a **layered architecture**:

1. **Controller Layer**: Handles HTTP requests (B2C/B2B separation).
2. **Service Layer**: Implements business logic (booking, payment, browsing).
3. **Repository Layer**: Handles database operations.
4. **DTO Layer**: Encapsulates request and response objects.
5. **Strategy Layer**: Implements different payment methods.
6. **State Layer**: Handles payment status changes.
7. **Config Layer**: Security, MDC, and AOP configurations.
8. **Metrics Layer**: Tracks booking/payment metrics for monitoring.

---


## TODO
- **AOP + MDC logging** for structured payment and booking logs.
- **Metrics collection** using **Micrometer**, integrable with **Prometheus** or any external monitoring system.
- **How do you monetize the  platform**

