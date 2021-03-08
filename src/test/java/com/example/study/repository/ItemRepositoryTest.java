package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class ItemRepositoryTest extends StudyApplicationTests {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void create(){

        Item item = new Item();
        item.setStatus("UNREGISTERED");
        item.setName("삼성노트북");
        item.setTitle("삼성 노트북 A100");
        item.setContent("2019년형 노트북 입니다.");
        item.setPrice(900000);
        item.setBrandName("삼성");
        item.setRegisteredAt(LocalDateTime.now());
        item.setCreatedAt(LocalDateTime.now());
        item.setCreatedBy("Partner01");
        // 디비의 아이디가 82기때문에 82L적음.
        item.setPartnerId(82L);

        //item을 매개변수로 save시키면 새로운item이 나오고
        Item newItem = itemRepository.save(item);
        //그아이는 반드시 널값이 아니여야한다.
        //Assert.assertNotNull(newItem)
        Assertions.assertThat(newItem).isNotNull();
    }

    @Test
    public void read(){
        Long id = 1L;
        //있을수도있고 없을수도있다 옵셔널.
        Optional<Item> item = itemRepository.findById(id);

        //제이유닛이 강의는4고 내꺼는 5라 다름.
        //Assert.assertTrue(item.isPresent());
        //값이 들어가있기때문에 true가 맞다.
        Assertions.assertThat(item.isPresent()).isTrue();


        //ifPresent item이 만약 있다며언~출력!
        item.ifPresent(i ->{
            System.out.println(i);
        });
    }
}
