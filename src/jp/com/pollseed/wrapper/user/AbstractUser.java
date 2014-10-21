package jp.com.pollseed.wrapper.user;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.model.DataModel;

abstract class AbstractUser {

    protected DataModel dataModel;
    protected UserItemDto dto;

    protected AbstractUser(DataModel dataModel, UserItemDto dto) {
        if (dataModel == null || dto == null || dto.userMap == null || dto.userMap.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.dataModel = dataModel;
        this.dto = dto;
    }

    protected abstract void affinity() throws TasteException;

}
