#!/bin/bash

# 루트 디렉토리 생성
mkdir -p spark-study
cd spark-study

# 디렉토리 구조 생성
dirs=(
  "1.introduction/1.1.what-is-spark"
  "1.introduction/1.2.installation"
  "1.introduction/1.3.spark-ecosystem"
  "2.core-concepts/2.1.rdd"
  "2.core-concepts/2.2.dataframes"
  "2.core-concepts/2.3.datasets"
  "3.spark-sql/3.1.sql-basics"
  "3.spark-sql/3.2.data-sources"
  "3.spark-sql/3.3.performance"
  "4.spark-streaming/4.1.dstream"
  "4.spark-streaming/4.2.structured-streaming"
  "4.spark-streaming/4.3.stream-processing"
  "5.machine-learning/5.1.mllib"
  "5.machine-learning/5.2.ml-pipeline"
  "5.machine-learning/5.3.advanced-ml"
  "6.graph-processing/6.1.graphx"
  "6.graph-processing/6.2.graph-frames"
  "7.optimization/7.1.performance-tuning"
  "7.optimization/7.2.caching"
  "7.optimization/7.3.job-scheduling"
  "8.security/8.1.authentication"
  "8.security/8.2.authorization"
  "8.security/8.3.encryption"
  "9.testing/9.1.unit-testing"
  "9.testing/9.2.integration-testing"
  "9.testing/9.3.performance-testing"
  "10.deployment/10.1.cluster-management"
  "10.deployment/10.2.monitoring"
  "10.deployment/10.3.troubleshooting"
  "11.tools-and-integrations/11.1.data-tools"
  "11.tools-and-integrations/11.2.development-tools"
  "11.tools-and-integrations/11.3.cloud-integrations"
)

for dir in "${dirs[@]}"; do
  mkdir -p "$dir"
done

# 파일 생성
touch 1.introduction/1.1.what-is-spark/{spark-overview.md,spark-vs-hadoop.md}
touch 1.introduction/1.2.installation/{local-setup.md,cluster-setup.md}
touch 1.introduction/1.3.spark-ecosystem/{spark-components.md,integration-with-other-tools.md}
touch 2.core-concepts/2.1.rdd/{rdd-basics.md,rdd-operations.md}
touch 2.core-concepts/2.2.dataframes/{dataframe-creation.md,dataframe-operations.md}
touch 2.core-concepts/2.3.datasets/{dataset-basics.md,dataset-vs-dataframe.md}
touch 3.spark-sql/3.1.sql-basics/{sql-queries.md,temporary-views.md}
touch 3.spark-sql/3.2.data-sources/{reading-data.md,writing-data.md}
touch 3.spark-sql/3.3.performance/{catalyst-optimizer.md,tungsten-execution-engine.md}
touch 4.spark-streaming/4.1.dstream/{dstream-basics.md,windowed-operations.md}
touch 4.spark-streaming/4.2.structured-streaming/{structured-streaming-basics.md,event-time-processing.md}
touch 4.spark-streaming/4.3.stream-processing/{fault-tolerance.md,checkpointing.md}
touch 5.machine-learning/5.1.mllib/{regression.md,classification.md}
touch 5.machine-learning/5.2.ml-pipeline/{feature-engineering.md,model-evaluation.md}
touch 5.machine-learning/5.3.advanced-ml/{clustering.md,recommendation-systems.md}
touch 6.graph-processing/6.1.graphx/{graph-creation.md,graph-algorithms.md}
touch 6.graph-processing/6.2.graph-frames/{graph-frames-basics.md,graph-analytics.md}
touch 7.optimization/7.1.performance-tuning/{memory-management.md,data-serialization.md}
touch 7.optimization/7.2.caching/{caching-strategies.md,persistence-levels.md}
touch 7.optimization/7.3.job-scheduling/{fair-scheduler.md,dynamic-allocation.md}
touch 8.security/8.1.authentication/{kerberos-setup.md,ssl-configuration.md}
touch 8.security/8.2.authorization/{acl-management.md,row-column-security.md}
touch 8.security/8.3.encryption/{data-encryption.md,network-encryption.md}
touch 9.testing/9.1.unit-testing/{testing-transformations.md,testing-actions.md}
touch 9.testing/9.2.integration-testing/{spark-testing-base.md,docker-based-testing.md}
touch 9.testing/9.3.performance-testing/{benchmarking.md,profiling.md}
touch 10.deployment/10.1.cluster-management/{yarn-deployment.md,kubernetes-deployment.md}
touch 10.deployment/10.2.monitoring/{spark-ui.md,metrics-and-instrumentation.md}
touch 10.deployment/10.3.troubleshooting/{common-issues.md,log-analysis.md}
touch 11.tools-and-integrations/11.1.data-tools/{apache-kafka.md,apache-hive.md}
touch 11.tools-and-integrations/11.2.development-tools/{intellij-idea.md,jupyter-notebooks.md}
touch 11.tools-and-integrations/11.3.cloud-integrations/{aws-emr.md,azure-databricks.md}

# README.md 생성
cat << EOF > README.md
# Spark 학습 가이드

이 저장소는 Apache Spark 학습을 위한 포괄적인 가이드를 제공합니다. 각 디렉토리와 문서는 Spark의 주요 개념과 기능을 다루고 있습니다.

## 디렉토리 구조

- \`1.introduction/\`: Spark 소개 및 기본 설정
- \`2.core-concepts/\`: RDD, DataFrame, Dataset 등 핵심 개념
- \`3.spark-sql/\`: Spark SQL을 이용한 데이터 처리
- \`4.spark-streaming/\`: 실시간 데이터 처리
- \`5.machine-learning/\`: Spark MLlib를 이용한 기계 학습
- \`6.graph-processing/\`: GraphX와 GraphFrames를 이용한 그래프 처리
- \`7.optimization/\`: 성능 최적화 기법
- \`8.security/\`: Spark 보안 설정
- \`9.testing/\`: Spark 애플리케이션 테스팅
- \`10.deployment/\`: 클러스터 배포 및 관리
- \`11.tools-and-integrations/\`: 관련 도구 및 통합

각 디렉토리 내의 문서들은 해당 주제에 대한 상세한 설명과 예제를 제공합니다.

## 시작하기

1. \`1.introduction/\` 디렉토리부터 시작하여 Spark의 기본 개념을 익히세요.
2. 각 주제별로 필요한 디렉토리를 탐색하며 학습을 진행하세요.
3. 예제 코드를 실행해보고, 설명을 참고하여 개념을 이해하세요.
4. 질문이나 문제가 있다면 이슈를 생성하여 문의해주세요.

## 기여하기

이 학습 가이드의 개선에 기여하고 싶다면 풀 리퀘스트를 보내주세요. 모든 기여를 환영합니다!

## 라이선스

이 프로젝트는 MIT 라이선스 하에 제공됩니다. 자세한 내용은 \`LICENSE\` 파일을 참조하세요.
EOF

echo "Spark 학습 디렉토리 구조가 생성되었습니다."