package com.project1.ticketing;

import org.springframework.context.ApplicationEventPublisher;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class queueTest {
    public static void main(String[] args) {
        long MAX_ACTIVE_TOKEN_NUM = 50;
        List<Token> waitingLine = new ArrayList<>();

        Token token1 = new Token("AA-BB");
        Token token2 = new Token("AA-BE");

        TokenManager tokenManager = new TokenManager(MAX_ACTIVE_TOKEN_NUM, waitingLine);

        tokenManager.put(token1);
        tokenManager.put(token2);

        System.out.println("토큰의 웨이팅번호는 " + token1.getWaitingNum());
        System.out.println("토큰 활성화");
        tokenManager.activate();

        System.out.println("활성화된 토큰의 갯수는 " + tokenManager.countActiveToken());
//
//        while(true){
//            time.sleep(5)
//            //active 유저 카운트
//            tokenManager;
            // 입장가능인원 > active 유저
            // 대기열 번호상 다음번 입장해야할 토큰 상태 변경


        }

    }



