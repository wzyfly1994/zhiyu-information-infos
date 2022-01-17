package com.zhiyu.common;

import com.zhiyu.common.entity.dto.elasticsearch.SearchDocDto;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author wengzhiyu
 * @since 2022/1/17 15:01
 */
public class AnyTest {

    @Test
    public void testA() {
        SearchDocDto dto1 = new SearchDocDto();
        dto1.setIndex("index1");

        dto1.setPageNo(1);

        SearchDocDto dto2 = new SearchDocDto();
        dto2.setIndex("index2");

        dto2.setPageNo(2);

        ArrayList<SearchDocDto> list = new ArrayList<>(2);
        list.add(dto1);
        list.add(dto2);
        //list.sort(Comparator.comparing(SearchDocDto::getPageNo).reversed());
        list.sort(new Comparator<SearchDocDto>() {
            @Override
            public int compare(SearchDocDto o1, SearchDocDto o2) {
                // >=0 升序  <0 降序
                // 不返回0 可能抛出 java.lang.IllegalArgumentException : Comparison method violates its general contract 异常
                System.out.println("o1--->" + o1.getPageNo());
                System.out.println("o2--->" + o2.getPageNo());
                int value = o1.getPageNo().compareTo(o2.getPageNo());
                System.out.println(value);
                return value;
            }
        });
        System.out.println(list);
    }

}
