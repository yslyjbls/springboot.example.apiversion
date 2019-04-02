package springboot.example.annotation;

import java.lang.reflect.Method;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * 自定义匹配处理器
 * @author lich
 *
 */
public class CustomRequestMappingHandlerMapping extends RequestMappingHandlerMapping{

	@Override
	protected RequestCondition<?> getCustomTypeCondition(Class<?> handlerType) {
		ApiVersion apiVersion = AnnotationUtils.findAnnotation(handlerType, ApiVersion.class);
		return createCondition(apiVersion);
	}
	
	@Override
	protected RequestCondition<?> getCustomMethodCondition(Method method) {
		ApiVersion apiVersion = AnnotationUtils.findAnnotation(method, ApiVersion.class);
		return createCondition(apiVersion);
	}
	
	private RequestCondition<ApiVersionCondition> createCondition(ApiVersion apiVersion) {
        return apiVersion == null ? null : new ApiVersionCondition(apiVersion.value());
    }
	
}
