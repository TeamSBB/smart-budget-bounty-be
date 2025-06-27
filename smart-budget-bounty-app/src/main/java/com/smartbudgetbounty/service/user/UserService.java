package com.smartbudgetbounty.service.user;

import com.smartbudgetbounty.dto.auth.LoginDtoRequest;
import com.smartbudgetbounty.dto.auth.LoginDtoResponse;
import com.smartbudgetbounty.dto.auth.RegisterDtoRequest;
import com.smartbudgetbounty.dto.auth.RegisterDtoResponse;
import com.smartbudgetbounty.entity.User;

public interface UserService {
    LoginDtoResponse loginUser(LoginDtoRequest loginUserDtoRequest);

    RegisterDtoResponse registerUser(RegisterDtoRequest registerUserDto);

    User getById(Long id);
}
