package com.example.study.ifs;

import com.example.study.model.network.Header;

public interface CrudInterface {

    Header create(); // todo request object 추가 - 추후에 추가할거다.
    Header read(Long id);
    Header update();
    Header delete(Long id);
}
