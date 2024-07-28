package com.manas.inventoryservice.mapper;

import com.manas.inventoryservice.dto.InventoryDTO;
import com.manas.inventoryservice.model.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InventoryMapper {
    InventoryMapper MAPPER = Mappers.getMapper(InventoryMapper.class);

    List<InventoryDTO> DtoToModel(List<Inventory> inventories);
}
