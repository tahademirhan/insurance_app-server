package com.insurance_app.insurance_server.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insurance_app.insurance_server.core.util.ResourceBundleUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    // Jackson JSON serializer instance
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception)
            throws IOException, ServletException {
        // HttpStatus httpStatus = HttpStatus.FORBIDDEN; // 403

        Map<String, Object> data = new HashMap<>();
        data.put("time", new Date());
        data.put("code", "ACCESS001");
        /*
         * data.put( "code", httpStatus.name() );
         */
        data.put("message", ResourceBundleUtil.getValue("ACCESS001"));

        // setting the response HTTP status code
        response.setStatus(200);
        response.setCharacterEncoding("UTF-8");

        response.setContentType("application/json; charset=UTF-8");
        // serializing the response body in JSON
        response.getOutputStream().write(objectMapper.writeValueAsString(data).getBytes(StandardCharsets.UTF_8));

    }
}
