FROM centos
RUN yum install -y java

ARG WORKDIR=/usr/bin/customer-service
WORKDIR ${WORKDIR}
ADD /target/customer-service-0.0.1-SNAPSHOT.jar ${WORKDIR}
EXPOSE 8081
ENTRYPOINT ["java"]
CMD ["-jar", "customer-service-0.0.1-SNAPSHOT.jar"]