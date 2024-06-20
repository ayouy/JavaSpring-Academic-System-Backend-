package com.mw.education.component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mw.education.domain.compose.Term;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class TermOperationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equalsIgnoreCase("POST") && request.getRequestURI().endsWith("/terms")) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String requestBody = reader.lines().collect(Collectors.joining(System.lineSeparator()));

            ObjectMapper mapper = new ObjectMapper();
            Term term = mapper.readValue(requestBody, Term.class);
            Date startDate = term.getStartDate();
            Date endDate = term.getEndDate();

            if (startDate != null && endDate != null && !startDate.before(endDate)) {
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setCharacterEncoding("UTF-8");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"code\":400,\"msg\":\"startDate必须早于endDate\"}");
                return false;
            }
        }
        return true;
    }
}