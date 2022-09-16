package com.svj.controller;

import com.svj.dto.DownTime;
import com.svj.dto.ProdRelease;
import org.springframework.boot.actuate.endpoint.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/downtime")
public class DownTimeEndPoint {

    List<DownTime> downTimes= new LinkedList<>();

    // issues is in format "issue 1, issue 2"
    @PostMapping("/{date}/{cost}")
    public void addNewReleaseInfo(@PathVariable String date, @PathVariable String cost, @RequestBody List<String> issuesList){
        DownTime downTime = DownTime.builder()
                .cost(cost)
                .downDate(date)
                .issues(issuesList)
                .build();
        downTimes.add(downTime);
    }

    @GetMapping
    public List<DownTime> getAllDownTimes(){
        return downTimes;
    }

    @GetMapping("/{date}")
    public DownTime getReleaseByDate(@PathVariable String date){
        return downTimes.stream().filter(downTime -> downTime.getDownDate().equals(date)).findFirst().get();
    }

    @DeleteMapping("/{date}")
    public void deleteReleaseByCRQ(@PathVariable String date){
        downTimes.remove(getReleaseByDate(date));
    }
}
