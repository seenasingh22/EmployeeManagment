package com.example.employee.schema;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
public class RoleSchema {
    @Id
    private String id;
    private String name;
    private int level;

    public RoleSchema() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public RoleSchema(String id, String name, int level) {
        this.id = id;
        this.name = name;
        this.level = level;
    }
}
