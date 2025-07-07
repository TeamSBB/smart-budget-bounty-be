package com.smartbudgetbounty.dto.generic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class SortRequest {
    @NotBlank
    String field;
    @Pattern(regexp = "asc|desc")
    String direction;

    public SortRequest(@NotBlank String field,
                       @Pattern(regexp = "asc|desc") String direction) {
        super();
        this.field = field;
        this.direction = direction;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }


}