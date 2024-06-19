package com.mw.education.controller;

import com.github.pagehelper.PageHelper;
import com.mw.education.dao.SpecialityMapper;
import com.mw.education.domain.joined_entity.SpecialityAndCollege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SpecialityController {
    @Autowired
    private SpecialityMapper specialityMapper;
    @GetMapping("/specialities")
    public AjaxResult selectAllJoinedCollege(@RequestParam(name = "pageSize", defaultValue = "10" ) int pageSize,
                                             @RequestParam(name = "pageNum", defaultValue = "1") int pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<SpecialityAndCollege> list = specialityMapper.getAllSpecialityAndCollege();
        return AjaxResult.success().rows(list.size(), list);
    }

    @GetMapping("/specialities/{id}")
    public AjaxResult selectByIdJoinedCollege(@PathVariable int id) {
        SpecialityAndCollege item = specialityMapper.getSpecialityAndCollegeBySpecialityId(id);
        return AjaxResult.success().data(item);
    }

}