package com.mw.education.controller;

import com.github.pagehelper.PageInfo;
import com.mw.education.domain.compose.College;
import com.mw.education.domain.joined_entity.CollegeAndDean;
import com.mw.education.domain.joined_entity.SpecialityAndCollege;
import com.mw.education.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/colleges")
public class CollegeController {

    @Autowired
    private CollegeService collegeService;  // 使用CollegeService

    @GetMapping
    public AjaxResult selectAll(@RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                @RequestParam(name = "pageNum", defaultValue = "1") int pageNum) {
        PageInfo<College> pageInfo = collegeService.selectAll(pageSize, pageNum);
        return AjaxResult.success().data(pageInfo);
    }

    @GetMapping("/{id}")
    public AjaxResult selectById(@PathVariable int id) {
        College college = collegeService.selectById(id);
        if (college != null) {
            return AjaxResult.success().data(college);
        } else {
            return AjaxResult.error().msg("未找到对应的学院信息");
        }
    }

    @GetMapping("/allCollegeAndDean")
    public AjaxResult getAllCollegeAndDean() {
        List<CollegeAndDean> list = collegeService.getAllCollegeAndDean();
        if (list != null && !list.isEmpty()) {
            return AjaxResult.success().data(list);
        } else {
            return AjaxResult.error().msg("未找到对应的学院及管理人员信息");
        }
    }

    @GetMapping("/collegeAndDean/{id}")
    public AjaxResult getCollegeAndDeanByCollegeId(@PathVariable int id) {
        List<CollegeAndDean> collegeAndDean = collegeService.getCollegeAndDeanByCollegeId(id);
        if (collegeAndDean != null) {
            return AjaxResult.success().data(collegeAndDean);
        } else {
            return AjaxResult.error().msg("未找到对应的学院及院长信息");
        }
    }

    @GetMapping("/allSpecialityAndCollege")
    public AjaxResult getAllSpecialityAndCollege() {
        List<SpecialityAndCollege> list = collegeService.getAllSpecialityAndCollege();
        if (list != null && !list.isEmpty()) {
            return AjaxResult.success().data(list);
        } else {
            return AjaxResult.error().msg("未找到对应的专业及学院信息");
        }
    }

    @GetMapping("/specialityAndCollege/{id}")
    public AjaxResult getSpecialityAndCollegeById(@PathVariable int id) {
        List<SpecialityAndCollege> list = collegeService.getSpecialityAndCollegeById(id);
        if (list != null && !list.isEmpty()) {
            return AjaxResult.success().data(list);
        } else {
            return AjaxResult.error().msg("未找到对应的专业及学院信息");
        }
    }

    @DeleteMapping("/{id}")
    public AjaxResult deleteById(@PathVariable int id) {
        boolean result = collegeService.deleteById(id);
        if (result) {
            return AjaxResult.success().msg("删除成功");
        } else {
            return AjaxResult.error().msg("删除失败，未找到对应学院");
        }
    }

    @PostMapping
    public AjaxResult add(@RequestBody College college) {
        boolean result = collegeService.add(college);
        if (result) {
            return AjaxResult.success().msg("添加成功");
        } else {
            return AjaxResult.error().msg("添加失败");
        }
    }

    @PutMapping
    public AjaxResult edit(@RequestBody College college) {
        boolean result = collegeService.edit(college);
        if (result) {
            return AjaxResult.success().msg("更新成功");
        } else {
            return AjaxResult.error().msg("更新失败，未找到对应学院");
        }
    }

}