package com.example.admin.everywheretrips.bean;

import java.util.List;

public class MyFollowBean {

    /**
     * code : 0
     * desc :
     * result : {"page":1,"limit":20,"count":1,"banmi":[{"id":28,"name":"李炜","location":"北京","occupation":"蜻蜓FM名嘴，知名文化记者","introduction":"你好，我是李炜，知名文化记者。闲来无事最喜欢与两三好友对坐闲侃，聊聊历史八卦、文化趣事。我向来讲求有枣没枣儿，先打三杆子，搂草打兔子，咱们讲哪算哪儿。我不是专家，不谈之乎者也洋洋洒洒。或沉土中千载，或睹世间沧桑穿朝越代，它们背后的故事远比宫斗精彩。浩渺的历史文化中，总有有趣的故事让人折服与感悟，拿出一点时间，我们一起去看世界之大。觉得有趣，图您一乐呵，万一不妥，请您多包涵。","following":4677,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1513046399561_9d3b02cad0eddee4347c93f43b3e5a7a.jpg","isFollowed":true}]}
     */

    private int code;
    private String desc;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * page : 1
         * limit : 20
         * count : 1
         * banmi : [{"id":28,"name":"李炜","location":"北京","occupation":"蜻蜓FM名嘴，知名文化记者","introduction":"你好，我是李炜，知名文化记者。闲来无事最喜欢与两三好友对坐闲侃，聊聊历史八卦、文化趣事。我向来讲求有枣没枣儿，先打三杆子，搂草打兔子，咱们讲哪算哪儿。我不是专家，不谈之乎者也洋洋洒洒。或沉土中千载，或睹世间沧桑穿朝越代，它们背后的故事远比宫斗精彩。浩渺的历史文化中，总有有趣的故事让人折服与感悟，拿出一点时间，我们一起去看世界之大。觉得有趣，图您一乐呵，万一不妥，请您多包涵。","following":4677,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1513046399561_9d3b02cad0eddee4347c93f43b3e5a7a.jpg","isFollowed":true}]
         */

        private int page;
        private int limit;
        private int count;
        private List<BanmiBean> banmi;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<BanmiBean> getBanmi() {
            return banmi;
        }

        public void setBanmi(List<BanmiBean> banmi) {
            this.banmi = banmi;
        }

        public static class BanmiBean {
            /**
             * id : 28
             * name : 李炜
             * location : 北京
             * occupation : 蜻蜓FM名嘴，知名文化记者
             * introduction : 你好，我是李炜，知名文化记者。闲来无事最喜欢与两三好友对坐闲侃，聊聊历史八卦、文化趣事。我向来讲求有枣没枣儿，先打三杆子，搂草打兔子，咱们讲哪算哪儿。我不是专家，不谈之乎者也洋洋洒洒。或沉土中千载，或睹世间沧桑穿朝越代，它们背后的故事远比宫斗精彩。浩渺的历史文化中，总有有趣的故事让人折服与感悟，拿出一点时间，我们一起去看世界之大。觉得有趣，图您一乐呵，万一不妥，请您多包涵。
             * following : 4677
             * photo : http://cdn.banmi.com/banmiapp/rahdna/1513046399561_9d3b02cad0eddee4347c93f43b3e5a7a.jpg
             * isFollowed : true
             */

            private int id;
            private String name;
            private String location;
            private String occupation;
            private String introduction;
            private int following;
            private String photo;
            private boolean isFollowed;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getOccupation() {
                return occupation;
            }

            public void setOccupation(String occupation) {
                this.occupation = occupation;
            }

            public String getIntroduction() {
                return introduction;
            }

            public void setIntroduction(String introduction) {
                this.introduction = introduction;
            }

            public int getFollowing() {
                return following;
            }

            public void setFollowing(int following) {
                this.following = following;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public boolean isIsFollowed() {
                return isFollowed;
            }

            public void setIsFollowed(boolean isFollowed) {
                this.isFollowed = isFollowed;
            }
        }
    }
}
