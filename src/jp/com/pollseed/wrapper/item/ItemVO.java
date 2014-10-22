package jp.com.pollseed.wrapper.item;

import java.util.HashMap;
import java.util.Map;

public class ItemVO {

    public Map<ItemName, ItemAffinityVO> itemMap = new HashMap<ItemVO.ItemName, ItemAffinityVO>();

    public static enum ItemName {
        TANIMOTO(1, "谷本係数"),
        CITY_BLOCK(2, "シティブロック距離"),
        LOG_LIKE(3, "稀にしか起こらない事象"),
        EUCLIDEAN(4, "ユークリッド距離"),
        COSINE(5, "コサイン類似度");

        public int num;
        public String name;

        ItemName(int num, String name) {
            this.num = num;
            this.name = name;
        }

        public static String getName(long val) {
            for (ItemName _val : ItemName.values()) {
                if (_val.num == val) {
                    return _val.name;
                }
            }
            return null;
        }
    }
}
