package com.shz.foundation.service.dynamic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import com.shz.foundation.utils.Collections3;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

/**
 * 将map参数转换成spring data 框架使用的排序参数
 * @author pc
 *
 */
public class SortFilter {
	
	/**
	 * 转换为Order数组
	 * @param sortParams
	 * @return
	 */
	public static List<Order> parse(Map<String, Object> sortParams) {
		List<Order> orders=new ArrayList<Order>();
		for (Entry<String, Object> entry : sortParams.entrySet()) {
			// 过滤掉空值
			String key = entry.getKey();
			String value = (String)entry.getValue();
			if (StringUtils.isBlank(value)) {
				continue;
			}
			Direction direction=Direction.fromString(value);
			Order order=new Order(direction, key);

			orders.add(order);
		}
		return orders;
	}
	
	/**
	 * 生成page查询条件
	 * @param page
	 * @param size
	 * @param sortParams
	 * @return
	 */
	public static Pageable toPageable(int page, int size, Map<String, Object> sortParams) {
		List<Order> orders=parse(sortParams);
		PageRequest request;
		if(Collections3.isEmpty(orders)) {
			request=new PageRequest(page-1, size);
		} else {
			Sort sort=new Sort(orders);
			request=new PageRequest(page-1, size, sort);
		}		
		return request;
	}
}
