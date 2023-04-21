package com.example.cryptotradingsystem.dto;

import java.util.List;

public class HoubiPriceResponseDto {
    private List<HoubiPriceDto> data;

    public List<HoubiPriceDto> getData() {
        return data;
    }

    public void setData(List<HoubiPriceDto> data) {
        this.data = data;
    }
}
