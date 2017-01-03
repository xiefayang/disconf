/*
* @author xiangsl   
* @date 2016年12月27日 下午10:47:48 
* @Description: 此处添加文件描述……
*/
package com.baidu.disconf.client.core.processor.impl;

import com.baidu.disconf.client.config.DisClientConfig;

/**
 * @Package com.baidu.disconf.client.core.processor.impl
 * @ClassName NodeUtils.java
 * @author xiangsl
 * @date 2016年12月27日 下午10:47:48
 * @Description: 此处添加类描述……
 * @version V1.0
 */
public class NodeUtils {
	public static String getNode(){
		String node = DisClientConfig.getInstance().NODE;
		if(null==node){
			return node;
		}else{
			return node.trim();
		}
	}
	public static String getNodeUrl(String url){
        int start=url.indexOf("key=");
        int end=url.indexOf("&", start);
        String code=url.substring(start, end);
        String newCode=NodeUtils.getNode();
        if(code==newCode){
        	return url;
        }
        code=code+"."+NodeUtils.getNode();
        String tempUrl=url.substring(0,start)+code+url.substring(end,url.length());
        return tempUrl;
	}
	/**
	 * 
	 * Description:取得大众化的key……
	 * @param keyName
	 * @return
	 */
	public static String getComKey(String keyName) {
		if (null == keyName) {
			return keyName;
		}
		String node = DisClientConfig.getInstance().NODE;
		if (null != node && !"".equals(node)) {
			if (keyName.endsWith("." + node)) {
				keyName = keyName.replace("." + node, "");
			}
		}
		return keyName;
	}
	/**
	 * 
	 * Description:取得特有节点的key……
	 * @param keyName
	 * @return
	 */
	public static String getOwnKey(String keyName) {
		if (null == keyName) {
			return keyName;
		}
		String node = DisClientConfig.getInstance().NODE;
		if (null != node && !"".equals(node)) {
			if (!keyName.endsWith("." + node)) {
				keyName=keyName+"."+node;
			}
		}
		return keyName;
	}

	public static boolean ifOwnKey(String keyName) {
		if (null == keyName) {
			return false;
		}
		String node = DisClientConfig.getInstance().NODE;
		if (null != node && !"".equals(node)) {
			if (keyName.endsWith("." + node)) {
				return true;
			}
		}
		return false;
	}
}
