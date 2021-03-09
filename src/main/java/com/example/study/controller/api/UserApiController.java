package com.example.study.controller.api;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.network.Header;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserApiController implements CrudInterface {

    @Override
    @PostMapping("") // /api/user
    public Header create(){  // C
        return null;
    }

    @Override
    @GetMapping("{id}")  // /api/user/{id}
    public Header read(Long id) {
        return null;
    }

    @Override
    public Header update() {
        return null;
    }

    @Override
    public Header delete(Long id) {
        return null;
    }

}
