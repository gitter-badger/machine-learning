package jp.com.machine.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jp.com.consts.CommonConsts.Critics;
import jp.com.machine.test.eval.EvalDto;
import jp.com.machine.test.eval.EvalItemDto;
import jp.com.machine.test.eval.Evaluator;
import jp.com.machine.test.eval.EvalItemDto.EvalName;
import jp.com.machine.test.user.User;
import jp.com.machine.test.user.UserDto;
import jp.com.machine.test.user.UserItemDto;
import jp.com.machine.test.user.UserItemDto.UserName;
import jp.com.machine.test.MahoutFileParser.Arrange;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.AveragingPreferenceInferrer;
import org.apache.mahout.cf.taste.impl.similarity.CityBlockSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.UncenteredCosineSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.ItemBasedRecommender;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

/**
 * Mahoutのアルゴリズムを使うためのイントロダクションクラス<br>
 * 使い方です。
 * <b>※only UTF-8
 */
class Introduction {

    private static final String DIRECTORY_PATH = "\\machineTest\\jp\\com\\machine\\test\\";
    private static final String INPUT_PATH = String.format("%sseg.csv", DIRECTORY_PATH);

    public static void main(String[] args) throws IOException, TasteException {

        DataModel dataModel = new FileDataModel(new File(INPUT_PATH));

        EvalItemDto evalDto = new EvalItemDto();
        evalDto.evalMap.put(EvalName.MAE, new EvalDto(0.78, 1.0));
        evalDto.evalMap.put(EvalName.RMS, new EvalDto(0.69, 1.0));
        Evaluator eval = new Evaluator(dataModel, evalDto);
        eval.eval();

        UserItemDto userDto = new UserItemDto();
        userDto.userMap.put(UserName.PEARSON, new UserDto(3, 1, 100));
        userDto.userMap.put(UserName.EUCLIDEAN, new UserDto(3, 1, 100));
        userDto.userMap.put(UserName.COSINE, new UserDto(3, 1, 100));
        User user = new User(dataModel, userDto);
        user.affinity();

        itemAffinity(dataModel);
    }
}
