build_all: create_lambdas_folder build_java8 build_java8_proguard build_java11 build_java11_jlink build_js build_graal

create_lambdas_folder:
	mkdir -p lambdas

build_java8:
	docker build -t lambda_jvm_java8 -f Dockerfile-java8 . && \
	docker create --name extract lambda_jvm_java8 && \
	docker cp extract:/opt/scala-lambda-runtimes/jvmLambda8/target/scala-2.13/assembly.jar ./lambdas/java8.jar && \
	docker rm extract

build_java11:
	docker build -t lambda_jvm_java11 -f Dockerfile-java11 . && \
    docker create --name extract lambda_jvm_java11 && \
    docker cp extract:/opt/scala-lambda-runtimes/jvmLambda11/target/scala-2.13/assembly.jar ./lambdas/java11.jar && \
    docker rm extract

build_java11_jlink:
	docker build -t lambda_jvm_java11_jlink -f Dockerfile-java11-jlink . && \
	docker create --name extract lambda_jvm_java11_jlink && \
	docker cp extract:/opt/scala-lambda-runtimes/jvmLambda11JLink/target/universal/package.zip ./lambdas/java11_jlink.zip && \
	docker rm extract && \
	zip -jur ./lambdas/java11_jlink.zip ./jvmLambda11JLink/bootstrap

build_java8_proguard:
	docker build -t lambda_jvm_java8_proguard -f Dockerfile-java8-proguard . && \
	docker create --name extract lambda_jvm_java8_proguard && \
	docker cp extract:/opt/scala-lambda-runtimes/jvmLambda8Proguard/target/scala-2.13/proguard/package.jar ./lambdas/java8_proguard.jar && \
	docker rm extract

build_js:
	docker build -t lambda_js -f Dockerfile-js . && \
	docker create --name extract lambda_js && \
	mkdir -p lambdas/js && \
	docker cp extract:/opt/scala-lambda-runtimes/jsLambda/target/scala-2.13/jslambda-opt.js ./lambdas/js/index.js && \
	chmod 644 lambdas/js/index.js && \
	zip -rmj lambdas/js.zip lambdas/js/* && \
	docker rm extract

build_graal:
	docker build -t lambda_graal -f Dockerfile-graal . && \
	docker create --name extract lambda_graal && \
	mkdir -p lambdas/graal && \
	docker cp extract:/opt/scala-lambda-runtimes/graalLambda/target/graalvm-native-image/graalLambda ./lambdas/graal/bootstrap && \
	zip -rmj lambdas/graal.zip lambdas/graal/* && \
	docker rm extract

serverless: prepare_serverless
	docker run -it \
		-w "/opt/scala-lambda-runtimes/" \
		-v ~/.aws/:/root/.aws:ro \
		--rm --entrypoint /bin/sh lambda_serverless -c "$(command)"

prepare_serverless:
	docker build -t lambda_serverless -f Dockerfile-deploy .
