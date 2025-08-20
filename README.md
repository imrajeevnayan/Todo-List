
# Todo List Application

A simple Todo List application built with `Spring Boot` (backend) and `React` with `Tailwind CSS` (frontend). The backend provides a RESTful API for managing todos, using `Spring Data JPA` with a `PostgreSQL` database. The frontend offers a user-friendly interface to create, update, complete, and delete todos.

## Frontend Screenshot

![Todo List Frontend](img.png)
*Replace the above placeholder with the actual screenshot URL after uploading it to a public repository (e.g., `https://raw.githubusercontent.com/<username>/todo-list/main/assets/frontend-screenshot.png`) or image hosting service. To capture the screenshot, run `mvn spring-boot:run` and open `http://localhost:8080`, or use the deployed Render URL.*

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
- Create a database named `todo_db`:

```bash
createdb todo_db
```

- Update `src/main/resources/application.properties` with your local `PostgreSQL` credentials, or rely on the default values:

```properties
spring.datasource.url=${SPRING_DATASOURCE_URL:jdbc:postgresql://localhost:5432/todo_db}
spring.datasource.username=${SPRING_DATASOURCE_USERNAME:postgres}
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD:your_password}
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
server.port=${PORT:10000}
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
- **Create a PostgreSQL Database on Render**:
    - Log in to `https://dashboard.render.com`.
    - Click `New +` and select `PostgreSQL`.
    - Use the following details (already created):
        - **Hostname**: `dpg-d2j3o8ogjchc73fq03og-a`
        - **Port**: `5432`
        - **Database**: `todo_9frh`
        - **Username**: `todo_9frh_user`
        - **Password**: `Q1nA4gjtIzsBPFS6kfX5Z4omZbDW49eU`
        - **Internal Database URL**: `postgresql://todo_9frh_user:Q1nA4gjtIzsBPFS6kfX5Z4omZbDW49eU@dpg-d2j3o8ogjchc73fq03og-a/todo_9frh`
    - Ensure the database is in the `Oregon (US West)` region and shows as `Available`.
- **Push to GitHub**:
    - Ensure your project is in a `GitHub` repository:

```bash
git add .
git commit -m "Configure for Render deployment with PostgreSQL"
git push origin main
```

- **Create a Web Service on Render**:
    - In the Render Dashboard, click `New +` and select `Web Service`.
    - Choose `Build and deploy from a Git repository` and connect your `GitHub` repository.
    - Set the runtime to `Docker`.
    - Configure the service:
        - **Region**: `Oregon (US West)` (to match the database).
        - **Branch**: `main`.
        - **Environment Variables** (under `Advanced`):
            - `SPRING_DATASOURCE_URL`: `postgresql://todo_9frh_user:Q1nA4gjtIzsBPFS6kfX5Z4omZbDW49eU@dpg-d2j3o8ogjchc73fq03og-a/todo_9frh`
            - `SPRING_DATASOURCE_USERNAME`: `todo_9frh_user`
            - `SPRING_DATASOURCE_PASSWORD`: `Q1nA4gjtIzsBPFS6kfX5Z4omZbDW49eU`
            - `PORT`: `10000` (optional, as it’s set in `application.properties`).
    - Click `Create Web Service`. Render will build the `Docker` image and deploy it.
- Access the deployed app via the provided `Render.com` URL (e.g., `https://todo-list-app.onrender.com`).

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
curl https://todo-list-app.onrender.com/todos
```

- **Create a todo**:

```bash
curl -X POST "https://todo-list-app.onrender.com/todos?title=Buy%20groceries"
```

- **Update a todo**:

```bash
curl -X PUT "https://todo-list-app.onrender.com/todos/1/update?title=Buy%20vegetables"
```

- **Complete a todo**:

```bash
curl -X PUT "https://todo-list-app.onrender.com/todos/1/complete"
```

- **Delete a todo**:

```bash
curl -X DELETE "https://todo-list-app.onrender.com/todos/1"
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

- **Security**: Avoid committing sensitive data to public repositories. The `application.properties` uses environment variables for `Render.com` deployment.
- **Docker**: The `Dockerfile` builds the `Spring Boot` application and serves the `React` frontend. Ensure it’s in the root directory.
- **Render Deployment**: Use `Render.com`’s free tier for testing, but note that free instances spin down on inactivity and the free `PostgreSQL` instance expires after 90 days.
- **Extending**: Add features like user authentication or filtering todos by modifying the backend and frontend.

## Troubleshooting
- **Database Errors**: Verify the `SPRING_DATASOURCE_*` environment variables in `Render.com` match the provided `PostgreSQL` details.
- **Lombok Errors**: Ensure `Lombok` is set up in your IDE.
- **Frontend Errors**: Check the browser console for network or JavaScript errors.
- **CORS Issues**: Apply the CORS configuration above if requests are blocked.
- **Render Deployment Errors**: Check `Render.com` logs for build or runtime issues. Ensure the `Dockerfile` is correct and the `PostgreSQL` URL is valid.

## Contributing
Feel free to fork the repository, create feature branches, and submit pull requests.

## License
This project is licensed under the `MIT License`.
```