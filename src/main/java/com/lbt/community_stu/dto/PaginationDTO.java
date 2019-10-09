package com.lbt.community_stu.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lbt on 2019/10/9 14:20
 */
@Data
public class PaginationDTO<T> {
    private List<T> data;
    private boolean showPre;//上一页
    private boolean showFirst;//首页
    private boolean showNext;//下一页
    private boolean showEnd;//尾页
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;

    public void setPagination(Integer totalPage,Integer page){
        this.totalPage = totalPage;
        this.page = page;
        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0){
                pages.add(0,page - i);
            }
            if (page + i <= totalPage){
                pages.add(page + i);
            }
        }
        //是否显示上一页
        if (page == 1){
            showPre = false;
        } else {
            showPre = true;
        }
        //是否显示下一页
        if (page == totalPage) {
            showNext = false;
        } else {
            showNext = true;
        }

        //是否显示第一页
        if (pages.contains(1)){
            showFirst = false;
        } else {
            showFirst = true;
        }

        //显示尾页
        if (pages.contains(totalPage)){
            showEnd = false;
        } else {
            showEnd = true;
        }
    }
}
