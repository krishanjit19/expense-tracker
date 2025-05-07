# Expense Tracker for Food Delivery Apps

This application helps users track their expenses across various food delivery platforms like Swiggy and Zomato using OTP-based authentication.

## Features

- OTP-based authentication for food delivery apps
- Expense tracking across multiple platforms
- Real-time expense updates
- Expense analytics and reporting
- User-friendly dashboard

## Tech Stack

### Frontend
- React.js
- Material-UI
- Axios for API calls
- Redux for state management

### Backend
- Java Spring Boot
- Spring Security
- Spring Data JPA
- MySQL Database

## Project Structure

```
expense-tracker/
├── frontend/           # React frontend application
└── backend/           # Spring Boot backend application
```

## Setup Instructions

### Frontend Setup
1. Navigate to the frontend directory:
   ```bash
   cd frontend
   ```
2. Install dependencies:
   ```bash
   npm install
   ```
3. Start the development server:
   ```bash
   npm start
   ```

### Backend Setup
1. Navigate to the backend directory:
   ```bash
   cd backend
   ```
2. Build the project:
   ```bash
   ./mvnw clean install
   ```
3. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

## Environment Variables

### Frontend
Create a `.env` file in the frontend directory with:
```
REACT_APP_API_URL=http://localhost:8080
```

### Backend
Create an `application.properties` file in the backend directory with:
```
spring.datasource.url=jdbc:mysql://localhost:3306/expense_tracker
spring.datasource.username=your_username
spring.datasource.password=your_password
```

## API Documentation

The API documentation will be available at `http://localhost:8080/swagger-ui.html` when the backend is running.

## Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a new Pull Request 