package com.company.Task;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Task",
                description = "Task",
                contact = @Contact(name = "developers telegram account",
                        url = "https://t.me/java_developer09"),
                license = @License(name = "License",
                        url = "http://localhost:8083/swagger-ui/index.html#/"),
                version = "version-3.0.2"),
        tags = {
                @Tag(
                        name = "Get",
                        description = "get"),
                @Tag(
                        name = "Create",
                        description = "create"),
                @Tag(
                        name = "Update",
                        description = "update"),
                @Tag(
                        name = "Delete",
                        description = "delete")
        },
        servers = @Server(
                // url = "http://185.196.213.118:8082",
                url = "http://localhost:8083",
                description = "Task host")
)
public class TaskApplication {
    public static void main(String[] args) {
        SpringApplication.run(TaskApplication.class, args);
    }
}