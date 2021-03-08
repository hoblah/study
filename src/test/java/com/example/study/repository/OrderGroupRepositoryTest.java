package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.OrderGroup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderGroupRepositoryTest extends StudyApplicationTests {

    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Test
    public void create(){

        OrderGroup orderGroup = new OrderGroup();
        orderGroup.setStatus("COMPLETE");
        orderGroup.setOrderType("ALL");
        orderGroup.setRevAddress("서울시 강남구");
        orderGroup.setRevName("홍길동");
        orderGroup.setPaymentType("CARD");
        orderGroup.setTotalPrice(BigDecimal.valueOf(900000));
        orderGroup.setTotalQuantity(1);
        //현재에서 minusDays(2) 이틀전에 주문했다.
        orderGroup.setOrderAt(LocalDateTime.now().minusDays(2));
        // 도착일자는
        orderGroup.setArrivalDate(LocalDateTime.now());
        orderGroup.setCreatedAt(LocalDateTime.now());
        orderGroup.setCreatedBy("AdminServer");
        // 지금 있는 아이디는 1001이기때문에 1001 선언.
        //orderGroup.setUserId(1001L);

        OrderGroup newOrderGroup = orderGroupRepository.save(orderGroup);

        //Assert.assertNotNull(newOrderGroup);
        Assertions.assertNotNull(newOrderGroup);

    }
}
