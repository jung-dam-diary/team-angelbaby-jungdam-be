package com.jungdam.image.config;

public enum FileFolder {
    AVATAR_IMAGES {
        @Override
        public String getFileFolder(AmazonS3Component amazonS3Component) {
            return amazonS3Component.getAvatarFolder();
        }
    }, THUMBNAIL_IMAGES {
        @Override
        public String getFileFolder(AmazonS3Component amazonS3Component) {
            return amazonS3Component.getThumbnailFolder();
        }
    }, DIARY_IMAGES {
        @Override
        public String getFileFolder(AmazonS3Component amazonS3Component) {
            return amazonS3Component.getDiaryFolder();
        }
    }, TEMPORARY {
        @Override
        public String getFileFolder(AmazonS3Component amazonS3Component) {
            return amazonS3Component.getTemporaryFolder();
        }
    };

    public abstract String getFileFolder(AmazonS3Component amazonS3Component);
}
