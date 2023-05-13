package com.pguethoff.web.mappers;

import com.pguethoff.domain.BeerOrderLine;
import com.pguethoff.web.model.BeerOrderLineDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
@DecoratedWith(BeerOrderLineDecorator.class)
public interface BeerOrderLineMapper {
    BeerOrderLineDto entityToDto(BeerOrderLine line);

    BeerOrderLine dtoToEntity(BeerOrderLineDto dto);
}
