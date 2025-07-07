# 🧠 Document Explainer

An AI-powered service that allows users to upload documents and receive smart summaries and answers using **Spring Boot 3**, **Spring AI (OpenAI)**, **MySQL**, and **Docker**. Includes a complete CI/CD pipeline using **GitHub Actions**.

---

## 🚀 Features

- 📄 Upload documents (PDF, DOCX)
- 🤖 Generate summaries using OpenAI (via Spring AI)
- ❓ Ask questions about the uploaded document
- 🧠 Embedding and retrieval-based response generation (RAG)
- 🐳 Dockerized setup with `docker-compose`
- 🔄 CI/CD pipeline using GitHub Actions

---

## 🧠 AI Integration with Spring AI + OpenAI

This project uses [Spring AI](https://docs.spring.io/spring-ai/reference/) with the **OpenAI** backend to extract and understand documents.

### How it works:
1. Document is parsed into text chunks.
2. Chunks are converted to embeddings using OpenAI's `/v1/embeddings`.
3. Vector search retrieves the most relevant chunks.
4. Final answers/summaries are generated using `gpt-3.5-turbo` or `gpt-4`.

### Configuration (`application.yml`)
```yaml
spring:
  ai:
    openai:
      api-key: ${OPENAI_API_KEY}
      base-url: https://api.openai.com/v1
      chat:
        model: gpt-3.5-turbo
```

Add `OPENAI_API_KEY` to your environment variables or CI secrets.

---

## 🛠️ Tech Stack

- **Backend:** Spring Boot 3, Spring AI (OpenAI), Spring Security
- **Database:** MySQL 8 (Dockerized)
- **Build Tool:** Maven
- **Containerization:** Docker, docker-compose
- **CI/CD:** GitHub Actions

---

> 📦 Your application runs on `http://localhost:8080` and MySQL on `localhost:3307`.

---

## 🚀 Running Locally

```bash
# Clone the repository
git clone https://github.com/AdityaZaware1/Document-Explainer.git
cd document-explainer

# Build the project
./mvnw clean package -DskipTests

# Run with Docker Compose
docker-compose up --build
```

---

## 🔁 GitHub Actions CI/CD

A full CI/CD pipeline runs on every push to `master`.

> 🔐 Make sure to add these secrets to GitHub:
> - `DOCKER_USERNAME`
> - `DOCKER_PASSWORD`
> - `OPENAI_API_KEY`

---



## 📬 API Endpoints (Sample)

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST   | `/api/v1/docs/upload` | Upload a document (PDF/DOCX) |
| GET    | `/api/v1/docs/{id}/summary` | Get AI-generated summary |
| GET    | `/api/v1/docs/{id}/qa?question=...` | Ask a question based on the document |
| POST   | `/api/v1/auth/login` | Authenticate and receive JWT |


## 🤝 Contributing

1. Fork the repo
2. Create your feature branch (`git checkout -b feature/my-feature`)
3. Commit your changes (`git commit -m 'Add new feature'`)
4. Push to the branch (`git push origin feature/my-feature`)
5. Open a Pull Request

---

## 📄 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## 👨‍💻 Author

**Aditya Zaware**  
Feel free to connect on [LinkedIn](https://linkedin.com/in/adityazaware)

---

Happy coding! 🚀