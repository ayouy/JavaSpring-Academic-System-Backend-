package com.mw.education.controller;

import com.github.pagehelper.PageInfo;
import com.mw.education.domain.compose.Term;
import com.mw.education.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/terms")
public class TermController {

    @Autowired
    private TermService termService;

    @GetMapping
    public AjaxResult selectAll(@RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                @RequestParam(name = "pageNum", defaultValue = "1") int pageNum) {
        PageInfo<Term> pageInfo = termService.selectAll(pageNum, pageSize);
        return AjaxResult.success().data(pageInfo);
    }

    @GetMapping("/{id}")
    public AjaxResult selectById(@PathVariable int id) {
        Term term = termService.selectById(id);
        if (term != null) {
            return AjaxResult.success().data(term);
        } else {
            return AjaxResult.error().msg("没有找到对应的学期信息");
        }
    }

    @DeleteMapping("/{id}")
    public AjaxResult deleteById(@PathVariable int id) {
        boolean result = termService.deleteById(id);
        if (result) {
            return AjaxResult.success().msg("删除成功");
        } else {
            return AjaxResult.error().msg("删除失败，未找到对应学期");
        }
    }

    @PutMapping
    public AjaxResult edit(@Valid @RequestBody Term term, BindingResult result) {
        if (result.hasErrors()) {
            String errorMsg = result.getAllErrors().get(0).getDefaultMessage();
            return AjaxResult.error().msg(errorMsg);
        }
        boolean isSuccess = termService.edit(term);
        if (isSuccess) {
            return AjaxResult.success().msg("更新成功");
        } else {
            return AjaxResult.error().msg("更新失败，未找到对应学期");
        }
    }

    @PostMapping
    public AjaxResult add(@Valid @RequestBody Term term, BindingResult result) {
        if (result.hasErrors()) {
            String errorMsg = result.getAllErrors().get(0).getDefaultMessage();
            return AjaxResult.error().msg(errorMsg);
        }
        boolean isSuccess = termService.add(term);
        if (isSuccess) {
            return AjaxResult.success().msg("添加成功");
        } else {
            return AjaxResult.error().msg("添加失败");
        }
    }
}