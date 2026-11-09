# Testify BOM Architecture

BOM 自带 groupId、artifactId、version 和发布元数据，不继承本仓库根父 POM。根父 POM 导入它，为实际子模块管理版本。
因此模型依赖单向为 parent → BOM，不产生 parent ↔ BOM 循环。

dependencyManagement 中的 ${project.version} 在 BOM 自身模型中解析，表示本仓库发布版本，不跟随消费工程版本。
BOM 不提供 Java 类、不引入运行时 JAR、不管理其他仓库的坐标。第三方与框架版本由消费方自己的父 POM或 framework BOM 管理。
本次只做 POM 结构与 IDE 静态检查，未执行 Maven 构建、安装或发布。
