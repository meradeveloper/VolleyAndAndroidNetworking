package volleyandandroid.com.volleyandandroidinone;

/**
 * Created by Localadmin on 11/15/2017.
 */
interface ApiPrsntr {
    fun onSuccesResponse(ResponseInString:String,url:String)
    fun onFailedResponse(error: String)
}