package jp.com.pollseed.wrapper.user;

import java.util.HashMap;
import java.util.Map;

public class UserItemVO {

    public UserItemVO() {
        this.userMap = new HashMap<UserItemVO.UserName, UserAffinityVO>(UserName.values().length);
    }

    public final Map<UserName, UserAffinityVO> userMap;

    public static enum UserName {
        PEARSON(1, "ピアソンの相関係数"),
        EUCLIDEAN(2, "ユークリッド距離"),
        COSINE(3, "コサイン類似度");

        public int num;
        public String name;

        UserName(int num, String name) {
            this.num = num;
            this.name = name;
        }

        public static String getName(long val) {
            for (UserName _val : UserName.values()) {
                if (_val.num == val) {
                    return _val.name;
                }
            }
            return null;
        }
    }
}
