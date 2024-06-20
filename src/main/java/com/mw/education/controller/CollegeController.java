package com.mw.education.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mw.education.dao.CollegeMapper;
import com.mw.education.domain.compose.College;
import com.mw.education.domain.joined_entity.CollegeAndDean;
import com.mw.education.domain.joined_entity.SpecialityAndCollege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/colleges")
public class CollegeController {

    @Autowired
    private CollegeMapper collegeMapper;

    @GetMapping
    public AjaxResult selectAll(@RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                @RequestParam(name = "pageNum", defaultValue = "1") int pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<College> list = collegeMapper.selectAll();
        PageInfo<College> pageInfo = new PageInfo<>(list);

        return AjaxResult.success().data(pageInfo);
    }

    @GetMapping("/{id}")
    public AjaxResult selectById(@PathVariable int id) {
        College college = collegeMapper.selectByPrimaryKey(id);
        if (college != null) {
            return AjaxResult.success().data(college);
        } else {
            return AjaxResult.error().msg("未找到对应的学院信息");
        }
    }
    @GetMapping("/allCollegeAndDean")
    public AjaxResult getAllCollegeAndDean() {
        List<CollegeAndDean> list = collegeMapper.getAllCollegeAndDean();
        if (list != null && !list.isEmpty()) {
            return AjaxResult.success().data(list);
        } else {
            return AjaxResult.error().msg("未找到对应的学院及管理人员信息");
        }
    }
    @GetMapping("/collegeAndDean/{id}")
    public AjaxResult getCollegeAndDeanByCollegeId(@PathVariable int id) {
        List<CollegeAndDean> collegeAndDean = collegeMapper.getCollegeAndDeanByCollegeId(id);
        if (collegeAndDean != null) {
            return AjaxResult.success().data(collegeAndDean);
        } else {
            return AjaxResult.error().msg("未找到对应的学院及院长信息");
        }
    }

    @GetMapping("/allSpecialityAndCollege")
    public AjaxResult getAllSpecialityAndCollege() {
        List<SpecialityAndCollege> list = collegeMapper.getAllSpecialityAndCollege();
        if (list != null && !list.isEmpty()) {
            return AjaxResult.success().data(list);
        } else {
            return AjaxResult.error().msg("未找到对应的专业及学院信息");
        }
    }

    @GetMapping("/specialityAndCollege/{id}")
    public AjaxResult getSpecialityAndCollegeById(@PathVariable int id) {
        List<SpecialityAndCollege> list = collegeMapper.getSpecialityAndCollegeById(id);
        if (list != null && !list.isEmpty()) {
            return AjaxResult.success().data(list);
        } else {
            return AjaxResult.error().msg("未找到对应的专业及学院信息");
        }
    }

    @DeleteMapping("/{id}")
    public AjaxResult deleteById(@PathVariable int id) {
        int result = collegeMapper.deleteByPrimaryKey(id);
        if (result > 0) {
            return AjaxResult.success().msg("删除成功");
        } else {
            return AjaxResult.error().msg("删除失败，未找到对应学院");
        }
    }

    @PostMapping
    public AjaxResult add(@RequestBody College college) {
        int result = collegeMapper.insertSelective(college);
        if (result > 0) {
            return AjaxResult.success().msg("添加成功");
        } else {
            return AjaxResult.error().msg("添加失败");
        }
    }

    @PutMapping
    public AjaxResult edit(@RequestBody College college) {
        int result = collegeMapper.updateByPrimaryKeySelective(college);
        if (result > 0) {
            return AjaxResult.success().msg("更新成功");
        } else {
            return AjaxResult.error().msg("更新失败，未找到对应学院");
        }
    }

}