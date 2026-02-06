package mate.academy.rickandmorty.dto;

public record InfoDto(
        int count,
        int pages,
        String next,
        String prev
) {
}
