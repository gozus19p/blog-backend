package it.manuelgozzi.blog.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author Manuel Gozzi
 */
@Slf4j
@Component
public class WebInterceptor implements HandlerInterceptor {

    private final ManuelGozziBlogConfiguration configuration;

    @Autowired
    public WebInterceptor(ManuelGozziBlogConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String auth = request.getHeader("Authorization");

        if (auth == null || auth.replaceAll("\\s", "").isEmpty() || !auth.contains("Basic ")) {

            log.warn("Received unauthorized request [{}]", request.getRequestURI());
            response.setStatus(401);
            return false;
        }

        try {

            String decoded = new String(Base64.getDecoder().decode(auth.replace("Basic ", "")), StandardCharsets.UTF_8);
            if (!decoded.contains(":")) {

                log.warn("Received unauthorized request [{}], malformed authorization header", request.getRequestURI());
                response.setStatus(401);
                return false;
            }

            var userAndPassword = decoded.split(":");
            if (userAndPassword.length != 2) {

                log.warn("Received unauthorized request [{}], format is invalid", request.getRequestURI());
                response.setStatus(401);
                return false;
            }

            return userAndPassword[0].equals(this.configuration.getAuth().getBasic().getUsername())
                    && userAndPassword[1].equals(this.configuration.getAuth().getBasic().getPassword());
        } catch (Exception e) {

            log.error(
                    "Something went wrong during base64 decodification [{}], request [{}] is unauthorized",
                    e.getMessage(),
                    request.getRequestURI()
            );
            response.setStatus(401);
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

        log.info("Request [{}], status [{}]", request.getRequestURI(), response.getStatus());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }
}
