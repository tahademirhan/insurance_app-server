# Insurance Marketplace Backend

This is the backend server for the Insurnace Marketplace application, developed using **Java Spring Boot**. The server handles API requests, manages data, and communicates with the database to provide the necessary functionalities for the marketplace.

## Features

- **RESTful API:** Provides endpoints for user authentication, brokerage services, and more.
- **Database Integration:** Connects to a database (PostgreSQL) to manage application data.
- **Security:** Implements security measures, including user authentication and authorization.
- **Logging:** Utilizes logging for monitoring and troubleshooting application performance.

## Tech Stack

- **Backend Framework:** Spring Boot
- **Database:** PostgreSQL
- **Security:** Spring Security
- **Build Tool:** Maven
- **Logging:** SLF4J with Logback

## Prerequisites

- Java 11
- Maven
- Database server (PostgreSQL) running

## Installation

To set up the backend server, follow these steps:

1. **Clone the repository:**

   ```bash
   git clone https://github.com/yourusername/your-repository-name.git
   cd your-repository-name
   ```

2. **Configure the Database:**
Create a database in your SQL server. Then update the `application.properties` or `application.yml` file with your database configuration.

   ```bash
   spring.datasource.url=jdbc:mysql://localhost:3306/yourdatabase
   spring.datasource.username=yourusername
   spring.datasource.password=yourpassword
   ```

3. **Install Dependencies:**
   ```bash
   mvn install
   ```
4. **Configure import.sql File:**
Change the line 15 `[YOUR_YANDEX_ACCOUNT]` with your yandex mail address and line 17 `[YOUR_APP_PASSWORD]` with your accounts app pasword.
To create an app password for your account go to `https://id.yandex.com/security/app-passwords` and select `Email Address - IMAP, POP3, SMTP`. Then go to Settings page from your email dashboard and select `Email Clients` page and enable `From the imap.yandex.com server via IMAP` and `App passwords and OAuth tokens` boxes.

5. **Import SQL Files:**
You should import the relevant files that `import.sql` and `import-lookup.sql` to complete the database configuration

## Running the Application
After the installation, you can run the application with:

  ```bash
   mvn spring-boot:run
   ```

The server will start on `http://localhost:8080` by default.

## API Documentation
The API documentation can be found at `http://localhost:8080/swagger-ui/index.html`.

## Contributing
Feel free to contribute by submitting issues or pull requests. Contributions, bug reports, and feature requests are always welcome!
