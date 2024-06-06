package com.project1.ticketing.api.controller;

import com.project1.ticketing.api.dto.request.TokenRequest;
import com.project1.ticketing.api.dto.response.TokenResponse;
import com.project1.ticketing.api.usecase.TokenUseCase;
import com.project1.ticketing.domain.token.components.TokenService;
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

    private final TokenService tokenService;

    @Operation(summary = "check token")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = TokenResponse.class)))
    @GetMapping("/tokens")
    public ResponseEntity<TokenResponse> checkWaitNum(@RequestHeader(value = "Authorization", required = false) String token) {

        // 토큰 유무 체크
        if (token == null){
            //토큰이 없을 경우, 발급해준다.
            tokenService.queue();
        }else{

        }

        TokenResponse tokenResponse = tokenService.checkWaitingNum(userId);
        return ResponseEntity.ok().body(tokenResponse);
    }
}