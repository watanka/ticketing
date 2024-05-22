package com.project1.ticketing.api.dto.request;


import lombok.Getter;
import lombok.Setter;

public record PaymentRequest(long userId, long reservationId){
}
