package cz.los.KL_Twitter.model;

import lombok.Value;

@Value
public class Like {

    Long userId;
    Long likedTweetId;
}
