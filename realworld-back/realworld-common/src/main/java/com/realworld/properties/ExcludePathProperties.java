package com.realworld.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.List;

/**
 * @author YYJYP
 */
@Data
@ConfigurationProperties(prefix = "realworld.back.exclude.path")
public class ExcludePathProperties {
	private List<String> pathList;
}
