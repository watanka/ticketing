package com.project1.ticketing.domain.token.infrastructure;


//
//public class MemoryTokenRepository implements ITokenRepository {
////
////    Map<Long, Map<String, Token>> waitQueue = new HashMap<>(); // {concert_id : {tokenId : Token}}
////    Map<Long, Integer> waitingNumMap = new HashMap<>();
//    Map<Long, Token> waitQueue = new ConcurrentHashMap<>();
//    AtomicInteger waitingNum;
//
//    @Override
//    public Token save(Token token) {
//        return null;
//    }
//
//    @Override
//    public Token findByUserId(long userId) {
//        return waitQueue.get(userId);
//    }
//
//    @Override
//    public List<Token> findByStatus(TokenStatus status) {
//        return null;
//    }
//
////    @Override
////    public List<Token> findTokensOrderByExpiredAt(int n) {
////        return null;
////    }
//
//
//}
