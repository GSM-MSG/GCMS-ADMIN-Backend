package com.example.msgadminapi.controller;

import com.example.msgadminapi.dto.request.ClubTitleModifyRequest;
import com.example.msgadminapi.dto.response.ClubResponseDto;
import com.example.msgadminapi.response.ResponseService;
import com.example.msgadminapi.response.result.CommonResultResponse;
import com.example.msgadminapi.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/club")
@RequiredArgsConstructor
public class ClubController {

    private final ClubService clubService;
    private final ResponseService responseService;

    @GetMapping
    public List<ClubResponseDto> clubFindAll() {
        return clubService.findAll();
    }

    @GetMapping("/search")
    public List<ClubResponseDto> search(@RequestParam("title") String title) {
        return clubService.search(title);
    }

    @PutMapping("/title")
    public CommonResultResponse titleModify(@RequestParam ClubTitleModifyRequest request) {
        clubService.clubTitleModify(request);
        return responseService.getSuccessResult();
    }

    @DeleteMapping("{clubIdx}")
    public CommonResultResponse clubDelete(@PathVariable String clubIdx) throws Exception {
        clubService.clubDelete(clubIdx);
        return responseService.getSuccessResult();
    }
}