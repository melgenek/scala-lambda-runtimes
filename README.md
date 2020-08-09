-rw-rw-r-- 1 yevhenii yevhenii 9,3M Aug  8 21:36 graal.zip
-rw-r--r-- 1 yevhenii yevhenii 5,7M Aug  8 21:32 java11.jar
-rw-r--r-- 1 yevhenii yevhenii  23M Aug  8 21:33 java11_jlink.zip
-rw-r--r-- 1 yevhenii yevhenii 5,7M Aug  8 21:31 java8.jar
-rw-r--r-- 1 yevhenii yevhenii 446K Aug  8 21:31 java8_proguard.jar
-rw-rw-r-- 1 yevhenii yevhenii 2,2K Aug  8 21:33 js.zip

![p95_total](p95_total_execution_time.png)

| Runtime |         | Are           | Cool  |
| ------------- |:-------------:| -----:|
| col 3 is      | right-aligned | $1600 |
| col 2 is      | centered      |   $12 |
| zebra stripes | are neat      |    $1 |

make serverless command="serverless deploy"

        filter @type = "REPORT"
        
        | fields @timestamp as Timestamp, @initDuration as InitDurationInMS, @duration as DurationInMS, @billedDuration as BilledDurationInMS, @memorySize/1000000 as MemorySetInMB, @maxMemoryUsed/1000000 as MemoryUsedInMB
        | sort Timestamp desc
        | head 200
    

java 8
make serverless command="lumigo-cli measure-lambda-cold-starts --functionName=scala-lambda-runtimes-dev-melgenekJvmLambda8 --invocations=100 --region=us-east-1"

{
  "functionName": "scala-lambda-runtimes-dev-melgenekJvmLambda8",
  "result": [
    {
      "functionName": "scala-lambda-runtimes-dev-melgenekJvmLambda8",
      "memorySize": 128,
      "coldStarts": 88,
      "min": 20.34,
      "p25": 22.72,
      "median": 25.33,
      "p75": 27.69,
      "p95": 30.57,
      "max": 42.88,
      "stddev": 3.6025
    }
  ]
}

java8 proguard
make serverless command="lumigo-cli measure-lambda-cold-starts --functionName=scala-lambda-runtimes-dev-melgenekJvmLambda8Proguard --invocations=100 --region=us-east-1"

{
  "functionName": "scala-lambda-runtimes-dev-melgenekJvmLambda8Proguard",
  "result": [
    {
      "functionName": "scala-lambda-runtimes-dev-melgenekJvmLambda8Proguard",
      "memorySize": 128,
      "coldStarts": 72,
      "min": 20.01,
      "p25": 23.28,
      "median": 24.83,
      "p75": 26.62,
      "p95": 33.61,
      "max": 42.84,
      "stddev": 3.9185
    }
  ]
}

java 11
make serverless command="lumigo-cli measure-lambda-cold-starts --functionName=scala-lambda-runtimes-dev-melgenekJvmLambda11 --invocations=100 --region=us-east-1"

{
  "functionName": "scala-lambda-runtimes-dev-melgenekJvmLambda11",
  "result": [
    {
      "functionName": "scala-lambda-runtimes-dev-melgenekJvmLambda11",
      "memorySize": 128,
      "coldStarts": 89,
      "min": 192.43,
      "p25": 212.79,
      "median": 221.4,
      "p75": 230.22,
      "p95": 257.32,
      "max": 280.31,
      "stddev": 16.4692
    }
  ]
}

java 11 jlink
make serverless command="lumigo-cli measure-lambda-cold-starts --functionName=scala-lambda-runtimes-dev-melgenekJvmLambda11JLink --invocations=100 --region=us-east-1"

{
  "functionName": "scala-lambda-runtimes-dev-melgenekJvmLambda11JLink",
  "result": [
    {
      "functionName": "scala-lambda-runtimes-dev-melgenekJvmLambda11JLink",
      "memorySize": 128,
      "coldStarts": 89,
      "min": 1458.23,
      "p25": 1686.54,
      "median": 1727.46,
      "p75": 1798.09,
      "p95": 2033.68,
      "max": 2617.22,
      "stddev": 156.6881
    }
  ]
}

js

make serverless command="lumigo-cli measure-lambda-cold-starts --functionName=scala-lambda-runtimes-dev-melgenekJsLambda --invocations=100 --region=us-east-1"

{
  "functionName": "scala-lambda-runtimes-dev-melgenekJsLambda",
  "result": [
    {
      "functionName": "scala-lambda-runtimes-dev-melgenekJsLambda",
      "memorySize": 128,
      "coldStarts": 55,
      "min": 123.05,
      "p25": 142.32,
      "median": 151.58,
      "p75": 170.47,
      "p95": 244.49,
      "max": 355.81,
      "stddev": 38.8808
    }
  ]
}

graal
make serverless command="lumigo-cli measure-lambda-cold-starts --functionName=scala-lambda-runtimes-dev-melgenekGraalLambda --invocations=100 --region=us-east-1"

{
  "functionName": "scala-lambda-runtimes-dev-melgenekGraalLambda",
  "result": [
    {
      "functionName": "scala-lambda-runtimes-dev-melgenekGraalLambda",
      "memorySize": 128,
      "coldStarts": 26,
      "min": 189.7,
      "p25": 210.8,
      "median": 217.33,
      "p75": 236.37,
      "p95": 296.72,
      "max": 314.08,
      "stddev": 31.8077
    }
  ]
}




    
    




