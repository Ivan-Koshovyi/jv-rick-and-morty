package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.ApiCharacterDto;
import mate.academy.rickandmorty.dto.PersonDto;
import mate.academy.rickandmorty.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(config = MapperConfig.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring")
public interface PersonMapper {
    PersonDto toDto(Person person);

    @Mapping(source = "id", target = "externalId")
    void updatePersonFromDto(ApiCharacterDto dto,
                           @MappingTarget Person person);

    @Mapping(source = "id", target = "externalId")
    Person toModel(ApiCharacterDto apiCharacterDto);
}
