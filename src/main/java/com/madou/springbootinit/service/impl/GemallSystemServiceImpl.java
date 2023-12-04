package com.madou.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.springbootinit.model.entity.GemallSystem;
import com.madou.springbootinit.service.GemallSystemService;
import com.madou.springbootinit.mapper.GemallSystemMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author MA_dou
* @description 针对表【gemall_system(系统配置表)】的数据库操作Service实现
* @createDate 2023-12-01 23:40:26
*/
@Service
public class GemallSystemServiceImpl extends ServiceImpl<GemallSystemMapper, GemallSystem>
    implements GemallSystemService{
    @Resource
    private GemallSystemMapper systemMapper;

    @Override
    public Map<String, String> queryAll() {

        List<GemallSystem> systemList = list();
        Map<String, String> systemConfigs = new HashMap<>();
        for (GemallSystem item : systemList) {
            systemConfigs.put(item.getKeyName(), item.getKeyValue());
        }

        return systemConfigs;
    }

    @Override
    public void addConfig(String key, String value) {
            GemallSystem system = new GemallSystem();
            system.setKeyName(key);
            system.setKeyValue(value);
            system.setAddTime(LocalDateTime.now());
            system.setUpdateTime(LocalDateTime.now());
            systemMapper.insert(system);
    }
}




