name := "better-testing"

organization := "rosien.net"

scalaVersion := "2.12.2"

libraryDependencies ++=
  Seq(
    "org.typelevel"  %% "cats"       % "0.9.0",
    "org.scalatest"  %% "scalatest"  % "3.0.1"  % "test",
    "org.scalacheck" %% "scalacheck" % "1.13.4" % "test")

scalacOptions ++=
  Seq(
    "-encoding", "UTF-8", // 2 args
    "-feature",
    "-deprecation",
    "-language:existentials",
    "-language:higherKinds",
    "-unchecked",
    "-Xlint",
    "-Ywarn-dead-code",
    "-Ywarn-value-discard")