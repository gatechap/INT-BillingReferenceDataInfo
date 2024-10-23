FROM gcr.io/tdg-sec-non-prod-bnxe/openjdk8-alpine
ENV TZ=Asia/Bangkok
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
COPY ./target/BillingReferenceDataInfo-0.0.1-SNAPSHOT.jar /data/
WORKDIR /data/
CMD ["java", "-jar", "BillingReferenceDataInfo-0.0.1-SNAPSHOT.jar"]