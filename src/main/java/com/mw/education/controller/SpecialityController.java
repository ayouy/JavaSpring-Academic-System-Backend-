package com.mw.education.controller;

import com.github.pagehelper.PageInfo;
import com.mw.education.domain.compose.Speciality;
import com.mw.education.domain.joined_entity.SpecialityAndCollege;
import com.mw.education.service.SpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/specialities")
public class SpecialityController {
    @Autowired
    private SpecialityService specialityService;

    @GetMapping
    public AjaxResult selectAllJoinedCollege(@RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                             @RequestParam(name = "pageNum", defaultValue = "1") int pageNum) {
        PageInfo<SpecialityAndCollege> pageInfo = specialityService.selectAllJoinedCollege(pageSize, pageNum);
        return AjaxResult.success().data(pageInfo);
    }

    @GetMapping("/{id}")
    public AjaxResult selectByIdJoinedCollege(@PathVariable int id) {
        SpecialityAndCollege item = specialityService.selectByIdJoinedCollege(id);
        return AjaxResult.success().data(item);
    }

    @DeleteMapping("/{id}")
    public AjaxResult deleteByPrimaryKey(@PathVariable int id) {
        boolean result = specialityService.deleteByPrimaryKey(id);
        if (result) {
            return AjaxResult.success().msg("删除成功");
        } else {
            return AjaxResult.error().msg("删除失败，未找到对应专业");
        }
    }

    @PostMapping
    public AjaxResult insert(@RequestBody Speciality speciality) {
        boolean result = specialityService.insert(speciality);
        if (result) {
            return AjaxResult.success().msg("添加成功");
        } else {
            return AjaxResult.error().msg("添加失败");
        }
    }

    @PutMapping
    public AjaxResult updateByPrimaryKey(@RequestBody Speciality speciality) {
        boolean result = specialityService.updateByPrimaryKey(speciality);
        if (result) {
            return AjaxResult.success().msg("更新成功");
        } else {
            return AjaxResult.error().msg("更新失败，未找到对应专业");
        }
    }

    @GetMapping("/all")
    public AjaxResult selectAll() {
        List<Speciality> list = specialityService.selectAll();
        return AjaxResult.success().data(list);
    }

    @GetMapping("/specialities/{id}")
    public AjaxResult selectByPrimaryKey(@PathVariable int id) {
        Speciality speciality = specialityService.selectByPrimaryKey(id);
        return AjaxResult.success().data(speciality);
    }
}