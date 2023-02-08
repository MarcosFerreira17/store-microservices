package com.mnia.orderservice.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Meta {
    private Integer actualPage;
    private Integer sizePage;
    private Integer totalPages;
    private Long totalRecords;
}
