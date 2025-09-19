package ru.aidar.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RouteValidator {
    public static final List<String> openApiEndpoints = List.of("/auth/token", "/auth/refresh-token", "/auth/validate", "/auth/refresh");
    public Predicate<ServerHttpRequest> IsSecured = serverHttpRequest -> openApiEndpoints.stream()
                                        .noneMatch(uri -> serverHttpRequest.getURI().toString().equals(uri));
}
