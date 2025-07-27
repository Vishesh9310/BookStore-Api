# 📚 Bookstore REST API

A Spring Boot RESTful API for managing books and authors.  
Supports CRUD operations, filtering, pagination, and sorting.

---

## 🚀 Tech Stack

- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**
- **H2 Database**
- **Swagger (Springdoc OpenAPI)**
- **Lombok**
- **Postman** (for testing)

---

## 📁 Project Structure

```

src/
├──config/            → for swagger configuration
├── controller/       → REST API controllers
├── entity/           → JPA entities (Book, Author)
├── repository/       → Spring Data JPA interfaces
├── service/          → Business logic layer
├── public/           → for public api
└── BookstoreApiApplication.java

```

---

## 🛠️ Features

- ✅ Create, Read, Update, Delete (CRUD) for **Books** and **Authors**
- 🔍 Filtering (e.g. search books by title )
- 📄 Pagination & Sorting on GET endpoints
- 🔐 Error handling with proper HTTP codes
- 📘 Swagger UI for API testing and docs

---

## 🔗 API Endpoints (Sample)

### 📚 Books
| Method | Endpoint                   | Description                                       |
|--------|----------------------------|---------------------------------------------------|
| `GET`  | `/books`                   | List all books (with pagination, sorting, filtering) |
| `POST` | `/books/{authorName}`      | Create new book                                   |
| `GET`  | `/books/{authorName}`      | Get book by authorname                            |
| `PUT`  | `/books/title/{bookName}`  | Update book                                       |
| `DELETE` | `/books/{title}`           | Delete book by title                              |

### ✍️ Authors
| Method | Endpoint                | Description   |
|--------|-------------------------|---------------|
| `GET`  | `/authors/{authorName}` | get authors   |
| `POST` | `/authors`              | Create author |
| `PUT`  | `/authors/{authorName}` | Update author |
| `DELETE` | `/authors/{authorName}` | Delete author |

---

## 🧪 Test the API

### 👉 Using Swagger UI
After running the app, open:
```
http://localhost:8080/swagger-ui/index.html#/

````

### 👉 Using Postman
Example `POST /books`:
```json
{
  "title": "the book",
  "content": "hello",
  "price": 83942
}
````

---

## 🔍 Filtering, Pagination & Sorting

You can pass these optional query params to `/books`:

| Param   | Description     |
| ------- | --------------- |
| `title` | Filter by title (contains) |
| `page`  | Page number (default `0`) |
| `size`  | Page size (default `10`) |
| `sort`  | Sorting field (e.g. `title,asc`) |

**Example:**

```
GET /books?title=code&page=0&size=5&sort=title,desc
```

---

## 🧰 How to Run

1. Clone the repo:

```bash
git clone https://github.com/Vishesh9310/BookStore-Api.git
cd BookStore-Api.git
```

2. Open in IntelliJ or VS Code
3. Run `BookstoreApiApplication.java`
4. Visit `http://localhost:8080/swagger-ui/index.html` to explore

---

## 🧑‍💻 Author

**Vishesh**
📧 [vk866797@gmail.com](mailto:vk866797@gmail.com)
🌐 [GitHub](https://github.com/Vishesh9310)