/**
 * Copyright 2018 bejson.com
 */
package com.madou.springbootinit.service.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Auto-generated: 2018-07-19 22:27:22
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class Traces {

    @JsonProperty("AcceptStation")
    private String AcceptStation;
    @JsonProperty("AcceptTime")
    private String AcceptTime;


}
