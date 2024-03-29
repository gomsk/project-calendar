package com.gomsk.project.api;


import com.gomsk.project.core.SimpleEntity;
import com.gomsk.project.core.SimpleEntityRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@EnableJpaAuditing
@EntityScan("com.gomsk.project.core")
@EnableJpaRepositories("com.gomsk.project.core")
@RestController
@SpringBootApplication(scanBasePackages = "com.gomsk.project")
public class ApiApplication {

    private final SimpleEntityRepository repository;

    public ApiApplication(SimpleEntityRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<SimpleEntity> findAll(){
        return repository.findAll();
    }

    @PostMapping("/save")
    public SimpleEntity saveOne(){
        final SimpleEntity entity = new SimpleEntity();
        entity.setName("hello");
        return repository.save(entity);
    }

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class,args);
    }


}
