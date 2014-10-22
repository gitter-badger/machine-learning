package jp.com.pollseed.wrapper.eval;

public class EvaluationVO {
    public final double trainingPercentage;
    public final double evaluationPercentage;

    public EvaluationVO(double trainingPercentage, double evaluationPercentage) {
        this.trainingPercentage = trainingPercentage;
        this.evaluationPercentage = evaluationPercentage;
    }
}
