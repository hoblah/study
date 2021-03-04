package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ItemRepositoryTest extends StudyApplicationTests {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void create(){

        Item item = new Item();
        item.setName("노트북");
        item.setPrice(100000);
        item.setContent("삼성 노트북");

        Item newItem = itemRepository.save(item);
        //낫널이맞다~ 제이유닛이 강의는4고 내꺼는 5라 다름.
        //Assert.assertNotNull(newItem);
        Assertions.assertThat(newItem).isNotNull();
    }

    @Test
    public void read(){
        Long id = 1L;
        //있을수도있고 없을수도있다 옵셔널.
        Optional<Item> item = itemRepository.findById(id);

        //낫널이맞다~ 제이유닛이 강의는4고 내꺼는 5라 다름.
        //Assert.assertTrue(item.isPresent());
        Assertions.assertThat(item.isPresent()).isTrue();


        //ifPresent item이 만약 있다며언~출력!
        item.ifPresent(i ->{
            System.out.println(i);
        });
    }
}
