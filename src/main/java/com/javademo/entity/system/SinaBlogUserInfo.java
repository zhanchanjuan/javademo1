package com.javademo.entity.system;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 新浪微博用户信息
 *
 * @author
 */
@Getter
@Setter
@ToString
public class SinaBlogUserInfo implements Serializable {

    private static final long serialVersionUID = -359432931065360991L;
    Long id; //	int64 	用户UID
    String idstr; // string 	字符串型的用户UID
    String screen_name; // string 	用户昵称
    String name; // string 	友好显示名称
    Integer province; // 	int 	用户所在省级ID
    Integer city; // int 	用户所在城市ID
    String location; //  	string 	用户所在地
    String description; //  	string 	用户个人描述
    String url; //  	string 	用户博客地址
    String profile_image_url; //  	string 	用户头像地址（中图），50×50像素
    String profile_url; //  	string 	用户的微博统一URL地址
    String domain; //  	string 	用户的个性化域名
    String weihao; //  	string 	用户的微号
    String gender; //  	string 	性别，m：男、f：女、n：未知
    Integer followers_count; //  	int 	粉丝数
    Integer friends_count; //  	int 	关注数
    Integer statuses_count; //  	int 	微博数
    Integer favourites_count; //  	int 	收藏数
    String created_at; //  	string 	用户创建（注册）时间
    Boolean allow_all_act_msg; // 	boolean 	是否允许所有人给我发私信，true：是，false：否
    Boolean geo_enabled; //  	boolean 	是否允许标识用户的地理位置，true：是，false：否
    Boolean verified; // 	boolean 	是否是微博认证用户，即加V用户，true：是，false：否
    String remark; // 	string 	用户备注信息，只有在查询用户关系时才返回此字段
    Object status; // 	object 	用户的最近一条微博信息字段 详细
    Boolean allow_all_comment; //  	boolean 	是否允许所有人对我的微博进行评论，true：是，false：否
    String avatar_large; // 	string 	用户头像地址（大图），180×180像素
    String avatar_hd; //  	string 	用户头像地址（高清），高清头像原图
    String verified_reason; // 	string 	认证原因
    Boolean follow_me; //  	boolean 	该用户是否关注当前登录用户，true：是，false：否
    Integer online_status; // 	int 	用户的在线状态，0：不在线、1：在线
    Integer bi_followers_count; //  	int 	用户的互粉数
    String lang; // 	string 	用户当前的语言版本，zh-cn：简体中文，zh-tw：繁体中文，en：英语
    private String registerName; // 平台注册名

}
