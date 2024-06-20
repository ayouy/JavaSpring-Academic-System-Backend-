package com.mw.education.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mw.education.dao.TermMapper;
import com.mw.education.domain.compose.Term;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/terms")
public class TermController {

    @Autowired
    private TermMapper termMapper;

    private Date startDate;

    private Date endDate;

    @GetMapping
    public AjaxResult selectAll(@RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                @RequestParam(name = "pageNum", defaultValue = "1") int pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<Term> list = termMapper.selectAll();
        PageInfo<Term> pageInfo = new PageInfo<>(list);

        return AjaxResult.success().data(pageInfo);
    }

    @GetMapping("/{id}")
    public AjaxResult selectById(@PathVariable int id) {
        Term term = termMapper.selectByPrimaryKey(id);
        if (term != null) {
            return AjaxResult.success().data(term);
        } else {
            return AjaxResult.error().msg("没有找到对应的学期信息");
        }
    }

    @DeleteMapping("/{id}")
    public AjaxResult deleteById(@PathVariable int id) {
        int n = termMapper.deleteByPrimaryKey(id);
        if (n > 0) {
            return AjaxResult.success().msg("删除成功");
        } else {
            return AjaxResult.error().msg("删除失败，未找到对应学期");
        }
    }

    @PutMapping
    public AjaxResult edit(@RequestBody Term term) {
        int n = termMapper.updateByPrimaryKeySelective(term);
        if (n > 0) {
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
        int n = termMapper.insertSelective(term);
        if (n > 0) {
            return AjaxResult.success().msg("添加成功");
        } else {
            return AjaxResult.error().msg("添加失败");
        }
    }

    private List<Term> selectAll() {

        return null;
    }
}