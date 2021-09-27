package br.com.libloghttputils.service;

import br.com.libloghttputils.config.LoggingProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static net.logstash.logback.argument.StructuredArguments.kv;
import static net.logstash.logback.argument.StructuredArguments.v;

@Slf4j
@Component
public class LoggingService implements Logging{

    @Autowired
    private LoggingProperties properties;

    @Autowired
    private CorrelationIdService correlationIdService;

    @Override
    public void logRequest(HttpServletRequest httpServletRequest, Object body) {

        correlationIdService.getCorrelationIdFromHeader(httpServletRequest);

        final var queryString = httpServletRequest.getQueryString();

        if (body != null) {
            log.info("{}", properties.getMessageRequest(), kv("body", body), v("path", getPath(httpServletRequest)));
            return;
        }

        //TODO #implementar quebra de query params e logar os campos separados
        if (queryString != null){
            log.info("{}", properties.getMessageRequest(), kv("fields",queryString), v("path", getPath(httpServletRequest)));
            return;
        }

        log.info("{}", properties.getMessageRequest(), v("path", getPath(httpServletRequest)));
    }

    @Override
    public void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object body) {

        log.info("{}", properties.getMessageResponse(),kv("body", body), v("path", getPath(httpServletRequest)));

    }

    private String getPath(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getRequestURI().substring(httpServletRequest.getContextPath().length());
    }
}
