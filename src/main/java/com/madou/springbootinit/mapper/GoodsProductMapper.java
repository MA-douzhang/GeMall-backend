package com.madou.springbootinit.mapper;

import org.apache.ibatis.annotations.Param;

public interface GoodsProductMapper {
    int addStock(@Param("id") Integer id, @Param("num") Integer num);
    int reduceStock(@Param("id") Integer id, @Param("num") Integer num);
}
