package com.svj.endpoint;

import com.svj.dto.ProdRelease;
import org.springframework.boot.actuate.endpoint.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Endpoint(id = "releases")
public class FeatureReleasesEndPoint {

    List<ProdRelease> prodReleases= new LinkedList<>();

    // features is in format "feature 1, feature 2"
    @WriteOperation
    public void addNewReleaseInfo(@Selector String crq,@Selector String date, String features){
        ProdRelease release = ProdRelease.builder()
                .crq(crq)
                .releaseDate(date)
                .features(Arrays.stream(features.split(",")).collect(Collectors.toList()))
                .build();
        prodReleases.add(release);
    }

    @ReadOperation
    public List<ProdRelease> getAllReleases(){
        return prodReleases;
    }

    @ReadOperation
    public ProdRelease getReleaseByCRQ(@Selector String crq){
        return prodReleases.stream().filter(prodRelease -> prodRelease.getCrq().equals(crq)).findFirst().get();
    }

    @DeleteOperation
    public void deleteReleaseByCRQ(@Selector String crq){
        prodReleases.remove(getReleaseByCRQ(crq));
    }
}
