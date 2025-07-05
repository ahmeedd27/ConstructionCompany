# ---------- Builder Stage ----------
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app

# نثبت maven مرة واحدة
RUN apt-get update && apt-get install -y maven

# ننسخ فقط الـ pom.xml الأول للاستفادة من الكاش
COPY pom.xml .

# نحمّل dependencies ونخزنها في الكاش (لو ما تغيرش pom.xml مش هيعيد التحميل)
RUN mvn dependency:go-offline

# ننسخ الكود فقط بعد كده
COPY src ./src

# نعمل البيلد
RUN mvn clean package -DskipTests

# ---------- Final Stage ----------
FROM eclipse-temurin:24-jdk

WORKDIR /app

# ننسخ الملف الناتج من مرحلة الـ builder
COPY --from=builder /app/target/SoltanSalman-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

# نشغل التطبيق
ENTRYPOINT ["java", "-jar", "app.jar"]