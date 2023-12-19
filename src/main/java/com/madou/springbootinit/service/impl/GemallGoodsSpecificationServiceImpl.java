package com.madou.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.springbootinit.model.entity.GemallGoodsSpecification;
import com.madou.springbootinit.service.GemallGoodsSpecificationService;
import com.madou.springbootinit.mapper.GemallGoodsSpecificationMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author MA_dou
 * @description 针对表【gemall_goods_specification(商品规格表)】的数据库操作Service实现
 * @createDate 2023-12-01 23:40:26
 */
@Service
public class GemallGoodsSpecificationServiceImpl extends ServiceImpl<GemallGoodsSpecificationMapper, GemallGoodsSpecification>
        implements GemallGoodsSpecificationService {

    @Override
    public Object getSpecificationVoList(Integer id) {

        List<GemallGoodsSpecification> goodsSpecificationList = queryByGid(id);

        Map<String, VO> map = new HashMap<>();
        List<VO> specificationVoList = new ArrayList<>();

        for (GemallGoodsSpecification goodsSpecification : goodsSpecificationList) {
            String specification = goodsSpecification.getSpecification();
            VO goodsSpecificationVo = map.get(specification);
            if (goodsSpecificationVo == null) {
                goodsSpecificationVo = new VO();
                goodsSpecificationVo.setName(specification);
                List<GemallGoodsSpecification> valueList = new ArrayList<>();
                valueList.add(goodsSpecification);
                goodsSpecificationVo.setValueList(valueList);
                map.put(specification, goodsSpecificationVo);
                specificationVoList.add(goodsSpecificationVo);
            } else {
                List<GemallGoodsSpecification> valueList = goodsSpecificationVo.getValueList();
                valueList.add(goodsSpecification);
            }
        }

        return specificationVoList;
    }

    public List<GemallGoodsSpecification> queryByGid(Integer id) {
        QueryWrapper<GemallGoodsSpecification> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("goods_id", id);
        return this.list(queryWrapper);
    }

    private class VO {
        private String name;
        private List<GemallGoodsSpecification> valueList;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<GemallGoodsSpecification> getValueList() {
            return valueList;
        }

        public void setValueList(List<GemallGoodsSpecification> valueList) {
            this.valueList = valueList;
        }
    }
}




