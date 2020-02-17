import org.apache.spark.ml.PipelineModel
import org.apache.spark.sql.SparkSession

object Scoring {
  def main(arg: Array[String]): Unit = {

    val spark: SparkSession = SparkSession
      .builder
      .master("local")
      .appName("rf-scoring-2")
      .getOrCreate()

    val data = spark.read.format("libsvm").load("data/__default__/user/current/sample_libsvm_data.txt")
    val sameModel = PipelineModel.load("/tmp/mleal-models/rf-exp-2")

    sameModel.transform(data).collect.foreach(println)

    print(sameModel.transform(data).head(10))
  }
}
