# Testify BOM Architecture

BOM 自带 groupId、artifactId、version 和发布元数据，不继承本仓库根父 POM。根父 POM 导入它，为实际子模块管理版本。
因此模型依赖单向为 parent → BOM，不产生 parent ↔ BOM 循环。

本 BOM 只显式声明 Maven Deploy 插件；通过原生属性 `spotless.skip=true`、`enforcer.skip=true` 跳过针对 BOM 的质量检查。
这些属性不通过 BOM import 传递，业务模块继续执行父 POM 配置的检查。

dependencyManagement 中的 ${project.version} 在 BOM 自身模型中解析，表示本仓库发布版本，不跟随消费工程版本。
BOM 不提供 Java 类，也不会自动引入 JAR。Testify core 的实现依赖 Framework，因此本 BOM 通过独立的
`loadup.framework.version` 导入配套 Framework BOM。Testify parent 只导入自身同版本 BOM，并保留构建配置。
消费方在 parent 中管理内部测试所需的 Testify BOM，实际工具依赖保持 `test` scope。
本次只做 POM 结构与 IDE 静态检查，未执行 Maven 构建、安装或发布。
