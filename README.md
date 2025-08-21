
# Todo List Application

A simple Todo List application built with `Spring Boot` (backend) and `React` with `Tailwind CSS` (frontend). The backend provides a RESTful API for managing todos, using `Spring Data JPA` with a `PostgreSQL` database. The frontend offers a user-friendly interface to create, update, complete, and delete todos.

## Live Demo

The application is deployed and accessible at: [https://todo-list-fo0g.onrender.com/](https://todo-list-fo0g.onrender.com/)

## Frontend Screenshot

![Todo List Frontend](img.png)
*Replace the above placeholder with the actual screenshot URL after uploading it to a public repository (e.g., `https://raw.githubusercontent.com/<username>/todo-list/main/assets/frontend-screenshot.png`) or image hosting service. To capture the screenshot, open `https://todo-list-fo0g.onrender.com/` or run `mvn spring-boot:run` and open `http://localhost:8080`.*

## Features
- Create, read, update, and delete (CRUD) todos.
- Mark todos as completed.
- Responsive frontend with `Tailwind CSS` styling.
- Backend API with `Spring Boot` and `PostgreSQL`.
- Version control initialized with `Git`.
- Containerized with `Docker` for deployment on `Render.com`.

## Project Structure

```plaintext
todo-list/
├── src/
│   ├── main/
│   │   ├── java/com/example/todo/
│   │   │   ├── entity/Todo.java
│   │   │   ├── repository/TodoRepository.java
│   │   │   ├── service/TodoService.java
│   │   │   ├── controller/TodoController.java
│   │   │   ├── TodoListApplication.java
│   │   ├── resources/
│   │       ├── static/index.html
│   │       ├── application.properties
├── pom.xml
├── .gitignore
├── Dockerfile
├── README.md
```

## Prerequisites
- `Java 17` or later
- `Maven` (for dependency management)
- `PostgreSQL` (database)
- `Docker` (for containerization)
- `Git` (for version control)
- `Node.js` (optional, only if extending the frontend separately)
- IDE (e.g., `IntelliJ IDEA`, `Eclipse`) with `Lombok` support
- `Render.com` account (for deployment)

## Setup Instructions

### 1. Clone the Repository

```bash
git clone <repository-url>
cd todo-list
```

### 2. Configure PostgreSQL Locally
- Install `PostgreSQL` (e.g., version 15 or later).
- Create a database named `todo_9frh`:

```bash
createdb todo_9frh
```

- Update `src/main/resources/application.properties` with your local `PostgreSQL` credentials, or rely on the default values:

```properties
spring.datasource.url=${SPRING_DATASOURCE_URL:jdbc:postgresql://localhost:5432/todo_9frh}
spring.datasource.username=${SPRING_DATASOURCE_USERNAME:postgres}
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD:your_password}
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
server.port=${PORT:10000}
spring.datasource.hikari.connection-timeout=20000
spring.datasource.hikari.maximum-pool-size=5
```

### 3. Configure Lombok
- Ensure your IDE has the `Lombok` plugin installed and annotation processing enabled.
- For `IntelliJ IDEA`:
    - Go to `Settings > Plugins`, install `Lombok`.
    - Enable annotation processing: `Settings > Build, Execution, Deployment > Compiler > Annotation Processors > Enable annotation processing`.

### 4. Build and Run Locally
- Build the project:

```bash
mvn clean install
```

- Run the `Spring Boot` application:

```bash
mvn spring-boot:run
```

- The application will start at `http://localhost:8080`.

### 5. Access the Application Locally
- Open `http://localhost:8080` in a browser to use the `React` frontend.
- Alternatively, test the API using tools like `Postman` or `curl`.

### 6. Deploy to Render.com
- **Verify PostgreSQL Database on Render**:
    - Log in to `https://dashboard.render.com`.
    - Your `PostgreSQL` database is already created with the following details:
        - **Hostname**: `dpg-d2j3o8ogjchc73fq03og-a`
        - **Port**: `5432`
        - **Database**: `todo_9frh`
        - **Username**: `todo_9frh_user`
        - **Password**: `[REDACTED]` (sensitive, set via environment variables)
        - **Internal Database URL**: `postgresql://todo_9frh_user:[REDACTED]@dpg-d2j3o8ogjchc73fq03og-a/todo_9frh`
    - Ensure the database is in the `Oregon (US West)` region and shows as `Available`.
- **Push to GitHub**:
    - Ensure your project is in a `GitHub` repository:

```bash
git add .
git commit -m "Add live demo link to README"
git push origin main
```

- **Create or Update Web Service on Render**:
    - In the Render Dashboard, click `New +` and select `Web Service`, or update the existing service.
    - Choose `Build and deploy from a Git repository` and connect your `GitHub` repository.
    - Set the runtime to `Docker`.
    - Configure the service:
        - **Region**: `Oregon (US West)` (to match the database).
        - **Branch**: `main`.
        - **Environment Variables** (under `Advanced`):
            - `SPRING_DATASOURCE_URL`: `jdbc:postgresql://dpg-d2j3o8ogjchc73fq03og-a:5432/todo_9frh?user=todo_9frh_user&password=[REDACTED]` (replace `[REDACTED]` with the actual password from your Render dashboard).
            - `SPRING_DATASOURCE_USERNAME`: `todo_9frh_user`
            - `SPRING_DATASOURCE_PASSWORD`: `[REDACTED]` (replace with the actual password).
            - `PORT`: `10000` (optional, as set in `application.properties`).
    - Click `Create Web Service` or save changes. The app is live at `https://todo-list-fo0g.onrender.com/`.

## API Endpoints

| Method | Endpoint                   | Description                     | Parameters                       |
|--------|----------------------------|---------------------------------|----------------------------------|
| GET    | `/todos`                   | Retrieve all todos              | None                             |
| POST   | `/todos`                   | Create a new todo               | `title` (query param)            |
| PUT    | `/todos/{id}/update`       | Update a todo’s title           | `id` (path), `title` (query param) |
| PUT    | `/todos/{id}/complete`     | Mark a todo as completed        | `id` (path)                      |
| DELETE | `/todos/{id}`              | Delete a todo                   | `id` (path)                      |

### Example API Requests
- **Get all todos**:

```bash
curl https://todo-list-fo0g.onrender.com/todos
```

- **Create a todo**:

```bash
curl -X POST "https://todo-list-fo0g.onrender.com/todos?title=Buy%20groceries"
```

- **Update a todo**:

```bash
curl -X PUT "https://todo-list-fo0g.onrender.com/todos/1/update?title=Buy%20vegetables"
```

- **Complete a todo**:

```bash
curl -X PUT "https://todo-list-fo0g.onrender.com/todos/1/complete"
```

- **Delete a todo**:

```bash
curl -X DELETE "https://todo-list-fo0g.onrender.com/todos/1"
```

## Frontend Usage
- **Add Todo**: Enter a title in the input field and click `Add`.
- **Edit Todo**: Click `Edit`, modify the title, and click `Save`.
- **Complete Todo**: Click `Complete` to mark a todo as done.
- **Delete Todo**: Click `Delete` to remove a todo.

## Dependencies
- **Backend**:
    - `spring-boot-starter-web`
    - `spring-boot-starter-data-jpa`
    - `postgresql`
    - `spring-boot-devtools`
    - `lombok`
- **Frontend** (via CDN in `index.html`):
    - `react@18`
    - `react-dom@18`
    - `axios@1.7.7`
    - `tailwindcss`
    - `@babel/standalone@7.25.6`

## Notes
- **CORS**: If the frontend cannot connect to the backend, add the following CORS configuration to `TodoListApplication.java`:

```java
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Bean
public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE");
        }
    };
}
```

- **Security**: Avoid committing sensitive data to public repositories. The `application.properties` uses environment variables for `Render.com` deployment to securely handle database credentials. Do not hardcode the `PostgreSQL` password in `application.properties`.
- **Docker**: The `Dockerfile` uses `maven:3.9.8-eclipse-temurin-17` for building and `eclipse-temurin:17-jre-alpine` for running the application. Ensure it’s in the root directory.
- **Render Deployment**: Use `Render.com`’s free tier for testing, but note that free instances spin down on inactivity and the free `PostgreSQL` instance expires after 90 days.
- **Extending**: Add features like user authentication or filtering todos by modifying the backend and frontend.

## Troubleshooting
- **JDBC URL Error**: If you see `BeanCreationException: 'url' must start with "jdbc"`, ensure `SPRING_DATASOURCE_URL` in Render starts with `jdbc:postgresql://` (e.g., `jdbc:postgresql://dpg-d2j3o8ogjchc73fq03og-a:5432/todo_9frh?user=todo_9frh_user&password=[REDACTED]`). Verify in the Render Dashboard under `Environment`. Delete any duplicate or incorrect `SPRING_DATASOURCE_URL` entries (e.g., `postgresql://...`). Clear the build cache in Render (`Advanced` > `Clear build cache & deploy`) if the error persists.
- **JAR File Not Found**: If you see `ERROR: failed to calculate checksum ... "/app/target/todo-0.0.1-SNAPSHOT.jar": not found`, ensure the `pom.xml` `<artifactId>` and `<version>` match the expected JAR name (e.g., `todo-0.0.1-SNAPSHOT.jar`). Run `mvn clean package -DskipTests` locally and check `target/` for the JAR. The `Dockerfile` uses `COPY --from=build /app/target/*.jar app.jar` to handle varying JAR names.
- **Docker Image Not Found**: If you encounter `ERROR: docker.io/library/maven:3.8.6-openjdk-17: not found`, ensure the `Dockerfile` uses `maven:3.9.8-eclipse-temurin-17`. Verify the image exists on Docker Hub: [https://hub.docker.com/_/maven](https://hub.docker.com/_/maven).
- **Database Errors**: Verify the `SPRING_DATASOURCE_*` environment variables in `Render.com` match the provided `PostgreSQL` details. Check Render logs for errors like `PSQLException: Connection refused`.
- **Java Version Mismatch**: Ensure your `pom.xml` specifies `<java.version>17</java.version>` to match the Docker images.
- **Lombok Errors**: Ensure `Lombok` is set up in your IDE.
- **Frontend Errors**: Check the browser console for network or JavaScript errors.
- **CORS Issues**: Apply the CORS configuration above if requests are blocked.
- **Render Deployment Errors**: Check `Render.com` logs for build or runtime issues. Ensure the `Dockerfile` is correct and the `PostgreSQL` credentials are valid.

## Contributing
Feel free to fork the repository, create feature branches, and submit pull requests.

## License
This project is licensed under the `MIT License`.
