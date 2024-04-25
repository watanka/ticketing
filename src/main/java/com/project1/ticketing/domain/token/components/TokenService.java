package com.project1.ticketing.domain.token.components;

import com.project1.ticketing.domain.token.models.Token;
import com.project1.ticketing.domain.token.models.TokenStatus;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TokenService {
    private List<Token> waitingLine;
    private long maxActiveTokenNum;
    private long currWaitingNum;
    public TokenService(long maxActiveTokenNum, List<Token> waitingLine) {
        this.maxActiveTokenNum = maxActiveTokenNum;
        this.waitingLine = waitingLine;
    }

    public void put(Token token) {
        waitingLine.add(token);
        token.setWaitingNum(++currWaitingNum);
    }

    public List<Token> getByStatus(TokenStatus tokenStatus){
        return waitingLine.stream()
                .filter(token -> token.getStatus() == tokenStatus)
                .collect(Collectors.toList());
    }

    public void activate(){
        long currActiveTokenCount = countActiveToken();
        long numToActivate = maxActiveTokenNum - currActiveTokenCount;
        List<Token> tokensInWait = getByStatus(TokenStatus.WAIT);
        tokensInWait.sort(Comparator.comparingLong(Token::getWaitingNum));

        tokensInWait.subList(0, (int) Math.min(numToActivate, tokensInWait.size()));

        for (Token token : tokensInWait) {
            System.out.println("토큰 uuid "+token.getUuid()+"활성화");
            token.setStatus(TokenStatus.ACTIVE);
        }
    }


    public long countActiveToken(){
        System.out.println("counting");
        return waitingLine.stream()
                .filter(token ->token.getStatus() == TokenStatus.ACTIVE)
                .count();
    }

    // 입장할 값




}
