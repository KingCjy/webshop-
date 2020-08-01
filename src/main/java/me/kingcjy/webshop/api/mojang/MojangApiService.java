package me.kingcjy.webshop.api.mojang;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * @author KingCjy
 */
@Service
@RequiredArgsConstructor
public class MojangApiService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public AuthenticateResponse authenticate(String email, String password) {
        String contents = toJson(email, password);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<String>(contents, headers);

        try {
            ResponseEntity<AuthenticateResponse> response = restTemplate.postForEntity("https://authserver.mojang.com/authenticate", request, AuthenticateResponse.class);

            AuthenticateResponse responseDto = response.getBody();
            SelectedProfile selectedProfile = responseDto.getSelectedProfile();
            selectedProfile.setUuid(makeUUIDFormat(responseDto.getSelectedProfile().getUuid()));
            return response.getBody();
        } catch (HttpClientErrorException e) {
            ErrorResponse errorResponse = getErrorResponse(e);
            throw new MojangApiException(errorResponse.getErrorMessage(), e);
        } catch (RuntimeException e) {
            throw new MojangApiException(e.getMessage(), e);
        }
    }

    private String toJson(String email, String password) {
        try {
            AuthenticateRequest request = new AuthenticateRequest(email, password);

            return objectMapper.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private String makeUUIDFormat(String before) {
        String first = before.substring(0, 8);
        String second = before.substring(8, 12);
        String third = before.substring(12, 16);
        String forth = before.substring(16, 20);
        String fifth = before.substring(20, 32);

        return first + "-" + second + "-" + third + "-" + forth + "-" + fifth;
    }

    private ErrorResponse getErrorResponse(HttpClientErrorException e) {
        try {
            return objectMapper.readValue(e.getResponseBodyAsString(), ErrorResponse.class);
        } catch (JsonProcessingException exception) {
            throw new IllegalArgumentException(e);
        }
    }
}
