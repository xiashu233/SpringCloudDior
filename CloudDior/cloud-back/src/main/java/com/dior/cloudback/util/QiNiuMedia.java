//package com.dior.cloudback.util;
//
///**
// *
// * @author "xinzhifu@knet.cn"
// */
//public class QiNiuMedia {
//
//	private static QiNiuMedia media = null;
//	public static final String ACCESSKEY = "7kzuv5YxLdsjJZr1wJDC7ay8wTHG22PNdaRCg_kW";
//	public static final String SECRETKEY = "pTlSe0JRBPHeKIsspNDYLTs2vI2eDHmvVvND7ctv";
//	public static final String BUCKETNAME = "xiashudior";
//	public static final String DOMAIN = "http://q7xvatz7b.bkt.clouddn.com/";
//	private String accessKey;// 设置accessKey
//	private String secretKey;// 设置secretKey
//	private String bucketName;// 设置存储空间
//	private String domain;// 设置七牛域名
//
//	public String getAccessKey() {
//		return accessKey;
//	}
//
//	public void setAccessKey(String accessKey) {
//		this.accessKey = accessKey;
//	}
//
//	public String getSecretKey() {
//		return secretKey;
//	}
//
//	public void setSecretKey(String secretKey) {
//		this.secretKey = secretKey;
//	}
//
//	public String getBucketName() {
//		return bucketName;
//	}
//
//	public void setBucketName(String bucketName) {
//		this.bucketName = bucketName;
//	}
//
//	public String getDomain() {
//		return domain;
//	}
//
//	public void setDomain(String domain) {
//		this.domain = domain;
//	}
//
//	/**
//	 * 实例化一个QiNiuMedia实例
//	 */
//	public static synchronized QiNiuMedia getInstance() {
//		if (media == null) {
//			media = new QiNiuMedia();
//			media.setAccessKey(ACCESSKEY);
//			media.setSecretKey(SECRETKEY);
//			media.setBucketName(BUCKETNAME);
//			media.setDomain(DOMAIN);
//		}
//		return media;
//	}
//}
