package com.du.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author dxy
 * @date 2019/8/22 17:30
 */
@Component
public class SpringContextUtil implements ApplicationContextAware{

	private ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;
	}

	/**
	 * 获取当前环境
	 *
	 * @return string
	 */
	public String getActiveProfile() {
		return context.getEnvironment().getActiveProfiles()[0];
	}
}
