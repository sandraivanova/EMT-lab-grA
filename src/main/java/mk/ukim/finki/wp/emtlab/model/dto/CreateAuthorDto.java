package mk.ukim.finki.wp.emtlab.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import mk.ukim.finki.wp.emtlab.model.domain.Author;
import mk.ukim.finki.wp.emtlab.model.domain.Country;

public record CreateAuthorDto(
        @NotBlank(message = "Author name is required")
        String name,

        @NotBlank(message = "Author surname is required")
        String surname,

        @NotNull(message = "Country is required")
        Long countryId

) {
    public Author toAuthor(Country country) {
        return new Author(name, surname, country);
    }

}
