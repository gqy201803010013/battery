


---

# battery 

这是一个基于前后端分离的项目，前端使用 Vue.js，后端基于 Spring Boot 开发，后端项目由 Maven 进行管理。

## 项目结构

```
- front/    // 前端项目目录 其余为后端项目
```

### 后端部分

#### 环境要求

- JDK 1.8
- Maven 3.6+

#### 后端项目启动步骤

1. 使用  IntelliJ IDEA 打开根目录下pom文件 。

2. 确保 Maven 依赖已安装并且可以成功构建项目：

   ```bash
   mvn clean install（不一定能成功）
   ```

3. 找到service目录下， `batteryApplication` 文件。

4. 运行 `batteryApplication` 文件，启动后端服务。

5. 后端启动后，默认监听 `http://localhost:8081`（或根据配置文件中的端口进行访问）。

### 数据库配置

后端项目的数据库连接配置在 `application.properties` 文件中。请根据需要配置数据库连接。

```application.properties
  # mysql数据库连接
  spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
  spring.datasource.url=jdbc:mysql://localhost:3306/bettery?serverTimezone=GMT%2B8
  spring.datasource.username=your name
  spring.datasource.password=your password
```

### API文档

API 文档可以通过后端启动后访问 `http://localhost:8081/swagger-ui.html` 查看。

### 前端部分

#### 环境要求

- Node.js: `10.24.1`
- npm: `6.14.12`

#### 前端项目启动步骤(cmd命令窗口)

1. 进入 `frontend/project` 目录：
   ```bash
   cd front\project
   ```

2. 初始化项目（确保已经安装 Node.js 和 npm）：
   ```bash
   npm init
   ```

3. 启动开发服务器：
   ```bash
   cd battery
   npm run dev
   ```

4. 打开浏览器，访问 [http://localhost:9525]。



