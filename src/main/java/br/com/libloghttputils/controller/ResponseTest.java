package br.com.libloghttputils.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseTest {

    private Long id;
    private String message;
}
