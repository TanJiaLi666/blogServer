package com.tanjiali.front.config;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

/**
 * @author tanjiali
 * @version 1.0
 * @data 2022/9/20 16:42
 */
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 新增数据执行
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        setValue("createTime", DateUtil.date(), metaObject);
        setValue("updateTime", DateUtil.date(), metaObject);
    }

    /**
     * 更新数据执行
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        setValue("updateTime", DateUtil.date(), metaObject);
        setValue("lastTime", DateUtil.date(), metaObject);
    }

    private void setValue(String fieldName, Object value, MetaObject metaObject) {
        Object field = getFieldValByName(fieldName, metaObject);
        if (field == null && value != null) {
            setFieldValByName(fieldName, value, metaObject);
        }
    }
}
