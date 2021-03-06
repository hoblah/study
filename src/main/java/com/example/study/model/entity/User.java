package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // == table
// @Table 클래스의 이름과 디비의 테이블명이 같다면 자동으로 매칭된다.
//컬럼명이 디비에는 _가 있어도 자동으로 매칭된다.
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@Column(name = "account") 이렇게도 매칭가능.
    private String account;
    private String email;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

    //User입장에서 보면 유저는 자신은 1이지만 OrderDetail은 1입니다.
    // 1 : N
    /*
    그렇기 때문에 코드로는 List로 받아올수있으며
    어노테이션으로 자신은 One이고 상대방은 Many가 됩니다.
    그리고 여기에 fetch타입을 걸어주게 되는데. 일단레이지로 걸고 설명은 추후에하겠다.
    그리고 어떠한 컬럼에 매핑시킬거냐, mappedBy!! 즉! 어떠한 변수에 매핑시킬거냐를 통해서 user라고 걸어준다.
    이 user는 OrderDetail에 있는 변수이름과 동일해야함. 우리가 선언했기때문에
    User클래스에서는 OrderDetail이라는 클래스안의 유저라는 변수에 매칭시키겟다. 라고 이해하면된다.
    User라는 클래스안에 OrderDetail이라는 클래스가 생겼고 OrderDetail과 1대N
    OrderDetail입장에서는 N대1로 서로가 연결된것을 확인함.
    */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<OrderDetail> orderDetailList;

}
