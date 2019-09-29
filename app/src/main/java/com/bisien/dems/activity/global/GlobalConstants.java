package com.bisien.dems.activity.global;

public class GlobalConstants {
//    我自己的IP是： 192.168.1.145
//        public static String URL_FIRST = "http://" + ip + ":8080/gledeye_lyj/";
//    public static String ip = "192.168.1.150";
//    public static String URL_FIRST = "http://" + ip + ":8080/gledeye_lyj/";

//    public static String ip = "192.168.1.145";
    public static String ip = "";
    public static String URL_FIRST = "http://" + ip + ":8080/gledeye/";
    public static String protocol = "http://";
    public static String prot = ":8080/gledeye/";
    public static String getUrlFirst(){
        return protocol + ip + prot;
    }
//    http://192.168.1.145:8080/gledeye/rest/equipment/get_list

}
