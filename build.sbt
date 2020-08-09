val ScalaVersion = "2.13.3"

name := "scala-lambda-runtimes"

lazy val jvmLambda8 = project.settings(
  scalaVersion := ScalaVersion,
  assemblyJarName in assembly := "assembly.jar"
)

lazy val jvmLambda11 = project.settings(
  scalaVersion := ScalaVersion,
  scalacOptions ++= Seq("-target:11"),
  assemblyJarName in assembly := "assembly.jar",
)

lazy val jvmLambda11JLink = project
  .enablePlugins(JlinkPlugin)
  .settings(
    scalaVersion := ScalaVersion,
    scalacOptions ++= Seq("-target:11"),
    topLevelDirectory := None,
    packageName in Universal := "package",
  )

lazy val jvmLambda8Proguard = project
  .enablePlugins(SbtProguard)
  .settings(
    scalaVersion := ScalaVersion,
    proguardOptions in Proguard ++= Seq(
      "-dontoptimize",
      "-dontnote",
      "-dontwarn",
      "-ignorewarnings",
      "-keep public class hello.HelloFunction",
      """-keepclassmembers public class hello.HelloFunction {
        |    *;
        |}""".stripMargin
    ),
    proguardInputs in Proguard := (dependencyClasspath in Compile).value.files,
    proguardFilteredInputs in Proguard ++= ProguardOptions.noFilter((packageBin in Compile).value),
    artifactName := { (_: ScalaVersion, _: ModuleID, _: Artifact) => "package.jar" }
  )

lazy val jsLambda = project
  .enablePlugins(ScalaJSPlugin)
  .settings(
    scalaVersion := ScalaVersion,
    scalaJSLinkerConfig ~= {
      _.withModuleKind(ModuleKind.CommonJSModule)
    }
  )

lazy val graalLambda = project
  .enablePlugins(GraalVMNativeImagePlugin)
  .settings(
    scalaVersion := ScalaVersion,
    scalacOptions ++= Seq("-target:11"),
    javacOptions ++= Seq("-source", "11", "-target", "11"),
    graalVMNativeImageOptions ++= Seq(
      "--no-fallback",
      "--enable-https",
      "--initialize-at-build-time=scala.runtime.Statics$VM" // https://github.com/scala/bug/issues/11634#issuecomment-580613538
    )
  )
