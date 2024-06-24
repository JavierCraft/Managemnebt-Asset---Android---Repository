package id.ac.polman.astra.nim0320220038.user.api;

public class ApiUtils {
    public static final String API_URL_1 = "http://192.168.1.100:8080/users/";
    public static final String API_URL_2 = "http://10.0.2.2:8080/alats/";

    private ApiUtils(){}

    public static UserService getUserService(){
        return RetrofitClient.getClient(API_URL_1).create(UserService.class);
    }

    public static AlatService getAlatService(){
        return RetrofitClient.getClient(API_URL_1).create(AlatService.class);
    }
}

