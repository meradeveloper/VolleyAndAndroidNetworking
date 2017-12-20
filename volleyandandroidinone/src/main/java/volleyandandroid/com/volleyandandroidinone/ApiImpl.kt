package learnkotlin.com.learnkotlin.Common.WebApiModule

import android.content.Context
import org.json.JSONObject


/**
 * Created by Localadmin on 11/15/2017.
 */
  class ApiImpl (context:Context) {


    init {
        //VolleyApi.apiPrsntr=apiPrsntr
        VolleyApi.context=context
    }

    companion object {
        var URL:String=""
        var JSONOBJECTBODY:JSONObject?=null

    }

    fun addCallback(impl: ApiPrsntr):CallingMethods
    {
        VolleyApi.apiPrsntr=impl
        return CallingMethods()
    }

    open class CallingMethods
    { fun callWithAndroidNetworking()= AndroidNetworkingApi.AURL()
      fun callWithVolley() = VolleyApi.VURL()
    }


}