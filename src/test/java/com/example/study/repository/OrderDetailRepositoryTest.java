package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.OrderDetail;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderDetailRepositoryTest extends StudyApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void create(){
        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setStatus("WAITING");
        // 도착일자는 plusDays(2)-이틀뒤쯤 도착할거같다라는 예상일자를 선언.
        // 현재일자로부터 이틀이 더 더해진다.
        orderDetail.setArrivalDate(LocalDateTime.now().plusDays(2));
        // 수량 1개
        orderDetail.setQuantity(1);
        // totalPrice는 BigDecimal타입이기때문에 .valueOf(값) 선언.
        orderDetail.setTotalPrice(BigDecimal.valueOf(900000));

        //orderDetail.setOrderGroupId(1001L); // Long -> OrderGroup //어떠한 장바구니에
        //orderDetail.setItemId(406L); //어떠한 상품

        orderDetail.setCreatedAt(LocalDateTime.now());
        orderDetail.setCreatedBy("AdminServer");

        OrderDetail newOrderDetail =  orderDetailRepository.save(orderDetail);

        //낫널이맞다~ 제이유닛이 강의는4고 내꺼는 5라 다름.
        //Assert.assertNotNull(newOrderDetail);
        Assertions.assertThat(newOrderDetail).isNotNull();

    }
}
