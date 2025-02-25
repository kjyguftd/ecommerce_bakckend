FROM eclipse-temurin:23-jdk AS build

# 设置工作目录
WORKDIR /app

# 复制项目文件
COPY . .

RUN chmod +x gradlew  # 确保 gradlew 具有执行权限

# 使用 Gradle 构建项目
RUN ./gradlew build --no-daemon

# 运行 Spring Boot 应用
CMD ["java", "-jar", "build/libs/ecommerce_backend-0.0.1-SNAPSHOT.jar"]
