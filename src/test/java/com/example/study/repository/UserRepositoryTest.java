package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests {
    // dependency Injection (DI)
    @Autowired
    private UserRepository userRepository;

    @Test
    public  void create(){
        // String sql = insert into user (%s, %s, %d ) value (account, email, age);
        User user = new User();
        user.setAccount("TestUser03");
        user.setEmail("TestUser03@gmail.com");
        user.setPhoneNumber("010-1111-3333");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("TestUser3");

        User newUser = userRepository.save(user);
        System.out.println("newUser : "+newUser);
    }

    @Test
    @Transactional //코드추가.
    public void read(){
        Optional<User> user = userRepository.findById(7L);

        user.ifPresent(selectUser ->{
            /*User클래스에 OrderDetail리스트객체를 만든것을
            get하게되면 리스트형식으로 반환하게된다.
            즉, 주문내역을 볼수있다. stream()을 통해서 detail내역을보면
            여러가지 내역이 있을거고 어떠한 아이템을 삿는지 알기위해서
            detail의 getItemId()만 찍어보도록 하겠다.
            7번이라는 유저를 가져와서 셀렉트를 한 다음에
            우리가방금 연결시킨 1대N인 오더디테일리스트를 가져와서
            리스트형태이기 때문에 스트림으로 뽀이치를 통해서 돌린다.
            각각의 디테일이 가지고 있는 각각의 아이템 아이디를를로그를찍어보겠다.
            실제로 통신을 할때는 리퀘스트 하나당 1트랜잭션이 잡히기 때문에
            테스트상태에서만 @Transactional 코드를 안 넣어주면 에러가 난다고 생각하면됨.
            정상적으로 실행되면 로그에 1이라고 찍힘. 이것은 위에서셀렉한 7이라는 사용자가
            디테일의 가지고있는 아이템의 아이디 이다.
            디비에서 order_detail의 테이블을 보면 7이라는 사용자가 아이템 1을 가지고있다.
            코드에서 셀렉할때 디테일에서셀렉한게아닌 유저테이블에서 사용자를 셀렉했다.
            User클래스에가면 orderDetailList가 관계매핑을 통해서 어떠한 주문내역이있다라는것을
            객체로 매핑을 해놨고 리스트객체타입은선언함으로 코드에선 getOrderDetailList()로
            주문상세내역을 가져올수있는것.
             */
           selectUser.getOrderDetailList().stream().forEach(detail ->{
               //System.out.println(detail.getItemId());
               /* 코드를 변경함으로써 어떤아이템을 구매했는지 까지도
                    아이디를 가져오는것이 아니라 객체를 바로 반환가능.
               */
               //밑의 코드를 선언하며 이사용자가 어떠한 아이템까지도 가지고있다.라는것까지 연관관계를 모두 연결시킬수있다.
               Item item = detail.getItem();
               System.out.println(detail.getItem());
           });
        });

    }
    @Test
    public void update(){
        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectUser ->{
            selectUser.setAccount("PPPP");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");

            userRepository.save(selectUser);
        });
    }

    @Test
    public void delete(){

        Optional<User> user = userRepository.findById(3L);

        //Assert.assertTrue(user.isPresent()); // true


        user.ifPresent(selectUser ->{
            userRepository.delete(selectUser);
        });

        Optional<User> deleteUser = userRepository.findById(3L);

       //Assert.assertTrue(user.isPresent()); // false
    }

}
