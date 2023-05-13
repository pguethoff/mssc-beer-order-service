package com.pguethoff.services.brewery;

import com.pguethoff.services.brewery.model.BeerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by pgue on 13.05.2023.
 */
//@Profile("!local-discovery")
@Slf4j
@ConfigurationProperties(prefix = "sfg.brewery", ignoreUnknownFields = false)
@Component
public class BeerServiceRestTemplateImpl implements BeerService {

    public static final String BEER_SERVICE_PATH_BY_UPC = "/api/v1/beerUpc/";
    public static final String BEER_SERVICE_PATH_BY_ID = "/api/v1/beer/";
    private final RestTemplate restTemplate;

    private String beerServiceHost;

    public void setBeerServiceHost(String beerServiceHost) {
        this.beerServiceHost = beerServiceHost;
    }

    public BeerServiceRestTemplateImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Optional<BeerDto> getBeerById(UUID uuid) {
        log.debug("Calling Beer Service");

        ResponseEntity<BeerDto> responseEntity = restTemplate
                .exchange(beerServiceHost + BEER_SERVICE_PATH_BY_ID + uuid.toString(), HttpMethod.GET, null,
                        new ParameterizedTypeReference<BeerDto>(){}, (Object) uuid);

        return Optional.ofNullable(responseEntity.getBody());
    }

    @Override
    public Optional<BeerDto> getBeerByUpc(String upc) {
        log.debug("Calling Beer Service");

        ResponseEntity<BeerDto> responseEntity = restTemplate
                .exchange(beerServiceHost + BEER_SERVICE_PATH_BY_UPC + upc, HttpMethod.GET, null,
                        new ParameterizedTypeReference<BeerDto>(){}, (Object) upc);

        return Optional.ofNullable(responseEntity.getBody());
    }
}
