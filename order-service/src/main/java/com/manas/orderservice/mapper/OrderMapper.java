package com.manas.orderservice.mapper;

import com.manas.orderservice.dto.OrderDTO;
import com.manas.orderservice.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderMapper MAPPER = Mappers.getMapper(OrderMapper.class);

    Order DtoToModel(OrderDTO orderDTO);
}
