# Stage 1: Maven package and create a jar file
FROM maven:3.5-jdk-8-alpine as build

# Workspace
WORKDIR /app/

# COPY resources from the host
COPY src	 					/app/src/
COPY *.xml 						/app/
COPY *.ini						/app/
COPY *.properties				/app/
COPY ExcludeTest				/app/ExcludeTest
COPY TestData				    /app/TestData




# Maven package command
RUN mvn clean package -DSkipTests



# Stage 2: Use the .jar file for execution
FROM openjdk:8u191-jre-alpine3.8

# Workspace
WORKDIR  /users/Automation/Cigniti_Automation/

# COPY .jar and resources into this image from the base image
COPY --from=build /app/target/HOOQ_Cigniti_Automation-jar-with-dependencies.jar  /users/Automation/Cigniti_Automation/HOOQ_Cigniti_Automation-jar-with-dependencies.jar
COPY --from=build /app/.														 /users/Automation/Cigniti_Automation/

# Execution of the .jar file
CMD java -jar -DProject=$Project -Denvironment=$environment -DsuiteType=$suiteType -DuserTypeList=$userTypeList -Dcountries=$countries -DtargetExecution=$targetExecution -DEmailList=$EmailList -DstrTestRailSuiteName=$strTestRailSuiteName -DstrTestRail=$strTestRail -DstrMaxRetryCount=$strMaxRetryCount -DbrowserstackAppId=$browserstackAppId -DisVideoRecord=$isVideoRecord -DeligibilityService=$eligibilityService -DRunType=$RunType -DgroupToRun="" HOOQ_Cigniti_Automation-jar-with-dependencies.jar