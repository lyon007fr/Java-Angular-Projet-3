package com.openclassrooms.ApiRentals.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RentalsDTO {

    private Long id;
    private String name;
    private Long surface;
    private Integer price;
    private String picture;
    private String description;
    private Long owner_id;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime created_at;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime updated_at;
}
