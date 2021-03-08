package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class CategoryRepositoryTest extends StudyApplicationTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void create(){
        String type = "COMPUTER";
        String title = "컴퓨터";
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        //한가지 객체 생성후 만들기.
        Category category = new Category();
        category.setType(type);
        category.setTitle(title);
        category.setCreatedAt(createdAt);
        category.setCreatedBy(createdBy);

        Category newCategory = categoryRepository.save(category);

        //Assert.assertNotNull(newCategory);
        //Assert.assertequals(newCategory.getType(),type);
        //Assert.assertequals(newCategory.getTitle(),title);
        Assertions.assertNotNull(newCategory);
        Assertions.assertEquals(newCategory.getType(),type);
        Assertions.assertEquals(newCategory.getTitle(),title);

    }

    @Test
    public void read(){

        String type = "COMPUTER";

        Optional<Category> optionalCategory = categoryRepository.findByType(type);

        // select * from category where type = "COMPUTER";

        optionalCategory.ifPresent(c -> {

            //Assert.assertEquals(c.getType(),type);
            Assertions.assertEquals(c.getType(),type);

            System.out.println(c.getId());
            System.out.println(c.getType());
            System.out.println(c.getTitle());
        });
    }
}
