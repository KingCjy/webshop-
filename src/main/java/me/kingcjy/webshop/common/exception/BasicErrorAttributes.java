package me.kingcjy.webshop.common.exception;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.kingcjy.webshop.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author KingCjy
 */
@RestController
@RequestMapping("${server.error.path:${error.path:/error}}")
public class BasicErrorAttributes extends DefaultErrorAttributes {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);

        HttpStatus httpStatus = HttpStatus.valueOf(Integer.parseInt(String.valueOf(errorAttributes.get("status"))));
        String message = (String) errorAttributes.get("message");
        String path = (String) errorAttributes.get("path");
        ResponseEntity<Response> response = Response.getResponse(path, httpStatus, message, null);

        Map<String, Object> map = new HashMap();
        map.put("meta", objectMapper.convertValue(response.getBody().getMeta(), Response.Meta.class));

        return map;
    }
}
