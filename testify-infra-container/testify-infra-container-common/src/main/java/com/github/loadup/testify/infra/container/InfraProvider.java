package com.github.loadup.testify.infra.container;

import java.util.Map;

public interface InfraProvider {
  /** 判断当前 Provider 支持的服务类型 (mysql, kafka, etc.) */
  String getType();

  /** 启动容器实例 */
  void start(Map<String, Object> config, boolean reuse);

  /** 获取容器启动后的连接属性映射 (如 spring.datasource.url -> jdbc:mysql://...) */
  Map<String, String> getExposedProperties();

  /** 停止容器 */
  void stop();
}
