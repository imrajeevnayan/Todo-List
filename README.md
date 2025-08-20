```markdown
# Todo List Application

A simple Todo List application built with `Spring Boot` (backend) and `React` with `Tailwind CSS` (frontend). The backend provides a RESTful API for managing todos, using `Spring Data JPA` with a `PostgreSQL` database. The frontend offers a user-friendly interface to create, update, complete, and delete todos.

## Features
- Create, read, update, and delete (CRUD) todos.
- Mark todos as completed.
- Responsive frontend with `Tailwind CSS` styling.
- Backend API with `Spring Boot` and `PostgreSQL`.
- Version control initialized with `Git`.

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
├── README.md
```

## Prerequisites
- `Java 17` or later
- `Maven` (for dependency management)
- `PostgreSQL` (database)
- `Git` (for version control)
- `Node.js` (optional, only if extending the frontend separately)
- IDE (e.g., `IntelliJ IDEA`, `Eclipse`) with `Lombok` support

## Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/imrajeevnayan/Todo-List.git
cd todo-list
```

### 2. Configure PostgreSQL
- Install `PostgreSQL` (e.g., version 15 or later).
- Create a database named `todo_db`:

```bash
createdb todo_db
```

- Update `src/main/resources/application.properties` with your `PostgreSQL` credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/todo_db
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

### 3. Configure Lombok
- Ensure your IDE has the `Lombok` plugin installed and annotation processing enabled.
- For `IntelliJ IDEA`:
    - Go to `Settings > Plugins`, install `Lombok`.
    - Enable annotation processing: `Settings > Build, Execution, Deployment > Compiler > Annotation Processors > Enable annotation processing`.

### 4. Build and Run
- Build the project:

```bash
mvn clean install
```

- Run the `Spring Boot` application:

```bash
mvn spring-boot:run
```

- The application will start at `http://localhost:8080`.

### 5. Access the Application
- Open `http://localhost:8080` in a browser to use the `React` frontend.
- Alternatively, test the API using tools like `Postman` or `curl`.

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
curl http://localhost:8080/todos
```

- **Create a todo**:

```bash
curl -X POST "http://localhost:8080/todos?title=Buy%20groceries"
```

- **Update a todo**:

```bash
curl -X PUT "http://localhost:8080/todos/1/update?title=Buy%20vegetables"
```

- **Complete a todo**:

```bash
curl -X PUT "http://localhost:8080/todos/1/complete"
```

- **Delete a todo**:

```bash
curl -X DELETE "http://localhost:8080/todos/1"
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

- **Security**: Avoid committing `application.properties` with sensitive data (e.g., database passwords) to public repositories. Use environment variables in production.
- **Extending**: Add features like user authentication or filtering todos by modifying the backend and frontend.

## Troubleshooting
- **Database Errors**: Ensure `PostgreSQL` is running and credentials are correct in `application.properties`.
- **Lombok Errors**: Verify `Lombok` is set up in your IDE.
- **Frontend Errors**: Check the browser console for network or JavaScript errors.
- **CORS Issues**: Apply the CORS configuration above if requests are blocked.

## Contributing
Feel free to fork the repository, create feature branches, and submit pull requests.

## License
This project is licensed under the `MIT License`.
```