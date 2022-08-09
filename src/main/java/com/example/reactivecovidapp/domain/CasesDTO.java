package com.example.reactivecovidapp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CasesDTO{

        private long id;
        private String country;
        private Integer totalCases;
}
