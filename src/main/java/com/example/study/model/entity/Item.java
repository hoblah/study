package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer price;
    private String content;

    //아이템 입장에선 본인은 1, Orderdetail은 N임.
    // 1 : N
    /* User와 같이 오더바이디테일타입의 리스트선언후
        OrderDetail클래스 안에있는 item변수에 매칭을 시키겠다.
    */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    private List<OrderDetail> orderDetailList;

}
