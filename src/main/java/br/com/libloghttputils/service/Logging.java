package br.com.libloghttputils.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Logging {

    void logRequest(HttpServletRequest httpServletRequest, Object body) throws JsonProcessingException;

    void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object body) throws JsonProcessingException;
}
