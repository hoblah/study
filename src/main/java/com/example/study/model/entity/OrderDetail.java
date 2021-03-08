package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // 디비 order_detail 테이블의 스네이크이기 때문에 자동연결됨. 클래스는 카멜이라서~가능.
@ToString(exclude = {"orderGroup"})
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private LocalDateTime arrivalDate;
    private Integer quantity;
    private BigDecimal totalPrice;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

    //왜래키추가.
    private Long itemId;
    //private Long orderGroupId;
    // OrderDetail N : 1 OrderGroup
    @ManyToOne
    private OrderGroup orderGroup;

}
