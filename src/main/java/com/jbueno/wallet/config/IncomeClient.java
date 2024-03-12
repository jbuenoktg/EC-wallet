package com.jbueno.wallet.config;

import io.netty.channel.ChannelOption;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.stereotype.Component;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Component("IncomeClient")
public class IncomeClient {

    private final HttpClient client;

    public IncomeClient() {
        this.client = HttpClient.create()
                //Connection Timeout: is a period within which a connection between a client and a server must be established
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(EpollChannelOption.TCP_KEEPIDLE, 300)
                .option(EpollChannelOption.TCP_KEEPINTVL, 60)
                //Response Timeout: The maximun time we wait to receive a response after sending a request
                .responseTimeout(Duration.ofSeconds(1))
                // Read and Write Timeout: A read timeout occurs when no data was read within a certain
                //period of time, while the write timeout when a write operation cannot finish at a specific time
                .doOnConnected(connection -> {
                    connection.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS));
                    connection.addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS));
                });
    }

    public HttpClient getClient() {
        return this.client;
    }
}
