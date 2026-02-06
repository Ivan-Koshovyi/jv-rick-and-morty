package mate.academy.rickandmorty.dto;

import java.util.List;

public record ApiCharacterResponseDto(
        InfoDto info,
        List<ApiCharacterDto> results
) {
}
