package com.pguethoff.web.mappers;

import com.pguethoff.domain.BeerOrderLine;
import com.pguethoff.services.brewery.BeerService;
import com.pguethoff.services.brewery.model.BeerDto;
import com.pguethoff.web.model.BeerOrderLineDto;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Optional;

/**
 * Created by pgue on 13.05.2023.
 */
@Slf4j
@NoArgsConstructor
public abstract class BeerOrderLineDecorator implements BeerOrderLineMapper{
    private BeerService beerService;
    private BeerOrderLineMapper mapper;

    @Autowired
    public void setBeerService(BeerService beerService) {
        this.beerService = beerService;
    }

    @Autowired
    @Qualifier("delegate")
    public void setMapper(BeerOrderLineMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public BeerOrderLineDto entityToDto(BeerOrderLine line) {
        BeerOrderLineDto dto = mapper.entityToDto(line);
        Optional<BeerDto> beerDtoOptional = beerService.getBeerByUpc(dto.getUpc());
        beerDtoOptional.ifPresent(beerDto -> {
            dto.setBeerId(beerDto.getUuid());
            dto.setBeerName(beerDto.getBeerName());
            dto.setBeerStyle(beerDto.getBeerStyle());
            dto.setPrice(beerDto.getPrice());
        });

        log.debug("mapped entity to dto with beer info");
        return dto;
    }

    @Override
    public BeerOrderLine dtoToEntity(BeerOrderLineDto dto) {
        return mapper.dtoToEntity(dto);
    }


}
