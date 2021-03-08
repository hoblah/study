package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.User;
import org.junit.jupiter.api.Assertions;
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

        String account = "Test01";
        String password = "Test01";
        String status = "REGISTERED";
        String email = "Test01@gmail.com";
        String phoneNumber = "010-1111-2222";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        // 하나의 객체 만듬.
        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);
        user.setCreatedAt(createdAt);
        user.setCreatedBy(createdBy);

        //User의 리파지토리를 통해 가져온다.
        User newUser = userRepository.save(user);
        // newUser가 null값이 아니여야한다고 지정해줌.
        Assertions.assertNotNull(newUser);

    }

    @Test
    @Transactional //코드추가.
    public void read(){
        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-2222");

        if(user != null){
            user.getOrderGroupList().stream().forEach(orderGroup -> {

                System.out.println("---------------주문묶음---------------");
                System.out.println("수령인 : "+orderGroup.getRevName());
                System.out.println("수령지 : "+orderGroup.getRevAddress());
                System.out.println("총금액 : "+orderGroup.getTotalPrice());
                System.out.println("총수량 : "+orderGroup.getTotalQuantity());

                System.out.println("---------------주문상세---------------");


                orderGroup.getOrderDetailList().forEach(orderDetail -> {
                    System.out.println("주문의 상태 : "+orderDetail.getStatus());
                    System.out.println("도착예정일자 : "+orderDetail.getArrivalDate());
                });

            });

        }

        //Assert.assertNotNull(user);
        Assertions.assertNotNull(user);
    }
    @Test
    public void update(){

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
