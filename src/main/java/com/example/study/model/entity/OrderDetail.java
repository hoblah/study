package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // 디비 order_detail 테이블의 스네이크무기떄문에 자동연결됨. 클래스는 카멜이라서~가능.
@ToString(exclude = {"user","item"})
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime orderAt;

    //OrderDetail 입장에서는 자기 자신은 N, 상대가되는 고객은 1이다. 그래서 @ManyToOne이라고 적는다.
    // N : 1
    //private Long userId;   //원래는 Long타입이지만 타입을 User로 바꿔주어야한다.
    //userId가아닌 user로 변수명변경, 롱타입으로 선언했을때 디비에 저장된 컬럼과 매칭이되려면
    //하이버네이트와 연관관계를 설정할때는 반드시 객체이름을 적어주어야하고,
    //@ManyToOne 어노테이션을 붙혀주면 알아서 하이버네이트에서 알아서 디비컬럼인 user_Id를 알아서 찾아가게된다..
   @ManyToOne
    private User user;

    //OrderDetail 입장에서는 자기 자신은 N, 아이템은 1이다. 그래서 @ManyToOne이라고 적는다.
    //private Long itemId;
    // N : 1
    //타입도 Item인 변수명item으로 변경함.
    @ManyToOne
    private Item item;
}
