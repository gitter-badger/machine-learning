package jp.com.pollseed.wrapper.eval;

public class EvalDto {
    public double trainingPercentage;
    public double evaluationPercentage;

    public EvalDto(double trainingPercentage, double evaluationPercentage) {
        this.trainingPercentage = trainingPercentage;
        this.evaluationPercentage = evaluationPercentage;
    }
}
