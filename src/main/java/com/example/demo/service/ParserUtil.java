package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ParserUtil {
	private static final Logger log = (Logger) LoggerFactory.getLogger(ParserUtil.class);
	
	public long parseId(Object idObject) {
		return parseObject(idObject, Long.class);
	}
	
	public String parseName(Object idObject) {
		return String.valueOf(idObject);
	}
	public int parseAge(Object idObject) {
		return parseObject(idObject, Integer.class);
	}
	
	private <T> T parseObject(Object obj, Class<T> clazz) {
		if(clazz.isInstance(obj)) {
			return clazz.cast(obj);
		} else if (obj instanceof String) {
			try {
				if(clazz == Long.class) {
					return clazz.cast(Long.parseLong((String) obj));
				}else if(clazz == Integer.class) {
					return clazz.cast(Integer.parseInt((String)obj));
				}
			}catch(NumberFormatException e){
				log.error("Failed to parse value:{} to type:{}. Error: {}",obj, clazz.getName(), e.getMessage());
				throw new IllegalArgumentException(clazz.getName() + ": " + obj, e);
			}
		}
		throw new IllegalArgumentException(obj.getClass().getName());
	}
}
