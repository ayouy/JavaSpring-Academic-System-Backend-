package com.mw.education.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mw.education.dao.DeanMapper;
import com.mw.education.domain.compose.Dean;
import com.mw.education.domain.joined_entity.CollegeAndDean;
import com.mw.education.controller.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/deans")
public class DeanController {

    @Autowired
    private DeanMapper deanMapper;

    @GetMapping
    public AjaxResult selectAll(@RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                @RequestParam(name = "pageNum", defaultValue = "1") int pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<Dean> list = deanMapper.selectAll();
        PageInfo<Dean> pageInfo = new PageInfo<>(list);

        return AjaxResult.success().data(pageInfo);
    }

    @GetMapping("/{id}")
    public AjaxResult selectById(@PathVariable int id) {
        Dean dean = deanMapper.selectByPrimaryKey(id);
        if (dean != null) {
            return AjaxResult.success().data(dean);
        } else {
            return AjaxResult.error().msg("没有找到对应的管理人员信息");
        }
    }

    @GetMapping("/collegeAndDean")
    public AjaxResult getAllCollegeAndDean() {
        List<CollegeAndDean> list = deanMapper.getAllCollegeAndDean();
        return AjaxResult.success().data(list);
    }

    @GetMapping("/collegeAndDean/{id}")
    public AjaxResult getCollegeAndDeanByDeanId(@PathVariable int id) {
        CollegeAndDean collegeAndDean = deanMapper.getCollegeAndDeanByDeanId(id);
        if (collegeAndDean != null) {
            return AjaxResult.success().data(collegeAndDean);
        } else {
            return AjaxResult.error().msg("没有找到对应的管理人员与学院信息");
        }
    }

    @DeleteMapping("/{id}")
    public AjaxResult deleteById(@PathVariable int id) {
        int n = deanMapper.deleteByPrimaryKey(id);
        if (n > 0) {
            return AjaxResult.success().msg("删除成功");
        } else {
            return AjaxResult.error().msg("删除失败，未找到对应管理人员");
        }
    }

    @PutMapping
    public AjaxResult edit(@RequestBody Dean dean) {
        int n = deanMapper.updateByPrimaryKeySelective(dean);
        if (n > 0) {
            return AjaxResult.success().msg("更新成功");
        } else {
            return AjaxResult.error().msg("更新失败，未找到对应管理人员");
        }
    }

    @PostMapping
    public AjaxResult add(@RequestBody Dean dean) {
        int n = deanMapper.insertSelective(dean);
        if (n > 0) {
            return AjaxResult.success().msg("添加成功");
        } else {
            return AjaxResult.error().msg("添加失败");
        }
    }
}