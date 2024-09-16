package ch.mno.wc3.services.facebook.queries;

import ch.mno.wc3.services.ServiceException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;

@Slf4j
public abstract class AbstractOperation<E> {


    protected String baseURL = "https://graph.facebook.com/v15.0";
    protected final Map<String, String> BASIC_OPTIONS = Map.of(
            "format", "json",
            "method", "get",
            "pretty", "0",
            "suppress_http_code", "1",
            "transport", "cors"
    );
    private static final ObjectMapper objectMapper = new ObjectMapper();
    protected WebClient webClient;

    protected AbstractOperation(WebClient webClient) {
        this.webClient = webClient;
    }


    protected abstract String buildUrl();

    protected abstract E execute();


//    public E execute() {
//        String url = buildUrl();
//        String json = download(url);
//        return parseResponse(json);
//    }
//    protected abstract E parseResponse(String json);


    protected String download(String url) {
        return download(url, String.class);
    }

    protected <T> T download(String url, Class<T> elementClass) {
        url = url.replace("{", "%7B").replace("}", "%7D");
        log.info("Downloading {}", url);


        return webClient.get()
                .uri(URI.create(url))
                .retrieve()  // Send request, wait response
                .onStatus(HttpStatusCode::isError, clientResponse ->
                        clientResponse.bodyToMono(String.class).flatMap(errorBody -> {
                            String errorMessage = "Error on Facebook API call, status " + clientResponse.statusCode() + ", body: " + errorBody;
                            return Mono.error(new ServiceException(errorMessage));
                        })
                )
                .bodyToMono(elementClass)  // Convert body
                .onErrorMap(ex -> new ServiceException("Download error : " + ex.getMessage(), ex))
                .block();  // Block to get result
    }


    protected <T> T extractValue(String json, String pattern, Function<String, T> parser) {
        var m = Pattern.compile(pattern).matcher(json);
        if (m.find()) {
            return parser.apply(m.group("value"));
        }
        throw new ServiceException("Cannot find value in JSON: " + json);
    }

    protected Integer extractInt(String json, String pattern) {
        return extractValue(json, pattern, Integer::parseInt);
    }

    protected Float extractFloat(String json, String pattern) {
        return extractValue(json, pattern, Float::parseFloat);
    }

    protected JsonNode parseJson(String json) {
        try {
            return objectMapper.readTree(json);
        } catch (Exception ex) {
            throw new ServiceException("Error parsing JSON: " + ex.getMessage(), ex);
        }
    }

    protected void setBaseURL4Test(String baseURL) {
        this.baseURL = baseURL;
    }

}