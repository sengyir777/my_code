package com.cr.basic.code.pcr.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author chengri.piao
 * @Title:
 * @Package
 * @Description:
 * @date 2020/8/17 14:53
 */
@Accessors(chain = true)
@Data
public class User {

    private String id;

    private String name;
}
