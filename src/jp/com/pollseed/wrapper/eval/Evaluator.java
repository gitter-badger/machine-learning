package jp.com.pollseed.wrapper.eval;

import java.util.Map;

import jp.com.pollseed.wrapper.eval.EvalItemVO.EvalName;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.RMSRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.common.RandomUtils;

public class Evaluator extends AbstractEvaluator {

    public Evaluator(DataModel dataModel, EvalItemVO dto) {
        super(dataModel, dto);
    }

    /**
     * <b>[推薦の評価方法]</b><br>
     * cf.)70%のデータを使用してレコメンダーを生成→30%のデータを使用して評価
     * @param dataModel
     * @throws TasteException
     */
    @Override
    public void eval() throws TasteException {

        System.out.println("◆ EVAL ======= Start ======");
        System.out.println("◆ -------------------------");

        final int size = super.dto.size;
        final Map<EvalName, EvaluationVO> evalMap = super.dto.evalMap;

        //        RandomUtils.useTestSeed();

        /* 実際の評価値と推定値の誤差 */
        // 絶対値を求める=意味平均誤差(mean average error)
        RecommenderEvaluator maeEval = new AverageAbsoluteDifferenceRecommenderEvaluator();
        // 自乗を足し合わせ平方根を取ったRMS(root mean squared)
        RecommenderEvaluator rmsEval = new RMSRecommenderEvaluator();

        RecommenderBuilder builder = new RecommenderBuilder() {

            @Override
            public Recommender buildRecommender(DataModel dataModel) throws TasteException {
                UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
                UserNeighborhood neighborhood = new NearestNUserNeighborhood(size, similarity, dataModel);
                return new GenericUserBasedRecommender(dataModel, neighborhood, similarity);
            }
        };

        // evaluate(RecommenderBuilder, DataModelBuilder, DataModel, 学習用データの割合, 検証用データの割合)
        EvaluationVO mae = evalMap.get(EvalName.MAE);
        EvaluationVO rms = evalMap.get(EvalName.RMS);
        double maeScore = maeEval.evaluate(builder, null, super.dataModel, mae.trainingPercentage, mae.evaluationPercentage);
        double rmsScore = rmsEval.evaluate(builder, null, super.dataModel, rms.trainingPercentage, rms.evaluationPercentage);

        System.out.println(EvalName.MAE.name + " : " + maeScore);
        System.out.println(EvalName.RMS.name + " : " + rmsScore);
        System.out.println("◆ -------------------------");
        System.out.println("◆ EVAL ======= END ======\r\n");
    }
}
