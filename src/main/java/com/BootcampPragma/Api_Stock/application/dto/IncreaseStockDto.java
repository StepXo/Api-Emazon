package com.BootcampPragma.Api_Stock.application.dto;

import lombok.Data;

@Data
public class IncreaseStockDto {
    private long articleId;
    private int quantity;
}
