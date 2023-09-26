package com.example.tily.user;

import com.example.tily._core.security.JWTProvider;
import com.example.tily._core.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    // 이메일 중복 체크 (후 인증코드 전송)
    @PostMapping("/email/check")
    public ResponseEntity<?> checkEmail(@RequestBody @Valid UserRequest.CheckEmailDTO requestDTO, Errors errors) {
        userService.checkEmail(requestDTO.getEmail());
        return ResponseEntity.ok().body(ApiUtils.success(null));
    }

    // 회원가입
    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody @Valid UserRequest.JoinDTO requestDTO, Errors errors) {
        userService.join(requestDTO);
        return ResponseEntity.ok().body(ApiUtils.success(null));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequest.LoginDTO requestDTO, Errors errors) {
        String jwt = userService.login(requestDTO);
        return ResponseEntity.ok().header(JWTProvider.HEADER, jwt).body(ApiUtils.success(null));
    }

    // 비밀번호 재설정
    @PostMapping("/password/change")
    public ResponseEntity<?> changePassword(@RequestBody @Valid UserRequest.ChangePwdDTO requestDTO, Errors errors) {
        userService.changePassword(requestDTO);
        return ResponseEntity.ok().body(ApiUtils.success(null));
    }

}
