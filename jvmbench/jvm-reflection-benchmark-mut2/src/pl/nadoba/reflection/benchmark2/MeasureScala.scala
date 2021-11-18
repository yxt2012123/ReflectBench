package pl.nadoba.reflection.benchmark

import scala.reflect.runtime.{universe => reflectUniverse, _}

object MeasureScala {
  def main(args: Array[String]) {
    val range = args.head.toInt

    val testClass = new TestClass()
    val primitiveNormal = primitiveField(range, testClass)
    println(s"Primitive field normal access: $primitiveNormal ns")

    val primitiveReflect = primitiveFieldJavaStyleReflection(range, testClass)
    println(s"Primitive field java-style reflection access: $primitiveReflect ns")

    val primitiveScalaReflect = primitiveFieldScalaExperimentalStyleReflection(range, testClass)
    println(s"Primitive field scala-style [experimental module] reflection access: $primitiveScalaReflect ns")
  }

  private def primitiveField(range: Int, testClass: TestClass): Double = {
    baseTest(range, testClass) { _ =>
      testClass.budget
      testClass.density
      testClass.isHealthy
      testClass.name
      testClass.velocity
    }
  }

  private def primitiveFieldJavaStyleReflection(range: Int, testClass: TestClass): Double = {
    baseTest(range, testClass) { _ =>
      testClass.getClass.getFields.foreach { field =>
        if (!field.getName.contains("reference")) field.get(testClass)
      }
    }
  }

  private def primitiveFieldScalaExperimentalStyleReflection(range: Int, testClass: TestClass): Double = {
    baseTest(range, testClass) { _ =>
      val testClassMirror = currentMirror.reflect(testClass)
      val budgetTerm = reflectUniverse.typeOf[TestClass].decl(reflectUniverse.TermName("budget")).asTerm
      testClassMirror.reflectField(budgetTerm).get
      val densityTerm = reflectUniverse.typeOf[TestClass].decl(reflectUniverse.TermName("density")).asTerm
      testClassMirror.reflectField(densityTerm).get
      val isHealthyTerm = reflectUniverse.typeOf[TestClass].decl(reflectUniverse.TermName("isHealthy")).asTerm
      testClassMirror.reflectField(isHealthyTerm).get
      val nameTerm = reflectUniverse.typeOf[TestClass].decl(reflectUniverse.TermName("name")).asTerm
      testClassMirror.reflectField(nameTerm).get
      val velocityTerm = reflectUniverse.typeOf[TestClass].decl(reflectUniverse.TermName("velocity")).asTerm
      testClassMirror.reflectField(velocityTerm).get
    }
  }

  private def baseTest(range: Int, testClass: TestClass)(measureFunction: (TestClass) => (Unit)): Double = {
    val measurements = (1 to 20).map { _ =>
      val startTime = System.nanoTime()
      (1 to range).foreach { _ =>
        measureFunction(testClass)
      }
      val endTime = System.nanoTime()

      val timeDifference = endTime - startTime
      val fieldsInEachIteration = 5
      timeDifference / fieldsInEachIteration.toDouble / range.toDouble
    }

    val dropFactor = measurements.length / 10
    val results = measurements.sorted.slice(dropFactor, measurements.length - dropFactor)
    results.sum / results.length
  }
}