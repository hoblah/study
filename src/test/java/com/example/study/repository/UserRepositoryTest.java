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

        String account = "Test03";
        String password = "Test03";
        String status = "REGISTERED";
        String email = "Test01@gmail.com";
        String phoneNumber = "010-1111-3333";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        // 하나의 객체 만듬.
        User user = new User();
        //User user = new User(account, password, status, email, phoneNumber, registeredAt);
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);

        User u = User.builder()
                .account(account)
                .password(password)
                .status(status)
                .email(email).build();



        //User의 리파지토리를 통해 가져온다.
        User newUser = userRepository.save(user);
        // newUser가 null값이 아니여야한다고 지정해줌.
        Assertions.assertNotNull(newUser);

    }

    @Test
    @Transactional //코드추가.
    public void read(){
        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-2222");

        user.setEmail("")
                .setPhoneNumber("")
                .setStatus("");

        User u = new User().setAccount("").setEmail("").setPassword("");


        if(user != null){
            user.getOrderGroupList().stream().forEach(orderGroup -> {

                System.out.println("---------------주문묶음---------------");
                System.out.println("수령인 : "+orderGroup.getRevName());
                System.out.println("수령지 : "+orderGroup.getRevAddress());
                System.out.println("총금액 : "+orderGroup.getTotalPrice());
                System.out.println("총수량 : "+orderGroup.getTotalQuantity());

                System.out.println("---------------주문상세---------------");


                orderGroup.getOrderDetailList().forEach(orderDetail -> {
                    System.out.println("파트너사 이름 : "+orderDetail.getItem().getPartner().getName());
                    System.out.println("파트너사 카테고리 : "+orderDetail.getItem().getPartner().getCategory().getTitle());
                    System.out.println("주문 상품 : "+orderDetail.getItem().getName());
                    System.out.println("고객센터 번호 : "+orderDetail.getItem().getPartner().getCallCenter());
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
