package com.mowagdy.retailstore.infrastructure.controller;

import com.mowagdy.retailstore.core.dto.BillCreationRequest;
import com.mowagdy.retailstore.core.dto.BillCreationResponse;
import com.mowagdy.retailstore.core.dto.BillFetchingResponse;
import com.mowagdy.retailstore.core.dto.RequestWithLongId;
import com.mowagdy.retailstore.infrastructure.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/bills")
public class BillController {

    @Autowired
    private BillService billService;

    @GetMapping("/{id}")
    public @ResponseBody
    BillFetchingResponse getSingleBill(@PathVariable("id") long id) {
        return billService.getBill(new RequestWithLongId(id));
    }

    @PostMapping("")
    public @ResponseBody
    BillCreationResponse addBill(@RequestBody(required = true) BillCreationRequest request) {
        return billService.addBill(request);
    }

}
