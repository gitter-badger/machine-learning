package jp.com.pollseed.wrapper.user;

import java.util.List;
import java.util.Map;

import jp.com.pollseed.wrapper.user.UserItemDto.UserName;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.AveragingPreferenceInferrer;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.UncenteredCosineSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class User extends AbstractUser {

    public User(DataModel dataModel, UserItemDto dto) {
        super(dataModel, dto);
    }

    /**
     * <b>[推薦の評価方法]</b><br>
     * 70%のデータを使用してレコメンダーを生成→30%のデータを使用して評価
     * @param dataModel
     * @throws TasteException
     */
    @Override
    public void affinity() throws TasteException {

        // ユーザの類似性を定義
        System.out.println("◆ USER ======= Start ======");
        System.out.println("◆ -------------------------");

        try {

            final Map<UserName, UserDto> userMap = super.dto.userMap;

            // ピアソン相関
            UserSimilarity a_pearson = new PearsonCorrelationSimilarity(super.dataModel);
            UserDto pearson = userMap.get(UserName.PEARSON);
            recommend(super.dataModel, a_pearson, pearson);

            // ユークリッド距離
            UserSimilarity a_euclid = new EuclideanDistanceSimilarity(super.dataModel);
            UserDto euclidean = userMap.get(UserName.EUCLIDEAN);
            recommend(super.dataModel, a_euclid, euclidean);

            // コサイン類似度
            UserSimilarity a_cosine = new UncenteredCosineSimilarity(super.dataModel);
            UserDto cosine = userMap.get(UserName.COSINE);
            recommend(super.dataModel, a_cosine, cosine);

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        System.out.println("◆ -------------------------");
        System.out.println("◆ USER ======= END ======\r\n");
    }

    /**
     * レコメンデーションを生成して出力
     * @param datamodel
     * @param similarity
     * @param userId
     * @param howMany
     * @throws TasteException
     */
    private static void recommend(DataModel datamodel, UserSimilarity similarity, UserDto dto) throws TasteException {
        System.out.println(similarity.getClass());
        similarity.setPreferenceInferrer(new AveragingPreferenceInferrer(datamodel));
        UserNeighborhood neighbor = new NearestNUserNeighborhood(dto.size, similarity, datamodel);
        Recommender recommender = new GenericUserBasedRecommender(datamodel, neighbor, similarity);
        List<RecommendedItem> items = recommender.recommend(dto.userId, dto.howMany);
        for (RecommendedItem item : items) {
            System.out.println(item);
        }
    }

    /**
     * 定数用
     * @param datamodel
     * @param similarity
     * @param userId
     * @param howMany
     * @throws TasteException
     * @deprecated
     */
    private static void criticsRecommend(DataModel datamodel, UserSimilarity similarity, UserDto dto) throws TasteException {
        System.out.println(similarity.getClass());
        similarity.setPreferenceInferrer(new AveragingPreferenceInferrer(datamodel));
        UserNeighborhood neighbor = new NearestNUserNeighborhood(dto.size, similarity, datamodel);
        Recommender recommender = new GenericUserBasedRecommender(datamodel, neighbor, similarity);
        List<RecommendedItem> items = recommender.recommend(dto.userId, dto.howMany);
        for (RecommendedItem item : items) {
            System.out.println("推薦[ アイテム名：" + Critics.getName(item.getItemID()) + ", 値：" + item.getValue() + " ]");
        }
    }
}
