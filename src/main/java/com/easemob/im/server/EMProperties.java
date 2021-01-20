package com.easemob.im.server;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 配置app的参数，包括appKey，clientId，clientSecret，baseUri
 *
 * appKey，clientId，clientSecret的获取，请登录环信console后台-->应用列表-->选择应用，点击查看即可
 *
 * 环信console后台链接：https://console.easemob.com/user/login
 * 如果没有账号请"创建新账号"，登录后"添加应用"，即可获取到 appKey，clientId，clientSecret
 *
 * baseUri为环信rest服务的域名，默认为 https://a1.easemob.com
 *
 * 如果是vip集群的客户，请与对应的环信商务联系索要域名
 *
 */
@Setter
@Getter
@AllArgsConstructor
public class EMProperties {
    private final String appKey;

    private final String clientId;

    private final String clientSecret;

    private final String baseUri;

    public String getBasePath() {
        String[] splitKey = this.appKey.split("#");
        return String.format("%s/%s/%s", this.baseUri, splitKey[0], splitKey[1]);
    }
}