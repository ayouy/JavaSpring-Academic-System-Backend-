package com.mw.education.controller;

import com.github.pagehelper.PageInfo;
import com.mw.education.domain.compose.Dean;
import com.mw.education.domain.joined_entity.CollegeAndDean;
import com.mw.education.service.DeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/deans")
public class DeanController {

    @Autowired
    private DeanService deanService;

    @GetMapping
    public AjaxResult selectAll(@RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                @RequestParam(name = "pageNum", defaultValue = "1") int pageNum) {
        PageInfo<Dean> pageInfo = deanService.selectAll(pageSize, pageNum);
        return AjaxResult.success().data(pageInfo);
    }

    @GetMapping("/{id}")
    public AjaxResult selectById(@PathVariable int id) {
        Dean dean = deanService.selectById(id);
        if (dean != null) {
            return AjaxResult.success().data(dean);
        } else {
            return AjaxResult.error().msg("没有找到对应的管理人员信息");
        }
    }

    @GetMapping("/collegeAndDean")
    public AjaxResult getAllCollegeAndDean() {
        List<CollegeAndDean> list = deanService.getAllCollegeAndDean();
        List<Map<String, Object>> result = new ArrayList<>();
        for (CollegeAndDean item : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("college", item.getCollege());
            map.put("dean", item.getDean());
            result.add(map);
        }
        return AjaxResult.success().data(result);
    }

    @GetMapping("/collegeAndDean/{id}")
    public AjaxResult getCollegeAndDeanByDeanId(@PathVariable int id) {
        CollegeAndDean collegeAndDean = deanService.getCollegeAndDeanByDeanId(id);
        if (collegeAndDean != null) {
            return AjaxResult.success().data(collegeAndDean);
        } else {
            return AjaxResult.error().msg("没有找到对应的管理人员与学院信息");
        }
    }

    @DeleteMapping("/{id}")
    public AjaxResult deleteById(@PathVariable int id) {
        boolean isSuccess = deanService.deleteById(id);
        if (isSuccess) {
            return AjaxResult.success().msg("删除成功");
        } else {
            return AjaxResult.error().msg("删除失败，未找到对应管理人员");
        }
    }

    @PutMapping
    public AjaxResult edit(@RequestBody Dean dean) {
        boolean isSuccess = deanService.edit(dean);
        if (isSuccess) {
            return AjaxResult.success().msg("更新成功");
        } else {
            return AjaxResult.error().msg("更新失败，未找到对应管理人员");
        }
    }

    @PostMapping
    public AjaxResult add(@RequestBody Dean dean) {
        boolean isSuccess = deanService.add(dean);
        if (isSuccess) {
            return AjaxResult.success().msg("添加成功");
        } else {
            return AjaxResult.error().msg("添加失败");
        }
    }
}