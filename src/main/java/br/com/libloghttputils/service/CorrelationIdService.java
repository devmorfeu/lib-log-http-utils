package br.com.libloghttputils.service;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import java.util.UUID;

import static net.logstash.logback.encoder.org.apache.commons.lang3.StringUtils.isBlank;

@Component
public class CorrelationIdService {

    private static final String CORRELATION_ID_LOG_VAR_NAME = "correlationId";
    private static final String CORRELATION_ID_HEADER_NAME = "X-Correlation-Id";

    public void getCorrelationIdFromHeader(HttpServletRequest request) {
        var correlationId = request.getHeader(CORRELATION_ID_HEADER_NAME);
        if (isBlank(correlationId)) {
            correlationId = generateUniqueCorrelationId();
        }
        MDC.put(CORRELATION_ID_LOG_VAR_NAME, correlationId);
    }

    private String generateUniqueCorrelationId() {
        return UUID.randomUUID().toString();
    }
}
