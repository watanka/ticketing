package com.project1.ticketing.api.controller;

import com.project1.ticketing.api.dto.request.TokenRequest;
import com.project1.ticketing.api.dto.response.TokenResponse;
import com.project1.ticketing.api.usecase.TokenUseCase;
//import com.project1.ticketing.domain.token.components.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class TokenController {

//    private final TokenService tokenService;

    @Operation(summary = "대기열 조회")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = TokenResponse.class)))
    @GetMapping("/tokens")
    public ResponseEntity<TokenResponse> checkWaitNum(@RequestHeader(value = "Authorization", required = false) String token) {

        // 토큰 유무 체크

//        tokenService.check
//        if (token == null){
//            //토큰이 없을 경우, 토큰을 발급해준다.
                // 토큰 발급 순서
                // JWT 토큰 생성
                // JWT 토큰 클레임을 레디스 카운터로 등록
////            tokenService.queue();
//        }else{
//          토큰이 이미 있을 경우, 몇번째 순서인지 알려준다.
        // 토큰 조회 방법
        // JWT 토큰 클레임을 키로 레디스 서버에 GET한다.
//        }
//
//        TokenResponse tokenResponse = tokenService.checkWaitingNum(userId);
//        return ResponseEntity.ok().body(tokenResponse);
        return null;
    }
}