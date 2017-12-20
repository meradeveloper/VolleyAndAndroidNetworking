package learnkotlin.com.learnkotlin.Common.WebApiModule


import org.json.JSONArray
import org.json.JSONObject
import android.content.Context
import android.util.Log
import com.android.volley.DefaultRetryPolicy
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONException


/**
 * Created by Sumit Rawat on 11/15/2017.
 */
class VolleyApi {

    companion object {
        lateinit var apiPrsntr:ApiPrsntr
        lateinit var context:Context
    }


    open class VURL
    {
        open fun url(url:String):VolleyApi.PostRequestBuilderVY {
            ApiImpl.URL=url
            return VolleyApi.PostRequestBuilderVY()
        }

    }


    open class SentApiVY {

        fun sentJsonArrayRequest()
        {
            Log.e("Login",ApiImpl.URL)
            val queue = Volley.newRequestQueue(context)

            val searchMsg = JsonArrayRequest( ApiImpl.URL, Response.Listener<JSONArray>{response -> try {
                if(response.length()>0)
                    apiPrsntr.onSuccesResponse(response.toString(),ApiImpl.URL)
                else
                    apiPrsntr.onFailedResponse(ApiImpl.URL)
            } catch (e: JSONException) {
                apiPrsntr.onFailedResponse(e.toString())
            }  }
                    ,Response.ErrorListener {error ->  apiPrsntr.onFailedResponse(error.toString()) })

            searchMsg.retryPolicy = DefaultRetryPolicy(
                    50000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
            queue.add(searchMsg)

        }

        fun sentJsonObjectRequest()
        {
            val queue = Volley.newRequestQueue(context)

            val searchMsg = JsonObjectRequest( ApiImpl.URL,ApiImpl.JSONOBJECTBODY, Response.Listener<JSONObject>{response ->
                try {
                    if(response.length()>0)
                        apiPrsntr.onSuccesResponse(response.toString(),ApiImpl.URL)
                    else
                        apiPrsntr.onFailedResponse("")
                } catch (e: JSONException) {
                    apiPrsntr.onFailedResponse(e.toString())
                }
            },Response.ErrorListener {error ->  apiPrsntr.onFailedResponse(error.toString())})

            searchMsg.retryPolicy = DefaultRetryPolicy(
                    50000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
            queue.add(searchMsg)

        }
    }


    open class PostRequestBuilderVY
    {
        open fun addJSONObjectBody(jsonObject: JSONObject?):VolleyApi.SentApiVY {
            ApiImpl.JSONOBJECTBODY = jsonObject
            return VolleyApi.SentApiVY()
        }

    }
}