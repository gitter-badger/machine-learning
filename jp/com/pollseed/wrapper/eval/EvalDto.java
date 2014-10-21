package jp.com.machine.test.eval;

public class EvalDto {
    public double trainingPercentage;
    public double evaluationPercentage;

    public EvalDto(double trainingPercentage, double evaluationPercentage) {
        this.trainingPercentage = trainingPercentage;
        this.evaluationPercentage = evaluationPercentage;
    }
}
