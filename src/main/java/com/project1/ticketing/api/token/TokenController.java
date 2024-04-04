package com.project1.ticketing.api.token;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/concert")
public class TokenController {

    // 토큰 생성 + 대기열.
    @PostMapping("/{concertId}")
    public getInWaitingLine(@PathVariable long concertId){
        Token newToken = TokenManager.createNewToken(concertId);
        return newToken;
    }

    // 생성한 토큰을 polling하고 계속 업데이트해줄 thread가 필요하다.




}
