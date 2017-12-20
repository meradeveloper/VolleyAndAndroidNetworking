package volleyandandroid.com.volleyandandroidinone;

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by Sumit Rawat on 11/15/2017.
 */
class AndroidNetworkingApi  {


    companion object {
        lateinit var apiPrsntr: ApiPrsntr
    }


    open class AURL
    {
        open fun url(url:String):AndroidNetworkingApi.PostRequestBuilderAN {
            ApiImpl.URL=url
            return AndroidNetworkingApi.PostRequestBuilderAN()
        }

    }



    open class SentApiAN {

        fun sent()
        {
            AndroidNetworking.post(ApiImpl.URL)
                    .addJSONObjectBody(ApiImpl.JSONOBJECTBODY)
                    .setContentType("application/json; charset=utf-8") // custom ContentType
                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsJSONArray(object : JSONArrayRequestListener {
                        override fun onResponse(response: JSONArray) {
                            if(response.length()>0)
                                AndroidNetworkingApi.apiPrsntr.onSuccesResponse(response.toString(),ApiImpl.URL)
                            else
                                AndroidNetworkingApi.apiPrsntr.onFailedResponse("")
                        }
                        override fun onError(error: ANError) {
                            AndroidNetworkingApi.apiPrsntr.onFailedResponse(error.toString())
                        }
                    })

        }
    }

    open class PostRequestBuilderAN
    {
        open fun addJSONObjectBody(jsonObject: JSONObject?):AndroidNetworkingApi.SentApiAN {
            ApiImpl.JSONOBJECTBODY = jsonObject
            return AndroidNetworkingApi.SentApiAN()
        }

    }

}