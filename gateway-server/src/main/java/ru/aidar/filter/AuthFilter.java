package ru.aidar.filter;

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
            if (routeFilter.IsSecured.test(exchange.getRequest())) return true;
        })));
    }

    public static class Config {

    }
}
