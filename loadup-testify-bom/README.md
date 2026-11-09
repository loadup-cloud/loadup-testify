# LoadUp Testify BOM

可独立安装、发布和导入的纯 POM 制品，当前管理 2 个对外发布的 JAR。它与代码位于同一 Git 仓库，版本跟随本仓库发布。

```xml
<dependency>
    <groupId>io.github.loadup-cloud</groupId>
    <artifactId>loadup-testify-bom</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <type>pom</type>
    <scope>import</scope>
</dependency>
```

将上面的 dependency 放入消费工程的 dependencyManagement，而不是普通 dependencies。
本 BOM 不导入 framework 或其他产品 BOM；消费方直接使用其他产品的无版本坐标时，另外导入其 BOM。

在本仓库根目录，仅安装 BOM：

```bash
mvnd -f loadup-testify-bom/pom.xml clean install
```

该命令不编译 Java。正式消费还需要安装或发布同版本实际 JAR。上传本仓库完整 reactor 时，使用已配置凭证和显式部署目标
`-DaltDeploymentRepository=github::https://maven.pkg.github.com/loadup-cloud/loadup-packages`；此处只说明配置，未执行安装、发布或构建。

新增或移除发布 JAR 时同步维护本 POM；内部测试 JAR 和聚合 POM 不进入对外版本清单。
