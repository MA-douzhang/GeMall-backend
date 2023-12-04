package com.madou.springbootinit.service;

import com.madou.springbootinit.model.entity.GemallSystem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author MA_dou
* @description 针对表【gemall_system(系统配置表)】的数据库操作Service
* @createDate 2023-12-01 23:40:26
*/
public interface GemallSystemService extends IService<GemallSystem> {

    /**
     * 读取所以配置
     * @return
     */
    Map<String, String> queryAll();

    /**
     * 添加配置
     * @param key
     * @param value
     */
    void addConfig(String key, String value);
}
