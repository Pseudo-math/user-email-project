package ru.aidar.filter;

import org.apache.http.HttpHeaders;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;

public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {
    private RouteValidator routeFilter;

    public AuthFilter(RouteValidator routeFilter) {
        super(Config.class);
        this.routeFilter = routeFilter;
    }

    @Override
    public GatewayFilter apply(Config config) {
      //  return null;
        return ((((exchange, chain) -> {
            if (routeFilter.IsSecured.test(exchange.getRequest())) {
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("Missing auth header");
                }
            }
        })));
    }

    public static class Config {

    }
}
