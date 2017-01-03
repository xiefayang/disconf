package com.baidu.disconf.client.core.filetype.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import com.baidu.disconf.client.config.DisClientConfig;
import com.baidu.disconf.client.core.filetype.DisconfFileTypeProcessor;
import com.baidu.disconf.client.support.utils.ConfigLoaderUtils;

/**
 * Properties 处理器
 *
 * @author knightliao
 */
public class DisconfPropertiesProcessorImpl implements DisconfFileTypeProcessor {

    @Override
    public Map<String, Object> getKvMap(String fileName) throws Exception {

        Properties properties;

        // 读取配置
        properties = ConfigLoaderUtils.loadConfig(fileName);
        if (properties == null) {
            return null;
        }

        Map<String, Object> map = new HashMap<String, Object>();
        for (Object object : properties.keySet()) {

            String key = String.valueOf(object);
            Object value = properties.get(object);

            map.put(key, value);
        }
        //取自己机器的节点
        String node=DisClientConfig.getInstance().NODE;
        if(null!=node&&!"".equals(node)){
	        Map<String,Object> rtnMap=new HashMap<String,Object>();
	        Map<String,Object> highMap=new HashMap<String,Object>();
	        for(Entry<String, Object> entry:map.entrySet()){
	        	//自己机器的配置优先
	        	if(entry.getKey().endsWith("."+node)){
	        		highMap.put(entry.getKey().replace("."+node, ""), entry.getValue());
	        	}else{
	        		rtnMap.put(entry.getKey(), entry.getValue());
	        	}
	        }
	        //将自己的覆盖公用的……
	        rtnMap.putAll(highMap);
	        map.putAll(rtnMap);
        }
        return map;
    }
}
