package springboot.example.annotation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.condition.RequestCondition;


/**
 * 版本控制
 * @author lich
 *
 */
public class ApiVersionCondition implements RequestCondition<ApiVersionCondition>{
	
	//路径中的版本前缀，这里用/v[1-9]/的形式
	private final static Pattern VERSION_PREFIX_PATTERN = Pattern.compile("v(\\d+)/");
	
	//api版本号
	private int apiVersion;

	public ApiVersionCondition(int apiVersion) {
		this.apiVersion = apiVersion;
	}
	
	

	/**
	 * 将不同的筛选条件进行合并
	 */
	@Override
	public ApiVersionCondition combine(ApiVersionCondition other) {
		// 采用最后定义优先原则，则方法上的定义覆盖类上面的定义
		return new ApiVersionCondition(other.getApiVersion());
	}

	/**
	 * 版本比对，用于排序
	 */
	@Override
	public int compareTo(ApiVersionCondition other, HttpServletRequest request) {
		//优先匹配最新版本号
		return other.getApiVersion() - this.apiVersion;
	}

	/**
	 * 根据request的url进行查找匹配的筛选条件
	 */
	@Override
	public ApiVersionCondition getMatchingCondition(HttpServletRequest request) {
		Matcher matcher = VERSION_PREFIX_PATTERN.matcher(request.getRequestURI());
		if(matcher.find()){
			Integer version = Integer.valueOf(matcher.group(1));
			if(version >= this.apiVersion){
				return this;
			}
		}
		return null;
	}
	
	public int getApiVersion() {
        return apiVersion;
    }


}
