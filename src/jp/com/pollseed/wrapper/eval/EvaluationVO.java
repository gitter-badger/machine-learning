package jp.com.pollseed.wrapper.eval;

public class EvaluationVO {
    public double trainingPercentage;
    public double evaluationPercentage;

    public EvaluationVO(double trainingPercentage, double evaluationPercentage) {
        this.trainingPercentage = trainingPercentage;
        this.evaluationPercentage = evaluationPercentage;
    }
}
