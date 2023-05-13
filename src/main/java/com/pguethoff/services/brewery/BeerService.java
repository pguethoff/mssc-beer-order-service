package com.pguethoff.services.brewery;

import com.pguethoff.services.brewery.model.BeerDto;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by pgue on 13.05.2023.
 */
public interface BeerService {

    Optional<BeerDto> getBeerById(UUID uuid);

    Optional<BeerDto> getBeerByUpc(String upc);

}
