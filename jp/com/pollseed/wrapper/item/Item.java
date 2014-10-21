package jp.com.machine.test.item;

import java.util.List;
import java.util.Map;

import jp.com.machine.test.item.ItemItemDto.ItemName;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.CityBlockSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.ItemBasedRecommender;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

public class Item extends AbstractItem {

    public Item(DataModel dataModel, ItemItemDto dto) {
        super(dataModel, dto);
    }

    /**
     * <b>[推薦の評価方法]</b><br>
     * cf.)70%のデータを使用してレコメンダーを生成→30%のデータを使用して評価
     * @param dataModel
     * @throws TasteException
     */
    @Override
    public void affinity() throws TasteException {

        // アイテムの類似性を定義
        System.out.println("◆ ITEM ======= Start ======");
        System.out.println("◆ -------------------------");

        try {

            final Map<ItemName, ItemDto> itemMap = super.dto.itemMap;

            // 谷本係数
            ItemSimilarity a_tanimoto = new TanimotoCoefficientSimilarity(super.dataModel);
            ItemDto tanimoto = itemMap.get(ItemName.TANIMOTO);
            recommend(super.dataModel, a_tanimoto, tanimoto);

            // シティブロック距離
            ItemSimilarity a_cityBlock = new CityBlockSimilarity(super.dataModel);
            ItemDto cityBlock = itemMap.get(ItemName.CITY_BLOCK);
            recommend(super.dataModel, a_cityBlock, cityBlock);

            // 稀にしか起こらない事象
            ItemSimilarity a_logLikelihood = new LogLikelihoodSimilarity(super.dataModel);
            ItemDto logLike = itemMap.get(ItemName.LOG_LIKE);
            recommend(super.dataModel, a_logLikelihood, logLike);

            // ユークリッド距離
            ItemSimilarity a_euclid = new org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity(super.dataModel);
            ItemDto euclidean = itemMap.get(ItemName.EUCLIDEAN);
            recommend(super.dataModel, a_euclid, euclidean);

            // コサイン類似度
            ItemSimilarity a_cosine = new org.apache.mahout.cf.taste.impl.similarity.UncenteredCosineSimilarity(super.dataModel);
            ItemDto cosine = itemMap.get(ItemName.COSINE);
            recommend(super.dataModel, a_cosine, cosine);

        } catch (IllegalArgumentException e) {}

        System.out.println("◆ -------------------------");
        System.out.println("◆ ITEM ======= END ======\r\n");
    }

    /**
     * レコメンデーションを生成して出力
     * @param dataModel
     * @param algorithm
     * @throws TasteException
     */
    private static void recommend(DataModel dataModel, ItemSimilarity algorithm, ItemDto dto) throws TasteException {
        System.out.println(algorithm.getClass());
        ItemBasedRecommender recommender = new GenericItemBasedRecommender(dataModel, algorithm);
        List<RecommendedItem> items = recommender.recommend(dto.userId, dto.howMany);
        for (RecommendedItem item : items) {
            System.out.println(item);
        }
    }
}
