package com.gemtrading.gateway_service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http.csrf(csrfSpec -> csrfSpec.disable())       // csfr = Cross site request Forgery
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/actuator/**").permitAll()
                        .pathMatchers("/eureka/**").permitAll()

                        .pathMatchers(HttpMethod.GET,"/api/v1/gems/**").hasAnyAuthority(
                                "SCOPE_gems.read" ,
                                "SCOPE_gems.write" , "ROLE_admin" , "ROLE_dealer")
                        .pathMatchers(HttpMethod.GET,"/api/v1/dealers/**").hasAnyAuthority(
                                "SCOPE_dealer.read" ,
                                "SCOPE_dealer.write" , "ROLE_admin" , "ROLE_dealer")
                        .pathMatchers(HttpMethod.GET,"/api/v1/trades/**").hasAnyAuthority(
                                "SCOPE_traders.read" ,
                                "SCOPE_traders.write" , "ROLE_admin" , "ROLE_trader")
                        .pathMatchers(HttpMethod.POST, "/api/v1/gems/**").hasAnyAuthority(
                                "SCOPE_gems.write" , "ROLE_admin" , "ROLE_gems").anyExchange().authenticated()
                ).oauth2ResourceServer(oAuth2ResourceServerSpec -> oAuth2ResourceServerSpec
                        .jwt(jwtSpec -> jwtSpec.jwtAuthenticationConverter(jwtAuthenticationConverter())));

        return http.build();
    }

    private Converter<Jwt, Mono<AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();

        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
            Collection<GrantedAuthority> scopeAuthorities = extractScopes(jwt);
            Collection<GrantedAuthority> realmRoles = extractRealmRoles(jwt);

            return Stream.of(scopeAuthorities, realmRoles)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());
        });

        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }

    private Collection<GrantedAuthority> extractScopes(Jwt jwt) {
        String scopeClaim = jwt.getClaimAsString("scope");
        if (scopeClaim == null || scopeClaim.isBlank()) {
            return List.of();
        }
        return Stream.of(scopeClaim.split(" "))
                .map(scope -> new SimpleGrantedAuthority("SCOPE_" + scope))
                .collect(Collectors.toList());
    }

    private Collection<GrantedAuthority> extractRealmRoles(Jwt jwt) {
        Map<String ,Object> realmAccess = jwt.getClaim("realm_access");
        if (realmAccess == null) {
            return List.of();
        }

        List<String> roles = (List<String>) realmAccess.get("roles");  //type casting

        if (roles == null) {
            return List.of();
        }

        return roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toSet());
    }
}