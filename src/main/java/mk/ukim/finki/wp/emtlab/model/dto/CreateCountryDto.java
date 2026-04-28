package mk.ukim.finki.wp.emtlab.model.dto;

import jakarta.validation.constraints.NotBlank;
import mk.ukim.finki.wp.emtlab.model.domain.Country;

public record CreateCountryDto(

        @NotBlank(message = "Country name is required")
        String name,

        @NotBlank(message = "Continent is required")
        String continent

) {
    public Country toCountry() {
        return new Country(name, continent);
    }
}