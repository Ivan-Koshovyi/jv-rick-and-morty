package mate.academy.rickandmorty.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PersonDto {
    private Long id;
    private Long externalId;
    private String name;
    private String status;
    private String gender;
}
