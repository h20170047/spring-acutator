package com.svj.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdRelease {
    private String crq; // change request number
    private String releaseDate;
    private List<String> features;
}
