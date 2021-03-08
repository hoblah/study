package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.OrderDetail;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderDetailRepositoryTest extends StudyApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void create(){
        OrderDetail orderDetail = new OrderDetail();

        //OrderDetail.setOrderAt(LocalDateTime.now());

        //어떤 사람?
        //orderDetail.setUserId(7L);

        //어떤 상품?
        //orderDetail.setItemId(1L);

        OrderDetail newOrderDetail =  orderDetailRepository.save(orderDetail);

        //낫널이맞다~ 제이유닛이 강의는4고 내꺼는 5라 다름.
        //Assert.assertNotNull(newOrderDetail);
        Assertions.assertThat(newOrderDetail).isNotNull();

    }
}
