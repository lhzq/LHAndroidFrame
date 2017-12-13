package com.jimtrency.lh.lhandroidframe.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/26.
 */

public class ImageBanderInfo {
    public RespondHeader mobileRespHeader;
    public BanderRespBody mobileRespBody;

    public class BanderRespBody {
        public List<BanderImageInfo> data;
    }

    public class BanderImageInfo {
        public String id;
        public String imageUrl;
        public String url;
        public String title;
    }

}
