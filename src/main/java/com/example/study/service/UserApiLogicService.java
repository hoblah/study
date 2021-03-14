package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.User;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {


    //@Autowired
    //private UserRepository userRepository;
    private final UserRepository userRepository;

    // 1. reqeust data
    // 2. user 생성
    // 3. 생성된 데이터 -> UserApiResponse return
    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        // 1. request data (데이터가져오기)
        UserApiRequest userApiRequest = request.getData();
        // 2. User생성.
        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status("REGISTERED")
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();

        User newUser = userRepository.save(user);
        // 3. 생성된 데이터 -> userApiResponse return.
        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {

        // id -> repository getOne, getById
        //Optional<User> optional = userRepository.findById(id);
        // user -> userApiResponse return
        /*
        return optional
                .map(user -> response(user))
                .orElseGet(
                        () -> Header.ERROR("데이터 없음")
                );
         */

        //이렇게 하면 람다식.
        return userRepository.findById(id)
            .map(user -> response(user))
            .orElseGet(
                    () -> Header.ERROR("데이터 없음")
            );

    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {

        // 1. data를 가져오고
        UserApiRequest userApiRequest = request.getData();
        // 2. id값으로 -> user의 데이터를 찾고
        Optional<User> optional = userRepository.findById(userApiRequest.getId());

        return optional.map(user -> {

            // 3. data -> update를 시키고
            user.setAccount(userApiRequest.getAccount())
                    .setPassword(userApiRequest.getPassword())
                    .setStatus(userApiRequest.getStatus())
                    .setPhoneNumber(userApiRequest.getPhoneNumber())
                    .setEmail(userApiRequest.getEmail())
                    .setRegisteredAt(userApiRequest.getRegisteredAt())
                    .setUnregisteredAt(userApiRequest.getUnregisteredAt());
                return user;
        })
                .map(user -> userRepository.save(user)) // update를 하고, ->새로운 유저 객체가 반환됨.newUser
                .map(updateUser -> response(updateUser))            // userApiResponse가 만들어 지는것이다.

        .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {

        // 1. id -> repository -> user
        Optional<User> optional = userRepository.findById(id);
        // 2. repository -> delete
        return optional.map(user ->{
            userRepository.delete(user);

            return Header.OK();
        })
        .orElseGet(() ->Header.ERROR("데이터 없음"));
        // 3. response return
    }

    private Header<UserApiResponse> response(User user){

        // user -> userApiResponse
        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword()) // todo 암호와, 길이를 리턴하던지 이러한 부분은 기획을 따라가면된다.
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        // Header + data return
        //return Header.OK(userApiResponse);
        return Header.OK(userApiResponse);
    }
}
