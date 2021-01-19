package com.apress.todo.client.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Обработчик ошибок, поступающих от сервера
 *
 * @author Pavel Zaytsev
 */
public class ToDoErrorHandler extends DefaultResponseErrorHandler {

    private Logger log = LoggerFactory.getLogger(ToDoErrorHandler.class);

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        // просто залогируем
        log.error(response.getStatusCode().toString());
        log.error(StreamUtils.copyToString(response.getBody(), Charset.defaultCharset()));
    }
}
